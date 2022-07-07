package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.commands;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;

/**
 * This class represents a filter command.
 *
 * @param <T> the generic value a filter uses
 */
public interface FilterCommand<T> {
  void execute();

  void addFilter(Filter<T> filter);
}
