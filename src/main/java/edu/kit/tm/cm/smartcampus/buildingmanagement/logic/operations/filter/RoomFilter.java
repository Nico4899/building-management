package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;

import java.util.ArrayList;
import java.util.Collection;

public enum RoomFilter implements Filter<Room> {
  ROOM_TYPE_FILTER {
    @Override
    public Collection<Room> filter(Collection<Room> collection, Collection<?> values) {
      return collection.stream().filter(room -> values.contains(room.getType())).toList();
    }
  },
  COMPONENT_TYPE_FILTER {
    @Override
    public Collection<Room> filter(Collection<Room> collection, Collection<?> values) {
      Collection<Room> filtered = new ArrayList<>();
      collection.forEach(
          room ->
              room.getComponents().stream()
                  .filter(component -> values.contains(component.getType()))
                  .map(component -> room)
                  .forEach(filtered::add));
      return filtered;
    }
  },
  FLOOR_FILTER {
    @Override
    public Collection<Room> filter(Collection<Room> collection, Collection<?> values) {
      return collection.stream().filter(room -> values.contains(room.getFloor())).toList();
    }
  }
}
