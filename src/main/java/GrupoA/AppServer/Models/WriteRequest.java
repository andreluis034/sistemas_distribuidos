package GrupoA.AppServer.Models;


public class WriteRequest implements Comparable<WriteRequest> {
    public String path;
    public byte[] data;
    public long offset;
    public long pid;

    @Override
    public int compareTo(WriteRequest writeRequest) {
        if(this.offset == writeRequest.offset)
            return (int) ((this.offset+this.data.length) - (writeRequest.offset + writeRequest.data.length));
        return (int) (this.offset - writeRequest.offset);
    }
}
