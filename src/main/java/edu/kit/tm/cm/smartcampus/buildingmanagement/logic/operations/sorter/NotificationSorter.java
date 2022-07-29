package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;

import java.util.Collection;
import java.util.Comparator;

public enum NotificationSorter implements Sorter<Notification> {
  ASCENDING_TIME_STAMP_SORTER {
    @Override
    public Collection<Notification> sort(Collection<Notification> collection) {
      return collection.stream()
          .sorted(Comparator.comparing(Notification::getCreationTime).reversed())
          .toList();
    }
  },
  DESCENDING_TIME_STAMP_SORTER {
    @Override
    public Collection<Notification> sort(Collection<Notification> collection) {
      return collection.stream()
          .sorted(Comparator.comparing(Notification::getCreationTime))
          .toList();
    }
  },
  DEFAULT_SORTER {
    @Override
    public Collection<Notification> sort(Collection<Notification> collection) {
      return collection;
    }
  }
}
