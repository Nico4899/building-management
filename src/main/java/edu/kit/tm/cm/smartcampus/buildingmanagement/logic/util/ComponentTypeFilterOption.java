package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.util;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.ComponentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class ComponentTypeFilterOption {
  private boolean selected;
  private Collection<ComponentType> filterValues;
}
