package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.sorters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.Sorter;

import java.util.Collection;
import java.util.Comparator;

/**
 * This class represents an implementation of {@link Sorter}, it sorts a collection of {@link Room}
 * by lexicographical name order.
 */
public class LexicographicNameRoomSorter implements Sorter<Room> {
  @Override
  public Collection<Room> sort(Collection<Room> collection) {
    return collection.stream().sorted(Comparator.comparing(Room::getRoomName)).toList();
  }
}
