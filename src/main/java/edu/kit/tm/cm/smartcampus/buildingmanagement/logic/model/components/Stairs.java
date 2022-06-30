package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

/**
 * A component representing stairs.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public class Stairs extends Component {

  // Stairs description
  private static final String DESCRIPTION = "Stairs";

  /**
   * Instantiates a new stairs component, with following parameters.
   *
   * @param identificationNumber a component's unique identification number (format: "c-(int)")
   */
  protected Stairs(final IdentificationNumber identificationNumber) {
    super(identificationNumber, parent);
  }

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
