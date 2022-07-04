package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.util;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.RoomType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class RoomTypeFilterOption {
  private boolean selected;
  private Collection<RoomType> filterValues;
}
