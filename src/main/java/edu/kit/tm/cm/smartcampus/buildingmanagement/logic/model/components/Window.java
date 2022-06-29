package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

public class Window extends Component {

    private static final String DESCRIPTION = "Window";

    protected Window(final IdentificationNumber identificationNumber) {
        super(identificationNumber);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
