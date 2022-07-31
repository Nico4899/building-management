package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions.InternalServerErrorException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions.ResourceNotFoundException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

/**
 * This class represents a custom REST error handler. It throws custom exceptions on error codes in
 * building management context.
 */
public class ClientExceptionInterceptor implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {

    return (response.getStatusCode().series() == CLIENT_ERROR
        || response.getStatusCode().series() == SERVER_ERROR);
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    switch (response.getStatusCode()) {
      case NOT_FOUND -> throw new ResourceNotFoundException(response.getStatusText());
      case BAD_REQUEST -> throw new InvalidArgumentsException(response.getStatusCode().toString());
      case INTERNAL_SERVER_ERROR -> throw new InternalServerErrorException(response.getStatusText());
      default -> throw new IOException(response.getStatusText());
    }
  }
}
