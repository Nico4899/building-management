package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter;

import java.util.Collection;

/**
 * This interface represents a sorting unit, which sorts a collection of type <T>.
 *
 * @param <T> the type parameter of the collection to sort
 * @author Bastian Bacher, Dennis Fadeev
 */
public interface Sorter<T> {
  /**
   * Sort a collection.
   *
   * @return sorted collection
   */
  Collection<T> sort(Collection<T> collection);
}
