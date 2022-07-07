package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception;

/**
 * Exception thrown on invalid arguments.
 */
public class InvalidArgumentsException extends RuntimeException {

  private static final String INVALID_ARGUMENTS_EXCEPTION_MESSAGE =
      "Arguments are invalid for %s request. Please check and retry! ";

  public InvalidArgumentsException() {
    super(INVALID_ARGUMENTS_EXCEPTION_MESSAGE);
  }
}
