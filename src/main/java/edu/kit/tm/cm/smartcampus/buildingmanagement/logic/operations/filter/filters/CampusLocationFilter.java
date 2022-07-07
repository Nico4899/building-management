package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.CampusLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;

import java.util.Collection;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of buildings
 * by campus location.
 */
@AllArgsConstructor
public class CampusLocationFilter implements Filter<Building> {

  private Collection<Building> collectionToFilter;
  private Collection<CampusLocation> filterValues;

  @Override
  public void filter() {
    collectionToFilter.removeIf(building -> !filterValues.contains(building.getCampusLocation()));
  }
}
