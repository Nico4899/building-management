package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

public class Sports extends Room {

    private static final String DESCRIPTION = "Sports";

    protected Sports(final IdentificationNumber identificationNumber, final GeographicalLocation geographicalLocation, final int floor) {
        super(identificationNumber, geographicalLocation, floor);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    // Super class implementations
    @Override
    public boolean isAccessible() {
        return false;
        //TODO implement on values
    }
}
