package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Room} by {@link RoomType}. The short form "RRTFilter" stands for "RoomRoomTypeFilter".
 */
@NoArgsConstructor
public class RRTFilter implements Filter<Room, RoomType> {

  private Collection<RoomType> filterValues = List.of(RoomType.values());

  @Override
  public Collection<Room> filter(@NonNull Collection<Room> collection) {
    return collection.stream().filter(room -> filterValues.contains(room.getRoomType())).toList();
  }

  @Override
  public void setFilterValues(@NonNull Collection<RoomType> filterValues) {
    this.filterValues = filterValues;
  }
}
