// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FileSystem.proto

package GrupoA.StorageController.gRPCService.FileSystem;

public interface LockArgsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:GrupoA.StorageController.gRPCService.FileSystem.LockArgs)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string path = 1;</code>
   */
  java.lang.String getPath();
  /**
   * <code>string path = 1;</code>
   */
  com.google.protobuf.ByteString
      getPathBytes();

  /**
   * <code>int64 id = 2;</code>
   */
  long getId();

  /**
   * <code>int64 crushMapVersion = 3;</code>
   */
  long getCrushMapVersion();

  /**
   * <code>int64 currentTime = 4;</code>
   */
  long getCurrentTime();
}
