package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.BuildingRoomTypeFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.CampusLocationFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.ComponentTypeFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.RoomRoomTypeFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Map;

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

  private Map<Room, Collection<Component>> roomComponentMap;
  private Map<Building, Collection<Room>> buildingRoomMap;
  private Map<Building, Collection<Component>> buildingComponentMap;

  public void executeBuildingFilter(Collection<Building> collection) {
    if (campusLocationFilterOption.isSelected()) {
      Filter<Building> filter =
          new CampusLocationFilter(collection, campusLocationFilterOption.getFilterValues());
      filter.filter();
    }
    if (roomTypeFilterOption.isSelected()) {
      Filter<Building> filter =
          new BuildingRoomTypeFilter(
              buildingRoomMap, roomTypeFilterOption.getFilterValues(), collection);
      filter.filter();
    }
    if (componentTypeFilterOption.isSelected()) {
      Filter<Building> filter =
          new ComponentTypeFilter<>(
              buildingComponentMap, componentTypeFilterOption.getFilterValues(), collection);
      filter.filter();
    }
  }

  public void executeRoomFilter(Collection<Room> collection) {
    if (roomTypeFilterOption.isSelected()) {
      Filter<Room> filter =
          new RoomRoomTypeFilter(collection, roomTypeFilterOption.getFilterValues());
      filter.filter();
    }
    if (componentTypeFilterOption.isSelected()) {
      Filter<Room> filter =
          new ComponentTypeFilter<>(
              roomComponentMap, componentTypeFilterOption.getFilterValues(), collection);
      filter.filter();
    }
  }
}
