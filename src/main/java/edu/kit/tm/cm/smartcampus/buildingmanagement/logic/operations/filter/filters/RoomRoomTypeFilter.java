package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;

import java.util.Collection;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of rooms by
 * room types.
 */
@AllArgsConstructor
public class RoomRoomTypeFilter implements Filter<Room> {

  private Collection<Room> collectionToFilter;
  private Collection<RoomType> filterValues;

  @Override
  public void filter() {
    collectionToFilter.removeIf(room -> filterValues.contains(room.getRoomType()));
  }
}
