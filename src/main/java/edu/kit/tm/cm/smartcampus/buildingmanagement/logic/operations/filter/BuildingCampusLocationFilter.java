package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.CampusLocation;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;

@AllArgsConstructor
public class BuildingCampusLocationFilter implements Filter<Building>{

  private Collection<Building> collectionTiFilter;
  private Collection<CampusLocation> filterValues;

  @Override
  public Collection<Building> filter() {
    Collection<Building> filteredCollection = new LinkedList<>();
    for (Building building: collectionTiFilter) {
      if (filterValues.contains(building.getCampusLocation())) {
        filteredCollection.add(building);
      }
    }
    return filteredCollection;
  }
}
