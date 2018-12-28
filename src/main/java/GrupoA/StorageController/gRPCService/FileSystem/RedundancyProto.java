// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FileSystem.proto

package GrupoA.StorageController.gRPCService.FileSystem;

/**
 * Protobuf enum {@code GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto}
 */
public enum RedundancyProto
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>ForwardErrorCorrection = 0;</code>
   */
  ForwardErrorCorrection(0),
  /**
   * <code>Replication = 1;</code>
   */
  Replication(1),
  /**
   * <code>None = 2;</code>
   */
  None(2),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>ForwardErrorCorrection = 0;</code>
   */
  public static final int ForwardErrorCorrection_VALUE = 0;
  /**
   * <code>Replication = 1;</code>
   */
  public static final int Replication_VALUE = 1;
  /**
   * <code>None = 2;</code>
   */
  public static final int None_VALUE = 2;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static RedundancyProto valueOf(int value) {
    return forNumber(value);
  }

  public static RedundancyProto forNumber(int value) {
    switch (value) {
      case 0: return ForwardErrorCorrection;
      case 1: return Replication;
      case 2: return None;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<RedundancyProto>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      RedundancyProto> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<RedundancyProto>() {
          public RedundancyProto findValueByNumber(int number) {
            return RedundancyProto.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return GrupoA.StorageController.gRPCService.FileSystem.FileSystemProto.getDescriptor().getEnumTypes().get(0);
  }

  private static final RedundancyProto[] VALUES = values();

  public static RedundancyProto valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private RedundancyProto(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto)
}
