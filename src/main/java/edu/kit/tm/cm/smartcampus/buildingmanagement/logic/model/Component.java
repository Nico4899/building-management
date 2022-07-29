package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

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
  private String description;
  private String identificationNumber;
  private GeographicalLocation geographicalLocation;
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
    STAIRS
  }
}
