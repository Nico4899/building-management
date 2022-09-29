package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class describes a component model object which can be held by {@link Building} or
 * {@link Room}.
 *
 * @author Bastian Bacher, Dennis Fadeev
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
   * @author Bastian Bacher, Dennis Fadeev
   */
  public enum Type {
    /**
     * Elevator.
     */
    ELEVATOR,
    /**
     * Stairs.
     */
    STAIRS,
    /**
     * Door.
     */
    DOOR,
    /**
     * Ramp.
     */
    RAMP,
    /**
     * Chair.
     */
    CHAIR,
    /**
     * Table.
     */
    TABLE,
    /**
     * Lamp.
     */
    LAMP,
    /**
     * Window.
     */
    WINDOW,
    /**
     * Canvas.
     */
    CANVAS,
    /**
     * Whiteboard.
     */
    WHITEBOARD,
    /**
     * Beamer.
     */
    BEAMER,
    /**
     * Power outlet.
     */
    POWER_OUTLET,
    /**
     * Sink.
     */
    SINK,
    /**
     * Heater.
     */
    HEATER
  }
}
