package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;

import java.util.Collection;
import java.util.Comparator;

/**
 * This enum describes various {@link Sorter} to sort {@link Room}. Each enum constant represents a
 * certain sorting strategy.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
public enum RoomSorter implements Sorter<Room> {
  /** The Lexicographic name sorter strategy. */
  LEXICOGRAPHIC_NAME_SORTER {
    @Override
    public Collection<Room> sort(Collection<Room> collection) {
      return collection.stream().sorted(Comparator.comparing(Room::getName)).toList();
    }
  },
  /** The Lexicographic number sorter strategy. */
  LEXICOGRAPHIC_NUMBER_SORTER {
    @Override
    public Collection<Room> sort(Collection<Room> collection) {
      return collection.stream().sorted(Comparator.comparing(Room::getNumber)).toList();
    }
  },
  /** The Default sorter strategy. */
  DEFAULT_SORTER {
    @Override
    public Collection<Room> sort(Collection<Room> collection) {
      return collection;
    }
  }
}
