package GrupoA.ConfigurationHandler.ConfigHandlerService;

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
    comments = "Source: ConfigHandler.proto")
public final class ConfigHandlerGrpc {

  private ConfigHandlerGrpc() {}

  public static final String SERVICE_NAME = "GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigHandler";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getJoinFileSystemConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "joinFileSystemConfig",
      requestType = GrupoA.ConfigurationHandler.ConfigHandlerService.Requester.class,
      responseType = GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getJoinFileSystemConfigMethod() {
    io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester, GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getJoinFileSystemConfigMethod;
    if ((getJoinFileSystemConfigMethod = ConfigHandlerGrpc.getJoinFileSystemConfigMethod) == null) {
      synchronized (ConfigHandlerGrpc.class) {
        if ((getJoinFileSystemConfigMethod = ConfigHandlerGrpc.getJoinFileSystemConfigMethod) == null) {
          ConfigHandlerGrpc.getJoinFileSystemConfigMethod = getJoinFileSystemConfigMethod = 
              io.grpc.MethodDescriptor.<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester, GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigHandler", "joinFileSystemConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.ConfigurationHandler.ConfigHandlerService.Requester.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ConfigHandlerMethodDescriptorSupplier("joinFileSystemConfig"))
                  .build();
          }
        }
     }
     return getJoinFileSystemConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getLeaveFileSystemConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "leaveFileSystemConfig",
      requestType = GrupoA.ConfigurationHandler.ConfigHandlerService.Requester.class,
      responseType = GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getLeaveFileSystemConfigMethod() {
    io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester, GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getLeaveFileSystemConfigMethod;
    if ((getLeaveFileSystemConfigMethod = ConfigHandlerGrpc.getLeaveFileSystemConfigMethod) == null) {
      synchronized (ConfigHandlerGrpc.class) {
        if ((getLeaveFileSystemConfigMethod = ConfigHandlerGrpc.getLeaveFileSystemConfigMethod) == null) {
          ConfigHandlerGrpc.getLeaveFileSystemConfigMethod = getLeaveFileSystemConfigMethod = 
              io.grpc.MethodDescriptor.<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester, GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigHandler", "leaveFileSystemConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.ConfigurationHandler.ConfigHandlerService.Requester.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ConfigHandlerMethodDescriptorSupplier("leaveFileSystemConfig"))
                  .build();
          }
        }
     }
     return getLeaveFileSystemConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getJoinCrushMapConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "joinCrushMapConfig",
      requestType = GrupoA.ConfigurationHandler.ConfigHandlerService.Requester.class,
      responseType = GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getJoinCrushMapConfigMethod() {
    io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester, GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getJoinCrushMapConfigMethod;
    if ((getJoinCrushMapConfigMethod = ConfigHandlerGrpc.getJoinCrushMapConfigMethod) == null) {
      synchronized (ConfigHandlerGrpc.class) {
        if ((getJoinCrushMapConfigMethod = ConfigHandlerGrpc.getJoinCrushMapConfigMethod) == null) {
          ConfigHandlerGrpc.getJoinCrushMapConfigMethod = getJoinCrushMapConfigMethod = 
              io.grpc.MethodDescriptor.<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester, GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigHandler", "joinCrushMapConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.ConfigurationHandler.ConfigHandlerService.Requester.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ConfigHandlerMethodDescriptorSupplier("joinCrushMapConfig"))
                  .build();
          }
        }
     }
     return getJoinCrushMapConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getLeaveCrushMapConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "leaveCrushMapConfig",
      requestType = GrupoA.ConfigurationHandler.ConfigHandlerService.Requester.class,
      responseType = GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
      GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getLeaveCrushMapConfigMethod() {
    io.grpc.MethodDescriptor<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester, GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> getLeaveCrushMapConfigMethod;
    if ((getLeaveCrushMapConfigMethod = ConfigHandlerGrpc.getLeaveCrushMapConfigMethod) == null) {
      synchronized (ConfigHandlerGrpc.class) {
        if ((getLeaveCrushMapConfigMethod = ConfigHandlerGrpc.getLeaveCrushMapConfigMethod) == null) {
          ConfigHandlerGrpc.getLeaveCrushMapConfigMethod = getLeaveCrushMapConfigMethod = 
              io.grpc.MethodDescriptor.<GrupoA.ConfigurationHandler.ConfigHandlerService.Requester, GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigHandler", "leaveCrushMapConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.ConfigurationHandler.ConfigHandlerService.Requester.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ConfigHandlerMethodDescriptorSupplier("leaveCrushMapConfig"))
                  .build();
          }
        }
     }
     return getLeaveCrushMapConfigMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConfigHandlerStub newStub(io.grpc.Channel channel) {
    return new ConfigHandlerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConfigHandlerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ConfigHandlerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConfigHandlerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ConfigHandlerFutureStub(channel);
  }

  /**
   */
  public static abstract class ConfigHandlerImplBase implements io.grpc.BindableService {

    /**
     */
    public void joinFileSystemConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request,
        io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinFileSystemConfigMethod(), responseObserver);
    }

    /**
     */
    public void leaveFileSystemConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request,
        io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLeaveFileSystemConfigMethod(), responseObserver);
    }

    /**
     */
    public void joinCrushMapConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request,
        io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinCrushMapConfigMethod(), responseObserver);
    }

    /**
     */
    public void leaveCrushMapConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request,
        io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLeaveCrushMapConfigMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getJoinFileSystemConfigMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
                GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>(
                  this, METHODID_JOIN_FILE_SYSTEM_CONFIG)))
          .addMethod(
            getLeaveFileSystemConfigMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
                GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>(
                  this, METHODID_LEAVE_FILE_SYSTEM_CONFIG)))
          .addMethod(
            getJoinCrushMapConfigMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
                GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>(
                  this, METHODID_JOIN_CRUSH_MAP_CONFIG)))
          .addMethod(
            getLeaveCrushMapConfigMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.ConfigurationHandler.ConfigHandlerService.Requester,
                GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>(
                  this, METHODID_LEAVE_CRUSH_MAP_CONFIG)))
          .build();
    }
  }

  /**
   */
  public static final class ConfigHandlerStub extends io.grpc.stub.AbstractStub<ConfigHandlerStub> {
    private ConfigHandlerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigHandlerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfigHandlerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigHandlerStub(channel, callOptions);
    }

    /**
     */
    public void joinFileSystemConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request,
        io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinFileSystemConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void leaveFileSystemConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request,
        io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLeaveFileSystemConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinCrushMapConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request,
        io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinCrushMapConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void leaveCrushMapConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request,
        io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLeaveCrushMapConfigMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ConfigHandlerBlockingStub extends io.grpc.stub.AbstractStub<ConfigHandlerBlockingStub> {
    private ConfigHandlerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigHandlerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfigHandlerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigHandlerBlockingStub(channel, callOptions);
    }

    /**
     */
    public GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse joinFileSystemConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request) {
      return blockingUnaryCall(
          getChannel(), getJoinFileSystemConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse leaveFileSystemConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request) {
      return blockingUnaryCall(
          getChannel(), getLeaveFileSystemConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse joinCrushMapConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request) {
      return blockingUnaryCall(
          getChannel(), getJoinCrushMapConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse leaveCrushMapConfig(GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request) {
      return blockingUnaryCall(
          getChannel(), getLeaveCrushMapConfigMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ConfigHandlerFutureStub extends io.grpc.stub.AbstractStub<ConfigHandlerFutureStub> {
    private ConfigHandlerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigHandlerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfigHandlerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigHandlerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> joinFileSystemConfig(
        GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinFileSystemConfigMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> leaveFileSystemConfig(
        GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request) {
      return futureUnaryCall(
          getChannel().newCall(getLeaveFileSystemConfigMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> joinCrushMapConfig(
        GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinCrushMapConfigMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse> leaveCrushMapConfig(
        GrupoA.ConfigurationHandler.ConfigHandlerService.Requester request) {
      return futureUnaryCall(
          getChannel().newCall(getLeaveCrushMapConfigMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN_FILE_SYSTEM_CONFIG = 0;
  private static final int METHODID_LEAVE_FILE_SYSTEM_CONFIG = 1;
  private static final int METHODID_JOIN_CRUSH_MAP_CONFIG = 2;
  private static final int METHODID_LEAVE_CRUSH_MAP_CONFIG = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ConfigHandlerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ConfigHandlerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_JOIN_FILE_SYSTEM_CONFIG:
          serviceImpl.joinFileSystemConfig((GrupoA.ConfigurationHandler.ConfigHandlerService.Requester) request,
              (io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>) responseObserver);
          break;
        case METHODID_LEAVE_FILE_SYSTEM_CONFIG:
          serviceImpl.leaveFileSystemConfig((GrupoA.ConfigurationHandler.ConfigHandlerService.Requester) request,
              (io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>) responseObserver);
          break;
        case METHODID_JOIN_CRUSH_MAP_CONFIG:
          serviceImpl.joinCrushMapConfig((GrupoA.ConfigurationHandler.ConfigHandlerService.Requester) request,
              (io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>) responseObserver);
          break;
        case METHODID_LEAVE_CRUSH_MAP_CONFIG:
          serviceImpl.leaveCrushMapConfig((GrupoA.ConfigurationHandler.ConfigHandlerService.Requester) request,
              (io.grpc.stub.StreamObserver<GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse>) responseObserver);
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

  private static abstract class ConfigHandlerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ConfigHandlerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigurationHandlerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ConfigHandler");
    }
  }

  private static final class ConfigHandlerFileDescriptorSupplier
      extends ConfigHandlerBaseDescriptorSupplier {
    ConfigHandlerFileDescriptorSupplier() {}
  }

  private static final class ConfigHandlerMethodDescriptorSupplier
      extends ConfigHandlerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ConfigHandlerMethodDescriptorSupplier(String methodName) {
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
      synchronized (ConfigHandlerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConfigHandlerFileDescriptorSupplier())
              .addMethod(getJoinFileSystemConfigMethod())
              .addMethod(getLeaveFileSystemConfigMethod())
              .addMethod(getJoinCrushMapConfigMethod())
              .addMethod(getLeaveCrushMapConfigMethod())
              .build();
        }
      }
    }
    return result;
  }
}
