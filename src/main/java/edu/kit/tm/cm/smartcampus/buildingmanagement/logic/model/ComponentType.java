package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

public enum ComponentType {
  ELEVATOR,
  STAIRS;

  public static ComponentType forNumber(int value) {
    return switch (value) {
      case 1 -> ELEVATOR;
      case 2 -> STAIRS;
      default -> null;
    };
  }
}
