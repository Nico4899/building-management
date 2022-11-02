package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.dto;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.GeographicalLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a dto for a update building request, to the building domain.
 *
 * @author Bastian Bacher
 */
@Getter
@Setter
@NoArgsConstructor
public class ClientUpdateBuildingRequest {
  private Floors floors;
  private Building.CampusLocation campusLocation;
  private String name;
  private String number;
  private String address;
  private String identificationNumber;
  private GeographicalLocation geographicalLocation;
}
