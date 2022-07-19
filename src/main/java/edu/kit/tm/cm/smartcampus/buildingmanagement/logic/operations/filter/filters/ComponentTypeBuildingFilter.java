package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Building} by {@link ComponentType}.
 */
@AllArgsConstructor
public class ComponentTypeBuildingFilter implements Filter<Building> {

  private Collection<ComponentType> filterValues;

  @Override
  public Collection<Building> filter(Collection<Building> collection) {
    Collection<Building> filtered = new ArrayList<>();
    collection.forEach(
        building ->
            building.getComponents().stream()
                .filter(component -> this.filterValues.contains(component.getComponentType()))
                .map(component -> building)
                .forEach(filtered::add));
    return filtered;
  }
}
