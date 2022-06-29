package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

public class Office extends Room {

    private static final String DESCRIPTION = "Office";

    protected Office(final IdentificationNumber identificationNumber, final GeographicalLocation geographicalLocation, final int floor) {
        super(identificationNumber, geographicalLocation, floor);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
