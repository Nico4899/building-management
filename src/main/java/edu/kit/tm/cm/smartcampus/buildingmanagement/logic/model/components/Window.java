package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

/**
 * A component representing a window.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public class Window extends Component {

  // Window description
  private static final String DESCRIPTION = "Window";

  /**
   * Instattiates a new window component, with following parameters.
   *
   * @param identificationNumber a component's unique identification number (format: "c-(int)")
   */
  protected Window(
      final IdentificationNumber identificationNumber, final IdentificationNumber parent) {
    super(identificationNumber, parent);
  }

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
