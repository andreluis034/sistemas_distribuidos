// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FileSystem.proto

package GrupoA.StorageController.gRPCService.FileSystem;

public interface iNodeAttributesOrBuilder extends
    // @@protoc_insertion_point(interface_extends:GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>.GrupoA.StorageController.gRPCService.FileSystem.FileType FileType = 10;</code>
   */
  int getFileTypeValue();
  /**
   * <code>.GrupoA.StorageController.gRPCService.FileSystem.FileType FileType = 10;</code>
   */
  GrupoA.StorageController.gRPCService.FileSystem.FileType getFileType();

  /**
   * <code>int32 UserPermissions = 2;</code>
   */
  int getUserPermissions();

  /**
   * <code>int32 GroupPermissions = 3;</code>
   */
  int getGroupPermissions();

  /**
   * <code>int32 OtherPermissions = 4;</code>
   */
  int getOtherPermissions();

  /**
   * <code>int64 OwnerId = 5;</code>
   */
  long getOwnerId();

  /**
   * <code>int64 GroupId = 6;</code>
   */
  long getGroupId();

  /**
   * <code>int64 INodeNumber = 7;</code>
   */
  long getINodeNumber();

  /**
   * <code>int64 ParentINodeNumber = 8;</code>
   */
  long getParentINodeNumber();

  /**
   * <code>int64 Size = 9;</code>
   */
  long getSize();

  /**
   * <code>int64 ctime = 11;</code>
   */
  long getCtime();

  /**
   * <code>int64 atime = 12;</code>
   */
  long getAtime();

  /**
   * <code>int64 mtime = 13;</code>
   */
  long getMtime();

  /**
   * <code>.GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto Redundancy = 14;</code>
   */
  int getRedundancyValue();
  /**
   * <code>.GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto Redundancy = 14;</code>
   */
  GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto getRedundancy();

  /**
   * <code>int64 crushMapVersion = 15;</code>
   */
  long getCrushMapVersion();
}
