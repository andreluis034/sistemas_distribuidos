// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: OSD.proto

package GrupoA.OSD.OSDService;

/**
 * Protobuf type {@code GrupoA.OSD.OSDService.GetObjectArgs}
 */
public  final class GetObjectArgs extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:GrupoA.OSD.OSDService.GetObjectArgs)
    GetObjectArgsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetObjectArgs.newBuilder() to construct.
  private GetObjectArgs(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetObjectArgs() {
    hash_ = 0L;
    relativeOffset_ = 0L;
    size_ = 0L;
    hasDuplicate_ = false;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetObjectArgs(
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
          case 8: {

            hash_ = input.readInt64();
            break;
          }
          case 16: {

            relativeOffset_ = input.readInt64();
            break;
          }
          case 24: {

            size_ = input.readInt64();
            break;
          }
          case 32: {

            hasDuplicate_ = input.readBool();
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
    return GrupoA.OSD.OSDService.OSDProto.internal_static_GrupoA_OSD_OSDService_GetObjectArgs_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return GrupoA.OSD.OSDService.OSDProto.internal_static_GrupoA_OSD_OSDService_GetObjectArgs_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            GrupoA.OSD.OSDService.GetObjectArgs.class, GrupoA.OSD.OSDService.GetObjectArgs.Builder.class);
  }

  public static final int HASH_FIELD_NUMBER = 1;
  private long hash_;
  /**
   * <code>int64 hash = 1;</code>
   */
  public long getHash() {
    return hash_;
  }

  public static final int RELATIVEOFFSET_FIELD_NUMBER = 2;
  private long relativeOffset_;
  /**
   * <code>int64 relativeOffset = 2;</code>
   */
  public long getRelativeOffset() {
    return relativeOffset_;
  }

  public static final int SIZE_FIELD_NUMBER = 3;
  private long size_;
  /**
   * <code>int64 size = 3;</code>
   */
  public long getSize() {
    return size_;
  }

  public static final int HASDUPLICATE_FIELD_NUMBER = 4;
  private boolean hasDuplicate_;
  /**
   * <code>bool hasDuplicate = 4;</code>
   */
  public boolean getHasDuplicate() {
    return hasDuplicate_;
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
    if (hash_ != 0L) {
      output.writeInt64(1, hash_);
    }
    if (relativeOffset_ != 0L) {
      output.writeInt64(2, relativeOffset_);
    }
    if (size_ != 0L) {
      output.writeInt64(3, size_);
    }
    if (hasDuplicate_ != false) {
      output.writeBool(4, hasDuplicate_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (hash_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, hash_);
    }
    if (relativeOffset_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, relativeOffset_);
    }
    if (size_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, size_);
    }
    if (hasDuplicate_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(4, hasDuplicate_);
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
    if (!(obj instanceof GrupoA.OSD.OSDService.GetObjectArgs)) {
      return super.equals(obj);
    }
    GrupoA.OSD.OSDService.GetObjectArgs other = (GrupoA.OSD.OSDService.GetObjectArgs) obj;

    boolean result = true;
    result = result && (getHash()
        == other.getHash());
    result = result && (getRelativeOffset()
        == other.getRelativeOffset());
    result = result && (getSize()
        == other.getSize());
    result = result && (getHasDuplicate()
        == other.getHasDuplicate());
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
    hash = (37 * hash) + HASH_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getHash());
    hash = (37 * hash) + RELATIVEOFFSET_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getRelativeOffset());
    hash = (37 * hash) + SIZE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getSize());
    hash = (37 * hash) + HASDUPLICATE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getHasDuplicate());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static GrupoA.OSD.OSDService.GetObjectArgs parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GrupoA.OSD.OSDService.GetObjectArgs parseFrom(
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
  public static Builder newBuilder(GrupoA.OSD.OSDService.GetObjectArgs prototype) {
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
   * Protobuf type {@code GrupoA.OSD.OSDService.GetObjectArgs}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:GrupoA.OSD.OSDService.GetObjectArgs)
      GrupoA.OSD.OSDService.GetObjectArgsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return GrupoA.OSD.OSDService.OSDProto.internal_static_GrupoA_OSD_OSDService_GetObjectArgs_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return GrupoA.OSD.OSDService.OSDProto.internal_static_GrupoA_OSD_OSDService_GetObjectArgs_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GrupoA.OSD.OSDService.GetObjectArgs.class, GrupoA.OSD.OSDService.GetObjectArgs.Builder.class);
    }

    // Construct using GrupoA.OSD.OSDService.GetObjectArgs.newBuilder()
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
      hash_ = 0L;

      relativeOffset_ = 0L;

      size_ = 0L;

      hasDuplicate_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return GrupoA.OSD.OSDService.OSDProto.internal_static_GrupoA_OSD_OSDService_GetObjectArgs_descriptor;
    }

    @java.lang.Override
    public GrupoA.OSD.OSDService.GetObjectArgs getDefaultInstanceForType() {
      return GrupoA.OSD.OSDService.GetObjectArgs.getDefaultInstance();
    }

    @java.lang.Override
    public GrupoA.OSD.OSDService.GetObjectArgs build() {
      GrupoA.OSD.OSDService.GetObjectArgs result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public GrupoA.OSD.OSDService.GetObjectArgs buildPartial() {
      GrupoA.OSD.OSDService.GetObjectArgs result = new GrupoA.OSD.OSDService.GetObjectArgs(this);
      result.hash_ = hash_;
      result.relativeOffset_ = relativeOffset_;
      result.size_ = size_;
      result.hasDuplicate_ = hasDuplicate_;
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
      if (other instanceof GrupoA.OSD.OSDService.GetObjectArgs) {
        return mergeFrom((GrupoA.OSD.OSDService.GetObjectArgs)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(GrupoA.OSD.OSDService.GetObjectArgs other) {
      if (other == GrupoA.OSD.OSDService.GetObjectArgs.getDefaultInstance()) return this;
      if (other.getHash() != 0L) {
        setHash(other.getHash());
      }
      if (other.getRelativeOffset() != 0L) {
        setRelativeOffset(other.getRelativeOffset());
      }
      if (other.getSize() != 0L) {
        setSize(other.getSize());
      }
      if (other.getHasDuplicate() != false) {
        setHasDuplicate(other.getHasDuplicate());
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
      GrupoA.OSD.OSDService.GetObjectArgs parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (GrupoA.OSD.OSDService.GetObjectArgs) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long hash_ ;
    /**
     * <code>int64 hash = 1;</code>
     */
    public long getHash() {
      return hash_;
    }
    /**
     * <code>int64 hash = 1;</code>
     */
    public Builder setHash(long value) {
      
      hash_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 hash = 1;</code>
     */
    public Builder clearHash() {
      
      hash_ = 0L;
      onChanged();
      return this;
    }

    private long relativeOffset_ ;
    /**
     * <code>int64 relativeOffset = 2;</code>
     */
    public long getRelativeOffset() {
      return relativeOffset_;
    }
    /**
     * <code>int64 relativeOffset = 2;</code>
     */
    public Builder setRelativeOffset(long value) {
      
      relativeOffset_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 relativeOffset = 2;</code>
     */
    public Builder clearRelativeOffset() {
      
      relativeOffset_ = 0L;
      onChanged();
      return this;
    }

    private long size_ ;
    /**
     * <code>int64 size = 3;</code>
     */
    public long getSize() {
      return size_;
    }
    /**
     * <code>int64 size = 3;</code>
     */
    public Builder setSize(long value) {
      
      size_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 size = 3;</code>
     */
    public Builder clearSize() {
      
      size_ = 0L;
      onChanged();
      return this;
    }

    private boolean hasDuplicate_ ;
    /**
     * <code>bool hasDuplicate = 4;</code>
     */
    public boolean getHasDuplicate() {
      return hasDuplicate_;
    }
    /**
     * <code>bool hasDuplicate = 4;</code>
     */
    public Builder setHasDuplicate(boolean value) {
      
      hasDuplicate_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool hasDuplicate = 4;</code>
     */
    public Builder clearHasDuplicate() {
      
      hasDuplicate_ = false;
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


    // @@protoc_insertion_point(builder_scope:GrupoA.OSD.OSDService.GetObjectArgs)
  }

  // @@protoc_insertion_point(class_scope:GrupoA.OSD.OSDService.GetObjectArgs)
  private static final GrupoA.OSD.OSDService.GetObjectArgs DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GrupoA.OSD.OSDService.GetObjectArgs();
  }

  public static GrupoA.OSD.OSDService.GetObjectArgs getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetObjectArgs>
      PARSER = new com.google.protobuf.AbstractParser<GetObjectArgs>() {
    @java.lang.Override
    public GetObjectArgs parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetObjectArgs(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetObjectArgs> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetObjectArgs> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public GrupoA.OSD.OSDService.GetObjectArgs getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

