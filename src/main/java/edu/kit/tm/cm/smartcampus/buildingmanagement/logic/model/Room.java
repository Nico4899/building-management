package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
  private int floor;
  private String roomName;
  private String roomNumber;
  private String identificationNumber;
  private String parentIdentificationNumber;
  private GeographicalLocation geographicalLocation;
  private RoomType roomType;
}
