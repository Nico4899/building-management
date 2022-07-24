package edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility;

import com.google.protobuf.Timestamp;
import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;

/** The type grpc object writer. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GrpcObjectWriter {

  /**
   * Write grpc component.
   *
   * @param component the component
   * @return the grpc component
   */
  public static GrpcComponent write(Component component) {
    return GrpcComponent.newBuilder()
        .setComponentDescription(component.getComponentDescription())
        .setLatitude(component.getLatitude())
        .setLongitude(component.getLongitude())
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
  public static GrpcRoom write(Room room) {
    return GrpcRoom.newBuilder()
        .setFloor(room.getFloor())
        .setLatitude(room.getLatitude())
        .setLongitude(room.getLongitude())
        .setRoomName(room.getRoomName())
        .setRoomNumber(room.getRoomNumber())
        .setParentIdentificationNumber(room.getParentIdentificationNumber())
        .setRoomType(write(room.getType()))
        .setIdentificationNumber(room.getIdentificationNumber())
        .build();
  }

  /**
   * Write grpc building.
   *
   * @param building the building
   * @return the grpc building
   */
  public static GrpcBuilding write(Building building) {
    return GrpcBuilding.newBuilder()
        .setBuildingName(building.getBuildingName())
        .setBuildingNumber(building.getBuildingNumber())
        .setIdentificationNumber(building.getIdentificationNumber())
        .setCampusLocation(write(building.getCampusLocation()))
        .setLatitude(building.getLatitude())
        .setLongitude(building.getLongitude())
        .setLowestFloor(building.getLowestFloor())
        .setHighestFloor(building.getHighestFloor())
        .build();
  }

  /**
   * Write grpc notification.
   *
   * @param notification the notification
   * @return the grpc notification
   */
  public static GrpcNotification write(Notification notification) {
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
  public static Collection<GrpcBuilding> writeBuildings(Collection<Building> buildings) {
    return buildings.stream().map(GrpcObjectWriter::write).toList();
  }

  /**
   * Write components collection.
   *
   * @param components the components
   * @return the collection
   */
  public static Collection<GrpcComponent> writeComponents(Collection<Component> components) {
    return components.stream().map(GrpcObjectWriter::write).toList();
  }

  /**
   * Write rooms collection.
   *
   * @param rooms the rooms
   * @return the collection
   */
  public static Collection<GrpcRoom> writeRooms(Collection<Room> rooms) {
    return rooms.stream().map(GrpcObjectWriter::write).toList();
  }

  /**
   * Write notifications collection.
   *
   * @param notifications the notifications
   * @return the collection
   */
  public static Collection<GrpcNotification> writeNotifications(
      Collection<Notification> notifications) {
    return notifications.stream().map(GrpcObjectWriter::write).toList();
  }

  /**
   * Write grpc campus location.
   *
   * @param campusLocation the campus location
   * @return the grpc campus location
   */
  public static GrpcCampusLocation write(Building.CampusLocation campusLocation) {
    return Enum.valueOf(GrpcCampusLocation.class, campusLocation.name());
  }

  /**
   * Write grpc room type.
   *
   * @param roomType the room type
   * @return the grpc room type
   */
  public static GrpcRoomType write(Room.Type roomType) {
    return Enum.valueOf(GrpcRoomType.class, roomType.name());
  }
}
