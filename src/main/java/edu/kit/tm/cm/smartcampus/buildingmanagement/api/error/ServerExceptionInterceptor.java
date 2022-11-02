package edu.kit.tm.cm.smartcampus.buildingmanagement.api.error;

import com.google.rpc.ErrorInfo;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This class represents a server exception interceptor, it intercepts on exceptions annotated with
 * {@link GrpcExceptionHandler} with proper {@link Status} and the exception message. The {@link
 * GrpcAdvice} annotation makes this interceptor global this removes the need of other exception
 * handling. It is an adapted spring implementation and is based on {@link ControllerAdvice} and
 * {@link ExceptionHandler}. Methods annotated with {@link GrpcExceptionHandler} will intercept on
 * given exceptions thrown and will return proper error codes within the {@link Status} of the
 * provided {@link StatusRuntimeException}.
 */
@GrpcAdvice
public class ServerExceptionInterceptor {

  /**
   * This method provides a proper response on {@link IllegalArgumentException} thrown, it provides
   * a {@link Status#INVALID_ARGUMENT} and the exception message. Cause and error code are also
   * given, the error is being sent as {@link StatusRuntimeException} for the grpc client.
   *
   * @param exception thrown exception
   * @return a proper {@link StatusRuntimeException}
   */
  @GrpcExceptionHandler(IllegalArgumentException.class)
  public StatusRuntimeException onError(IllegalArgumentException exception) {
    Metadata trailers = new Metadata();
    ErrorInfo errorInfo = ErrorInfo.newBuilder().build();
    Metadata.Key<ErrorInfo> errorInfoTrailerKey = ProtoUtils.keyForProto(errorInfo);
    trailers.put(errorInfoTrailerKey, errorInfo);
    return Status.INVALID_ARGUMENT.withCause(exception).asRuntimeException(trailers);
  }

  /**
   * This method provides a proper response on {@link NoSuchElementException} thrown, it provides a
   * {@link Status#NOT_FOUND} and the exception message. Cause and error code are also * given, the
   * error is being sent as {@link StatusRuntimeException} for the grpc client.
   *
   * @param exception thrown exception
   * @return a proper {@link StatusRuntimeException}
   */
  @GrpcExceptionHandler(NoSuchElementException.class)
  public StatusRuntimeException onError(NoSuchElementException exception) {
    Metadata trailers = new Metadata();
    ErrorInfo errorInfo = ErrorInfo.newBuilder().build();
    Metadata.Key<ErrorInfo> errorInfoTrailerKey = ProtoUtils.keyForProto(errorInfo);
    trailers.put(errorInfoTrailerKey, errorInfo);
    return Status.NOT_FOUND.withCause(exception).asRuntimeException(trailers);
  }

  /**
   * This method provides a proper response on {@link InternalError} thrown, it provides a {@link
   * Status#INTERNAL} and the exception message. Cause and error code are also given, the error is
   * being sent as {@link StatusRuntimeException} for the grpc client.
   *
   * @param exception thrown exception
   * @return a proper {@link StatusRuntimeException}
   */
  @GrpcExceptionHandler(InternalError.class)
  public StatusRuntimeException onError(InternalError exception) {
    Metadata trailers = new Metadata();
    ErrorInfo errorInfo = ErrorInfo.newBuilder().build();
    Metadata.Key<ErrorInfo> errorInfoTrailerKey = ProtoUtils.keyForProto(errorInfo);
    trailers.put(errorInfoTrailerKey, errorInfo);
    return Status.INTERNAL.withCause(exception).asRuntimeException(trailers);
  }

  /**
   * This method provides a proper response on all other {@link Exception} thrown, it provides a
   * {@link Status#UNKNOWN} and the exception message. Cause and error code are also given, the
   * error is being sent as {@link StatusRuntimeException} for the grpc client.
   *
   * @param exception thrown exception
   * @return a proper {@link StatusRuntimeException}
   */
  @GrpcExceptionHandler(Exception.class)
  public StatusRuntimeException onError(Exception exception) {
    return Status.UNKNOWN
        .withDescription(exception.getMessage())
        .withCause(exception)
        .asRuntimeException();
  }
}
