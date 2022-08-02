package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions;

/**
 * This exception is thrown whenever some internal server error is captured, it contains a proper
 * error message.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
public class InternalServerErrorException extends RuntimeException {

  /**
   * Constructs a new {@link InternalServerErrorException}.
   *
   * @param message error message
   */
  public InternalServerErrorException(String message) {
    super(message);
  }
}
