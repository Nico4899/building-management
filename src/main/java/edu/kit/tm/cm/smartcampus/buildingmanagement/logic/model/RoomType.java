package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

/**
 * This enum represents the possible room types.
 */
public enum RoomType {
  UNKNOWN_ROOM_TYPE,
  CAFETERIA,
  RESTROOM,
  RESTROOM_HANDICAPPED,
  OFFICE,
  LIBRARY,
  SEMINAR_ROOM,
  LECTURE_ROOM,
  SPORTS;

  /**
   * Get room type by ordinal number.
   *
   * @param value ordinal value
   * @return room type with given ordinal
   */
  public static RoomType forNumber(int value) {
    return switch (value) {
      case 0 -> UNKNOWN_ROOM_TYPE;
      case 1 -> RESTROOM;
      case 2 -> RESTROOM_HANDICAPPED;
      case 3 -> OFFICE;
      case 4 -> LIBRARY;
      case 5 -> SEMINAR_ROOM;
      case 6 -> LECTURE_ROOM;
      case 7 -> SPORTS;
      case 8 -> CAFETERIA;
      default -> null;
    };
  }
}
