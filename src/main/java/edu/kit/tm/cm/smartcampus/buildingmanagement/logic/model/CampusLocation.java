package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

public enum CampusLocation {
  UNKNOWN_CAMPUS_LOCATION,
  NORTH_CAMPUS,
  EAST_CAMPUS,
  SOUTH_CAMPUS,
  WEST_CAMPUS;

  public static CampusLocation forNumber(int value) {
    return switch (value) {
      case 1 -> NORTH_CAMPUS;
      case 2 -> EAST_CAMPUS;
      case 3 -> WEST_CAMPUS;
      case 4 -> SOUTH_CAMPUS;
      default -> null;
    };
  }
}
