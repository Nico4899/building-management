package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.dto;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.GeographicalLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a dto for a create building request, to the building domain.
 *
 * @author Bastian Bacher
 */
@Getter
@Setter
@NoArgsConstructor
public class ClientCreateBuildingRequest {
  private String name;
  private String number;
  private String address;
  private Building.CampusLocation campusLocation;
  private GeographicalLocation geographicalLocation;
  private Floors floors;
}
