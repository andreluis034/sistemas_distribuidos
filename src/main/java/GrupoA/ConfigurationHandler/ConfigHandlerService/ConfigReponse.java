// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConfigHandler.proto

package GrupoA.ConfigurationHandler.ConfigHandlerService;

/**
 * Protobuf type {@code GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse}
 */
public  final class ConfigReponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse)
    ConfigReponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ConfigReponse.newBuilder() to construct.
  private ConfigReponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ConfigReponse() {
    configuration_ = "";
    id_ = "";
    memberCount_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ConfigReponse(
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

            configuration_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            id_ = s;
            break;
          }
          case 24: {

            memberCount_ = input.readInt32();
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
    return GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigurationHandlerProto.internal_static_GrupoA_ConfigurationHandler_ConfigHandlerService_ConfigReponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigurationHandlerProto.internal_static_GrupoA_ConfigurationHandler_ConfigHandlerService_ConfigReponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.class, GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.Builder.class);
  }

  public static final int CONFIGURATION_FIELD_NUMBER = 1;
  private volatile java.lang.Object configuration_;
  /**
   * <code>string configuration = 1;</code>
   */
  public java.lang.String getConfiguration() {
    java.lang.Object ref = configuration_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      configuration_ = s;
      return s;
    }
  }
  /**
   * <code>string configuration = 1;</code>
   */
  public com.google.protobuf.ByteString
      getConfigurationBytes() {
    java.lang.Object ref = configuration_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      configuration_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ID_FIELD_NUMBER = 2;
  private volatile java.lang.Object id_;
  /**
   * <code>string id = 2;</code>
   */
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 2;</code>
   */
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MEMBERCOUNT_FIELD_NUMBER = 3;
  private int memberCount_;
  /**
   * <code>int32 memberCount = 3;</code>
   */
  public int getMemberCount() {
    return memberCount_;
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
    if (!getConfigurationBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, configuration_);
    }
    if (!getIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, id_);
    }
    if (memberCount_ != 0) {
      output.writeInt32(3, memberCount_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getConfigurationBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, configuration_);
    }
    if (!getIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, id_);
    }
    if (memberCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, memberCount_);
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
    if (!(obj instanceof GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse)) {
      return super.equals(obj);
    }
    GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse other = (GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse) obj;

    boolean result = true;
    result = result && getConfiguration()
        .equals(other.getConfiguration());
    result = result && getId()
        .equals(other.getId());
    result = result && (getMemberCount()
        == other.getMemberCount());
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
    hash = (37 * hash) + CONFIGURATION_FIELD_NUMBER;
    hash = (53 * hash) + getConfiguration().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId().hashCode();
    hash = (37 * hash) + MEMBERCOUNT_FIELD_NUMBER;
    hash = (53 * hash) + getMemberCount();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parseFrom(
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
  public static Builder newBuilder(GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse prototype) {
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
   * Protobuf type {@code GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse)
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigurationHandlerProto.internal_static_GrupoA_ConfigurationHandler_ConfigHandlerService_ConfigReponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigurationHandlerProto.internal_static_GrupoA_ConfigurationHandler_ConfigHandlerService_ConfigReponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.class, GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.Builder.class);
    }

    // Construct using GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.newBuilder()
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
      configuration_ = "";

      id_ = "";

      memberCount_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigurationHandlerProto.internal_static_GrupoA_ConfigurationHandler_ConfigHandlerService_ConfigReponse_descriptor;
    }

    @java.lang.Override
    public GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse getDefaultInstanceForType() {
      return GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.getDefaultInstance();
    }

    @java.lang.Override
    public GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse build() {
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse buildPartial() {
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse result = new GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse(this);
      result.configuration_ = configuration_;
      result.id_ = id_;
      result.memberCount_ = memberCount_;
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
      if (other instanceof GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse) {
        return mergeFrom((GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse other) {
      if (other == GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.getDefaultInstance()) return this;
      if (!other.getConfiguration().isEmpty()) {
        configuration_ = other.configuration_;
        onChanged();
      }
      if (!other.getId().isEmpty()) {
        id_ = other.id_;
        onChanged();
      }
      if (other.getMemberCount() != 0) {
        setMemberCount(other.getMemberCount());
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
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object configuration_ = "";
    /**
     * <code>string configuration = 1;</code>
     */
    public java.lang.String getConfiguration() {
      java.lang.Object ref = configuration_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        configuration_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string configuration = 1;</code>
     */
    public com.google.protobuf.ByteString
        getConfigurationBytes() {
      java.lang.Object ref = configuration_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        configuration_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string configuration = 1;</code>
     */
    public Builder setConfiguration(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      configuration_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string configuration = 1;</code>
     */
    public Builder clearConfiguration() {
      
      configuration_ = getDefaultInstance().getConfiguration();
      onChanged();
      return this;
    }
    /**
     * <code>string configuration = 1;</code>
     */
    public Builder setConfigurationBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      configuration_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object id_ = "";
    /**
     * <code>string id = 2;</code>
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 2;</code>
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 2;</code>
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 2;</code>
     */
    public Builder clearId() {
      
      id_ = getDefaultInstance().getId();
      onChanged();
      return this;
    }
    /**
     * <code>string id = 2;</code>
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      id_ = value;
      onChanged();
      return this;
    }

    private int memberCount_ ;
    /**
     * <code>int32 memberCount = 3;</code>
     */
    public int getMemberCount() {
      return memberCount_;
    }
    /**
     * <code>int32 memberCount = 3;</code>
     */
    public Builder setMemberCount(int value) {
      
      memberCount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 memberCount = 3;</code>
     */
    public Builder clearMemberCount() {
      
      memberCount_ = 0;
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


    // @@protoc_insertion_point(builder_scope:GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse)
  }

  // @@protoc_insertion_point(class_scope:GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse)
  private static final GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse();
  }

  public static GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ConfigReponse>
      PARSER = new com.google.protobuf.AbstractParser<ConfigReponse>() {
    @java.lang.Override
    public ConfigReponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ConfigReponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ConfigReponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ConfigReponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

