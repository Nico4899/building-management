package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.dto;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Floors;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.GeographicalLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientCreateBuildingRequest {
  private Floors floors;
  private Building.CampusLocation campusLocation;
  private String name;
  private String number;
  private GeographicalLocation geographicalLocation;
}
