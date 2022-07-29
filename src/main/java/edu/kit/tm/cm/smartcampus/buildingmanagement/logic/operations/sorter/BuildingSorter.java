package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;

import java.util.Collection;
import java.util.Comparator;

public enum BuildingSorter implements Sorter<Building> {
  LEXICOGRAPHIC_NAME_SORTER {
    @Override
    public Collection<Building> sort(Collection<Building> collection) {
      return collection.stream().sorted(Comparator.comparing(Building::getName)).toList();
    }
  },
  LEXICOGRAPHIC_NUMBER_SORTER {
    @Override
    public Collection<Building> sort(Collection<Building> collection) {
      return collection.stream().sorted(Comparator.comparing(Building::getNumber)).toList();
    }
  },
  DEFAULT_SORTER {
    @Override
    public Collection<Building> sort(Collection<Building> collection) {
      return collection;
    }
  }
}
