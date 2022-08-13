package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import java.util.Collection;

/**
 * This interface describes a generic building management service filter.
 *
 * @param <T> the object type to filter a collection of
 * @author Bastian Bacher, Dennis Fadeev
 */
public interface Filter<T> {
  /**
   * Filters a given collection by filter values.
   *
   * @param collection collection to be filtered
   * @param values filter values to filter for
   * @return filtered collection
   */
  Collection<T> filter(Collection<T> collection, Collection<?> values);
}
