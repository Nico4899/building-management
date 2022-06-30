package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils;

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
  public IdentificationNumber fromString(final String string) {
    return null;
    //TODO implement
  }

}
