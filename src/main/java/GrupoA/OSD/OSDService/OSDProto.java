// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: OSD.proto

package GrupoA.OSD.OSDService;

public final class OSDProto {
  private OSDProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_OSD_OSDService_MiniObject_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_OSD_OSDService_MiniObject_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_OSD_OSDService_GetObjectArgs_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_OSD_OSDService_GetObjectArgs_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_OSD_OSDService_ObjectData_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_OSD_OSDService_ObjectData_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_OSD_OSDService_BooleanMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_OSD_OSDService_BooleanMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GrupoA_OSD_OSDService_EmptyMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GrupoA_OSD_OSDService_EmptyMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\tOSD.proto\022\025GrupoA.OSD.OSDService\"i\n\nMi" +
      "niObject\022\014\n\004hash\030\001 \001(\003\022\022\n\nobjectData\030\002 \001" +
      "(\014\022\023\n\013startOffset\030\003 \001(\005\022\021\n\tendOffset\030\004 \001" +
      "(\005\022\021\n\tduplicate\030\005 \001(\010\"\035\n\rGetObjectArgs\022\014" +
      "\n\004hash\030\001 \001(\003\".\n\nObjectData\022\014\n\004hash\030\001 \001(\003" +
      "\022\022\n\nobjectData\030\002 \001(\014\" \n\016BooleanMessage\022\016" +
      "\n\006result\030\001 \001(\010\"\016\n\014EmptyMessage2\306\003\n\003OSD\022U" +
      "\n\tputObject\022!.GrupoA.OSD.OSDService.Obje" +
      "ctData\032#.GrupoA.OSD.OSDService.EmptyMess" +
      "age\"\000\022[\n\017writeMiniObject\022!.GrupoA.OSD.OS" +
      "DService.MiniObject\032#.GrupoA.OSD.OSDServ" +
      "ice.EmptyMessage\"\000\022]\n\016readMiniObject\022$.G" +
      "rupoA.OSD.OSDService.GetObjectArgs\032#.Gru" +
      "poA.OSD.OSDService.EmptyMessage\"\000\022V\n\tget" +
      "Object\022$.GrupoA.OSD.OSDService.GetObject" +
      "Args\032!.GrupoA.OSD.OSDService.ObjectData\"" +
      "\000\022T\n\004ping\022#.GrupoA.OSD.OSDService.EmptyM" +
      "essage\032%.GrupoA.OSD.OSDService.BooleanMe" +
      "ssage\"\000B\020B\010OSDProtoP\001\242\002\001Ob\006proto3"
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
    internal_static_GrupoA_OSD_OSDService_MiniObject_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_GrupoA_OSD_OSDService_MiniObject_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_OSD_OSDService_MiniObject_descriptor,
        new java.lang.String[] { "Hash", "ObjectData", "StartOffset", "EndOffset", "Duplicate", });
    internal_static_GrupoA_OSD_OSDService_GetObjectArgs_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_GrupoA_OSD_OSDService_GetObjectArgs_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_OSD_OSDService_GetObjectArgs_descriptor,
        new java.lang.String[] { "Hash", });
    internal_static_GrupoA_OSD_OSDService_ObjectData_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_GrupoA_OSD_OSDService_ObjectData_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_OSD_OSDService_ObjectData_descriptor,
        new java.lang.String[] { "Hash", "ObjectData", });
    internal_static_GrupoA_OSD_OSDService_BooleanMessage_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_GrupoA_OSD_OSDService_BooleanMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_OSD_OSDService_BooleanMessage_descriptor,
        new java.lang.String[] { "Result", });
    internal_static_GrupoA_OSD_OSDService_EmptyMessage_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_GrupoA_OSD_OSDService_EmptyMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GrupoA_OSD_OSDService_EmptyMessage_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
