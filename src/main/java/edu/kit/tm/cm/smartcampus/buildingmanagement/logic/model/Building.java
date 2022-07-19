package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class describes a building with its attributes on KIT campus. It possesses a campus
 * location, name, number, number floors, identification number and a geographical location. It is
 * used as model object for building management logic.
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Building {
  private final Collection<Room> rooms = new ArrayList<>();
  private final Collection<Component> components = new ArrayList<>();
  private int numFloors;
  private CampusLocation campusLocation;
  private String buildingName;
  private String buildingNumber;
  private String identificationNumber;
  private GeographicalLocation geographicalLocation;
}
