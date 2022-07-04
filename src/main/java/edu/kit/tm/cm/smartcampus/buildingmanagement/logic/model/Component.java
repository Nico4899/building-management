package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Component {
  private String componentDescription;
  private String identificationNumber;
  private GeographicalLocation geographicalLocation;
  private String parentIdentificationNumber;
  private ComponentType componentType;
}
