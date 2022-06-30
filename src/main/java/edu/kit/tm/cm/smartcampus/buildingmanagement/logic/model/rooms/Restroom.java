package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

/**
 * A room representing a restroom.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public class Restroom extends Room {

  // Restroom description
  private static final String DESCRIPTION = "Restroom";

  /**
   * Instantiates a restroom with following parameters.
   *
   * @param identificationNumber the room's unique identification number (format: "r-(int)")
   * @param geographicalLocation the geographical location of the room
   * @param floor the floor in which the room is located
   */
  protected Restroom(
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
