package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

public class Elevator extends Component {

    private static final String DESCRIPTION = "Elevator";

    protected Elevator(final IdentificationNumber identificationNumber) {
        super(identificationNumber);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
