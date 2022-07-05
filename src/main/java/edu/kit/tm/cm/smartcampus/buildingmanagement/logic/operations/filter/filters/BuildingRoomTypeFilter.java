package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;

@AllArgsConstructor
public class BuildingRoomTypeFilter implements Filter<Building> {

  private Map<Building, Collection<Room>> buildingRoomMap;
  private Collection<RoomType> filterValues;
  private Collection<Building> collectionToFilter;

  @Override
  public void filter() {
    for (Map.Entry<Building, Collection<Room>> entry : buildingRoomMap.entrySet()) {
      for (Room room : entry.getValue()) {
        if (filterValues.contains(room.getRoomType())) {
          collectionToFilter.remove(entry.getKey());
        }
      }
    }
  }
}
