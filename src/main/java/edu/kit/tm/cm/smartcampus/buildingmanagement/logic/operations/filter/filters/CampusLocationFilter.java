package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.CampusLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class CampusLocationFilter implements Filter<Building> {

  private Collection<Building> collectionToFilter;
  private Collection<CampusLocation> filterValues;

  @Override
  public void filter() {
    collectionToFilter.removeIf(building -> filterValues.contains(building.getCampusLocation()));
  }
}
