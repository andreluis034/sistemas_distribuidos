package GrupoA.StorageController.gRPCService.FileSystem;

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
    comments = "Source: FileSystem.proto")
public final class FileSystemGrpc {

  private FileSystemGrpc() {}

  public static final String SERVICE_NAME = "GrupoA.StorageController.gRPCService.FileSystem.FileSystem";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getRmFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rmFile",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getRmFileMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getRmFileMethod;
    if ((getRmFileMethod = FileSystemGrpc.getRmFileMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getRmFileMethod = FileSystemGrpc.getRmFileMethod) == null) {
          FileSystemGrpc.getRmFileMethod = getRmFileMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "rmFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("rmFile"))
                  .build();
          }
        }
     }
     return getRmFileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getRmDirMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rmDir",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getRmDirMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getRmDirMethod;
    if ((getRmDirMethod = FileSystemGrpc.getRmDirMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getRmDirMethod = FileSystemGrpc.getRmDirMethod) == null) {
          FileSystemGrpc.getRmDirMethod = getRmDirMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "rmDir"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("rmDir"))
                  .build();
          }
        }
     }
     return getRmDirMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getMkDirMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "mkDir",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getMkDirMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getMkDirMethod;
    if ((getMkDirMethod = FileSystemGrpc.getMkDirMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getMkDirMethod = FileSystemGrpc.getMkDirMethod) == null) {
          FileSystemGrpc.getMkDirMethod = getMkDirMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "mkDir"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("mkDir"))
                  .build();
          }
        }
     }
     return getMkDirMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LongArg,
      GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> getGetAttrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAttr",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.LongArg.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LongArg,
      GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> getGetAttrMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LongArg, GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> getGetAttrMethod;
    if ((getGetAttrMethod = FileSystemGrpc.getGetAttrMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getGetAttrMethod = FileSystemGrpc.getGetAttrMethod) == null) {
          FileSystemGrpc.getGetAttrMethod = getGetAttrMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.LongArg, GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "getAttr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.LongArg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("getAttr"))
                  .build();
          }
        }
     }
     return getGetAttrMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FileSystemStub newStub(io.grpc.Channel channel) {
    return new FileSystemStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FileSystemBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FileSystemBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FileSystemFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FileSystemFutureStub(channel);
  }

  /**
   */
  public static abstract class FileSystemImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *    rpc mkFile (pathOnlyArgs) returns (BooleanMessage) {}
     * </pre>
     */
    public void rmFile(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getRmFileMethod(), responseObserver);
    }

    /**
     */
    public void rmDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getRmDirMethod(), responseObserver);
    }

    /**
     */
    public void mkDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getMkDirMethod(), responseObserver);
    }

    /**
     */
    public void getAttr(GrupoA.StorageController.gRPCService.FileSystem.LongArg request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAttrMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRmFileMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
                GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>(
                  this, METHODID_RM_FILE)))
          .addMethod(
            getRmDirMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
                GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>(
                  this, METHODID_RM_DIR)))
          .addMethod(
            getMkDirMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
                GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>(
                  this, METHODID_MK_DIR)))
          .addMethod(
            getGetAttrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.LongArg,
                GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes>(
                  this, METHODID_GET_ATTR)))
          .build();
    }
  }

  /**
   */
  public static final class FileSystemStub extends io.grpc.stub.AbstractStub<FileSystemStub> {
    private FileSystemStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileSystemStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileSystemStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileSystemStub(channel, callOptions);
    }

    /**
     * <pre>
     *    rpc mkFile (pathOnlyArgs) returns (BooleanMessage) {}
     * </pre>
     */
    public void rmFile(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRmFileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rmDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRmDirMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void mkDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMkDirMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAttr(GrupoA.StorageController.gRPCService.FileSystem.LongArg request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAttrMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FileSystemBlockingStub extends io.grpc.stub.AbstractStub<FileSystemBlockingStub> {
    private FileSystemBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileSystemBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileSystemBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileSystemBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *    rpc mkFile (pathOnlyArgs) returns (BooleanMessage) {}
     * </pre>
     */
    public GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage rmFile(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return blockingUnaryCall(
          getChannel(), getRmFileMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage rmDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return blockingUnaryCall(
          getChannel(), getRmDirMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage mkDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return blockingUnaryCall(
          getChannel(), getMkDirMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes getAttr(GrupoA.StorageController.gRPCService.FileSystem.LongArg request) {
      return blockingUnaryCall(
          getChannel(), getGetAttrMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FileSystemFutureStub extends io.grpc.stub.AbstractStub<FileSystemFutureStub> {
    private FileSystemFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileSystemFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileSystemFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileSystemFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *    rpc mkFile (pathOnlyArgs) returns (BooleanMessage) {}
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> rmFile(
        GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getRmFileMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> rmDir(
        GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getRmDirMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> mkDir(
        GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getMkDirMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> getAttr(
        GrupoA.StorageController.gRPCService.FileSystem.LongArg request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAttrMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RM_FILE = 0;
  private static final int METHODID_RM_DIR = 1;
  private static final int METHODID_MK_DIR = 2;
  private static final int METHODID_GET_ATTR = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FileSystemImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FileSystemImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RM_FILE:
          serviceImpl.rmFile((GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>) responseObserver);
          break;
        case METHODID_RM_DIR:
          serviceImpl.rmDir((GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>) responseObserver);
          break;
        case METHODID_MK_DIR:
          serviceImpl.mkDir((GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>) responseObserver);
          break;
        case METHODID_GET_ATTR:
          serviceImpl.getAttr((GrupoA.StorageController.gRPCService.FileSystem.LongArg) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes>) responseObserver);
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

  private static abstract class FileSystemBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FileSystemBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GrupoA.StorageController.gRPCService.FileSystem.FileSystemProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FileSystem");
    }
  }

  private static final class FileSystemFileDescriptorSupplier
      extends FileSystemBaseDescriptorSupplier {
    FileSystemFileDescriptorSupplier() {}
  }

  private static final class FileSystemMethodDescriptorSupplier
      extends FileSystemBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FileSystemMethodDescriptorSupplier(String methodName) {
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
      synchronized (FileSystemGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FileSystemFileDescriptorSupplier())
              .addMethod(getRmFileMethod())
              .addMethod(getRmDirMethod())
              .addMethod(getMkDirMethod())
              .addMethod(getGetAttrMethod())
              .build();
        }
      }
    }
    return result;
  }
}
