package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.CampusLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Collection;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Building} by {@link CampusLocation}. The short form "CLFilter" stands for "CampusLocationFilter".
 */
@AllArgsConstructor
public class CLFilter implements Filter<Building> {

  private Collection<CampusLocation> filterValues;

  @Override
  public Collection<Building> filter(@NonNull Collection<Building> collection) {
    return collection.stream()
        .filter(building -> filterValues.contains(building.getCampusLocation()))
        .toList();
  }
}
