package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.CampusLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import lombok.Data;

@Data
public class FilterOptions {
  private FilterOption<CampusLocation> campusLocationFilterOption;
  private FilterOption<RoomType> roomTypeFilterOption;
  private FilterOption<ComponentType> componentTypeFilterOption;
}
