package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.problem;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.Service;

/**
 * This class describes a problem connector which is being used by {@link Service}+ to provide an
 * interface for a REST connector to the problem domain microservice.
 *
 * @author Bastian Bacher
 */
public interface ProblemConnector {
  /**
   * Remove problems by reference identification number
   */
  void removeProblemsByReferenceIdentificationNumber(String identificationNumber);
}
