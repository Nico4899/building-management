package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;

import java.util.Collection;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Building} by {@link Building.CampusLocation}.
 */
@AllArgsConstructor
public class CampusLocationBuildingFilter implements Filter<Building> {

  private Collection<Building.CampusLocation> filterValues;

  @Override
  public Collection<Building> filter(Collection<Building> collection) {
    return collection.stream()
        .filter(building -> this.filterValues.contains(building.getCampusLocation()))
        .toList();
  }
}
