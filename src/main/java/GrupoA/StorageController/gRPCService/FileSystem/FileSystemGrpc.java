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
      GrupoA.StorageController.gRPCService.FileSystem.IntArg> getRmFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rmFile",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.IntArg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.IntArg> getRmFileMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.IntArg> getRmFileMethod;
    if ((getRmFileMethod = FileSystemGrpc.getRmFileMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getRmFileMethod = FileSystemGrpc.getRmFileMethod) == null) {
          FileSystemGrpc.getRmFileMethod = getRmFileMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.IntArg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "rmFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.IntArg.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("rmFile"))
                  .build();
          }
        }
     }
     return getRmFileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.IntArg> getRmDirMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rmDir",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.IntArg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.IntArg> getRmDirMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.IntArg> getRmDirMethod;
    if ((getRmDirMethod = FileSystemGrpc.getRmDirMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getRmDirMethod = FileSystemGrpc.getRmDirMethod) == null) {
          FileSystemGrpc.getRmDirMethod = getRmDirMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.IntArg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "rmDir"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.IntArg.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("rmDir"))
                  .build();
          }
        }
     }
     return getRmDirMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> getGetAttrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAttr",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> getGetAttrMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> getGetAttrMethod;
    if ((getGetAttrMethod = FileSystemGrpc.getGetAttrMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getGetAttrMethod = FileSystemGrpc.getGetAttrMethod) == null) {
          FileSystemGrpc.getGetAttrMethod = getGetAttrMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "getAttr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("getAttr"))
                  .build();
          }
        }
     }
     return getGetAttrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute,
      GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getSetAttrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setAttr",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute,
      GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getSetAttrMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute, GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getSetAttrMethod;
    if ((getSetAttrMethod = FileSystemGrpc.getSetAttrMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getSetAttrMethod = FileSystemGrpc.getSetAttrMethod) == null) {
          FileSystemGrpc.getSetAttrMethod = getSetAttrMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute, GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "setAttr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("setAttr"))
                  .build();
          }
        }
     }
     return getSetAttrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.DirContents> getReadDirMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "readDir",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.DirContents.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
      GrupoA.StorageController.gRPCService.FileSystem.DirContents> getReadDirMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.DirContents> getReadDirMethod;
    if ((getReadDirMethod = FileSystemGrpc.getReadDirMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getReadDirMethod = FileSystemGrpc.getReadDirMethod) == null) {
          FileSystemGrpc.getReadDirMethod = getReadDirMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs, GrupoA.StorageController.gRPCService.FileSystem.DirContents>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "readDir"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.DirContents.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("readDir"))
                  .build();
          }
        }
     }
     return getReadDirMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.NodeArgs,
      GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getCreateNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateNode",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.NodeArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.NodeArgs,
      GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getCreateNodeMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.NodeArgs, GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> getCreateNodeMethod;
    if ((getCreateNodeMethod = FileSystemGrpc.getCreateNodeMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getCreateNodeMethod = FileSystemGrpc.getCreateNodeMethod) == null) {
          FileSystemGrpc.getCreateNodeMethod = getCreateNodeMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.NodeArgs, GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "CreateNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.NodeArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("CreateNode"))
                  .build();
          }
        }
     }
     return getCreateNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LockArgs,
      GrupoA.StorageController.gRPCService.FileSystem.LockResponse> getSetLockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetLock",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.LockArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.LockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LockArgs,
      GrupoA.StorageController.gRPCService.FileSystem.LockResponse> getSetLockMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LockArgs, GrupoA.StorageController.gRPCService.FileSystem.LockResponse> getSetLockMethod;
    if ((getSetLockMethod = FileSystemGrpc.getSetLockMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getSetLockMethod = FileSystemGrpc.getSetLockMethod) == null) {
          FileSystemGrpc.getSetLockMethod = getSetLockMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.LockArgs, GrupoA.StorageController.gRPCService.FileSystem.LockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "SetLock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.LockArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.LockResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("SetLock"))
                  .build();
          }
        }
     }
     return getSetLockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LockArgs,
      GrupoA.StorageController.gRPCService.FileSystem.LockResponse> getUpdateLockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateLock",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.LockArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.LockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LockArgs,
      GrupoA.StorageController.gRPCService.FileSystem.LockResponse> getUpdateLockMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LockArgs, GrupoA.StorageController.gRPCService.FileSystem.LockResponse> getUpdateLockMethod;
    if ((getUpdateLockMethod = FileSystemGrpc.getUpdateLockMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getUpdateLockMethod = FileSystemGrpc.getUpdateLockMethod) == null) {
          FileSystemGrpc.getUpdateLockMethod = getUpdateLockMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.LockArgs, GrupoA.StorageController.gRPCService.FileSystem.LockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "UpdateLock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.LockArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.LockResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("UpdateLock"))
                  .build();
          }
        }
     }
     return getUpdateLockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LockArgs,
      GrupoA.StorageController.gRPCService.FileSystem.LockResponse> getReleaseLockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReleaseLock",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.LockArgs.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.LockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LockArgs,
      GrupoA.StorageController.gRPCService.FileSystem.LockResponse> getReleaseLockMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.LockArgs, GrupoA.StorageController.gRPCService.FileSystem.LockResponse> getReleaseLockMethod;
    if ((getReleaseLockMethod = FileSystemGrpc.getReleaseLockMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getReleaseLockMethod = FileSystemGrpc.getReleaseLockMethod) == null) {
          FileSystemGrpc.getReleaseLockMethod = getReleaseLockMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.LockArgs, GrupoA.StorageController.gRPCService.FileSystem.LockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "ReleaseLock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.LockArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.LockResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("ReleaseLock"))
                  .build();
          }
        }
     }
     return getReleaseLockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage,
      GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse> getGetLatestMapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLatestMap",
      requestType = GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage.class,
      responseType = GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage,
      GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse> getGetLatestMapMethod() {
    io.grpc.MethodDescriptor<GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage, GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse> getGetLatestMapMethod;
    if ((getGetLatestMapMethod = FileSystemGrpc.getGetLatestMapMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getGetLatestMapMethod = FileSystemGrpc.getGetLatestMapMethod) == null) {
          FileSystemGrpc.getGetLatestMapMethod = getGetLatestMapMethod = 
              io.grpc.MethodDescriptor.<GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage, GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrupoA.StorageController.gRPCService.FileSystem.FileSystem", "GetLatestMap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("GetLatestMap"))
                  .build();
          }
        }
     }
     return getGetLatestMapMethod;
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
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.IntArg> responseObserver) {
      asyncUnimplementedUnaryCall(getRmFileMethod(), responseObserver);
    }

    /**
     */
    public void rmDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.IntArg> responseObserver) {
      asyncUnimplementedUnaryCall(getRmDirMethod(), responseObserver);
    }

    /**
     */
    public void getAttr(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAttrMethod(), responseObserver);
    }

    /**
     */
    public void setAttr(GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getSetAttrMethod(), responseObserver);
    }

    /**
     */
    public void readDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.DirContents> responseObserver) {
      asyncUnimplementedUnaryCall(getReadDirMethod(), responseObserver);
    }

    /**
     */
    public void createNode(GrupoA.StorageController.gRPCService.FileSystem.NodeArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateNodeMethod(), responseObserver);
    }

    /**
     */
    public void setLock(GrupoA.StorageController.gRPCService.FileSystem.LockArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.LockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetLockMethod(), responseObserver);
    }

    /**
     */
    public void updateLock(GrupoA.StorageController.gRPCService.FileSystem.LockArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.LockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateLockMethod(), responseObserver);
    }

    /**
     */
    public void releaseLock(GrupoA.StorageController.gRPCService.FileSystem.LockArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.LockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReleaseLockMethod(), responseObserver);
    }

    /**
     */
    public void getLatestMap(GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetLatestMapMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRmFileMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
                GrupoA.StorageController.gRPCService.FileSystem.IntArg>(
                  this, METHODID_RM_FILE)))
          .addMethod(
            getRmDirMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
                GrupoA.StorageController.gRPCService.FileSystem.IntArg>(
                  this, METHODID_RM_DIR)))
          .addMethod(
            getGetAttrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
                GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes>(
                  this, METHODID_GET_ATTR)))
          .addMethod(
            getSetAttrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute,
                GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>(
                  this, METHODID_SET_ATTR)))
          .addMethod(
            getReadDirMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs,
                GrupoA.StorageController.gRPCService.FileSystem.DirContents>(
                  this, METHODID_READ_DIR)))
          .addMethod(
            getCreateNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.NodeArgs,
                GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>(
                  this, METHODID_CREATE_NODE)))
          .addMethod(
            getSetLockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.LockArgs,
                GrupoA.StorageController.gRPCService.FileSystem.LockResponse>(
                  this, METHODID_SET_LOCK)))
          .addMethod(
            getUpdateLockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.LockArgs,
                GrupoA.StorageController.gRPCService.FileSystem.LockResponse>(
                  this, METHODID_UPDATE_LOCK)))
          .addMethod(
            getReleaseLockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.LockArgs,
                GrupoA.StorageController.gRPCService.FileSystem.LockResponse>(
                  this, METHODID_RELEASE_LOCK)))
          .addMethod(
            getGetLatestMapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage,
                GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse>(
                  this, METHODID_GET_LATEST_MAP)))
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
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.IntArg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRmFileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rmDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.IntArg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRmDirMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAttr(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAttrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setAttr(GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetAttrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void readDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.DirContents> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReadDirMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createNode(GrupoA.StorageController.gRPCService.FileSystem.NodeArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setLock(GrupoA.StorageController.gRPCService.FileSystem.LockArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.LockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetLockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateLock(GrupoA.StorageController.gRPCService.FileSystem.LockArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.LockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateLockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void releaseLock(GrupoA.StorageController.gRPCService.FileSystem.LockArgs request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.LockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReleaseLockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLatestMap(GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage request,
        io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetLatestMapMethod(), getCallOptions()), request, responseObserver);
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
    public GrupoA.StorageController.gRPCService.FileSystem.IntArg rmFile(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return blockingUnaryCall(
          getChannel(), getRmFileMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.IntArg rmDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return blockingUnaryCall(
          getChannel(), getRmDirMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes getAttr(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return blockingUnaryCall(
          getChannel(), getGetAttrMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage setAttr(GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute request) {
      return blockingUnaryCall(
          getChannel(), getSetAttrMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.DirContents readDir(GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return blockingUnaryCall(
          getChannel(), getReadDirMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage createNode(GrupoA.StorageController.gRPCService.FileSystem.NodeArgs request) {
      return blockingUnaryCall(
          getChannel(), getCreateNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.LockResponse setLock(GrupoA.StorageController.gRPCService.FileSystem.LockArgs request) {
      return blockingUnaryCall(
          getChannel(), getSetLockMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.LockResponse updateLock(GrupoA.StorageController.gRPCService.FileSystem.LockArgs request) {
      return blockingUnaryCall(
          getChannel(), getUpdateLockMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.LockResponse releaseLock(GrupoA.StorageController.gRPCService.FileSystem.LockArgs request) {
      return blockingUnaryCall(
          getChannel(), getReleaseLockMethod(), getCallOptions(), request);
    }

    /**
     */
    public GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse getLatestMap(GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetLatestMapMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.IntArg> rmFile(
        GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getRmFileMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.IntArg> rmDir(
        GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getRmDirMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes> getAttr(
        GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAttrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> setAttr(
        GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute request) {
      return futureUnaryCall(
          getChannel().newCall(getSetAttrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.DirContents> readDir(
        GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getReadDirMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage> createNode(
        GrupoA.StorageController.gRPCService.FileSystem.NodeArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.LockResponse> setLock(
        GrupoA.StorageController.gRPCService.FileSystem.LockArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getSetLockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.LockResponse> updateLock(
        GrupoA.StorageController.gRPCService.FileSystem.LockArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateLockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.LockResponse> releaseLock(
        GrupoA.StorageController.gRPCService.FileSystem.LockArgs request) {
      return futureUnaryCall(
          getChannel().newCall(getReleaseLockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse> getLatestMap(
        GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetLatestMapMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RM_FILE = 0;
  private static final int METHODID_RM_DIR = 1;
  private static final int METHODID_GET_ATTR = 2;
  private static final int METHODID_SET_ATTR = 3;
  private static final int METHODID_READ_DIR = 4;
  private static final int METHODID_CREATE_NODE = 5;
  private static final int METHODID_SET_LOCK = 6;
  private static final int METHODID_UPDATE_LOCK = 7;
  private static final int METHODID_RELEASE_LOCK = 8;
  private static final int METHODID_GET_LATEST_MAP = 9;

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
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.IntArg>) responseObserver);
          break;
        case METHODID_RM_DIR:
          serviceImpl.rmDir((GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.IntArg>) responseObserver);
          break;
        case METHODID_GET_ATTR:
          serviceImpl.getAttr((GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes>) responseObserver);
          break;
        case METHODID_SET_ATTR:
          serviceImpl.setAttr((GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>) responseObserver);
          break;
        case METHODID_READ_DIR:
          serviceImpl.readDir((GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.DirContents>) responseObserver);
          break;
        case METHODID_CREATE_NODE:
          serviceImpl.createNode((GrupoA.StorageController.gRPCService.FileSystem.NodeArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage>) responseObserver);
          break;
        case METHODID_SET_LOCK:
          serviceImpl.setLock((GrupoA.StorageController.gRPCService.FileSystem.LockArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.LockResponse>) responseObserver);
          break;
        case METHODID_UPDATE_LOCK:
          serviceImpl.updateLock((GrupoA.StorageController.gRPCService.FileSystem.LockArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.LockResponse>) responseObserver);
          break;
        case METHODID_RELEASE_LOCK:
          serviceImpl.releaseLock((GrupoA.StorageController.gRPCService.FileSystem.LockArgs) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.LockResponse>) responseObserver);
          break;
        case METHODID_GET_LATEST_MAP:
          serviceImpl.getLatestMap((GrupoA.StorageController.gRPCService.FileSystem.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse>) responseObserver);
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
              .addMethod(getGetAttrMethod())
              .addMethod(getSetAttrMethod())
              .addMethod(getReadDirMethod())
              .addMethod(getCreateNodeMethod())
              .addMethod(getSetLockMethod())
              .addMethod(getUpdateLockMethod())
              .addMethod(getReleaseLockMethod())
              .addMethod(getGetLatestMapMethod())
              .build();
        }
      }
    }
    return result;
  }
}
