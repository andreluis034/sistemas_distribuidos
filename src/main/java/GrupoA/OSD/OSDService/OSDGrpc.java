package GrupoA.OSD.OSDService;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: OSD.proto")
public final class OSDGrpc {

  private OSDGrpc() {}

  public static final String SERVICE_NAME = "GrupoA.OSD.OSDService.OSD";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.ObjectData,
      GrupoA.OSD.OSDService.EmptyMessage> getPutObjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "putObject",
      requestType = GrupoA.OSD.OSDService.ObjectData.class,
      responseType = GrupoA.OSD.OSDService.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.ObjectData,
      GrupoA.OSD.OSDService.EmptyMessage> getPutObjectMethod() {
    io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.ObjectData, GrupoA.OSD.OSDService.EmptyMessage> getPutObjectMethod;
    if ((getPutObjectMethod = OSDGrpc.getPutObjectMethod) == null) {
      synchronized (OSDGrpc.class) {
        if ((getPutObjectMethod = OSDGrpc.getPutObjectMethod) == null) {
          OSDGrpc.getPutObjectMethod = getPutObjectMethod = 
              io.grpc.MethodDescriptor.<GrupoA.OSD.OSDService.ObjectData, GrupoA.OSD.OSDService.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.OSD.OSDService.OSD", "putObject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.ObjectData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new OSDMethodDescriptorSupplier("putObject"))
                  .build();
          }
        }
     }
     return getPutObjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.MiniObject,
      GrupoA.OSD.OSDService.EmptyMessage> getWriteMiniObjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "writeMiniObject",
      requestType = GrupoA.OSD.OSDService.MiniObject.class,
      responseType = GrupoA.OSD.OSDService.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.MiniObject,
      GrupoA.OSD.OSDService.EmptyMessage> getWriteMiniObjectMethod() {
    io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.MiniObject, GrupoA.OSD.OSDService.EmptyMessage> getWriteMiniObjectMethod;
    if ((getWriteMiniObjectMethod = OSDGrpc.getWriteMiniObjectMethod) == null) {
      synchronized (OSDGrpc.class) {
        if ((getWriteMiniObjectMethod = OSDGrpc.getWriteMiniObjectMethod) == null) {
          OSDGrpc.getWriteMiniObjectMethod = getWriteMiniObjectMethod = 
              io.grpc.MethodDescriptor.<GrupoA.OSD.OSDService.MiniObject, GrupoA.OSD.OSDService.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.OSD.OSDService.OSD", "writeMiniObject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.MiniObject.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new OSDMethodDescriptorSupplier("writeMiniObject"))
                  .build();
          }
        }
     }
     return getWriteMiniObjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs,
      GrupoA.OSD.OSDService.ObjectData> getReadMiniObjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "readMiniObject",
      requestType = GrupoA.OSD.OSDService.GetObjectArgs.class,
      responseType = GrupoA.OSD.OSDService.ObjectData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs,
      GrupoA.OSD.OSDService.ObjectData> getReadMiniObjectMethod() {
    io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs, GrupoA.OSD.OSDService.ObjectData> getReadMiniObjectMethod;
    if ((getReadMiniObjectMethod = OSDGrpc.getReadMiniObjectMethod) == null) {
      synchronized (OSDGrpc.class) {
        if ((getReadMiniObjectMethod = OSDGrpc.getReadMiniObjectMethod) == null) {
          OSDGrpc.getReadMiniObjectMethod = getReadMiniObjectMethod = 
              io.grpc.MethodDescriptor.<GrupoA.OSD.OSDService.GetObjectArgs, GrupoA.OSD.OSDService.ObjectData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.OSD.OSDService.OSD", "readMiniObject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.GetObjectArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.ObjectData.getDefaultInstance()))
                  .setSchemaDescriptor(new OSDMethodDescriptorSupplier("readMiniObject"))
                  .build();
          }
        }
     }
     return getReadMiniObjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs,
      GrupoA.OSD.OSDService.ObjectData> getGetObjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getObject",
      requestType = GrupoA.OSD.OSDService.GetObjectArgs.class,
      responseType = GrupoA.OSD.OSDService.ObjectData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs,
      GrupoA.OSD.OSDService.ObjectData> getGetObjectMethod() {
    io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs, GrupoA.OSD.OSDService.ObjectData> getGetObjectMethod;
    if ((getGetObjectMethod = OSDGrpc.getGetObjectMethod) == null) {
      synchronized (OSDGrpc.class) {
        if ((getGetObjectMethod = OSDGrpc.getGetObjectMethod) == null) {
          OSDGrpc.getGetObjectMethod = getGetObjectMethod = 
              io.grpc.MethodDescriptor.<GrupoA.OSD.OSDService.GetObjectArgs, GrupoA.OSD.OSDService.ObjectData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.OSD.OSDService.OSD", "getObject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.GetObjectArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.ObjectData.getDefaultInstance()))
                  .setSchemaDescriptor(new OSDMethodDescriptorSupplier("getObject"))
                  .build();
          }
        }
     }
     return getGetObjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.EmptyMessage,
      GrupoA.OSD.OSDService.BooleanMessage> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ping",
      requestType = GrupoA.OSD.OSDService.EmptyMessage.class,
      responseType = GrupoA.OSD.OSDService.BooleanMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.EmptyMessage,
      GrupoA.OSD.OSDService.BooleanMessage> getPingMethod() {
    io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.EmptyMessage, GrupoA.OSD.OSDService.BooleanMessage> getPingMethod;
    if ((getPingMethod = OSDGrpc.getPingMethod) == null) {
      synchronized (OSDGrpc.class) {
        if ((getPingMethod = OSDGrpc.getPingMethod) == null) {
          OSDGrpc.getPingMethod = getPingMethod = 
              io.grpc.MethodDescriptor.<GrupoA.OSD.OSDService.EmptyMessage, GrupoA.OSD.OSDService.BooleanMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.OSD.OSDService.OSD", "ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.BooleanMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new OSDMethodDescriptorSupplier("ping"))
                  .build();
          }
        }
     }
     return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.OSDInSamePaG,
      GrupoA.OSD.OSDService.EmptyMessage> getPushMapUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pushMapUpdate",
      requestType = GrupoA.OSD.OSDService.OSDInSamePaG.class,
      responseType = GrupoA.OSD.OSDService.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.OSDInSamePaG,
      GrupoA.OSD.OSDService.EmptyMessage> getPushMapUpdateMethod() {
    io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.OSDInSamePaG, GrupoA.OSD.OSDService.EmptyMessage> getPushMapUpdateMethod;
    if ((getPushMapUpdateMethod = OSDGrpc.getPushMapUpdateMethod) == null) {
      synchronized (OSDGrpc.class) {
        if ((getPushMapUpdateMethod = OSDGrpc.getPushMapUpdateMethod) == null) {
          OSDGrpc.getPushMapUpdateMethod = getPushMapUpdateMethod = 
              io.grpc.MethodDescriptor.<GrupoA.OSD.OSDService.OSDInSamePaG, GrupoA.OSD.OSDService.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.OSD.OSDService.OSD", "pushMapUpdate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.OSDInSamePaG.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new OSDMethodDescriptorSupplier("pushMapUpdate"))
                  .build();
          }
        }
     }
     return getPushMapUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs,
      GrupoA.OSD.OSDService.EmptyMessage> getDeleteObjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteObject",
      requestType = GrupoA.OSD.OSDService.GetObjectArgs.class,
      responseType = GrupoA.OSD.OSDService.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs,
      GrupoA.OSD.OSDService.EmptyMessage> getDeleteObjectMethod() {
    io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs, GrupoA.OSD.OSDService.EmptyMessage> getDeleteObjectMethod;
    if ((getDeleteObjectMethod = OSDGrpc.getDeleteObjectMethod) == null) {
      synchronized (OSDGrpc.class) {
        if ((getDeleteObjectMethod = OSDGrpc.getDeleteObjectMethod) == null) {
          OSDGrpc.getDeleteObjectMethod = getDeleteObjectMethod = 
              io.grpc.MethodDescriptor.<GrupoA.OSD.OSDService.GetObjectArgs, GrupoA.OSD.OSDService.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.OSD.OSDService.OSD", "deleteObject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.GetObjectArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new OSDMethodDescriptorSupplier("deleteObject"))
                  .build();
          }
        }
     }
     return getDeleteObjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs,
      GrupoA.OSD.OSDService.EmptyMessage> getTruncateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "truncate",
      requestType = GrupoA.OSD.OSDService.GetObjectArgs.class,
      responseType = GrupoA.OSD.OSDService.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs,
      GrupoA.OSD.OSDService.EmptyMessage> getTruncateMethod() {
    io.grpc.MethodDescriptor<GrupoA.OSD.OSDService.GetObjectArgs, GrupoA.OSD.OSDService.EmptyMessage> getTruncateMethod;
    if ((getTruncateMethod = OSDGrpc.getTruncateMethod) == null) {
      synchronized (OSDGrpc.class) {
        if ((getTruncateMethod = OSDGrpc.getTruncateMethod) == null) {
          OSDGrpc.getTruncateMethod = getTruncateMethod = 
              io.grpc.MethodDescriptor.<GrupoA.OSD.OSDService.GetObjectArgs, GrupoA.OSD.OSDService.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.OSD.OSDService.OSD", "truncate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.GetObjectArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.OSD.OSDService.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new OSDMethodDescriptorSupplier("truncate"))
                  .build();
          }
        }
     }
     return getTruncateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OSDStub newStub(io.grpc.Channel channel) {
    return new OSDStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OSDBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new OSDBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OSDFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new OSDFutureStub(channel);
  }

  /**
   */
  public static abstract class OSDImplBase implements io.grpc.BindableService {

    /**
     */
    public void putObject(GrupoA.OSD.OSDService.ObjectData request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getPutObjectMethod(), responseObserver);
    }

    /**
     */
    public void writeMiniObject(GrupoA.OSD.OSDService.MiniObject request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteMiniObjectMethod(), responseObserver);
    }

    /**
     */
    public void readMiniObject(GrupoA.OSD.OSDService.GetObjectArgs request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.ObjectData> responseObserver) {
      asyncUnimplementedUnaryCall(getReadMiniObjectMethod(), responseObserver);
    }

    /**
     */
    public void getObject(GrupoA.OSD.OSDService.GetObjectArgs request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.ObjectData> responseObserver) {
      asyncUnimplementedUnaryCall(getGetObjectMethod(), responseObserver);
    }

    /**
     */
    public void ping(GrupoA.OSD.OSDService.EmptyMessage request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.BooleanMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    public void pushMapUpdate(GrupoA.OSD.OSDService.OSDInSamePaG request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getPushMapUpdateMethod(), responseObserver);
    }

    /**
     */
    public void deleteObject(GrupoA.OSD.OSDService.GetObjectArgs request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteObjectMethod(), responseObserver);
    }

    /**
     */
    public void truncate(GrupoA.OSD.OSDService.GetObjectArgs request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getTruncateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPutObjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.OSD.OSDService.ObjectData,
                GrupoA.OSD.OSDService.EmptyMessage>(
                  this, METHODID_PUT_OBJECT)))
          .addMethod(
            getWriteMiniObjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.OSD.OSDService.MiniObject,
                GrupoA.OSD.OSDService.EmptyMessage>(
                  this, METHODID_WRITE_MINI_OBJECT)))
          .addMethod(
            getReadMiniObjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.OSD.OSDService.GetObjectArgs,
                GrupoA.OSD.OSDService.ObjectData>(
                  this, METHODID_READ_MINI_OBJECT)))
          .addMethod(
            getGetObjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.OSD.OSDService.GetObjectArgs,
                GrupoA.OSD.OSDService.ObjectData>(
                  this, METHODID_GET_OBJECT)))
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.OSD.OSDService.EmptyMessage,
                GrupoA.OSD.OSDService.BooleanMessage>(
                  this, METHODID_PING)))
          .addMethod(
            getPushMapUpdateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.OSD.OSDService.OSDInSamePaG,
                GrupoA.OSD.OSDService.EmptyMessage>(
                  this, METHODID_PUSH_MAP_UPDATE)))
          .addMethod(
            getDeleteObjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.OSD.OSDService.GetObjectArgs,
                GrupoA.OSD.OSDService.EmptyMessage>(
                  this, METHODID_DELETE_OBJECT)))
          .addMethod(
            getTruncateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.OSD.OSDService.GetObjectArgs,
                GrupoA.OSD.OSDService.EmptyMessage>(
                  this, METHODID_TRUNCATE)))
          .build();
    }
  }

  /**
   */
  public static final class OSDStub extends io.grpc.stub.AbstractStub<OSDStub> {
    private OSDStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OSDStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OSDStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OSDStub(channel, callOptions);
    }

    /**
     */
    public void putObject(GrupoA.OSD.OSDService.ObjectData request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPutObjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void writeMiniObject(GrupoA.OSD.OSDService.MiniObject request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteMiniObjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void readMiniObject(GrupoA.OSD.OSDService.GetObjectArgs request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.ObjectData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReadMiniObjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getObject(GrupoA.OSD.OSDService.GetObjectArgs request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.ObjectData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetObjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ping(GrupoA.OSD.OSDService.EmptyMessage request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.BooleanMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pushMapUpdate(GrupoA.OSD.OSDService.OSDInSamePaG request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPushMapUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteObject(GrupoA.OSD.OSDService.GetObjectArgs request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteObjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void truncate(GrupoA.OSD.OSDService.GetObjectArgs request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTruncateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class OSDBlockingStub extends io.grpc.stub.AbstractStub<OSDBlockingStub> {
    private OSDBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OSDBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OSDBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OSDBlockingStub(channel, callOptions);
    }

    /**
     */
    public GrupoA.OSD.OSDService.EmptyMessage putObject(GrupoA.OSD.OSDService.ObjectData request) {
      return blockingUnaryCall(
          getChannel(), getPutObjectMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.OSD.OSDService.EmptyMessage writeMiniObject(GrupoA.OSD.OSDService.MiniObject request) {
      return blockingUnaryCall(
          getChannel(), getWriteMiniObjectMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.OSD.OSDService.ObjectData readMiniObject(GrupoA.OSD.OSDService.GetObjectArgs request) {
      return blockingUnaryCall(
          getChannel(), getReadMiniObjectMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.OSD.OSDService.ObjectData getObject(GrupoA.OSD.OSDService.GetObjectArgs request) {
      return blockingUnaryCall(
          getChannel(), getGetObjectMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.OSD.OSDService.BooleanMessage ping(GrupoA.OSD.OSDService.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.OSD.OSDService.EmptyMessage pushMapUpdate(GrupoA.OSD.OSDService.OSDInSamePaG request) {
      return blockingUnaryCall(
          getChannel(), getPushMapUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.OSD.OSDService.EmptyMessage deleteObject(GrupoA.OSD.OSDService.GetObjectArgs request) {
      return blockingUnaryCall(
          getChannel(), getDeleteObjectMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.OSD.OSDService.EmptyMessage truncate(GrupoA.OSD.OSDService.GetObjectArgs request) {
      return blockingUnaryCall(
          getChannel(), getTruncateMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class OSDFutureStub extends io.grpc.stub.AbstractStub<OSDFutureStub> {
    private OSDFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OSDFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OSDFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OSDFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.OSD.OSDService.EmptyMessage> putObject(
        GrupoA.OSD.OSDService.ObjectData request) {
      return futureUnaryCall(
          getChannel().newCall(getPutObjectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.OSD.OSDService.EmptyMessage> writeMiniObject(
        GrupoA.OSD.OSDService.MiniObject request) {
      return futureUnaryCall(
          getChannel().newCall(getWriteMiniObjectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.OSD.OSDService.ObjectData> readMiniObject(
        GrupoA.OSD.OSDService.GetObjectArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getReadMiniObjectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.OSD.OSDService.ObjectData> getObject(
        GrupoA.OSD.OSDService.GetObjectArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getGetObjectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.OSD.OSDService.BooleanMessage> ping(
        GrupoA.OSD.OSDService.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.OSD.OSDService.EmptyMessage> pushMapUpdate(
        GrupoA.OSD.OSDService.OSDInSamePaG request) {
      return futureUnaryCall(
          getChannel().newCall(getPushMapUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.OSD.OSDService.EmptyMessage> deleteObject(
        GrupoA.OSD.OSDService.GetObjectArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteObjectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.OSD.OSDService.EmptyMessage> truncate(
        GrupoA.OSD.OSDService.GetObjectArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getTruncateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PUT_OBJECT = 0;
  private static final int METHODID_WRITE_MINI_OBJECT = 1;
  private static final int METHODID_READ_MINI_OBJECT = 2;
  private static final int METHODID_GET_OBJECT = 3;
  private static final int METHODID_PING = 4;
  private static final int METHODID_PUSH_MAP_UPDATE = 5;
  private static final int METHODID_DELETE_OBJECT = 6;
  private static final int METHODID_TRUNCATE = 7;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OSDImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OSDImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PUT_OBJECT:
          serviceImpl.putObject((GrupoA.OSD.OSDService.ObjectData) request,
              (io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage>) responseObserver);
          break;
        case METHODID_WRITE_MINI_OBJECT:
          serviceImpl.writeMiniObject((GrupoA.OSD.OSDService.MiniObject) request,
              (io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage>) responseObserver);
          break;
        case METHODID_READ_MINI_OBJECT:
          serviceImpl.readMiniObject((GrupoA.OSD.OSDService.GetObjectArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.ObjectData>) responseObserver);
          break;
        case METHODID_GET_OBJECT:
          serviceImpl.getObject((GrupoA.OSD.OSDService.GetObjectArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.ObjectData>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((GrupoA.OSD.OSDService.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.BooleanMessage>) responseObserver);
          break;
        case METHODID_PUSH_MAP_UPDATE:
          serviceImpl.pushMapUpdate((GrupoA.OSD.OSDService.OSDInSamePaG) request,
              (io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage>) responseObserver);
          break;
        case METHODID_DELETE_OBJECT:
          serviceImpl.deleteObject((GrupoA.OSD.OSDService.GetObjectArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage>) responseObserver);
          break;
        case METHODID_TRUNCATE:
          serviceImpl.truncate((GrupoA.OSD.OSDService.GetObjectArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.EmptyMessage>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class OSDBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OSDBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GrupoA.OSD.OSDService.OSDProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OSD");
    }
  }

  private static final class OSDFileDescriptorSupplier
      extends OSDBaseDescriptorSupplier {
    OSDFileDescriptorSupplier() {}
  }

  private static final class OSDMethodDescriptorSupplier
      extends OSDBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OSDMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (OSDGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OSDFileDescriptorSupplier())
              .addMethod(getPutObjectMethod())
              .addMethod(getWriteMiniObjectMethod())
              .addMethod(getReadMiniObjectMethod())
              .addMethod(getGetObjectMethod())
              .addMethod(getPingMethod())
              .addMethod(getPushMapUpdateMethod())
              .addMethod(getDeleteObjectMethod())
              .addMethod(getTruncateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
