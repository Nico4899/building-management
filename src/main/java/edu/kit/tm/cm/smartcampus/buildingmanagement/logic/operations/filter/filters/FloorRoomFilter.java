package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;

import java.util.Collection;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Room} by floor number.
 */
@AllArgsConstructor
public class FloorRoomFilter implements Filter<Room> {

  private final Collection<Integer> floors;

  @Override
  public Collection<Room> filter(Collection<Room> collection) {
    return collection.stream().filter(room -> this.floors.contains(room.getFloor())).toList();
  }
}
