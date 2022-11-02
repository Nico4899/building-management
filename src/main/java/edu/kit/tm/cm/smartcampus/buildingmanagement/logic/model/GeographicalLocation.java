package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a geographical location for {@link Room}, {@link Building} and {@link
 * Component} containing of coordinates.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
@NoArgsConstructor
@Getter
@Setter
public class GeographicalLocation {
  private double latitude;
  private double longitude;
}
