package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

public class Door extends Component {

    private static final String DESCRIPTION = "Door";

    protected Door(final IdentificationNumber identificationNumber) {
        super(identificationNumber);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}
