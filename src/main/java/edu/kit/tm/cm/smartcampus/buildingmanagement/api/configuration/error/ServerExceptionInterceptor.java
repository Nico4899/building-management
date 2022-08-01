package edu.kit.tm.cm.smartcampus.buildingmanagement.api.configuration.error;

import com.google.rpc.ErrorInfo;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions.InternalServerErrorException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions.ResourceNotFoundException;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
   * This method provides a proper response on {@link InvalidArgumentsException} thrown, it provides
   * a {@link Status#INVALID_ARGUMENT} and the exception message. Cause and error code are also
   * given, the error is being sent as {@link StatusRuntimeException} for the grpc client.
   *
   * @param exception thrown exception
   * @return a proper {@link StatusRuntimeException}
   */
  @GrpcExceptionHandler(InvalidArgumentsException.class)
  public StatusRuntimeException onError(InvalidArgumentsException exception) {
    Metadata trailers = new Metadata();
    ErrorInfo errorInfo = ErrorInfo.newBuilder().setReason(exception.getMessage()).build();
    Metadata.Key<ErrorInfo> errorInfoTrailerKey = ProtoUtils.keyForProto(errorInfo);
    trailers.put(errorInfoTrailerKey, errorInfo);
    return Status.INVALID_ARGUMENT.withCause(exception).asRuntimeException(trailers);
  }

  /**
   * This method provides a proper response on {@link ResourceNotFoundException} thrown, it provides
   * a {@link Status#NOT_FOUND} and the exception message. Cause and error code are also * given,
   * the error is being sent as {@link StatusRuntimeException} for the grpc client.
   *
   * @param exception thrown exception
   * @return a proper {@link StatusRuntimeException}
   */
  @GrpcExceptionHandler(ResourceNotFoundException.class)
  public StatusRuntimeException onError(ResourceNotFoundException exception) {
    Metadata trailers = new Metadata();
    ErrorInfo errorInfo = ErrorInfo.newBuilder().setReason(exception.getMessage()).build();
    Metadata.Key<ErrorInfo> errorInfoTrailerKey = ProtoUtils.keyForProto(errorInfo);
    trailers.put(errorInfoTrailerKey, errorInfo);
    return Status.NOT_FOUND.withCause(exception).asRuntimeException(trailers);
  }

  /**
   * This method provides a proper response on {@link InternalServerErrorException} thrown, it
   * provides a {@link Status#INTERNAL} and the exception message. Cause and error code are also *
   * given, the error is being sent as {@link StatusRuntimeException} for the grpc client.
   *
   * @param exception thrown exception
   * @return a proper {@link StatusRuntimeException}
   */
  @GrpcExceptionHandler(InternalServerErrorException.class)
  public StatusRuntimeException onError(InternalServerErrorException exception) {
    Metadata trailers = new Metadata();
    ErrorInfo errorInfo = ErrorInfo.newBuilder().setReason(exception.getMessage()).build();
    Metadata.Key<ErrorInfo> errorInfoTrailerKey = ProtoUtils.keyForProto(errorInfo);
    trailers.put(errorInfoTrailerKey, errorInfo);
    return Status.INTERNAL.withCause(exception).asRuntimeException(trailers);
  }

  /**
   * This method provides a proper response on all other {@link Exception} thrown, it provides a
   * {@link Status#UNKNOWN} and the exception message. Cause and error code are also * given, the
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
