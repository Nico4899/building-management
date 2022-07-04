package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

@AllArgsConstructor
public class RoomComponentTypeFilter implements Filter<Room>{

  private Map<Room, Collection<Component>> roomComponentMap;
  private Collection<ComponentType> filterValues;

  @Override
  public Collection<Room> filter() {
    Collection<Room> filteredCollection = new LinkedList<>();
    for (Map.Entry<Room, Collection<Component>> entry: roomComponentMap.entrySet()) {
      for (Component component: entry.getValue()) {
        if (filterValues.contains(component.getComponentType())) {
          filteredCollection.add(entry.getKey());
        }
      }
    }
    return filteredCollection;
  }
}
