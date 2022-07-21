package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Building} by {@link Room.Type}.
 */
@AllArgsConstructor
public class RoomTypeBuildingFilter implements Filter<Building> {

  private Collection<Room.Type> filterValues;

  @Override
  public Collection<Building> filter(Collection<Building> collection) {
    Collection<Building> filtered = new ArrayList<>();
    collection.forEach(
        building ->
            building.getRooms().stream()
                .filter(room -> this.filterValues.contains(room.getType()))
                .map(room -> building)
                .forEach(filtered::add));
    return filtered;
  }
}
