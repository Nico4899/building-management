package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** This class describes a component which can be held by {@link Building} or {@link Room}. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Component {
  private String componentDescription;
  private String identificationNumber;
  private GeographicalLocation geographicalLocation;
  private String parentIdentificationNumber;
  private ComponentType componentType;
}
