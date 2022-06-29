package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

public class Restroom extends Room {

    private static final String DESCRIPTION = "Restroom";

    protected Restroom(final IdentificationNumber identificationNumber, final GeographicalLocation geographicalLocation, final int floor) {
        super(identificationNumber, geographicalLocation, floor);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
