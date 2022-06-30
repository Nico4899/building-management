package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.AccessibleObject;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents an implementation of a building on campus, it extends @AccessibleObject
 * which itself implements @Notificatable and can therefore hold notifications, components and
 * additionally rooms. Its functionally is mostly built up on those three attributes.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public class Building extends AccessibleObject {

  // collection of rooms held by the building
  private final Map<IdentificationNumber, Room> rooms;

  // location on campus
  private final CampusLocation campusLocation;

  // number of floors the building possesses
  private final int numFloors;

  /*
  Accessibility booleans (true if accessibility conform)
  references: - https://www.bfb-barrierefrei-bauen.de/kategorie/konzept-planung/oeffentliche-gebaeude/#:~:text=Nach%20der%20Musterbauordnung%20(MBO)%20m%C3%BCssen,Hilfe%20zug%C3%A4nglich%20und%20nutzbar%E2%80%9C%20sein.
              - https://www.aktion-mensch.de/inklusion/bildung/impulse/barrierefreiheit/raeumliche-barrierefreiheit
  */

  // Entrance areas
  private boolean discoverability;
  private boolean reachability;
  private boolean movementArea;
  private boolean doorOpenerAndBellSystem;

  // Ramps
  private boolean widthAndLength;
  private boolean tilt;
  private boolean wheelDeflector;
  private boolean rampDetectability;

  // Stairs and single levels
  private boolean stairs;
  private boolean flightOfStairs;
  private boolean stages;
  private boolean stairDetectability;

  // Handrails on stairs and ramps
  private boolean arrangement;
  private boolean height;
  private boolean handrailDetectability;
  private boolean form;
  private boolean orientation;

  //TODO maybe all inside components?

  /**
   * Instantiates a building, with the following parameters, instantiates a rooms collection and
   * calls super on @AccessibleObject.
   *
   * @param identificationNumber the unique building identification number (format: "b-(int)")
   * @param geographicalLocation the geographical location of the building
   * @param campusLocation location on campus(e.g., North, South etc.)
   * @param numFloors amount of floors the building possesses
   */
  protected Building(
      final IdentificationNumber identificationNumber,
      final GeographicalLocation geographicalLocation,
      final CampusLocation campusLocation,
      final int numFloors) {
    super(identificationNumber, geographicalLocation);
    this.campusLocation = campusLocation;
    this.numFloors = numFloors;
    this.rooms = new HashMap<>();
  }

  // Room maintenance methods

  /**
   * Adds a room to the building's rooms.
   *
   * @param room the room to be added
   */
  public void addRoom(final Room room) {
    this.rooms.put(room.getIdentificationNumber(), room);
  }

  /**
   * Updates a room in the building's rooms.
   *
   * @param room the room to be updated
   */
  public void updateRoom(final Room room) {
    this.addRoom(room);
    this.removeRoom(room);
  }

  /**
   * Removes a room from the building's rooms.
   *
   * @param room the room to be removed
   */
  public void removeRoom(final Room room) {
    this.rooms.remove(room.getIdentificationNumber());
  }

  /**
   * Gets all rooms this building possesses.
   *
   * @return a collection of rooms
   */
  public Collection<Room> getRooms() {
    return this.rooms.values();
  }

  // Getters

  /**
   * Gets the building's location on campus.
   *
   * @return location on campus
   */
  public CampusLocation getCampusLocation() {
    return campusLocation;
  }

  /**
   * Gets the number of floors this building possesses.
   *
   * @return number of floors
   */
  public int getNumFloors() {
    return numFloors;
  }

  // Super class implementations

  @Override
  public boolean accessibilityConform() {
    return false;
  }

}
