package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;

import java.util.Collection;
import java.util.Comparator;

public enum RoomSorter implements Sorter<Room> {
  LEXICOGRAPHIC_NAME_SORTER {
    @Override
    public Collection<Room> sort(Collection<Room> collection) {
      return collection.stream().sorted(Comparator.comparing(Room::getName)).toList();
    }
  },
  LEXICOGRAPHIC_NUMBER_SORTER {
    @Override
    public Collection<Room> sort(Collection<Room> collection) {
      return collection.stream().sorted(Comparator.comparing(Room::getNumber)).toList();
    }
  },
  DEFAULT_SORTER {
    @Override
    public Collection<Room> sort(Collection<Room> collection) {
      return collection;
    }
  }
}
