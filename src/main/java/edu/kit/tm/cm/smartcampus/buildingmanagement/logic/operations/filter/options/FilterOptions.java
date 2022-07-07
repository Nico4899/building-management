package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.CampusLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * This class describes an object containing of filter options for the building management service
 * filter system.
 */
@Data
@AllArgsConstructor
@Builder
public class FilterOptions {
  private FilterOption<CampusLocation> campusLocationFilterOption;
  private FilterOption<RoomType> roomTypeFilterOption;
  private FilterOption<ComponentType> componentTypeFilterOption;
}
