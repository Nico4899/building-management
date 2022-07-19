package edu.kit.tm.cm.smartcampus.buildingmanagement.api.utilitly;

import com.google.protobuf.Timestamp;
import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import lombok.AllArgsConstructor;

import java.util.Collection;

/** The type grpc object writer. */
@AllArgsConstructor
@org.springframework.stereotype.Component
public class GrpcObjectWriter {

  /**
   * Write grpc component.
   *
   * @param component the component
   * @return the grpc component
   */
  public GrpcComponent write(Component component) {
    return GrpcComponent.newBuilder()
        .setComponentDescription(component.getComponentDescription())
        .setGeographicalLocation(write(component.getGeographicalLocation()))
        .setParentIdentificationNumber(component.getParentIdentificationNumber())
        .setIdentificationNumber(component.getIdentificationNumber())
        .build();
  }

  /**
   * Write grpc room.
   *
   * @param room the room
   * @return the grpc room
   */
  public GrpcRoom write(Room room) {
    return GrpcRoom.newBuilder()
        .setFloor(room.getFloor())
        .setGeographicalLocation(write(room.getGeographicalLocation()))
        .setRoomName(room.getRoomName())
        .setRoomNumber(room.getRoomNumber())
        .setParentIdentificationNumber(room.getParentIdentificationNumber())
        .setRoomType(write(room.getRoomType()))
        .setIdentificationNumber(room.getIdentificationNumber())
        .build();
  }

  /**
   * Write grpc building.
   *
   * @param building the building
   * @return the grpc building
   */
  public GrpcBuilding write(Building building) {
    return GrpcBuilding.newBuilder()
        .setBuildingName(building.getBuildingName())
        .setBuildingNumber(building.getBuildingNumber())
        .setIdentificationNumber(building.getIdentificationNumber())
        .setCampusLocation(write(building.getCampusLocation()))
        .setGeographicalLocation(write(building.getGeographicalLocation()))
        .setNumFloors(building.getNumFloors())
        .build();
  }

  /**
   * Write grpc notification.
   *
   * @param notification the notification
   * @return the grpc notification
   */
  public GrpcNotification write(Notification notification) {
    return GrpcNotification.newBuilder()
        .setNotificationTitle(notification.getNotificationTitle())
        .setNotificationDescription(notification.getNotificationDescription())
        .setParentIdentificationNumber(notification.getParentIdentificationNumber())
        .setIdentificationNumber(notification.getIdentificationNumber())
        .setCreationTime(
            Timestamp.newBuilder().setNanos(notification.getCreationTime().getNanos()).build())
        .build();
  }

  /**
   * Write buildings collection.
   *
   * @param buildings the buildings
   * @return the collection
   */
  public Collection<GrpcBuilding> writeBuildings(Collection<Building> buildings) {
    return buildings.stream().map(this::write).toList();
  }

  /**
   * Write components collection.
   *
   * @param components the components
   * @return the collection
   */
  public Collection<GrpcComponent> writeComponents(Collection<Component> components) {
    return components.stream().map(this::write).toList();
  }

  /**
   * Write rooms collection.
   *
   * @param rooms the rooms
   * @return the collection
   */
  public Collection<GrpcRoom> writeRooms(Collection<Room> rooms) {
    return rooms.stream().map(this::write).toList();
  }

  /**
   * Write notifications collection.
   *
   * @param notifications the notifications
   * @return the collection
   */
  public Collection<GrpcNotification> writeNotifications(Collection<Notification> notifications) {
    return notifications.stream().map(this::write).toList();
  }

  /**
   * Write grpc geographical location.
   *
   * @param geographicalLocation the geographical location
   * @return the grpc geographical location
   */
  public GrpcGeographicalLocation write(GeographicalLocation geographicalLocation) {
    return GrpcGeographicalLocation.newBuilder()
        .setLatitude(geographicalLocation.getLatitude())
        .setLongitude(geographicalLocation.getLongitude())
        .build();
  }

  /**
   * Write grpc campus location.
   *
   * @param campusLocation the campus location
   * @return the grpc campus location
   */
  public GrpcCampusLocation write(CampusLocation campusLocation) {
    return GrpcCampusLocation.forNumber(campusLocation.ordinal() + 1);
  }

  /**
   * Write grpc room type.
   *
   * @param roomType the room type
   * @return the grpc room type
   */
  public GrpcRoomType write(RoomType roomType) {
    return GrpcRoomType.forNumber(roomType.ordinal() + 1);
  }
}
