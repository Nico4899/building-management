package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;

import java.util.Collection;
import java.util.Comparator;

/**
 * This enum describes various {@link Sorter} to sort {@link Building}. Each enum constant
 * represents a certain sorting strategy.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
public enum BuildingSorter implements Sorter<Building> {
  /** The Lexicographic name sorter strategy. */
  LEXICOGRAPHIC_NAME_SORTER {
    @Override
    public Collection<Building> sort(Collection<Building> collection) {
      return collection.stream().sorted(Comparator.comparing(Building::getName)).toList();
    }
  },
  /** The Lexicographic number sorter strategy. */
  LEXICOGRAPHIC_NUMBER_SORTER {
    @Override
    public Collection<Building> sort(Collection<Building> collection) {
      return collection.stream().sorted(Comparator.comparing(Building::getNumber)).toList();
    }
  },
  /** The Default sorter strategy. */
  DEFAULT_SORTER {
    @Override
    public Collection<Building> sort(Collection<Building> collection) {
      return collection;
    }
  }
}
