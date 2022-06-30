package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filters;

import java.util.Collection;

/**
 * The filter interface providing functional description of a generic filter, with generic objects
 * to filter.
 *
 * @param <S> the type of the objects to be filtered
 * @author Bastian Bacher
 * @version 1.0
 */
public interface Filter<S> {

  /**
   * Filter the collection.
   *
   * @return the collection to be filtered
   */
  Collection<S> filter();

  /**
   * Gets keyword of the filter.
   *
   * @return the keyword of the filter
   */
  String getKeyword();

  // TODO implement filters
}
