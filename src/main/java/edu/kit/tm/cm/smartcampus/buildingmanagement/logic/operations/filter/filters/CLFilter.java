package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.CampusLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of {@link
 * Building} by {@link CampusLocation}. The short form "CLFilter" stands for "CampusLocationFilter".
 */
@NoArgsConstructor
public class CLFilter implements Filter<Building, CampusLocation> {

  private Collection<CampusLocation> filterValues = List.of(CampusLocation.values());

  @Override
  public Collection<Building> filter(@NonNull Collection<Building> collection) {
    return collection.stream()
        .filter(building -> filterValues.contains(building.getCampusLocation()))
        .toList();
  }

  @Override
  public void setFilterValues(@NonNull Collection<CampusLocation> filterValues) {
    this.filterValues = filterValues;
  }
}
