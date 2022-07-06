package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
@Builder
public class FilterOption<T>{
  private boolean selected;
  private Collection<T> filterValues;
}
