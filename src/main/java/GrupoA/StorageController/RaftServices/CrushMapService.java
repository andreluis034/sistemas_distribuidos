package GrupoA.StorageController.RaftServices;

import org.jgroups.protocols.raft.StateMachine;

import java.io.DataInput;
import java.io.DataOutput;

public class CrushMapService implements StateMachine {
    @Override
    public byte[] apply(byte[] bytes, int i, int i1) throws Exception {
        return new byte[0];
    }

    @Override
    public void readContentFrom(DataInput dataInput) throws Exception {

    }

    @Override
    public void writeContentTo(DataOutput dataOutput) throws Exception {

    }
}
