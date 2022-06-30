package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

/**
 * A room representing an office.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public class Office extends Room {

  // Office description
  private static final String DESCRIPTION = "Office";

  /**
   * Instantiates an office with following parameters.
   *
   * @param identificationNumber the room's unique identification number (format: "r-(int)")
   * @param geographicalLocation the geographical location of the room
   * @param floor the floor in which the room is located
   */
  protected Office(
      final IdentificationNumber identificationNumber,
      final GeographicalLocation geographicalLocation,
      final int floor,
      final IdentificationNumber parent) {
    super(identificationNumber, geographicalLocation, floor, parent);
  }

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }

  // Super class implementations

  @Override
  public boolean accessibilityConform() {
    return false;
    //TODO implement
  }
}
