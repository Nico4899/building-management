package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * This enum represents a variety of {@link Filter} strategies to filter objects of type {@link
 * Room}.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
public enum RoomFilter implements Filter<Room> {
  /** The Room type filter strategy. */
  ROOM_TYPE_FILTER {
    @Override
    public Collection<Room> filter(Collection<Room> collection, Collection<?> values) {
      return collection.stream().filter(room -> values.contains(room.getType())).toList();
    }
  },
  /** The Component type filter strategy. */
  COMPONENT_TYPE_FILTER {
    @Override
    public Collection<Room> filter(Collection<Room> collection, Collection<?> values) {
      Collection<Room> filtered = new HashSet<>();
      collection.forEach(
          room ->
              room.getComponents().stream()
                  .filter(component -> values.contains(component.getType()))
                  .map(component -> room)
                  .forEach(filtered::add));
      return filtered;
    }
  },
  /** The Floor filter strategy. */
  FLOOR_FILTER {
    @Override
    public Collection<Room> filter(Collection<Room> collection, Collection<?> values) {
      return collection.stream().filter(room -> values.contains(room.getFloor())).toList();
    }
  }
}
