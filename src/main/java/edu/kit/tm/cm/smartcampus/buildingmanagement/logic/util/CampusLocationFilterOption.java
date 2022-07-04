package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.util;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.CampusLocation;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class CampusLocationFilterOption {
  private boolean selected;
  private Collection<CampusLocation> filterValues;
}
