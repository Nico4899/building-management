package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;

import java.util.ArrayList;
import java.util.Collection;

public enum BuildingFilter implements Filter<Building> {
  CAMPUS_LOCATION_FILTER {
    @Override
    public Collection<Building> filter(Collection<Building> collection, Collection<?> values) {
      return collection.stream()
          .filter(building -> values.contains(building.getCampusLocation()))
          .toList();
    }
  },
  ROOM_TYPE_FILTER {
    @Override
    public Collection<Building> filter(Collection<Building> collection, Collection<?> values) {
      Collection<Building> filtered = new ArrayList<>();
      collection.forEach(
          building ->
              building.getRooms().stream()
                  .filter(room -> values.contains(room.getType()))
                  .map(room -> building)
                  .forEach(filtered::add));
      return filtered;
    }
  },
  COMPONENT_TYPE_FILTER {
    @Override
    public Collection<Building> filter(Collection<Building> collection, Collection<?> values) {
      Collection<Building> filtered = new ArrayList<>();
      collection.forEach(
          building ->
              building.getComponents().stream()
                  .filter(component -> values.contains(component.getType()))
                  .map(component -> building)
                  .forEach(filtered::add));
      return filtered;
    }
  }
}
