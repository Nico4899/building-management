package edu.kit.tm.cm.smartcampus.buildingmanagement.api.utilitly;

import com.google.protobuf.Timestamp;
import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;

import java.util.Collection;

public final class GrpcObjectWriter {

  private GrpcObjectWriter() {}

  public static GrpcComponent write(Component component) {
    return GrpcComponent.newBuilder()
        .setComponentDescription(component.getComponentDescription())
        .setGeographicalLocation(write(component.getGeographicalLocation()))
        .setParentIdentificationNumber(component.getParentIdentificationNumber())
        .setIdentificationNumber(component.getIdentificationNumber())
        .build();
  }

  public static GrpcRoom write(Room room) {
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

  public static GrpcBuilding write(Building building) {
    return GrpcBuilding.newBuilder()
        .setBuildingName(building.getBuildingName())
        .setBuildingNumber(building.getBuildingNumber())
        .setIdentificationNumber(building.getIdentificationNumber())
        .setCampusLocation(write(building.getCampusLocation()))
        .setGeographicalLocation(write(building.getGeographicalLocation()))
        .setNumFloors(building.getNumFloors())
        .build();
  }

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

  public static Collection<GrpcBuilding> writeBuildings(Collection<Building> buildings) {
    return buildings.stream().map(GrpcObjectWriter::write).toList();
  }

  public static Collection<GrpcComponent> writeComponents(Collection<Component> components) {
    return components.stream().map(GrpcObjectWriter::write).toList();
  }

  public static Collection<GrpcRoom> writeRooms(Collection<Room> rooms) {
    return rooms.stream().map(GrpcObjectWriter::write).toList();
  }

  public static Collection<GrpcNotification> writeNotifications(
      Collection<Notification> notifications) {
    return notifications.stream().map(GrpcObjectWriter::write).toList();
  }

  public static GrpcGeographicalLocation write(GeographicalLocation geographicalLocation) {
    return GrpcGeographicalLocation.newBuilder()
        .setLatitude(geographicalLocation.getLatitude())
        .setLongitude(geographicalLocation.getLongitude())
        .build();
  }

  public static GrpcCampusLocation write(CampusLocation campusLocation) {
    return GrpcCampusLocation.forNumber(campusLocation.ordinal() + 1);
  }

  public static GrpcRoomType write(RoomType roomType) {
    return GrpcRoomType.forNumber(roomType.ordinal() + 1);
  }
}
