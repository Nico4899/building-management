package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Building {
  private int numFloors;
  private CampusLocation campusLocation;
  private String buildingName;
  private String buildingNumber;
  private String identificationNumber;
  private GeographicalLocation geographicalLocation;
}
