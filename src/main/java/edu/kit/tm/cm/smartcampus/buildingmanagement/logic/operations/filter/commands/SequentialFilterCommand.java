package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.commands;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Implementation of {@link FilterCommand}.
 *
 * @param <T> see in {@link FilterCommand}
 */
@NoArgsConstructor
public class SequentialFilterCommand<T> implements FilterCommand<T> {

  private Collection<Filter<T>> filters;

  @Override
  public void execute() {
    for (Filter<T> filter : filters) {
      filter.filter();
    }
  }

  @Override
  public void addFilter(Filter<T> filter) {
    this.filters.add(filter);
  }
}
