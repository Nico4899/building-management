package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

/**
 * This interface describes a generic building management filter for building or room collections.
 *
 * @param <T> the object type to filter a collection of
 */
public interface Filter<T> {
  void filter();
}
