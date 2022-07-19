package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import java.util.Collection;

/**
 * This interface describes a generic building management service filter.
 *
 * @param <T> the object type to filter a collection of
 */
public interface Filter<T> {
  /**
   * Filters a given collection by filter values.
   *
   * @param collection collection to be filtered
   * @return filtered collection
   */
  Collection<T> filter(Collection<T> collection);
}
