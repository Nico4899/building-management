package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;

/**
 * This class represents an implementation of {@link Filter}, it filters a collection of generic
 * objects by component types.
 */
@AllArgsConstructor
public class ComponentTypeFilter<T> implements Filter<T> {

  private Map<T, Collection<Component>> genericComponentMap;
  private Collection<ComponentType> filterValues;
  private Collection<T> collectionToFilter;

  @Override
  public void filter() {
    for (Map.Entry<T, Collection<Component>> entry : genericComponentMap.entrySet()) {
      for (Component component : entry.getValue()) {
        if (filterValues.contains(component.getComponentType())) {
          collectionToFilter.remove(entry.getKey());
        }
      }
    }
  }
}
