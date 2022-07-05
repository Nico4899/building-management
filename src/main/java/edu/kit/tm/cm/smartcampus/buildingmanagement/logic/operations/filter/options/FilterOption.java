package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options;

import lombok.Data;

import java.util.Collection;

@Data
public class FilterOption<T>{
  private boolean selected;
  private Collection<T> filterValues;
}
