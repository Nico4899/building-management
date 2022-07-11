package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Room} by {@link ComponentType}. The short form "RCTFilter" stands for "RoomComponentTypeFilter".
 */
@AllArgsConstructor
public class RCTFilter implements Filter<Room> {

  Collection<ComponentType> filterValues;

  @Override
  public Collection<Room> filter(@NonNull Collection<Room> collection) {
    Collection<Room> filtered = new ArrayList<>();
    collection.forEach(
        room ->
            room.getComponents().stream()
                .filter(component -> filterValues.contains(component.getComponentType()))
                .map(component -> room)
                .forEach(filtered::add));
    return filtered;
  }
}
