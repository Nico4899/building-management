package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.CampusLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** This class represents a container for filter options since their generic */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class FilterOptions {
  private FilterOption<CampusLocation> campusLocationFilterOption;
  private FilterOption<ComponentType> componentTypeFilterOption;
  private FilterOption<RoomType> roomTypeFilterOption;
}
