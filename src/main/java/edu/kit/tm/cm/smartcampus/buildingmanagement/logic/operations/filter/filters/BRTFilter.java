package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Building} by {@link RoomType}. The short form "BRTFilter" stands for "BuildingRoomTypeFilter".
 */
@AllArgsConstructor
public class BRTFilter implements Filter<Building> {

  private Collection<RoomType> filterValues;

  @Override
  public Collection<Building> filter(Collection<Building> collection) {
    Collection<Building> filtered = new ArrayList<>();
    collection.forEach(
        building ->
            building.getRooms().stream()
                .filter(room -> filterValues.contains(room.getRoomType()))
                .map(room -> building)
                .forEach(filtered::add));
    return filtered;
  }
}
