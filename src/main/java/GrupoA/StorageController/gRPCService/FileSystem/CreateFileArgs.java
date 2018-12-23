// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FileSystem.proto

package GrupoA.StorageController.gRPCService.FileSystem;

/**
 * Protobuf type {@code GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs}
 */
public  final class CreateFileArgs extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs)
    CreateFileArgsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CreateFileArgs.newBuilder() to construct.
  private CreateFileArgs(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CreateFileArgs() {
    path_ = "";
    mode_ = 0L;
    uid_ = 0L;
    gid_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CreateFileArgs(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            path_ = s;
            break;
          }
          case 16: {

            mode_ = input.readInt64();
            break;
          }
          case 24: {

            uid_ = input.readInt64();
            break;
          }
          case 32: {

            gid_ = input.readInt64();
            break;
          }
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
    return GrupoA.StorageController.gRPCService.FileSystem.FileSystemProto.internal_static_GrupoA_StorageController_gRPCService_FileSystem_CreateFileArgs_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return GrupoA.StorageController.gRPCService.FileSystem.FileSystemProto.internal_static_GrupoA_StorageController_gRPCService_FileSystem_CreateFileArgs_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs.class, GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs.Builder.class);
  }

  public static final int PATH_FIELD_NUMBER = 1;
  private volatile java.lang.Object path_;
  /**
   * <code>string path = 1;</code>
   */
  public java.lang.String getPath() {
    java.lang.Object ref = path_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      path_ = s;
      return s;
    }
  }
  /**
   * <code>string path = 1;</code>
   */
  public com.google.protobuf.ByteString
      getPathBytes() {
    java.lang.Object ref = path_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      path_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MODE_FIELD_NUMBER = 2;
  private long mode_;
  /**
   * <code>int64 mode = 2;</code>
   */
  public long getMode() {
    return mode_;
  }

  public static final int UID_FIELD_NUMBER = 3;
  private long uid_;
  /**
   * <code>int64 uid = 3;</code>
   */
  public long getUid() {
    return uid_;
  }

  public static final int GID_FIELD_NUMBER = 4;
  private long gid_;
  /**
   * <code>int64 gid = 4;</code>
   */
  public long getGid() {
    return gid_;
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
    if (!getPathBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, path_);
    }
    if (mode_ != 0L) {
      output.writeInt64(2, mode_);
    }
    if (uid_ != 0L) {
      output.writeInt64(3, uid_);
    }
    if (gid_ != 0L) {
      output.writeInt64(4, gid_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getPathBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, path_);
    }
    if (mode_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, mode_);
    }
    if (uid_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, uid_);
    }
    if (gid_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, gid_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs)) {
      return super.equals(obj);
    }
    GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs other = (GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs) obj;

    boolean result = true;
    result = result && getPath()
        .equals(other.getPath());
    result = result && (getMode()
        == other.getMode());
    result = result && (getUid()
        == other.getUid());
    result = result && (getGid()
        == other.getGid());
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
    hash = (37 * hash) + PATH_FIELD_NUMBER;
    hash = (53 * hash) + getPath().hashCode();
    hash = (37 * hash) + MODE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getMode());
    hash = (37 * hash) + UID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUid());
    hash = (37 * hash) + GID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getGid());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parseFrom(
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
  public static Builder newBuilder(GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs prototype) {
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
   * Protobuf type {@code GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs)
      GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return GrupoA.StorageController.gRPCService.FileSystem.FileSystemProto.internal_static_GrupoA_StorageController_gRPCService_FileSystem_CreateFileArgs_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return GrupoA.StorageController.gRPCService.FileSystem.FileSystemProto.internal_static_GrupoA_StorageController_gRPCService_FileSystem_CreateFileArgs_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs.class, GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs.Builder.class);
    }

    // Construct using GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs.newBuilder()
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
      path_ = "";

      mode_ = 0L;

      uid_ = 0L;

      gid_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return GrupoA.StorageController.gRPCService.FileSystem.FileSystemProto.internal_static_GrupoA_StorageController_gRPCService_FileSystem_CreateFileArgs_descriptor;
    }

    @java.lang.Override
    public GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs getDefaultInstanceForType() {
      return GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs.getDefaultInstance();
    }

    @java.lang.Override
    public GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs build() {
      GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs buildPartial() {
      GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs result = new GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs(this);
      result.path_ = path_;
      result.mode_ = mode_;
      result.uid_ = uid_;
      result.gid_ = gid_;
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
      if (other instanceof GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs) {
        return mergeFrom((GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs other) {
      if (other == GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs.getDefaultInstance()) return this;
      if (!other.getPath().isEmpty()) {
        path_ = other.path_;
        onChanged();
      }
      if (other.getMode() != 0L) {
        setMode(other.getMode());
      }
      if (other.getUid() != 0L) {
        setUid(other.getUid());
      }
      if (other.getGid() != 0L) {
        setGid(other.getGid());
      }
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
      GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object path_ = "";
    /**
     * <code>string path = 1;</code>
     */
    public java.lang.String getPath() {
      java.lang.Object ref = path_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        path_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string path = 1;</code>
     */
    public com.google.protobuf.ByteString
        getPathBytes() {
      java.lang.Object ref = path_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        path_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string path = 1;</code>
     */
    public Builder setPath(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      path_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string path = 1;</code>
     */
    public Builder clearPath() {
      
      path_ = getDefaultInstance().getPath();
      onChanged();
      return this;
    }
    /**
     * <code>string path = 1;</code>
     */
    public Builder setPathBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      path_ = value;
      onChanged();
      return this;
    }

    private long mode_ ;
    /**
     * <code>int64 mode = 2;</code>
     */
    public long getMode() {
      return mode_;
    }
    /**
     * <code>int64 mode = 2;</code>
     */
    public Builder setMode(long value) {
      
      mode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 mode = 2;</code>
     */
    public Builder clearMode() {
      
      mode_ = 0L;
      onChanged();
      return this;
    }

    private long uid_ ;
    /**
     * <code>int64 uid = 3;</code>
     */
    public long getUid() {
      return uid_;
    }
    /**
     * <code>int64 uid = 3;</code>
     */
    public Builder setUid(long value) {
      
      uid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 uid = 3;</code>
     */
    public Builder clearUid() {
      
      uid_ = 0L;
      onChanged();
      return this;
    }

    private long gid_ ;
    /**
     * <code>int64 gid = 4;</code>
     */
    public long getGid() {
      return gid_;
    }
    /**
     * <code>int64 gid = 4;</code>
     */
    public Builder setGid(long value) {
      
      gid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 gid = 4;</code>
     */
    public Builder clearGid() {
      
      gid_ = 0L;
      onChanged();
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


    // @@protoc_insertion_point(builder_scope:GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs)
  }

  // @@protoc_insertion_point(class_scope:GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs)
  private static final GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs();
  }

  public static GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CreateFileArgs>
      PARSER = new com.google.protobuf.AbstractParser<CreateFileArgs>() {
    @java.lang.Override
    public CreateFileArgs parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CreateFileArgs(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CreateFileArgs> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CreateFileArgs> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public GrupoA.StorageController.gRPCService.FileSystem.CreateFileArgs getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

