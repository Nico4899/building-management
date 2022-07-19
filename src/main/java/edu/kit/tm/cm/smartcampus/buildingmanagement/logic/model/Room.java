package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents a room which can hold {@link Component} and is being held by {@link
 * Building}.
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Room {
  private final Collection<Component> components = new ArrayList<>();
  private int floor;
  private String roomName;
  private String roomNumber;
  private String identificationNumber;
  private String parentIdentificationNumber;
  private GeographicalLocation geographicalLocation;
  private RoomType roomType;
}
