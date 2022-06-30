package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.AccessibleObject;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Bastian Bacher
 * @version 1.0
 *
 * This class represents an implementation of a building on campus, it's defined at:
 * <a href="https://git.scc.kit.edu/cm-tm/cm-team/3.projectwork/pse/docsc/-/blob/master/pages/ubiquitous_language.md">...</a>
 * it extends @AccessibleObject which itself implements @Notificatable and can therefore hold
 * notifications, components and additionally rooms. Its functionally is mostly built up on those three attributes.
 */
public class Building extends AccessibleObject {

    // collection of rooms held by the building
    private final Collection<Room> rooms;

    // location on campus
    private final CampusLocation campusLocation;

    // number of floors the building possesses
    private final int numFloors;

    /**
     * Constructs a building, by the following parameters, instantiates a rooms collection and calls super on @AccessibleObject.
     *
     * @param identificationNumber the unique building identification number (Format: "b-(int)")
     * @param geographicalLocation the geographical location of the building
     * @param campusLocation location on campus(e.g., North, South etc.)
     * @param numFloors amount of floors the building possesses
     */
    protected Building(final IdentificationNumber identificationNumber, final GeographicalLocation geographicalLocation, final CampusLocation campusLocation, final int numFloors) {
        super(identificationNumber, geographicalLocation);
        this.campusLocation = campusLocation;
        this.numFloors = numFloors;
        this.rooms = new HashSet<>();
    }

    // Room maintenance methods

    /**
     * Adds a room to the building's rooms.
     *
     * @param room the room to be added
     */
    public void addRoom(final Room room) {
        this.rooms.add(room);
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
        this.rooms.remove(room);
    }

    /**
     * Gets all rooms this building possesses.
     *
     * @return a collection of rooms
     */
    public Collection<Room> getRooms() {
        return this.rooms;
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
    public boolean isAccessible() {
        return false;
        //TODO implement on values e.g., has elevator && all relevant rooms are accessible and handicapped toilet
    }

}
