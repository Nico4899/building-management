package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception;

/** The type Unauthorized access exception. */
public class UnauthorizedAccessException extends RuntimeException {
  /**
   * Instantiates a new Unauthorized access exception.
   *
   * @param message the message
   */
  public UnauthorizedAccessException(String message) {
    super(message);
  }
}
