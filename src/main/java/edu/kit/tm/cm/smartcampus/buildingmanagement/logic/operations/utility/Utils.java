package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.utility;

import com.google.protobuf.Timestamp;
import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.dto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.BuildingFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.RoomFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.settings.ListSettings;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.settings.Settings;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.BuildingSorter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.NotificationSorter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.RoomSorter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.Sorter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a utility class for this service, it contains of nested utilities, such as
 * {@link Writer} or {@link Reader}. These utilities use static methods to provide global service
 * utility logic.
 *
 * @version 1.0
 * @author Bastian Bacher, Dennis Fadeev
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

  /**
   * This class represents a writing class which translates model objects to gRPC or REST data
   * transfer objects.
   *
   * @version 1.0
   * @author Bastian Bacher, Dennis Fadeev
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Writer {

    /**
     * Write grpc component.
     *
     * @param component the component
     * @return the grpc component
     */
    public static GrpcComponent write(Component component) {
      return GrpcComponent.newBuilder()
          .setComponentDescription(component.getDescription())
          .setGrpcGeographicalLocation(write(component.getGeographicalLocation()))
          .setParentIdentificationNumber(component.getParentIdentificationNumber())
          .setIdentificationNumber(component.getIdentificationNumber())
          .build();
    }

    public static GrpcGeographicalLocation write(GeographicalLocation geographicalLocation) {
      return GrpcGeographicalLocation.newBuilder()
          .setLongitude(geographicalLocation.getLongitude())
          .setLatitude(geographicalLocation.getLatitude())
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
          .setGrpcGeographicalLocation(write(room.getGeographicalLocation()))
          .setRoomName(room.getName())
          .setRoomNumber(room.getNumber())
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
          .setBuildingName(building.getName())
          .setBuildingNumber(building.getNumber())
          .setIdentificationNumber(building.getIdentificationNumber())
          .setCampusLocation(write(building.getCampusLocation()))
          .setGrpcGeographicalLocation(write(building.getGeographicalLocation()))
          .setGrpcFloors(write(building.getFloors()))
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
          .setNotificationTitle(notification.getTitle())
          .setNotificationDescription(notification.getDescription())
          .setParentIdentificationNumber(notification.getParentIdentificationNumber())
          .setIdentificationNumber(notification.getIdentificationNumber())
          .setCreationTime(
              Timestamp.newBuilder().setNanos(notification.getCreationTime().getNanos()).build())
          .build();
    }

    public static GrpcFloors write(Floors floors) {
      return GrpcFloors.newBuilder()
          .setLowestFloor(floors.getLowestFloor())
          .setHighestFloor(floors.getHighestFloor())
          .build();
    }

    /**
     * Write buildings collection.
     *
     * @param buildings the buildings
     * @return the collection
     */
    public static Collection<GrpcBuilding> writeBuildings(Collection<Building> buildings) {
      return buildings.stream().map(Utils.Writer::write).toList();
    }

    /**
     * Write components collection.
     *
     * @param components the components
     * @return the collection
     */
    public static Collection<GrpcComponent> writeComponents(Collection<Component> components) {
      return components.stream().map(Utils.Writer::write).toList();
    }

    /**
     * Write rooms collection.
     *
     * @param rooms the rooms
     * @return the collection
     */
    public static Collection<GrpcRoom> writeRooms(Collection<Room> rooms) {
      return rooms.stream().map(Utils.Writer::write).toList();
    }

    /**
     * Write notifications collection.
     *
     * @param notifications the notifications
     * @return the collection
     */
    public static Collection<GrpcNotification> writeNotifications(
        Collection<Notification> notifications) {
      return notifications.stream().map(Utils.Writer::write).toList();
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

  /**
   * This class represents a reading class which translates gRPC or REST data transfer objects to
   * model objects.
   *
   * @version 1.0
   * @author Bastian Bacher, Dennis Fadeev
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Reader {
    /**
     * Read component.
     *
     * @param grpcComponent the grpc component
     * @return the component
     */
    public static Component read(GrpcComponent grpcComponent) {
      Component component = new Component();
      component.setDescription(grpcComponent.getComponentDescription());
      component.setIdentificationNumber(grpcComponent.getIdentificationNumber());
      component.setParentIdentificationNumber(grpcComponent.getParentIdentificationNumber());
      component.setGeographicalLocation(read(grpcComponent.getGrpcGeographicalLocation()));
      component.setType(read(grpcComponent.getComponentType()));
      return component;
    }

    /**
     * Read component.
     *
     * @param grpcGeographicalLocation the grpc component
     * @return the component
     */
    public static GeographicalLocation read(GrpcGeographicalLocation grpcGeographicalLocation) {
      GeographicalLocation geographicalLocation = new GeographicalLocation();
      geographicalLocation.setLongitude(grpcGeographicalLocation.getLongitude());
      geographicalLocation.setLatitude(grpcGeographicalLocation.getLatitude());
      return geographicalLocation;
    }

    /**
     * Read component.
     *
     * @param grpcFloors the grpc component
     * @return the component
     */
    public static Floors read(GrpcFloors grpcFloors) {
      Floors floors = new Floors();
      floors.setLowestFloor(grpcFloors.getLowestFloor());
      floors.setHighestFloor(grpcFloors.getHighestFloor());
      return floors;
    }

    /**
     * Read favorite.
     *
     * @param grpcFavorite the grpc favorite
     * @return the favorite
     */
    public static Favorite read(GrpcFavorite grpcFavorite) {
      Favorite favorite = new Favorite();
      favorite.setOwner(grpcFavorite.getOwner());
      favorite.setReferenceIdentificationNumber(grpcFavorite.getReferenceIdentificationNumber());
      return favorite;
    }

    /**
     * Read building.
     *
     * @param grpcBuilding the grpc building
     * @return the building
     */
    public static Building read(GrpcBuilding grpcBuilding) {
      Building building = new Building();
      building.setNumber(grpcBuilding.getBuildingNumber());
      building.setName(grpcBuilding.getBuildingName());
      building.setCampusLocation(read(grpcBuilding.getCampusLocation()));
      building.setFloors(read(grpcBuilding.getGrpcFloors()));
      building.setIdentificationNumber(grpcBuilding.getIdentificationNumber());
      building.setGeographicalLocation(read(grpcBuilding.getGrpcGeographicalLocation()));
      return building;
    }

    /**
     * Read room.
     *
     * @param grpcRoom the grpc room
     * @return the room
     */
    public static Room read(GrpcRoom grpcRoom) {
      Room room = new Room();
      room.setNumber(grpcRoom.getRoomNumber());
      room.setName(grpcRoom.getRoomName());
      room.setFloor(grpcRoom.getFloor());
      room.setParentIdentificationNumber(grpcRoom.getParentIdentificationNumber());
      room.setGeographicalLocation(read(grpcRoom.getGrpcGeographicalLocation()));
      room.setType(read(grpcRoom.getRoomType()));
      room.setIdentificationNumber(grpcRoom.getIdentificationNumber());
      return room;
    }

    /**
     * Read room type.
     *
     * @param grpcRoomType the grpc room type
     * @return the room type
     */
    public static Room.Type read(GrpcRoomType grpcRoomType) {
      return Enum.valueOf(Room.Type.class, grpcRoomType.name());
    }

    /**
     * Read campus location.
     *
     * @param grpcCampusLocation the grpc campus location
     * @return the campus location
     */
    public static Building.CampusLocation read(GrpcCampusLocation grpcCampusLocation) {
      return Enum.valueOf(Building.CampusLocation.class, grpcCampusLocation.name());
    }

    /**
     * Read component type.
     *
     * @param grpcComponentType the grpc component type
     * @return the component type
     */
    public static Component.Type read(GrpcComponentType grpcComponentType) {
      return Enum.valueOf(Component.Type.class, grpcComponentType.name());
    }

    /**
     * Read configuration.
     *
     * @param grpcListSettings the grpc configuration object
     * @return model configuration
     */
    public static Settings<Building> readListBuildingSettings(GrpcListSettings grpcListSettings) {
      Map<Filter<Building>, Collection<?>> filters = new HashMap<>();
      if (grpcListSettings.getSelection().getCampusLocationFilterSelected()) {
        filters.put(
            BuildingFilter.CAMPUS_LOCATION_FILTER,
            grpcListSettings.getValues().getGrpcCampusLocationsList().stream()
                .map(Utils.Reader::read)
                .toList());
      }
      if (grpcListSettings.getSelection().getComponentTypeFilterSelected()) {
        filters.put(
            BuildingFilter.COMPONENT_TYPE_FILTER,
            grpcListSettings.getValues().getGrpcComponentTypesList().stream()
                .map(Utils.Reader::read)
                .toList());
      }
      if (grpcListSettings.getSelection().getRoomTypeFilterSelected()) {
        filters.put(
            BuildingFilter.ROOM_TYPE_FILTER,
            grpcListSettings.getValues().getGrpcRoomTypesList().stream()
                .map(Utils.Reader::read)
                .toList());
      }
      ListSettings<Building> settings = new ListSettings<>();
      settings.setFilters(filters);
      settings.setSorter(readBuildingSorter(grpcListSettings.getSelection().getGrpcSortOption()));
      return settings;
    }

    /**
     * Read configuration.
     *
     * @param grpcListSettings the grpc configuration object
     * @return model configuration
     */
    public static Settings<Room> readListRoomsSettings(GrpcListSettings grpcListSettings) {
      Map<Filter<Room>, Collection<?>> filters = new HashMap<>();
      if (grpcListSettings.getSelection().getCampusLocationFilterSelected()) {
        filters.put(RoomFilter.FLOOR_FILTER, grpcListSettings.getValues().getFloorsList());
      }
      if (grpcListSettings.getSelection().getComponentTypeFilterSelected()) {
        filters.put(
            RoomFilter.COMPONENT_TYPE_FILTER,
            grpcListSettings.getValues().getGrpcComponentTypesList().stream()
                .map(Utils.Reader::read)
                .toList());
      }
      if (grpcListSettings.getSelection().getRoomTypeFilterSelected()) {
        filters.put(
            RoomFilter.ROOM_TYPE_FILTER,
            grpcListSettings.getValues().getGrpcRoomTypesList().stream()
                .map(Utils.Reader::read)
                .toList());
      }
      ListSettings<Room> settings = new ListSettings<>();
      settings.setFilters(filters);
      settings.setSorter(readRoomSorter(grpcListSettings.getSelection().getGrpcSortOption()));
      return settings;
    }

    /**
     * Read configuration.
     *
     * @param grpcListSettings the grpc configuration object
     * @return model configuration
     */
    public static Settings<Notification> readListNotificationsSettings(
        GrpcListSettings grpcListSettings) {
      Map<Filter<Notification>, Collection<?>> filters = new HashMap<>();
      ListSettings<Notification> settings = new ListSettings<>();
      settings.setFilters(filters);
      settings.setSorter(
          readNotificationSorter(grpcListSettings.getSelection().getGrpcSortOption()));
      return settings;
    }

    /**
     * Read a grpc object and return a model object.
     *
     * @param grpcSortOption grpc problem sort option.
     * @return model problem sorter object
     */
    public static Sorter<Building> readBuildingSorter(GrpcSortOption grpcSortOption) {
      return Enum.valueOf(BuildingSorter.class, grpcSortOption.name());
    }

    /**
     * Read a grpc object and return a model object.
     *
     * @param grpcSortOption grpc problem sort option.
     * @return model problem sorter object
     */
    public static Sorter<Room> readRoomSorter(GrpcSortOption grpcSortOption) {
      return Enum.valueOf(RoomSorter.class, grpcSortOption.name());
    }

    /**
     * Read a grpc object and return a model object.
     *
     * @param grpcSortOption grpc problem sort option.
     * @return model problem sorter object
     */
    public static Sorter<Notification> readNotificationSorter(GrpcSortOption grpcSortOption) {
      return Enum.valueOf(NotificationSorter.class, grpcSortOption.name());
    }
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ClientRequestWriter {

    public static ClientCreateBuildingRequest writeClientCreateBuildingRequest(Building building) {
      ClientCreateBuildingRequest clientCreateBuildingRequest = new ClientCreateBuildingRequest();
      clientCreateBuildingRequest.setName(building.getName());
      clientCreateBuildingRequest.setFloors(building.getFloors());
      clientCreateBuildingRequest.setNumber(building.getNumber());
      clientCreateBuildingRequest.setGeographicalLocation(building.getGeographicalLocation());
      return clientCreateBuildingRequest;
    }

    public static ClientUpdateBuildingRequest writeClientUpdateBuildingRequest(Building building) {
      ClientUpdateBuildingRequest clientUpdateBuildingRequest = new ClientUpdateBuildingRequest();
      clientUpdateBuildingRequest.setName(building.getName());
      clientUpdateBuildingRequest.setFloors(building.getFloors());
      clientUpdateBuildingRequest.setNumber(building.getNumber());
      clientUpdateBuildingRequest.setGeographicalLocation(building.getGeographicalLocation());
      clientUpdateBuildingRequest.setIdentificationNumber(building.getIdentificationNumber());
      return clientUpdateBuildingRequest;
    }

    public static ClientCreateRoomRequest writeClientCreateRoomRequest(Room room) {
      ClientCreateRoomRequest clientCreateRoomRequest = new ClientCreateRoomRequest();
      clientCreateRoomRequest.setFloor(room.getFloor());
      clientCreateRoomRequest.setName(room.getName());
      clientCreateRoomRequest.setType(room.getType());
      clientCreateRoomRequest.setGeographicalLocation(room.getGeographicalLocation());
      clientCreateRoomRequest.setParentIdentificationNumber(room.getParentIdentificationNumber());
      clientCreateRoomRequest.setNumber(room.getNumber());
      return clientCreateRoomRequest;
    }

    public static ClientUpdateRoomRequest writeClientUpdateRoomRequest(Room room) {
      ClientUpdateRoomRequest clientUpdateRoomRequest = new ClientUpdateRoomRequest();
      clientUpdateRoomRequest.setFloor(room.getFloor());
      clientUpdateRoomRequest.setName(room.getName());
      clientUpdateRoomRequest.setType(room.getType());
      clientUpdateRoomRequest.setGeographicalLocation(room.getGeographicalLocation());
      clientUpdateRoomRequest.setParentIdentificationNumber(room.getParentIdentificationNumber());
      clientUpdateRoomRequest.setNumber(room.getNumber());
      clientUpdateRoomRequest.setIdentificationNumber(room.getIdentificationNumber());
      return clientUpdateRoomRequest;
    }

    public static ClientCreateComponentRequest writeClientCreateComponentRequest(
        Component component) {
      ClientCreateComponentRequest clientCreateComponentRequest =
          new ClientCreateComponentRequest();
      clientCreateComponentRequest.setDescription(clientCreateComponentRequest.getDescription());
      clientCreateComponentRequest.setType(component.getType());
      clientCreateComponentRequest.setGeographicalLocation(component.getGeographicalLocation());
      clientCreateComponentRequest.setParentIdentificationNumber(
          component.getParentIdentificationNumber());
      return clientCreateComponentRequest;
    }

    public static ClientUpdateComponentRequest writeClientUpdateComponentRequest(
        Component component) {
      ClientUpdateComponentRequest clientUpdateComponentRequest =
          new ClientUpdateComponentRequest();
      clientUpdateComponentRequest.setDescription(component.getDescription());
      clientUpdateComponentRequest.setType(component.getType());
      clientUpdateComponentRequest.setGeographicalLocation(component.getGeographicalLocation());
      clientUpdateComponentRequest.setParentIdentificationNumber(
          component.getParentIdentificationNumber());
      return clientUpdateComponentRequest;
    }
  }
}
