package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

/**
 * This enum represents all component types.
 */
public enum ComponentType {
  ELEVATOR,
  STAIRS;

  /**
   * Get component type by ordinal number.
   *
   * @param value ordinal value
   * @return component type with given ordinal
   */
  public static ComponentType forNumber(int value) {
    return switch (value) {
      case 1 -> ELEVATOR;
      case 2 -> STAIRS;
      default -> null;
    };
  }
}
