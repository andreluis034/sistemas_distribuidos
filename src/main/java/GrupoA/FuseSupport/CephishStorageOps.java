package GrupoA.FuseSupport;

import GrupoA.AppServer.Models.INode;
import GrupoA.AppServer.Models.NodeAttributes;
import fuse.Errno;
import fuse.FileInfo;
import fuse.Stat;
import jlowfuse.FuseReq;
import jlowfuse.LowlevelOps;
import jlowfuse.Reply;
import jlowfuse.classic.ClassicLowlevelOps;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.nio.ByteBuffer;

public class CephishStorageOps extends ClassicLowlevelOps implements LowlevelOps {
    private RestClient restClient;
    @Override
    public void init() {
        restClient = new RestClient(FuseSupport.Address);
    }

    @Override
    public void destroy() {
        System.out.println("Destroy");
    }

    @Override
    public void lookup(FuseReq fuseReq, long l, String s) {
        System.out.println("lookup");
        super.lookup(fuseReq, l, s);
    }

    @Override
    public void forget(FuseReq fuseReq, long l, long l1) {
        System.out.println("forget");
        super.forget(fuseReq,l ,l1);
    }

    @Override
    public void getattr(FuseReq fuseReq, long inode, FileInfo fileInfo) {
        System.out.println("getattr");
        System.out.println();
        NodeAttributes attr =  this.restClient.getAttribute(inode);
        if (attr == null) {
            Reply.err(fuseReq, Errno.ENOENT);
            return;
        }
        Reply.attr(fuseReq, attr.toFuseStat(), 0.0);
        //Reply.attr(fuseReq,)
       // Reply.attr(fuseReq, );
        //super.getattr(fuseReq, inode, fileInfo);
    }

    @Override
    public void setattr(FuseReq fuseReq, long l, Stat stat, int i, FileInfo fileInfo) {
        System.out.println("setattr");
        super.setattr(fuseReq, l, stat, i, fileInfo);
    }

    @Override
    public void readlink(FuseReq fuseReq, long l) {
        System.out.println("readlink");
        super.readlink(fuseReq, l);
    }

    @Override
    public void mknod(FuseReq fuseReq, long l, String s, short i, short i1) {
        System.out.println("mknod");
        super.mknod(fuseReq, l, s, i, i1);
    }

    @Override
    public void mkdir(FuseReq fuseReq, long parentINode, String name, short mode) {
        System.out.println("mkdir");
        super.mkdir(fuseReq, parentINode, name, mode);
    }

    @Override
    public void unlink(FuseReq fuseReq, long l, String s) {
        System.out.println("unlink");
        super.unlink(fuseReq, l, s);
    }

    @Override
    public void rmdir(FuseReq fuseReq, long l, String s) {
        System.out.println("rmdir");
        super.rmdir(fuseReq, l, s);
    }

    @Override
    public void symlink(FuseReq fuseReq, String s, long l, String s1) {
        System.out.println("symlink");
        super.symlink(fuseReq,s,l,s1);
    }

    @Override
    public void rename(FuseReq fuseReq, long l, String s, long l1, String s1) {
        System.out.println("rename");
        super.rename(fuseReq, l, s, l1, s1);
    }

    @Override
    public void link(FuseReq fuseReq, long l, long l1, String s) {
        System.out.println("link");
        super.link(fuseReq, l, l1, s);
    }

    @Override
    public void open(FuseReq fuseReq, long l, FileInfo fileInfo) {
        System.out.println("open");
        super.open(fuseReq, l, fileInfo);
    }

    @Override
    public void read(FuseReq fuseReq, long l, long l1, long l2, FileInfo fileInfo) {
        System.out.println("read");
        super.read(fuseReq, l, l1, l2, fileInfo);
    }

    @Override
    public void write(FuseReq fuseReq, long l, ByteBuffer byteBuffer, long l1, FileInfo fileInfo) {
        System.out.println("write");
        super.write(fuseReq, l, byteBuffer, l1, fileInfo);
    }

    @Override
    public void flush(FuseReq fuseReq, long l, FileInfo fileInfo) {
        System.out.println("flush");
        super.flush(fuseReq, l, fileInfo);
    }

    @Override
    public void release(FuseReq fuseReq, long l, FileInfo fileInfo) {
        System.out.println("release");
        super.release(fuseReq, l, fileInfo);
    }

    @Override
    public void fsync(FuseReq fuseReq, long l, int i, FileInfo fileInfo) {
        System.out.println("fsync");
        super.fsync(fuseReq, l, i, fileInfo);
    }

    @Override
    public void opendir(FuseReq fuseReq, long l, FileInfo fileInfo) {
        System.out.println("opendir");
        super.opendir(fuseReq, l, fileInfo);
    }

    @Override
    public void readdir(FuseReq fuseReq, long l, long l1, long l2, FileInfo fileInfo) {
        System.out.println("readdir");
        super.readdir(fuseReq, l, l1, l2, fileInfo);
    }

    @Override
    public void releasedir(FuseReq fuseReq, long l, FileInfo fileInfo) {
        System.out.println("releasedir");
        super.releasedir(fuseReq, l, fileInfo);
    }

    @Override
    public void fsyncdir(FuseReq fuseReq, long l, int i, FileInfo fileInfo) {
        System.out.println("fsyncdir");
        super.fsyncdir(fuseReq, l, i, fileInfo);
    }

    @Override
    public void statfs(FuseReq fuseReq, long l) {
        System.out.println("statfs");
        super.statfs(fuseReq, l);
    }

    @Override
    public void setxattr(FuseReq fuseReq, long l, String s, ByteBuffer byteBuffer, int i) {
        System.out.println("setxattr");
        super.setxattr(fuseReq, l, s, byteBuffer, i);
    }

    @Override
    public void getxattr(FuseReq fuseReq, long l, String s, int i) {
        System.out.println(s);
        System.out.printf("getxattr(%l, %s, %i)\n", l, s ,i);
        super.getxattr(fuseReq, l, s, i);
    }

    @Override
    public void listxattr(FuseReq fuseReq, long l, int i) {
        System.out.println("listxattr");
        super.listxattr(fuseReq, l,i);
    }

    @Override
    public void removexattr(FuseReq fuseReq, long l, String s) {
        System.out.println("removexattr");
        super.removexattr(fuseReq, l, s);
    }

    @Override
    public void access(FuseReq fuseReq, long l, int i) {
        System.out.println("access");
        super.access(fuseReq, l, i);
    }

    @Override
    public void create(FuseReq fuseReq, long l, String s, short i, FileInfo fileInfo) {
        System.out.println("create");
        super.create(fuseReq, l, s, i, fileInfo);
    }

    @Override
    public void bmap(FuseReq fuseReq, long l, int i, long l1) {
        System.out.println("bmap");
        super.bmap(fuseReq, l, i, l1);
    }
}
