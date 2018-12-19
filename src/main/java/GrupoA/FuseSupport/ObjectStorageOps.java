package GrupoA.FuseSupport;

import fuse.FileInfo;
import fuse.Stat;
import jlowfuse.FuseReq;
import jlowfuse.LowlevelOps;
import jlowfuse.classic.ClassicLowlevelOps;

import java.nio.ByteBuffer;

public class ObjectStorageOps implements LowlevelOps {
    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void lookup(FuseReq fuseReq, long l, String s) {

    }

    @Override
    public void forget(FuseReq fuseReq, long l, long l1) {

    }

    @Override
    public void getattr(FuseReq fuseReq, long l, FileInfo fileInfo) {

    }

    @Override
    public void setattr(FuseReq fuseReq, long l, Stat stat, int i, FileInfo fileInfo) {

    }

    @Override
    public void readlink(FuseReq fuseReq, long l) {

    }

    @Override
    public void mknod(FuseReq fuseReq, long l, String s, short i, short i1) {

    }

    @Override
    public void mkdir(FuseReq fuseReq, long l, String s, short i) {

    }

    @Override
    public void unlink(FuseReq fuseReq, long l, String s) {

    }

    @Override
    public void rmdir(FuseReq fuseReq, long l, String s) {

    }

    @Override
    public void symlink(FuseReq fuseReq, String s, long l, String s1) {

    }

    @Override
    public void rename(FuseReq fuseReq, long l, String s, long l1, String s1) {

    }

    @Override
    public void link(FuseReq fuseReq, long l, long l1, String s) {

    }

    @Override
    public void open(FuseReq fuseReq, long l, FileInfo fileInfo) {

    }

    @Override
    public void read(FuseReq fuseReq, long l, long l1, long l2, FileInfo fileInfo) {

    }

    @Override
    public void write(FuseReq fuseReq, long l, ByteBuffer byteBuffer, long l1, FileInfo fileInfo) {

    }

    @Override
    public void flush(FuseReq fuseReq, long l, FileInfo fileInfo) {

    }

    @Override
    public void release(FuseReq fuseReq, long l, FileInfo fileInfo) {

    }

    @Override
    public void fsync(FuseReq fuseReq, long l, int i, FileInfo fileInfo) {

    }

    @Override
    public void opendir(FuseReq fuseReq, long l, FileInfo fileInfo) {

    }

    @Override
    public void readdir(FuseReq fuseReq, long l, long l1, long l2, FileInfo fileInfo) {

    }

    @Override
    public void releasedir(FuseReq fuseReq, long l, FileInfo fileInfo) {

    }

    @Override
    public void fsyncdir(FuseReq fuseReq, long l, int i, FileInfo fileInfo) {

    }

    @Override
    public void statfs(FuseReq fuseReq, long l) {

    }

    @Override
    public void setxattr(FuseReq fuseReq, long l, String s, ByteBuffer byteBuffer, int i) {

    }

    @Override
    public void getxattr(FuseReq fuseReq, long l, String s, int i) {

    }

    @Override
    public void listxattr(FuseReq fuseReq, long l, int i) {

    }

    @Override
    public void removexattr(FuseReq fuseReq, long l, String s) {

    }

    @Override
    public void access(FuseReq fuseReq, long l, int i) {

    }

    @Override
    public void create(FuseReq fuseReq, long l, String s, short i, FileInfo fileInfo) {

    }

    @Override
    public void bmap(FuseReq fuseReq, long l, int i, long l1) {

    }
}
