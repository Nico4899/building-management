package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.AccessibleObject;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

/**
 * A Room as an abstract piece of a building. It possesses core functionality of the Building
 * Management microservice. It extends its superclass @AccessibleObject, but doesn't implement its
 * abstract classes.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public abstract class Room extends AccessibleObject {

  // floor in which the room is located
  private final int floor;

  private final IdentificationNumber parent;

  /**
   * Instantiates a room with following parameters.
   *
   * @param identificationNumber the room's unique identification number (format: "r-(int)")
   * @param geographicalLocation the geographical location of the room
   * @param floor the floor in which the room is located inside the building
   * @param parent the rooms parent building identification number (format: "b-(int)")
   */
  protected Room(
      final IdentificationNumber identificationNumber,
      final GeographicalLocation geographicalLocation,
      int floor,
      IdentificationNumber parent) {
    super(identificationNumber, geographicalLocation);
    this.floor = floor;
    this.parent = parent;
  }

  // Getters

  /**
   * Gets the room's floor.
   *
   * @return the floor in which the room is located
   */
  public int getFloor() {
    return floor;
  }

  // Abstract classes implemented by its subclasses
  /**
   * Gets a room's description.
   *
   * @return the description of the room
   */
  public abstract String getDescription();
}
