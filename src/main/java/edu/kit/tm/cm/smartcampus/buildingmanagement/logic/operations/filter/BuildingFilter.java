package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * This enum represents a variety of {@link Filter} strategies to filter objects of type {@link
 * Building}.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
public enum BuildingFilter implements Filter<Building> {
  /** The Campus location filter strategy. */
  CAMPUS_LOCATION_FILTER {
    @Override
    public Collection<Building> filter(Collection<Building> collection, Collection<?> values) {
      return collection.stream()
          .filter(building -> values.contains(building.getCampusLocation()))
          .toList();
    }
  },
  /** The Room type filter strategy. */
  ROOM_TYPE_FILTER {
    @Override
    public Collection<Building> filter(Collection<Building> collection, Collection<?> values) {
      Collection<Building> filtered = new HashSet<>();
      collection.forEach(
          building ->
              building.getRooms().stream()
                  .filter(room -> values.contains(room.getType()))
                  .map(room -> building)
                  .forEach(filtered::add));
      return filtered;
    }
  },
  /** The Component type filter strategy. */
  COMPONENT_TYPE_FILTER {
    @Override
    public Collection<Building> filter(Collection<Building> collection, Collection<?> values) {
      Collection<Building> filtered = new HashSet<>();
      collection.forEach(
          building ->
              building.getComponents().stream()
                  .filter(component -> values.contains(component.getType()))
                  .map(component -> building)
                  .forEach(filtered::add));
      return filtered;
    }
  }
}
