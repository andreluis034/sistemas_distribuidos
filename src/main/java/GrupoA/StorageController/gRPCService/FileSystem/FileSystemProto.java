// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FileSystem.proto

package GrupoA.StorageController.gRPCService.FileSystem;

public final class FileSystemProto {
  private FileSystemProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_LongArg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_StorageController_gRPCService_FileSystem_LongArg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_iNodeAttributes_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_StorageController_gRPCService_FileSystem_iNodeAttributes_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_pathOnlyArgs_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_StorageController_gRPCService_FileSystem_pathOnlyArgs_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_Metada_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_StorageController_gRPCService_FileSystem_Metada_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_ObjectData_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_StorageController_gRPCService_FileSystem_ObjectData_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_BooleanMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_StorageController_gRPCService_FileSystem_BooleanMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020FileSystem.proto\022/GrupoA.StorageContro" +
      "ller.gRPCService.FileSystem\"\030\n\007LongArg\022\r" +
      "\n\005iNode\030\001 \001(\003\"\314\001\n\017iNodeAttributes\022\014\n\004nam" +
      "e\030\001 \001(\t\022\027\n\017UserPermissions\030\002 \001(\005\022\030\n\020Grou" +
      "pPermissions\030\003 \001(\005\022\030\n\020OtherPermissions\030\004" +
      " \001(\005\022\017\n\007OwnerId\030\005 \001(\003\022\017\n\007GroupId\030\006 \001(\003\022\023" +
      "\n\013INodeNumber\030\007 \001(\003\022\031\n\021ParentINodeNumber" +
      "\030\010 \001(\003\022\014\n\004Size\030\t \001(\003\" \n\014pathOnlyArgs\022\020\n\010" +
      "filePath\030\001 \001(\t\")\n\006Metada\"\037\n\nMetadaType\022\007" +
      "\n\003DIR\020\000\022\010\n\004FILE\020\001\".\n\nObjectData\022\014\n\004hash\030" +
      "\001 \001(\003\022\022\n\nobjectData\030\002 \001(\014\" \n\016BooleanMess" +
      "age\022\016\n\006result\030\001 \001(\0102\273\004\n\nFileSystem\022\212\001\n\006r" +
      "mFile\022=.GrupoA.StorageController.gRPCSer" +
      "vice.FileSystem.pathOnlyArgs\032?.GrupoA.St" +
      "orageController.gRPCService.FileSystem.B" +
      "ooleanMessage\"\000\022\211\001\n\005rmDir\022=.GrupoA.Stora" +
      "geController.gRPCService.FileSystem.path" +
      "OnlyArgs\032?.GrupoA.StorageController.gRPC" +
      "Service.FileSystem.BooleanMessage\"\000\022\211\001\n\005" +
      "mkDir\022=.GrupoA.StorageController.gRPCSer" +
      "vice.FileSystem.pathOnlyArgs\032?.GrupoA.St" +
      "orageController.gRPCService.FileSystem.B" +
      "ooleanMessage\"\000\022\207\001\n\007getAttr\0228.GrupoA.Sto" +
      "rageController.gRPCService.FileSystem.Lo" +
      "ngArg\032@.GrupoA.StorageController.gRPCSer" +
      "vice.FileSystem.iNodeAttributes\"\000B\027B\017Fil" +
      "eSystemProtoP\001\242\002\001Ob\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_LongArg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_LongArg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_StorageController_gRPCService_FileSystem_LongArg_descriptor,
        new java.lang.String[] { "INode", });
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_iNodeAttributes_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_iNodeAttributes_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_StorageController_gRPCService_FileSystem_iNodeAttributes_descriptor,
        new java.lang.String[] { "Name", "UserPermissions", "GroupPermissions", "OtherPermissions", "OwnerId", "GroupId", "INodeNumber", "ParentINodeNumber", "Size", });
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_pathOnlyArgs_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_pathOnlyArgs_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_StorageController_gRPCService_FileSystem_pathOnlyArgs_descriptor,
        new java.lang.String[] { "FilePath", });
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_Metada_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_Metada_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_StorageController_gRPCService_FileSystem_Metada_descriptor,
        new java.lang.String[] { });
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_ObjectData_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_ObjectData_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_StorageController_gRPCService_FileSystem_ObjectData_descriptor,
        new java.lang.String[] { "Hash", "ObjectData", });
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_BooleanMessage_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_GrupoA_StorageController_gRPCService_FileSystem_BooleanMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_StorageController_gRPCService_FileSystem_BooleanMessage_descriptor,
        new java.lang.String[] { "Result", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
