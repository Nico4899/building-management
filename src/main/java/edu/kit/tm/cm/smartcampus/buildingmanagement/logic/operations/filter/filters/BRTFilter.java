package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Building} by {@link RoomType}. The short form "BRTFilter" stands for "BuildingRoomTypeFilter".
 */
@NoArgsConstructor
public class BRTFilter implements Filter<Building, RoomType> {

  private Collection<RoomType> filterValues = List.of(RoomType.values());

  @Override
  public Collection<Building> filter(@NonNull Collection<Building> collection) {
    Collection<Building> filtered = new ArrayList<>();
    collection.forEach(
        building ->
            building.getRooms().stream()
                .filter(room -> filterValues.contains(room.getRoomType()))
                .map(room -> building)
                .forEach(filtered::add));
    return filtered;
  }

  @Override
  public void setFilterValues(@NonNull Collection<RoomType> filterValues) {
    this.filterValues = filterValues;
  }
}
