package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import java.util.ArrayList;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a room which can hold {@link Component} and is being held by
 * {@link Building}
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Room {

  private final Collection<Component> components = new ArrayList<>();

  private int floor;
  private String name;
  private String number;
  private String identificationNumber;
  private String parentIdentificationNumber;
  private GeographicalLocation geographicalLocation;
  private Type type;

  /**
   * This enum describes various types a {@link Component} can have.
   *
   * @author Bastian Bacher, Dennis Fadeev
   */
  public enum Type {
    /**
     * Cafeteria.
     */
    CAFETERIA,
    /**
     * Restroom.
     */
    RESTROOM,
    /**
     * Restroom for Handicapped people.
     */
    RESTROOM_HANDICAPPED,
    /**
     * Office.
     */
    OFFICE,
    /**
     * Laboratory.
     */
    LABORATORY,
    /**
     * Seminar room.
     */
    SEMINAR_ROOM,
    /**
     * Lecture Room.
     */
    LECTURE_ROOM,
    /**
     * Sports.
     */
    SPORTS
  }
}
