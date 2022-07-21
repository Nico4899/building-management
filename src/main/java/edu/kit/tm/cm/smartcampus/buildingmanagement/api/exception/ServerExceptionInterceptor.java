package edu.kit.tm.cm.smartcampus.buildingmanagement.api.exception;

import com.google.rpc.ErrorInfo;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InternalServerErrorException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.UnauthorizedAccessException;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ServerExceptionInterceptor {

  @GrpcExceptionHandler(InvalidArgumentsException.class)
  public StatusRuntimeException onError(IllegalArgumentException exception) {
    Metadata trailers = new Metadata();
    ErrorInfo errorInfo = ErrorInfo.newBuilder().setReason(exception.getMessage()).build();
    Metadata.Key<ErrorInfo> errorInfoTrailerKey = ProtoUtils.keyForProto(errorInfo);
    trailers.put(errorInfoTrailerKey, errorInfo);
    return Status.INVALID_ARGUMENT.withCause(exception).asRuntimeException(trailers);
  }

  @GrpcExceptionHandler(ResourceNotFoundException.class)
  public StatusRuntimeException onError(ResourceNotFoundException exception) {
    Metadata trailers = new Metadata();
    ErrorInfo errorInfo = ErrorInfo.newBuilder().setReason(exception.getMessage()).build();
    Metadata.Key<ErrorInfo> errorInfoTrailerKey = ProtoUtils.keyForProto(errorInfo);
    trailers.put(errorInfoTrailerKey, errorInfo);
    return Status.NOT_FOUND.withCause(exception).asRuntimeException(trailers);
  }

  @GrpcExceptionHandler(UnauthorizedAccessException.class)
  public StatusRuntimeException onError(UnauthorizedAccessException exception) {
    Metadata trailers = new Metadata();
    ErrorInfo errorInfo = ErrorInfo.newBuilder().setReason(exception.getMessage()).build();
    Metadata.Key<ErrorInfo> errorInfoTrailerKey = ProtoUtils.keyForProto(errorInfo);
    trailers.put(errorInfoTrailerKey, errorInfo);
    return Status.UNAUTHENTICATED.withCause(exception).asRuntimeException(trailers);
  }

  @GrpcExceptionHandler(InternalServerErrorException.class)
  public StatusRuntimeException onError(InternalServerErrorException exception) {
    Metadata trailers = new Metadata();
    ErrorInfo errorInfo = ErrorInfo.newBuilder().setReason(exception.getMessage()).build();
    Metadata.Key<ErrorInfo> errorInfoTrailerKey = ProtoUtils.keyForProto(errorInfo);
    trailers.put(errorInfoTrailerKey, errorInfo);
    return Status.INTERNAL.withCause(exception).asRuntimeException(trailers);
  }

  @GrpcExceptionHandler(Exception.class)
  public StatusRuntimeException onError(Exception exception) {
    return Status.UNKNOWN
        .withDescription(exception.getMessage())
        .withCause(exception)
        .asRuntimeException();
  }
}
