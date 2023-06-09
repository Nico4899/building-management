package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.dto;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.GeographicalLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * This class represents a dto for a create room request, to the building domain.
 *
 * @author Bastian Bacher
 */
@Getter
@Setter
@NoArgsConstructor
public class ClientCreateRoomRequest {
  private int floor;
  private String name;
  private String number;
  private String parentIdentificationNumber;
  private GeographicalLocation geographicalLocation;
  private Room.Type type;
}
