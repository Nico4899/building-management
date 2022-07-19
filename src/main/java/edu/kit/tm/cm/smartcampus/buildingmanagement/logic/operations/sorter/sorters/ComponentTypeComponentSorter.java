package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.sorters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.Sorter;

import java.util.Collection;
import java.util.Comparator;

/**
 * This class represents an implementation of {@link Sorter}, it sorts a collection of {@link
 * Component} by {@link ComponentType}.
 */
public class ComponentTypeComponentSorter implements Sorter<Component> {

  @Override
  public Collection<Component> sort(Collection<Component> collection) {
    return collection.stream().sorted(Comparator.comparing(Component::getComponentType)).toList();
  }
}
