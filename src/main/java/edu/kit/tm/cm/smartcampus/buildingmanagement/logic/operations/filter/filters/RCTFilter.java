package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Room} by {@link ComponentType}. The short form "RCTFilter" stands for "RoomComponentTypeFilter".
 */
@NoArgsConstructor
public class RCTFilter implements Filter<Room, ComponentType> {

  Collection<ComponentType> filterValues = List.of(ComponentType.values());

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

  @Override
  public void setFilterValues(@NonNull Collection<ComponentType> filterValues) {
    this.filterValues = filterValues;
  }
}
