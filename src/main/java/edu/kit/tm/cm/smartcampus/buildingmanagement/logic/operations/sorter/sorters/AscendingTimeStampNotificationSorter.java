package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.sorters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.Sorter;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Comparator;

/**
 * This class represents an implementation of {@link Sorter}, it sorts a collection of {@link
 * Notification} by oldest time stamp.
 */
@AllArgsConstructor
public class AscendingTimeStampNotificationSorter implements Sorter<Notification> {

  @Override
  public Collection<Notification> sort(Collection<Notification> collection) {
    return collection.stream()
        .sorted(Comparator.comparing(Notification::getCreationTime).reversed())
        .toList();
  }
}
