package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.commands;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;

public interface FilterCommand<T> {
  void execute();

  void addFilter(Filter<T> filter);
}
