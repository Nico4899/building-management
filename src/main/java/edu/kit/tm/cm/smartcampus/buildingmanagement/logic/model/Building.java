package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class describes a building model object, it can hold a collection of {@link Room} and {@link
 * Component}*, further it holds information about geographical location and campus location. This
 * class provides {@link CampusLocation} to describe its location on KIT-Campus.
 *
 * @version 1.0
 * @author Bastian Bacher
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Building {
  private final Collection<Room> rooms = new ArrayList<>();
  private final Collection<Component> components = new ArrayList<>();

  private int numFloors;
  private CampusLocation campusLocation;

  private String buildingName;
  private String buildingNumber;

  private String identificationNumber;

  private double longitude;
  private double latitude;

  /**
   * This nested enum contains various KIT-Campus locations.
   *
   * @version 1.0
   * @author Bastian Bacher
   */
  public enum CampusLocation {
    /** North campus location. */
    NORTH_CAMPUS,
    /** East campus location. */
    EAST_CAMPUS,
    /** South campus location. */
    SOUTH_CAMPUS,
    /** West campus location. */
    WEST_CAMPUS
  }
}
