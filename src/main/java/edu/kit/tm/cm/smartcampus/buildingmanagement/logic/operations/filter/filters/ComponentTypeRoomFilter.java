package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Room} by {@link Component.Type}.
 */
@AllArgsConstructor
public class ComponentTypeRoomFilter implements Filter<Room> {

  private Collection<Component.Type> filterValues;

  @Override
  public Collection<Room> filter(Collection<Room> collection) {
    Collection<Room> filtered = new ArrayList<>();
    collection.forEach(
        room ->
            room.getComponents().stream()
                .filter(component -> this.filterValues.contains(component.getType()))
                .map(component -> room)
                .forEach(filtered::add));
    return filtered;
  }
}
