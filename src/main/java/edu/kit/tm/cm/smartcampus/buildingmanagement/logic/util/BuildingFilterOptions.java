package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuildingFilterOptions {
  private RoomTypeFilterOption roomTypeFilterOption;
  private CampusLocationFilterOption campusLocationFilterOption;
  private ComponentTypeFilterOption componentTypeFilterOption;
}
