package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

/**
 * A component representing a Door.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public class Door extends Component {

  // Door description
  private static final String DESCRIPTION = "Door";

  /**
   * Instantiates a new door component, with following parameters.
   *
   * @param identificationNumber a component's unique identification number (format: "c-(int)")
   */
  protected Door(final IdentificationNumber identificationNumber) {
    super(identificationNumber, parent);
  }

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
