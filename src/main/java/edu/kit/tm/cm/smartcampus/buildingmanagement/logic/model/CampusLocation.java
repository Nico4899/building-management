package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

/**
 * This enum describes campus location.
 */
public enum CampusLocation {
  UNKNOWN_CAMPUS_LOCATION,
  NORTH_CAMPUS,
  EAST_CAMPUS,
  SOUTH_CAMPUS,
  WEST_CAMPUS;

  /**
   * Get campus location by ordinal number.
   *
   * @param value ordinal value
   * @return campus location by value
   */
  public static CampusLocation forNumber(int value) {
    return switch (value) {
      case 0 -> UNKNOWN_CAMPUS_LOCATION;
      case 1 -> NORTH_CAMPUS;
      case 2 -> EAST_CAMPUS;
      case 3 -> WEST_CAMPUS;
      case 4 -> SOUTH_CAMPUS;
      default -> null;
    };
  }
}
