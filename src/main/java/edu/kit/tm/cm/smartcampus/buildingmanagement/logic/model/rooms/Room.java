package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.AccessibleObject;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

public abstract class Room extends AccessibleObject {

    private final int floor;

    protected Room(final IdentificationNumber identificationNumber, final GeographicalLocation geographicalLocation, int floor) {
        super(identificationNumber, geographicalLocation);
        this.floor = floor;
    }

    // Getters
    public int getFloor() {
        return floor;
    }

    // Abstract methods for subclasses
    public abstract String getDescription();
}
