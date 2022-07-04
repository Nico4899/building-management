package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

@AllArgsConstructor
public class BuildingComponentTypeFilter implements Filter<Building>{

  private Map<Building, Collection<Component>>  buildingComponentMap;
  private Collection<ComponentType> filterValues;

  @Override
  public Collection<Building> filter() {
    Collection<Building> filteredCollection = new LinkedList<>();
    for (Map.Entry<Building, Collection<Component>> entry: buildingComponentMap.entrySet()) {
      for (Component component: entry.getValue()){
        if (filterValues.contains(component.getComponentType())) {
          filteredCollection.add(entry.getKey());
        }
      }
    }
    return filteredCollection;
  }
}
