package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import java.util.Collection;

public interface Filter<T>{
  Collection<T> filter();
}
