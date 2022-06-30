package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

/**
 * A room representing a seminar room.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public class SeminarRoom extends Room {

  // Seminar room description
  private static final String DESCRIPTION = "Seminar Room";

  /**
   * Instantiates a seminar room with following parameters.
   *
   * @param identificationNumber the room's unique identification number (format: "r-(int)")
   * @param geographicalLocation the geographical location of the room
   * @param floor the floor in which the room is located
   */
  protected SeminarRoom(
      final IdentificationNumber identificationNumber,
      final GeographicalLocation geographicalLocation,
      final int floor) {
    super(identificationNumber, geographicalLocation, floor, parent);
  }

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }

  // Super class implementations
  @Override
  public boolean isAccessible() {
    return false;
    // TODO implement on values
  }
}
