package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/** Exception thrown on invalid arguments. */
@AllArgsConstructor
@NoArgsConstructor
public class InvalidArgumentsException extends RuntimeException {

  public static final String COLON = ": ";
  public static final String LEFT_PARENTHESIS = "(";
  public static final String RIGHT_PARENTHESIS = ")";
  public static final String COMMA = ", ";
  private static final String INVALID_ARGUMENTS_EXCEPTION_MESSAGE =
      "Arguments are Invalid! Please check the following: %s";
  private String invalidArguments;

  /**
   * Constructs a new invalid arguments exception
   *
   * @param name parameter name
   * @param input input value
   * @param hint error hint
   * @param hasHint has a hint or not
   */
  public InvalidArgumentsException(String name, String input, String hint, boolean hasHint) {
    super();
    this.appendWrongArguments(name, input, hint, hasHint);
  }

  public void appendWrongArguments(String name, String input, String hint, boolean hasHint) {
    if (invalidArguments.isBlank()) {
      if (hasHint) {
        invalidArguments = name + COLON + input + LEFT_PARENTHESIS + hint + RIGHT_PARENTHESIS;
      } else {
        invalidArguments = name + COLON + input;
      }
    }
    if (hasHint) {
      invalidArguments +=
          invalidArguments
              + COMMA
              + name
              + COLON
              + input
              + LEFT_PARENTHESIS
              + hint
              + RIGHT_PARENTHESIS;
    } else {
      invalidArguments += invalidArguments + COMMA + name + COLON + input;
    }
  }

  @Override
  public String getMessage() {
    return String.format(INVALID_ARGUMENTS_EXCEPTION_MESSAGE, invalidArguments);
  }
}
