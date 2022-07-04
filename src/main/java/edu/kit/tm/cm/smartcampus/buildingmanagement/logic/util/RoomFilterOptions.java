package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomFilterOptions {
  private RoomTypeFilterOption roomTypeFilterOption;
  private ComponentTypeFilterOption componentTypeFilterOption;
}
