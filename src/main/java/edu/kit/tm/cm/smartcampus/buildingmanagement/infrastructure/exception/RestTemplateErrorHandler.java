package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

/**
 * This class represents a custom REST error handler. It throws custom exceptions on error codes in
 * building management context.
 */
public class RestTemplateErrorHandler implements ResponseErrorHandler {

  private static final int REQUESTED_DATA_DOESNT_EXIST = 404;

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {

    return (response.getStatusCode().series() == CLIENT_ERROR
        || response.getStatusCode().series() == SERVER_ERROR);
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
      if (response.getRawStatusCode() == REQUESTED_DATA_DOESNT_EXIST) {
        throw new ResourceNotFoundException();
      }
  }
}
