package GrupoA.StorageController.gRPCService.OSDListener;

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
 * <pre>
 *rpc rmFile (pathOnlyArgs) returns (BooleanMessage) {}
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: OSDListener.proto")
public final class OSDListenerGrpc {

  private OSDListenerGrpc() {}

  public static final String SERVICE_NAME = "GrupoA.StorageController.gRPCService.OSDListener.OSDListener";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.OSDListener.OSDDetails,
      GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG> getReceiveAnnouncementMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveAnnouncement",
      requestType = GrupoA.StorageController.gRPCService.OSDListener.OSDDetails.class,
      responseType = GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.OSDListener.OSDDetails,
      GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG> getReceiveAnnouncementMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.OSDListener.OSDDetails, GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG> getReceiveAnnouncementMethod;
    if ((getReceiveAnnouncementMethod = OSDListenerGrpc.getReceiveAnnouncementMethod) == null) {
      synchronized (OSDListenerGrpc.class) {
        if ((getReceiveAnnouncementMethod = OSDListenerGrpc.getReceiveAnnouncementMethod) == null) {
          OSDListenerGrpc.getReceiveAnnouncementMethod = getReceiveAnnouncementMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.OSDListener.OSDDetails, GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.OSDListener.OSDListener", "receiveAnnouncement"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.OSDListener.OSDDetails.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG.getDefaultInstance()))
                  .setSchemaDescriptor(new OSDListenerMethodDescriptorSupplier("receiveAnnouncement"))
                  .build();
          }
        }
     }
     return getReceiveAnnouncementMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.OSDListener.OSDDetails,
      GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage> getLeaveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "leave",
      requestType = GrupoA.StorageController.gRPCService.OSDListener.OSDDetails.class,
      responseType = GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.OSDListener.OSDDetails,
      GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage> getLeaveMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.OSDListener.OSDDetails, GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage> getLeaveMethod;
    if ((getLeaveMethod = OSDListenerGrpc.getLeaveMethod) == null) {
      synchronized (OSDListenerGrpc.class) {
        if ((getLeaveMethod = OSDListenerGrpc.getLeaveMethod) == null) {
          OSDListenerGrpc.getLeaveMethod = getLeaveMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.OSDListener.OSDDetails, GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.OSDListener.OSDListener", "leave"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.OSDListener.OSDDetails.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new OSDListenerMethodDescriptorSupplier("leave"))
                  .build();
          }
        }
     }
     return getLeaveMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OSDListenerStub newStub(io.grpc.Channel channel) {
    return new OSDListenerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OSDListenerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new OSDListenerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OSDListenerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new OSDListenerFutureStub(channel);
  }

  /**
   * <pre>
   *rpc rmFile (pathOnlyArgs) returns (BooleanMessage) {}
   * </pre>
   */
  public static abstract class OSDListenerImplBase implements io.grpc.BindableService {

    /**
     */
    public void receiveAnnouncement(GrupoA.StorageController.gRPCService.OSDListener.OSDDetails request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveAnnouncementMethod(), responseObserver);
    }

    /**
     */
    public void leave(GrupoA.StorageController.gRPCService.OSDListener.OSDDetails request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getLeaveMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getReceiveAnnouncementMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.OSDListener.OSDDetails,
                GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG>(
                  this, METHODID_RECEIVE_ANNOUNCEMENT)))
          .addMethod(
            getLeaveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.OSDListener.OSDDetails,
                GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage>(
                  this, METHODID_LEAVE)))
          .build();
    }
  }

  /**
   * <pre>
   *rpc rmFile (pathOnlyArgs) returns (BooleanMessage) {}
   * </pre>
   */
  public static final class OSDListenerStub extends io.grpc.stub.AbstractStub<OSDListenerStub> {
    private OSDListenerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OSDListenerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OSDListenerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OSDListenerStub(channel, callOptions);
    }

    /**
     */
    public void receiveAnnouncement(GrupoA.StorageController.gRPCService.OSDListener.OSDDetails request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReceiveAnnouncementMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void leave(GrupoA.StorageController.gRPCService.OSDListener.OSDDetails request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLeaveMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *rpc rmFile (pathOnlyArgs) returns (BooleanMessage) {}
   * </pre>
   */
  public static final class OSDListenerBlockingStub extends io.grpc.stub.AbstractStub<OSDListenerBlockingStub> {
    private OSDListenerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OSDListenerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OSDListenerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OSDListenerBlockingStub(channel, callOptions);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG receiveAnnouncement(GrupoA.StorageController.gRPCService.OSDListener.OSDDetails request) {
      return blockingUnaryCall(
          getChannel(), getReceiveAnnouncementMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage leave(GrupoA.StorageController.gRPCService.OSDListener.OSDDetails request) {
      return blockingUnaryCall(
          getChannel(), getLeaveMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *rpc rmFile (pathOnlyArgs) returns (BooleanMessage) {}
   * </pre>
   */
  public static final class OSDListenerFutureStub extends io.grpc.stub.AbstractStub<OSDListenerFutureStub> {
    private OSDListenerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OSDListenerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OSDListenerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OSDListenerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG> receiveAnnouncement(
        GrupoA.StorageController.gRPCService.OSDListener.OSDDetails request) {
      return futureUnaryCall(
          getChannel().newCall(getReceiveAnnouncementMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage> leave(
        GrupoA.StorageController.gRPCService.OSDListener.OSDDetails request) {
      return futureUnaryCall(
          getChannel().newCall(getLeaveMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RECEIVE_ANNOUNCEMENT = 0;
  private static final int METHODID_LEAVE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OSDListenerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OSDListenerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RECEIVE_ANNOUNCEMENT:
          serviceImpl.receiveAnnouncement((GrupoA.StorageController.gRPCService.OSDListener.OSDDetails) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG>) responseObserver);
          break;
        case METHODID_LEAVE:
          serviceImpl.leave((GrupoA.StorageController.gRPCService.OSDListener.OSDDetails) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage>) responseObserver);
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

  private static abstract class OSDListenerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OSDListenerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GrupoA.StorageController.gRPCService.OSDListener.OSDListenerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OSDListener");
    }
  }

  private static final class OSDListenerFileDescriptorSupplier
      extends OSDListenerBaseDescriptorSupplier {
    OSDListenerFileDescriptorSupplier() {}
  }

  private static final class OSDListenerMethodDescriptorSupplier
      extends OSDListenerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OSDListenerMethodDescriptorSupplier(String methodName) {
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
      synchronized (OSDListenerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OSDListenerFileDescriptorSupplier())
              .addMethod(getReceiveAnnouncementMethod())
              .addMethod(getLeaveMethod())
              .build();
        }
      }
    }
    return result;
  }
}
