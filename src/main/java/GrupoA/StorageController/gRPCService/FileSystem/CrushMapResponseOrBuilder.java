// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FileSystem.proto

package GrupoA.StorageController.gRPCService.FileSystem;

public interface CrushMapResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 Version = 1;</code>
   */
  long getVersion();

  /**
   * <code>repeated .GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.PlacementGroupProto PGs = 2;</code>
   */
  java.util.List<GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.PlacementGroupProto> 
      getPGsList();
  /**
   * <code>repeated .GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.PlacementGroupProto PGs = 2;</code>
   */
  GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.PlacementGroupProto getPGs(int index);
  /**
   * <code>repeated .GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.PlacementGroupProto PGs = 2;</code>
   */
  int getPGsCount();
  /**
   * <code>repeated .GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.PlacementGroupProto PGs = 2;</code>
   */
  java.util.List<? extends GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.PlacementGroupProtoOrBuilder> 
      getPGsOrBuilderList();
  /**
   * <code>repeated .GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.PlacementGroupProto PGs = 2;</code>
   */
  GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.PlacementGroupProtoOrBuilder getPGsOrBuilder(
      int index);
}
