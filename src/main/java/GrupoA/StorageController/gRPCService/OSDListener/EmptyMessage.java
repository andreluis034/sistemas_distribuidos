// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: OSDListener.proto

package GrupoA.StorageController.gRPCService.OSDListener;

/**
 * Protobuf type {@code GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage}
 */
public  final class EmptyMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage)
    EmptyMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use EmptyMessage.newBuilder() to construct.
  private EmptyMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EmptyMessage() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private EmptyMessage(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return GrupoA.StorageController.gRPCService.OSDListener.OSDListenerProto.internal_static_GrupoA_StorageController_gRPCService_OSDListener_EmptyMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return GrupoA.StorageController.gRPCService.OSDListener.OSDListenerProto.internal_static_GrupoA_StorageController_gRPCService_OSDListener_EmptyMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage.class, GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage.Builder.class);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage)) {
      return super.equals(obj);
    }
    GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage other = (GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage) obj;

    boolean result = true;
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage)
      GrupoA.StorageController.gRPCService.OSDListener.EmptyMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return GrupoA.StorageController.gRPCService.OSDListener.OSDListenerProto.internal_static_GrupoA_StorageController_gRPCService_OSDListener_EmptyMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return GrupoA.StorageController.gRPCService.OSDListener.OSDListenerProto.internal_static_GrupoA_StorageController_gRPCService_OSDListener_EmptyMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage.class, GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage.Builder.class);
    }

    // Construct using GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return GrupoA.StorageController.gRPCService.OSDListener.OSDListenerProto.internal_static_GrupoA_StorageController_gRPCService_OSDListener_EmptyMessage_descriptor;
    }

    @java.lang.Override
    public GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage getDefaultInstanceForType() {
      return GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage.getDefaultInstance();
    }

    @java.lang.Override
    public GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage build() {
      GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage buildPartial() {
      GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage result = new GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage(this);
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage) {
        return mergeFrom((GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage other) {
      if (other == GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage.getDefaultInstance()) return this;
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage)
  }

  // @@protoc_insertion_point(class_scope:GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage)
  private static final GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage();
  }

  public static GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EmptyMessage>
      PARSER = new com.google.protobuf.AbstractParser<EmptyMessage>() {
    @java.lang.Override
    public EmptyMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new EmptyMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EmptyMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EmptyMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

