package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

public class Stairs extends Component {

    private static final String DESCRIPTION = "Stairs";

    protected Stairs(final IdentificationNumber identificationNumber) {
        super(identificationNumber);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
