package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;

import java.util.Collection;
import java.util.Comparator;
/**
 * This enum describes various {@link Sorter} to sort {@link Notification}. Each enum constant
 * represents a certain sorting strategy.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
public enum NotificationSorter implements Sorter<Notification> {
  /** The Ascending time stamp sorter strategy. */
  ASCENDING_TIME_STAMP_SORTER {
    @Override
    public Collection<Notification> sort(Collection<Notification> collection) {
      return collection.stream()
          .sorted(Comparator.comparing(Notification::getCreationTime).reversed())
          .toList();
    }
  },
  /** The Descending time stamp sorter strategy. */
  DESCENDING_TIME_STAMP_SORTER {
    @Override
    public Collection<Notification> sort(Collection<Notification> collection) {
      return collection.stream()
          .sorted(Comparator.comparing(Notification::getCreationTime))
          .toList();
    }
  },
  /** The Default sorter strategy. */
  DEFAULT_SORTER {
    @Override
    public Collection<Notification> sort(Collection<Notification> collection) {
      return collection;
    }
  }
}
