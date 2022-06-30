package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exceptions.InvalidIdentificationNumberException;

/**
 * This record describes a unique identification number of a unit.
 * <p>
 * it can have the following formats:
 * </p>
 * <li>
 * building identification number: "b-(int)"
 * </li>
 * <li>
 * room identification number: "r-(int)"
 * </li>
 * <li>
 * component identification number: "c-(int)"
 * </li>
 * <li>
 * notification identification number: "n-(int)"
 * </li>
 * <li>
 * favorite identification number: "f-(int)"
 * </li>
 * <li>
 * problem identification number: "p-(int)"
 * </li>
 *
 * @param context context to which this identification number belongs (e.g., "b","c", etc.)
 * @param id int interpreted as id
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public record IdentificationNumber(String context, int id) {

  private static final String IDENTIFICATION_NUMBER_REGEX = "[bcrnfp]-\\d+";

  private static final String WRONG_FORMAT_IDENTIFICATION_NUMBER = "Input \\s is invalid format, must be \\s";

  private static final String SPLITERATOR = "-";

  /**
   * Get identification number by string.
   *
   * @param string identification number in string format
   * @return identification number as object
   */
  public IdentificationNumber fromString(final String string) throws InvalidIdentificationNumberException {
    if (!string.matches(IDENTIFICATION_NUMBER_REGEX)) {
      throw new InvalidIdentificationNumberException(
        String.format(WRONG_FORMAT_IDENTIFICATION_NUMBER, string, IDENTIFICATION_NUMBER_REGEX));
    }
    String context = string.split(SPLITERATOR)[0];
    int id = Integer.parseInt(string.split(SPLITERATOR)[2]);
    return new IdentificationNumber(context, id);
  }

  @Override
  public String toString() {
    return context + SPLITERATOR + id;
  }
}
