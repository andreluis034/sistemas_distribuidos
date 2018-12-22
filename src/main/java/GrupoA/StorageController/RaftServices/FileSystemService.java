package GrupoA.StorageController.RaftServices;

import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import GrupoA.StorageController.FileSystem.FSTree;
import org.jgroups.JChannel;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.Role;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.raft.RaftHandle;
import org.jgroups.util.*;

import java.io.*;
import java.nio.file.Files;
import java.rmi.ServerError;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileSystemService implements StateMachine, RAFT.RoleChange {
    protected JChannel ch;
    protected RaftHandle raft;
    protected FSTree fsTree = new FSTree();
    protected long replyTimeout = 20 * 1000; // 20 seconds

    private static FileSystemService service = null;
    public synchronized static FileSystemService getInstance() {
        return service;
    }
    public synchronized static FileSystemService getInstance(String config, String raftId) throws Exception {

        if(service == null) {
            JChannel ch = new JChannel("./raft.xml").name(raftId);
            service = new FileSystemService(ch);
            ch.connect("FSTreeCluster");
        }
        return service;
    }

    private FileSystemService(JChannel ch){
        this.setChannel(ch);
    }

    public void setChannel(JChannel ch) {
        this.ch=ch;
        this.raft=new RaftHandle(this.ch, this);
        raft.addRoleListener(this);
    }

    protected enum Command {mkDir, rmDir, mkFile, rmFile, ls, getINode}

    public boolean mkDir(String path) throws Exception {
        return (boolean)invoke(Command.mkDir, path);
    }

    public boolean rmDir(String path) throws Exception {
        return (boolean)invoke(Command.rmDir, path);
    }

    public boolean mkFile(String path, int fileSize, int blocks, long hash) throws Exception {
        return (boolean)invoke(Command.mkFile, path, fileSize, blocks, hash);
    }

    public boolean rmFile(String path) throws Exception {
        return (boolean)invoke(Command.rmFile, path);
    }

    @SuppressWarnings("unchecked")
    public List<String> ls(String path) throws Exception {
        return (List<String>)invoke(Command.ls, path);
    }

    public FSTree.Node getINode(long iNode) {

    }

    @Override
    public byte[] apply(byte[] bytes, int offset, int length) throws Exception {
        ByteArrayDataInputStream in = new ByteArrayDataInputStream(bytes, offset, length);
        Command command = Command.values()[in.readByte()];
        String path = Bits.readAsciiString(in).toString();

        // Journaling the state change
        try {
            Files.write(fsTree.journal_path, Collections.singleton("Applying " + command
                    + "on path: \"" + path + "\""));
        } catch (IOException ignored) {
            System.out.println("Applying " + command + "on path: \"" + path + "\"");
            System.err.println("Couldn't write to log file");
        }

        boolean bool_return_value;

        try {
            switch(command) {
                case mkDir:
                    bool_return_value = fsTree.mkDir(path);

                    try {
                        Files.write(fsTree.journal_path,
                                Collections.singleton("Return value of command 'mkDir' was: " + bool_return_value));
                    } catch (IOException ignored) {
                        System.out.println("Return value of command 'mkDir' was: " + bool_return_value);
                        System.err.println("Couldn't write to log file");
                    }

                    return Util.objectToByteBuffer(bool_return_value);
                case rmDir:
                    bool_return_value = fsTree.rmDir(path);

                    try {
                        Files.write(fsTree.journal_path,
                                Collections.singleton("Return value of command 'rmDir' was: " + bool_return_value));
                    } catch (IOException ignored) {
                        System.out.println("Return value of command 'rmDir' was: " + bool_return_value);
                        System.err.println("Couldn't write to log file");
                    }

                    return Util.objectToByteBuffer(bool_return_value);
                case mkFile:
                    int fileSize = Bits.readInt(in);
                    int blocks = Bits.readInt(in);
                    long hash = Bits.readLong(in);

                    bool_return_value = fsTree.mkFile(path, fileSize, blocks, hash);

                    try {
                        Files.write(fsTree.journal_path,
                                Collections.singleton("Return value of command 'mkFile' was: " + bool_return_value));
                    } catch (IOException ignored) {
                        System.out.println("Return value of command 'mkFile' was: " + bool_return_value);
                        System.err.println("Couldn't write to log file");
                    }

                    return Util.objectToByteBuffer(bool_return_value);
                case rmFile:
                    bool_return_value = fsTree.rmFile(path);

                    try {
                        Files.write(fsTree.journal_path,
                                Collections.singleton("Return value of command 'rmFile' was: " + bool_return_value));
                    } catch (IOException ignored) {
                        System.out.println("Return value of command 'rmFile' was: " + bool_return_value);
                        System.err.println("Couldn't write to log file");
                    }

                    return Util.objectToByteBuffer(bool_return_value);
                case ls:
                    List<String> return_value = fsTree.ls(path);

                    try {
                        Files.write(fsTree.journal_path,
                                Collections.singleton("Success of command 'ls' was: " + (return_value == null)));
                    } catch (IOException ignored) {
                        System.out.println("Success of command 'ls' was: " + (return_value == null));
                        System.err.println("Couldn't write to log file");
                    }

                    // Hack, just to make sure it is able to be transformed into ByteBuffer
                    // Only enters this 'if' if there was an error on the path
                    // Need to check, on the AppServer, if the return_value is a singletonList with the string "/"
                    // (Because there a node can't have "/" on its path)
                    if (return_value == null)
                        return_value = Collections.singletonList("/");

                    return Util.objectToByteBuffer(return_value);
                default:
                    try {
                        Files.write(fsTree.journal_path,
                                Collections.singleton("Command " + command + " is unknown"));
                    } catch (IOException ignored) {
                        System.out.println("Command " + command + " is unknown");
                        System.err.println("Couldn't write to log file");
                    }

                    throw new IllegalArgumentException("Command " + command + " is unknown");
            }
        } catch (Exception e) {
            try {
                Files.write(fsTree.journal_path,
                        Collections.singleton("An error occurred while trying to apply the command:\n"
                                + e.toString()));
            } catch (IOException ignored) {
                System.out.println("An exception occurred while trying to apply the command:");
                System.out.println(e.toString());
                System.err.println("Couldn't write to log file\n");
            }

            e.printStackTrace();
            throw e;
        }

    }

    protected Object invoke(Command command, int iNode) {

    }

    protected Object invoke(Command command, String path) throws Exception {
        //Size: length of string + null terminator
        ByteArrayDataOutputStream out = new ByteArrayDataOutputStream(path.length() + 1);

        System.out.println(command);
        System.out.println(path);
        try {
            out.writeByte(command.ordinal());
            Bits.writeAsciiString(new AsciiString(path), out);
        } catch(Exception ex) {
            throw new Exception("Serialization failure (CMD = " + command + ", PATH = " + path + ")");
        }

        byte[] buf = out.buffer();
        byte[] rsp = raft.set(buf, 0, out.position(), replyTimeout, TimeUnit.MILLISECONDS);

        return Util.objectFromByteBuffer(rsp);
    }

    protected Object invoke(Command command, String path, int fileSize, int blocks, long hash) throws Exception {
        //Size: length of string + null terminator, int size = 4, long size = 8
        ByteArrayDataOutputStream out = new ByteArrayDataOutputStream(path.length() + 1 + 4 + 4 + 8);

        try {
            out.writeByte(command.ordinal());
            Bits.writeAsciiString(new AsciiString(path), out);
            Bits.writeInt(fileSize, out);
            Bits.writeInt(blocks, out);
            Bits.writeLong(hash, out);
        } catch(Exception ex) {
            throw new Exception("Serialization failure (CMD = " + command + ", PATH = " + path + ")");
        }

        byte[] buf = out.buffer();
        byte[] rsp = raft.set(buf, 0, out.position(), replyTimeout, TimeUnit.MILLISECONDS);

        return Util.objectFromByteBuffer(rsp);
    }

    @Override
    public void readContentFrom(DataInput dataInput) throws Exception {
        System.out.println("readContentFrom");
        int arrayLength = dataInput.readInt();
        byte[] byteArray = new byte[arrayLength];

        dataInput.readFully(byteArray);
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        ObjectInput in = new ObjectInputStream(bis);

        fsTree = (FSTree)in.readObject();
    }

    @Override
    public void writeContentTo(DataOutput dataOutput) throws Exception {
        System.out.println("writeContentTo");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(fsTree);
        out.close();

        byte[] byteArray = bos.toByteArray();

        dataOutput.write(byteArray.length);
        dataOutput.write(byteArray);
    }

    //TODO singleton service
    @Override
    public void roleChanged(Role role) {
        System.out.println("[FileSystemService]-> Changed role to " + role);
    }
}
