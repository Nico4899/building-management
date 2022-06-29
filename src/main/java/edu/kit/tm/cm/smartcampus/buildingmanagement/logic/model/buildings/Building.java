package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.AccessibleObject;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

import java.util.Collection;
import java.util.HashSet;

public class Building extends AccessibleObject {

    private final Collection<Room> rooms;

    private final CampusLocation campusLocation;

    protected Building(final IdentificationNumber identificationNumber, final GeographicalLocation geographicalLocation, CampusLocation campusLocation) {
        super(identificationNumber, geographicalLocation);
        this.campusLocation = campusLocation;
        this.rooms = new HashSet<>();
    }

    // Room maintenance methods
    public void addRoom(final Room room) {
        this.rooms.add(room);
    }

    public void updateRoom(final Room room) {
        this.addRoom(room);
        this.removeRoom(room);
    }

    public void removeRoom(final Room room) {
        this.rooms.remove(room);
    }

    public Collection<Room> getRooms() {
        return this.rooms;
    }

    // Getters
    public CampusLocation getCampusLocation() {
        return campusLocation;
    }

    // Super class implementations
    @Override
    public boolean isAccessible() {
        return false;
        //TODO implement on values e.g., has elevator && all relevant rooms are accessible and handicapped toilet
    }
}
