package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.validator;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions.ResourceNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents a parent class validator for any given attribute constraints. In case of
 * invalid arguments, it throws {@link InvalidArgumentsException} and in case of nonexistence of
 * given objects in the database, it throws {@link ResourceNotFoundException}.
 *
 * @param <T> the type of which this validator validates objects
 */
public abstract class Validator<T> {

  // public constants
  /** The constant IDENTIFICATION_NUMBER_NAME. */
  public static final String IDENTIFICATION_NUMBER_NAME = "identification_number";

  /** The constant RIN_PATTERN. */
  public static final String RIN_PATTERN = "^r-\\d+$";

  /** The constant FIN_PATTERN. */
  public static final String FIN_PATTERN = "^f-\\d+$";

  /** The constant BIN_PATTERN. */
  public static final String BIN_PATTERN = "^b-\\d+$";

  /** The constant CIN_PATTERN. */
  public static final String CIN_PATTERN = "^c-\\d+$";

  /** The constant REFERENCE_IDENTIFICATION_NUMBER_NAME. */
  public static final String REFERENCE_IDENTIFICATION_NUMBER_NAME =
      "reference_identification_number";

  /** The constant OWNER_NAME. */
  public static final String OWNER_NAME = "owner";

  /** The constant BIN_RIN_CIN_PATTERN. */
  public static final String BIN_RIN_CIN_PATTERN =
      BIN_PATTERN + "|" + RIN_PATTERN + "|" + CIN_PATTERN;

  // private constants
  private static final String NULL = "null";
  private static final String SHOULD_NOT_BE_NULL_MESSAGE = "should not be null";
  private static final String SHOULD_NOT_BE_EMPTY_MESSAGE = "should not be empty";
  private static final String SHOULD_MATCH_MESSAGE = "should match: ";

  private final FavoriteRepository favoriteRepository;

  /**
   * Instantiates a new Validator.
   *
   * @param favoriteRepository the favorite repository
   */
  @Autowired
  protected Validator(FavoriteRepository favoriteRepository) {
    this.favoriteRepository = favoriteRepository;
  }

  /**
   * Validates weather objects are not null or not.
   *
   * @param objects Map of objects to be checked and their names (key=name, value=object)
   */
  protected void validateNotNull(Map<String, Object> objects) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
        new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, Object> entry : objects.entrySet()) {
      if (entry.getValue() == null) {
        invalidArgumentsStringBuilder.appendMessage(
            entry.getKey(), NULL, SHOULD_NOT_BE_NULL_MESSAGE, true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validates weather Strings are not empty or not.
   *
   * @param strings Map of strings to be checked and their names (key=name, value=string)
   */
  protected void validateNotEmpty(Map<String, String> strings) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
        new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, String> entry : strings.entrySet()) {
      if (entry.getValue().isEmpty()) {
        invalidArgumentsStringBuilder.appendMessage(
            entry.getKey(), entry.getValue(), SHOULD_NOT_BE_EMPTY_MESSAGE, true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validates weather Strings match given regexes or not.
   *
   * @param strings Map of strings and their regexes to be checked and their names (key=name,
   *     value=pair of string and regex)
   */
  protected void validateMatchesRegex(Map<String, Pair<String, String>> strings) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
        new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, Pair<String, String>> entry : strings.entrySet()) {
      if (!entry.getValue().getFirst().matches(entry.getValue().getSecond())) {
        invalidArgumentsStringBuilder.appendMessage(
            entry.getKey(),
            entry.getValue().getFirst(),
            SHOULD_MATCH_MESSAGE + entry.getValue().getSecond(),
            true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validate if entity exists.
   *
   * @param inputIdentificationNumber the input identification number
   * @param name the name of the given value
   */
  protected void validateExists(String inputIdentificationNumber, String name) {
    if (!favoriteRepository.existsById(inputIdentificationNumber)) {
      throw new ResourceNotFoundException(name, inputIdentificationNumber);
    }
  }

  /**
   * Validate a given identification number for requests containing only the identification number.
   *
   * @param identificationNumber the identification number
   */
  public void validate(String identificationNumber) {
    validateNotNull(Map.of(IDENTIFICATION_NUMBER_NAME, identificationNumber));
    validateMatchesRegex(
        Map.of(IDENTIFICATION_NUMBER_NAME, Pair.of(identificationNumber, getValidateRegex())));
    validateExists(identificationNumber, IDENTIFICATION_NUMBER_NAME);
  }

  /**
   * Gets validate regex for the {@link Validator#validate(String)} method.
   *
   * @return the validate regex
   */
  protected abstract String getValidateRegex();

  /**
   * Validate create operation.
   *
   * @param object the object to be validated
   */
  public abstract void validateCreate(T object);

  @NoArgsConstructor
  private static class InvalidArgumentsStringBuilder {

    private static final String COLON = ": ";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final String COMMA = ", ";

    List<String> values = new ArrayList<>();
    /**
     * Append error message.
     *
     * @param name the name
     * @param input the input
     * @param hint the hint
     * @param hasHint if a hint is provided
     */
    public void appendMessage(String name, String input, String hint, boolean hasHint) {
      if (values.isEmpty()) {
        if (hasHint) {
          appendFirstIterationWithHint(name, input, hint);
        } else {
          appendFirstIterationWithoutHint(name, input);
        }
      } else if (hasHint) {
        appendWithHint(name, input, hint);
      } else {
        appendWithoutHint(name, input);
      }
    }

    /**
     * Build error message string.
     *
     * @return the built string
     */
    public String[] build() {
      return values.toArray(new String[0]);
    }

    private void appendWithHint(String name, String input, String hint) {
      values.add(COMMA + name + COLON + input + LEFT_PARENTHESIS + hint + RIGHT_PARENTHESIS);
    }

    private void appendWithoutHint(String name, String input) {
      values.add(COMMA + name + COLON + input);
    }

    private void appendFirstIterationWithoutHint(String name, String input) {
      values.add(name + COLON + input);
    }

    private void appendFirstIterationWithHint(String name, String input, String hint) {
      values.add(name + COLON + input + LEFT_PARENTHESIS + hint + RIGHT_PARENTHESIS);
    }
  }
}
