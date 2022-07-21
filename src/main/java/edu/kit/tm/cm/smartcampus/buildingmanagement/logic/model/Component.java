package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InternalServerErrorException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class describes a component model object which can be held by {@link Building} or {@link
 * Room}.
 *
 * @version 1.0
 * @author Bastian Bacher
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Component {
  private String componentDescription;
  private String identificationNumber;
  private double latitude;
  private double longitude;
  private String parentIdentificationNumber;
  private Type type;

  /**
   * This enum describes various types a {@link Component} can have.
   *
   * @version 1.0
   * @author Bastian Bacher
   */
  public enum Type {
    /** Elevator type. */
    ELEVATOR,
    /** Stairs type. */
    STAIRS;

    /**
     * This static method provides a {@link Type} for its ordinal.
     *
     * @param ordinal the ordinal number of the enum constant
     * @return the type with the ordinal number
     */
    public static Type forOrdinal(int ordinal) {
      if (ordinal > values().length || ordinal < 0)
        throw new InternalServerErrorException("value with ordinal does not exist");
      return values()[ordinal];
    }
  }
}
