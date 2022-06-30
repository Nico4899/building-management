package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

/**
 * A component representing an elevator.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public class Elevator extends Component {

  // Elevator description
  private static final String DESCRIPTION = "Elevator";

  /**
   * Instantiates a new elevator component, with the following parameters.
   *
   * @param identificationNumber a component's unique identification number (format "c-(int)")
   */
  protected Elevator(
      final IdentificationNumber identificationNumber, final IdentificationNumber parent) {
    super(identificationNumber, parent);
  }

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }

  @Override
  public boolean accessibilityConform() {
    return false;
    //TODO implement
  }
}
