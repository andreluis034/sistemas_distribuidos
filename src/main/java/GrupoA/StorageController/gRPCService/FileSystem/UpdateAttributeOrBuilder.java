// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FileSystem.proto

package GrupoA.StorageController.gRPCService.FileSystem;

public interface UpdateAttributeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.GrupoA.StorageController.gRPCService.FileSystem.ProtoAttributeUpdateRequestType Type = 1;</code>
   */
  int getTypeValue();
  /**
   * <code>.GrupoA.StorageController.gRPCService.FileSystem.ProtoAttributeUpdateRequestType Type = 1;</code>
   */
  GrupoA.StorageController.gRPCService.FileSystem.ProtoAttributeUpdateRequestType getType();

  /**
   * <code>int64 Value = 2;</code>
   */
  long getValue();

  /**
   * <code>string path = 3;</code>
   */
  java.lang.String getPath();
  /**
   * <code>string path = 3;</code>
   */
  com.google.protobuf.ByteString
      getPathBytes();
}
