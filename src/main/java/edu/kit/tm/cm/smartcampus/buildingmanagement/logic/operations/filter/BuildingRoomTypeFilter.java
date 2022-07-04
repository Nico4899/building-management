package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

@AllArgsConstructor
public class BuildingRoomTypeFilter implements Filter<Building> {

  private Map<Building, Collection<Room>> buildingRoomMap;
  private Collection<RoomType> filterValues;

  @Override
  public Collection<Building> filter() {
    Collection<Building> filteredCollection = new LinkedList<>();
    for (Map.Entry<Building, Collection<Room>> entry: buildingRoomMap.entrySet()) {
      for (Room room : entry.getValue()) {
        if (filterValues.contains(room.getRoomType())) {
          filteredCollection.add(entry.getKey());
        }
      }
    }
    return filteredCollection;
  }

}
