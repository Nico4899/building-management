package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions;

import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * This exception is thrown whenever invalid arguments are found, it contains a proper error
 * message.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
@NoArgsConstructor
public class InvalidArgumentsException extends RuntimeException {

  private static final String INVALID_ARGUMENTS_EXCEPTION_MESSAGE =
      "Arguments are Invalid! Please check the following: %s";

  /**
   * Constructs a new {@link InvalidArgumentsException}.
   *
   * @param message the message provided
   */
  public InvalidArgumentsException(String message) {
    super(message);
  }

  /**
   * Constructs a new {@link InvalidArgumentsException}.
   *
   * @param messages the messages for each invalid argument
   */
  public InvalidArgumentsException(String[] messages) {
    super(String.format(INVALID_ARGUMENTS_EXCEPTION_MESSAGE, Arrays.toString(messages)));
  }
}
