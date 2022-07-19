package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.sorters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.Sorter;

import java.util.Collection;
import java.util.Comparator;

/**
 * This class represents an implementation of {@link Sorter}, it sorts a collection of {@link
 * Building} by lexicographical name order.
 */
public class LexicographicNameBuildingSorter implements Sorter<Building> {
  @Override
  public Collection<Building> sort(Collection<Building> collection) {
    return collection.stream().sorted(Comparator.comparing(Building::getBuildingName)).toList();
  }
}
