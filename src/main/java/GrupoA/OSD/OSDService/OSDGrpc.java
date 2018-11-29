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
    public void getObject(GrupoA.OSD.OSDService.GetObjectArgs request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.ObjectData> responseObserver) {
      asyncUnimplementedUnaryCall(getGetObjectMethod(), responseObserver);
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
            getGetObjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.OSD.OSDService.GetObjectArgs,
                GrupoA.OSD.OSDService.ObjectData>(
                  this, METHODID_GET_OBJECT)))
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
    public void getObject(GrupoA.OSD.OSDService.GetObjectArgs request,
        io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.ObjectData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetObjectMethod(), getCallOptions()), request, responseObserver);
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
    public GrupoA.OSD.OSDService.ObjectData getObject(GrupoA.OSD.OSDService.GetObjectArgs request) {
      return blockingUnaryCall(
          getChannel(), getGetObjectMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.OSD.OSDService.ObjectData> getObject(
        GrupoA.OSD.OSDService.GetObjectArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getGetObjectMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PUT_OBJECT = 0;
  private static final int METHODID_GET_OBJECT = 1;

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
        case METHODID_GET_OBJECT:
          serviceImpl.getObject((GrupoA.OSD.OSDService.GetObjectArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.OSD.OSDService.ObjectData>) responseObserver);
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
              .addMethod(getGetObjectMethod())
              .build();
        }
      }
    }
    return result;
  }
}
