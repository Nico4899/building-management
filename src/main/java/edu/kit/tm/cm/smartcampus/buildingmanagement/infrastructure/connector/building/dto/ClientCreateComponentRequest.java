package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.dto;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.GeographicalLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a dto for a create component request, to the building domain.
 *
 * @author Bastian Bacher
 */
@Getter
@Setter
@NoArgsConstructor
public class ClientCreateComponentRequest {
  private String parentIdentificationNumber;
  private Component.Type type;
  private String description;
  private GeographicalLocation geographicalLocation;
}
