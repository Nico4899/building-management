package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;

@AllArgsConstructor
public class RoomRoomTypeFilter implements Filter<Room>{

  private Collection<Room> collectionToFilter;
  private Collection<RoomType> filterValues;

  @Override
  public Collection<Room> filter() {
    Collection<Room> filteredCollection = new LinkedList<>();
    for (Room room: collectionToFilter) {
      if(filterValues.contains(room.getRoomType())) {
        filteredCollection.add(room);
      }
    }
    return filteredCollection;
  }
}
