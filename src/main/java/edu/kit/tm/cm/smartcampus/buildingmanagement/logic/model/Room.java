package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InternalServerErrorException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents a room which can hold {@link Component} and is being held by {@link
 * Building}*.
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Room {

  private final Collection<Component> components = new ArrayList<>();

  private int floor;
  private String roomName;
  private String roomNumber;
  private String identificationNumber;
  private String parentIdentificationNumber;
  private double latitude;
  private double longitude;
  private Type type;

  /**
   * This enum describes various types a {@link Component} can have.
   *
   * @version 1.0
   * @author Bastian Bacher
   */
  public enum Type {
    /** Cafeteria type. */
    CAFETERIA,
    /** Restroom type. */
    RESTROOM,
    /** Restroom handicapped type. */
    RESTROOM_HANDICAPPED,
    /** Office type. */
    OFFICE,
    /** Library type. */
    LIBRARY,
    /** Seminar room type. */
    SEMINAR_ROOM,
    /** Lecture room type. */
    LECTURE_ROOM,
    /** Sports type. */
    SPORTS;

    /**
     * This static method provides a {@link Type} for its ordinal.
     *
     * @param ordinal the ordinal number of the enum constant
     * @return the type with the ordinal number
     */
    public static Type forOrdinal(int ordinal) {
      if (ordinal >= values().length || ordinal < 0) throw new InternalServerErrorException();
      return values()[ordinal];
    }
  }
}
