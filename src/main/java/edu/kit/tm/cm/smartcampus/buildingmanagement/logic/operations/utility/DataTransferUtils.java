package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.utility;

import com.google.protobuf.Timestamp;
import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.dto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.Service;
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
 * {@link ServerResponseWriter} or {@link ServerRequestReader}. These utilities use static methods
 * to provide global service utility logic.
 *
 * @version 1.0
 * @author Bastian Bacher, Dennis Fadeev
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataTransferUtils {

  /**
   * This class represents a writing class which translates model objects to gRPC or REST data
   * transfer objects.
   *
   * @version 1.0
   * @author Bastian Bacher, Dennis Fadeev
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ServerResponseWriter {

    public static GetBuildingResponse writeGetBuildingResponse(Building building) {
      return GetBuildingResponse.newBuilder().setGrpcBuilding(writeGrpcBuilding(building)).build();
    }

    public static GetRoomResponse writeGetRoomResponse(Room room) {
      return GetRoomResponse.newBuilder().setRoom(writeGrpcRoom(room)).build();
    }

    public static GetComponentResponse writeGetComponentResponse(Component component) {
      return GetComponentResponse.newBuilder().setComponent(writeGrpcComponent(component)).build();
    }

    public static CreateBuildingResponse writeCreateBuildingResponse(Building building) {
      return CreateBuildingResponse.newBuilder().setBuilding(writeGrpcBuilding(building)).build();
    }

    public static CreateRoomResponse writeCreateRoomResponse(Room room) {
      return CreateRoomResponse.newBuilder().setRoom(writeGrpcRoom(room)).build();
    }

    public static CreateComponentResponse writeCreateComponentResponse(Component component) {
      return CreateComponentResponse.newBuilder()
          .setComponent(writeGrpcComponent(component))
          .build();
    }

    public static CreateFavoriteResponse writeCreateFavoriteResponse() {
      return CreateFavoriteResponse.newBuilder().build();
    }

    public static UpdateBuildingResponse writeUpdateBuildingResponse(Building building) {
      return UpdateBuildingResponse.newBuilder().setBuilding(writeGrpcBuilding(building)).build();
    }

    public static UpdateRoomResponse writeUpdateRoomResponse(Room room) {
      return UpdateRoomResponse.newBuilder().setRoom(writeGrpcRoom(room)).build();
    }

    public static UpdateComponentResponse writeUpdateComponentResponse(Component component) {
      return UpdateComponentResponse.newBuilder()
          .setComponent(writeGrpcComponent(component))
          .build();
    }

    public static ListBuildingsResponse writeListBuildingsResponse(Collection<Building> buildings) {
      return ListBuildingsResponse.newBuilder()
          .addAllBuildings(buildings.stream().map(ServerResponseWriter::writeGrpcBuilding).toList())
          .build();
    }

    public static ListRoomsResponse writeListRoomsResponse(Collection<Room> rooms) {
      return ListRoomsResponse.newBuilder()
          .addAllRooms(rooms.stream().map(ServerResponseWriter::writeGrpcRoom).toList())
          .build();
    }

    public static ListComponentsResponse writeListComponentsResponse(
        Collection<Component> components) {
      return ListComponentsResponse.newBuilder()
          .addAllComponents(
              components.stream().map(ServerResponseWriter::writeGrpcComponent).toList())
          .build();
    }

    public static ListNotificationsResponse writeListNotificationsResponse(
        Collection<Notification> notifications) {
      return ListNotificationsResponse.newBuilder()
          .addAllNotifications(
              notifications.stream().map(ServerResponseWriter::writeGrpcNotification).toList())
          .build();
    }

    public static ListFavoriteBuildingsResponse writeListFavoriteBuildingsResponse(
        Collection<Building> buildings) {
      return ListFavoriteBuildingsResponse.newBuilder()
          .addAllBuildings(buildings.stream().map(ServerResponseWriter::writeGrpcBuilding).toList())
          .build();
    }

    public static ListFavoriteRoomsResponse writeListFavoriteRoomsResponse(Collection<Room> rooms) {
      return ListFavoriteRoomsResponse.newBuilder()
          .addAllRooms(rooms.stream().map(ServerResponseWriter::writeGrpcRoom).toList())
          .build();
    }

    public static ListFavoriteComponentsResponse writeListFavoriteComponentsResponse(
        Collection<Component> components) {
      return ListFavoriteComponentsResponse.newBuilder()
          .addAllComponents(
              components.stream().map(ServerResponseWriter::writeGrpcComponent).toList())
          .build();
    }

    public static RemoveResponse writeRemoveResponse() {
      return RemoveResponse.newBuilder().build();
    }

    private static GrpcComponent writeGrpcComponent(Component component) {
      return GrpcComponent.newBuilder()
          .setComponentDescription(component.getDescription())
          .setGrpcGeographicalLocation(
              writeGrpcGeographicalLocation(component.getGeographicalLocation()))
          .setParentIdentificationNumber(component.getParentIdentificationNumber())
          .setIdentificationNumber(component.getIdentificationNumber())
          .build();
    }

    private static GrpcRoom writeGrpcRoom(Room room) {
      return GrpcRoom.newBuilder()
          .setFloor(room.getFloor())
          .setGrpcGeographicalLocation(
              writeGrpcGeographicalLocation(room.getGeographicalLocation()))
          .setRoomName(room.getName())
          .setRoomNumber(room.getNumber())
          .setParentIdentificationNumber(room.getParentIdentificationNumber())
          .setRoomType(writeGrpcRoomType(room.getType()))
          .setIdentificationNumber(room.getIdentificationNumber())
          .build();
    }

    private static GrpcBuilding writeGrpcBuilding(Building building) {
      return GrpcBuilding.newBuilder()
          .setBuildingName(building.getName())
          .setBuildingNumber(building.getNumber())
          .setIdentificationNumber(building.getIdentificationNumber())
          .setCampusLocation(writeGrpcCampusLocation(building.getCampusLocation()))
          .setGrpcGeographicalLocation(
              writeGrpcGeographicalLocation(building.getGeographicalLocation()))
          .setGrpcFloors(writeGrpcFloors(building.getFloors()))
          .build();
    }

    private static GrpcNotification writeGrpcNotification(Notification notification) {
      return GrpcNotification.newBuilder()
          .setNotificationTitle(notification.getTitle())
          .setNotificationDescription(notification.getDescription())
          .setParentIdentificationNumber(notification.getParentIdentificationNumber())
          .setIdentificationNumber(notification.getIdentificationNumber())
          .setCreationTime(
              Timestamp.newBuilder().setNanos(notification.getCreationTime().getNanos()).build())
          .build();
    }

    private static GrpcFloors writeGrpcFloors(Floors floors) {
      return GrpcFloors.newBuilder()
          .setLowestFloor(floors.getLowestFloor())
          .setHighestFloor(floors.getHighestFloor())
          .build();
    }

    private static GrpcCampusLocation writeGrpcCampusLocation(
        Building.CampusLocation campusLocation) {
      return Enum.valueOf(GrpcCampusLocation.class, campusLocation.name());
    }

    private static GrpcRoomType writeGrpcRoomType(Room.Type roomType) {
      return Enum.valueOf(GrpcRoomType.class, roomType.name());
    }

    private static GrpcGeographicalLocation writeGrpcGeographicalLocation(
        GeographicalLocation geographicalLocation) {
      return GrpcGeographicalLocation.newBuilder()
          .setLongitude(geographicalLocation.getLongitude())
          .setLatitude(geographicalLocation.getLatitude())
          .build();
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
  public static class ServerRequestReader {

    public static Building readGetBuildingRequest(
        GetBuildingRequest getBuildingRequest, Service service) {
      return service.getBuilding(getBuildingRequest.getIdentificationNumber());
    }

    public static Room readGetRoomRequest(GetRoomRequest getRoomRequest, Service service) {
      return service.getRoom(getRoomRequest.getIdentificationNumber());
    }

    public static Component readGetComponentRequest(
        GetComponentRequest getComponentRequest, Service service) {
      return service.getComponent(getComponentRequest.getIdentificationNumber());
    }

    public static Building readCreateBuildingRequest(
        CreateBuildingRequest createBuildingRequest, Service service) {
      Building building = new Building();
      building.setFloors(readFloors(createBuildingRequest.getGrpcFloors()));
      building.setNumber(createBuildingRequest.getBuildingNumber());
      building.setName(createBuildingRequest.getBuildingName());
      building.setCampusLocation(readCampusLocation(createBuildingRequest.getCampusLocation()));
      building.setGeographicalLocation(
          readGeographicalLocation(createBuildingRequest.getGrpcGeographicalLocation()));
      return service.createBuilding(building);
    }

    public static Building readUpdateBuildingRequest(
        UpdateBuildingRequest updateBuildingRequest, Service service) {
      Building building = new Building();
      building.setIdentificationNumber(updateBuildingRequest.getIdentificationNumber());
      building.setFloors(readFloors(updateBuildingRequest.getGrpcFloors()));
      building.setNumber(updateBuildingRequest.getBuildingNumber());
      building.setName(updateBuildingRequest.getBuildingName());
      building.setCampusLocation(readCampusLocation(updateBuildingRequest.getCampusLocation()));
      building.setGeographicalLocation(
          readGeographicalLocation(updateBuildingRequest.getGrpcGeographicalLocation()));
      return service.updateBuilding(building);
    }

    public static Room readCreateRoomRequest(CreateRoomRequest createRoomRequest, Service service) {
      Room room = new Room();
      room.setType(readRoomType(createRoomRequest.getRoomType()));
      room.setNumber(createRoomRequest.getRoomNumber());
      room.setName(createRoomRequest.getRoomName());
      room.setFloor(createRoomRequest.getFloor());
      room.setParentIdentificationNumber(createRoomRequest.getParentIdentificationNumber());
      room.setGeographicalLocation(
          readGeographicalLocation(createRoomRequest.getGrpcGeographicalLocation()));
      return service.createRoom(room);
    }

    public static Room readUpdateRoomRequest(UpdateRoomRequest updateRoomRequest, Service service) {
      Room room = new Room();
      room.setIdentificationNumber(updateRoomRequest.getIdentificationNumber());
      room.setNumber(updateRoomRequest.getRoomNumber());
      room.setName(updateRoomRequest.getRoomName());
      room.setFloor(updateRoomRequest.getFloor());
      room.setParentIdentificationNumber(updateRoomRequest.getParentIdentificationNumber());
      room.setGeographicalLocation(
          readGeographicalLocation(updateRoomRequest.getGrpcGeographicalLocation()));
      room.setType(readRoomType(updateRoomRequest.getRoomType()));
      return service.updateRoom(room);
    }

    public static Component readCreateComponentRequest(
        CreateComponentRequest createComponentRequest, Service service) {
      Component component = new Component();
      component.setDescription(createComponentRequest.getComponentDescription());
      component.setParentIdentificationNumber(
          createComponentRequest.getParentIdentificationNumber());
      component.setGeographicalLocation(
          readGeographicalLocation(createComponentRequest.getGrpcGeographicalLocation()));
      component.setType(readComponentType(createComponentRequest.getComponentType()));
      return service.createComponent(component);
    }

    public static Component readUpdateComponentRequest(
        UpdateComponentRequest updateComponentRequest, Service service) {
      Component component = new Component();
      component.setIdentificationNumber(updateComponentRequest.getIdentificationNumber());
      component.setDescription(updateComponentRequest.getComponentDescription());
      component.setParentIdentificationNumber(
          updateComponentRequest.getParentIdentificationNumber());
      component.setGeographicalLocation(
          readGeographicalLocation(updateComponentRequest.getGrpcGeographicalLocation()));
      component.setType(readComponentType(updateComponentRequest.getComponentType()));
      return service.updateComponent(component);
    }

    public static void readCreateFavoriteRequest(
        CreateFavoriteRequest createFavoriteRequest, Service service) {
      Favorite favorite = new Favorite();
      favorite.setOwner(createFavoriteRequest.getOwner());
      favorite.setReferenceIdentificationNumber(
          createFavoriteRequest.getReferenceIdentificationNumber());
      service.createFavorite(favorite);
    }

    public static Collection<Building> readListBuildingsRequest(
        ListBuildingsRequest listBuildingsRequest, Service service) {
      return service.listBuildings(
          readListBuildingSettings(listBuildingsRequest.getGrpcListSettings()));
    }

    public static Collection<Room> readListRoomsRequest(
        ListRoomsRequest listRoomsRequest, Service service) {
      return service.listRooms(
          readListRoomsSettings(listRoomsRequest.getGrpcListSettings()),
          listRoomsRequest.getIdentificationNumber());
    }

    public static Collection<Component> readListComponentsRequest(
        ListComponentsRequest listComponentsRequest, Service service) {
      return service.listComponents(listComponentsRequest.getIdentificationNumber());
    }

    public static Collection<Notification> readListNotificationsRequest(
        ListNotificationsRequest listNotificationsRequest, Service service) {
      return service.listNotifications(
          readListNotificationsSettings(listNotificationsRequest.getGrpcListSettings()),
          listNotificationsRequest.getIdentificationNumber());
    }

    public static Collection<Building> readListFavoriteBuildings(
        ListFavoriteBuildingsRequest listFavoriteBuildingsRequest, Service service) {
      return service.listFavoriteBuildings(
          readListBuildingSettings(listFavoriteBuildingsRequest.getGrpcListSettings()),
          listFavoriteBuildingsRequest.getOwner());
    }

    public static Collection<Room> readListFavoriteRooms(
        ListFavoriteRoomsRequest listFavoriteRoomsRequest, Service service) {
      return service.listFavoriteRooms(
          readListRoomsSettings(listFavoriteRoomsRequest.getGrpcListSettings()),
          listFavoriteRoomsRequest.getOwner());
    }

    public static Collection<Component> readListFavoriteComponentsRequest(
        ListFavoriteComponentsRequest listFavoriteComponentsRequest, Service service) {
      return service.listFavoriteComponents(listFavoriteComponentsRequest.getOwner());
    }

    public static void readRemoveBuildingRequest(RemoveRequest removeRequest, Service service) {
      service.removeBuilding(removeRequest.getIdentificationNumber());
    }

    public static void readRemoveComponentRequest(RemoveRequest removeRequest, Service service) {
      service.removeComponent(removeRequest.getIdentificationNumber());
    }

    public static void readRemoveRoomRequest(RemoveRequest removeRequest, Service service) {
      service.removeRoom(removeRequest.getIdentificationNumber());
    }

    public static void readRemoveFavoriteRequest(RemoveRequest removeRequest, Service service) {
      service.removeFavorite(removeRequest.getIdentificationNumber());
    }

    private static GeographicalLocation readGeographicalLocation(
        GrpcGeographicalLocation grpcGeographicalLocation) {
      GeographicalLocation geographicalLocation = new GeographicalLocation();
      geographicalLocation.setLongitude(grpcGeographicalLocation.getLongitude());
      geographicalLocation.setLatitude(grpcGeographicalLocation.getLatitude());
      return geographicalLocation;
    }

    private static Floors readFloors(GrpcFloors grpcFloors) {
      Floors floors = new Floors();
      floors.setLowestFloor(grpcFloors.getLowestFloor());
      floors.setHighestFloor(grpcFloors.getHighestFloor());
      return floors;
    }

    private static Room.Type readRoomType(GrpcRoomType grpcRoomType) {
      return Enum.valueOf(Room.Type.class, grpcRoomType.name());
    }

    private static Building.CampusLocation readCampusLocation(
        GrpcCampusLocation grpcCampusLocation) {
      return Enum.valueOf(Building.CampusLocation.class, grpcCampusLocation.name());
    }

    private static Component.Type readComponentType(GrpcComponentType grpcComponentType) {
      return Enum.valueOf(Component.Type.class, grpcComponentType.name());
    }

    private static Settings<Building> readListBuildingSettings(GrpcListSettings grpcListSettings) {
      Map<Filter<Building>, Collection<?>> filters = new HashMap<>();
      if (grpcListSettings.getSelection().getCampusLocationFilterSelected()) {
        filters.put(
            BuildingFilter.CAMPUS_LOCATION_FILTER,
            grpcListSettings.getValues().getGrpcCampusLocationsList().stream()
                .map(DataTransferUtils.ServerRequestReader::readCampusLocation)
                .toList());
      }
      if (grpcListSettings.getSelection().getComponentTypeFilterSelected()) {
        filters.put(
            BuildingFilter.COMPONENT_TYPE_FILTER,
            grpcListSettings.getValues().getGrpcComponentTypesList().stream()
                .map(DataTransferUtils.ServerRequestReader::readComponentType)
                .toList());
      }
      if (grpcListSettings.getSelection().getRoomTypeFilterSelected()) {
        filters.put(
            BuildingFilter.ROOM_TYPE_FILTER,
            grpcListSettings.getValues().getGrpcRoomTypesList().stream()
                .map(DataTransferUtils.ServerRequestReader::readRoomType)
                .toList());
      }
      ListSettings<Building> settings = new ListSettings<>();
      settings.setFilters(filters);
      settings.setSorter(readBuildingSorter(grpcListSettings.getSelection().getGrpcSortOption()));
      return settings;
    }

    private static Settings<Room> readListRoomsSettings(GrpcListSettings grpcListSettings) {
      Map<Filter<Room>, Collection<?>> filters = new HashMap<>();
      if (grpcListSettings.getSelection().getCampusLocationFilterSelected()) {
        filters.put(RoomFilter.FLOOR_FILTER, grpcListSettings.getValues().getFloorsList());
      }
      if (grpcListSettings.getSelection().getComponentTypeFilterSelected()) {
        filters.put(
            RoomFilter.COMPONENT_TYPE_FILTER,
            grpcListSettings.getValues().getGrpcComponentTypesList().stream()
                .map(DataTransferUtils.ServerRequestReader::readComponentType)
                .toList());
      }
      if (grpcListSettings.getSelection().getRoomTypeFilterSelected()) {
        filters.put(
            RoomFilter.ROOM_TYPE_FILTER,
            grpcListSettings.getValues().getGrpcRoomTypesList().stream()
                .map(DataTransferUtils.ServerRequestReader::readRoomType)
                .toList());
      }
      ListSettings<Room> settings = new ListSettings<>();
      settings.setFilters(filters);
      settings.setSorter(readRoomSorter(grpcListSettings.getSelection().getGrpcSortOption()));
      return settings;
    }

    private static Settings<Notification> readListNotificationsSettings(
        GrpcListSettings grpcListSettings) {
      Map<Filter<Notification>, Collection<?>> filters = new HashMap<>();
      ListSettings<Notification> settings = new ListSettings<>();
      settings.setFilters(filters);
      settings.setSorter(
          readNotificationSorter(grpcListSettings.getSelection().getGrpcSortOption()));
      return settings;
    }

    private static Sorter<Building> readBuildingSorter(GrpcSortOption grpcSortOption) {
      return Enum.valueOf(BuildingSorter.class, grpcSortOption.name());
    }

    private static Sorter<Room> readRoomSorter(GrpcSortOption grpcSortOption) {
      return Enum.valueOf(RoomSorter.class, grpcSortOption.name());
    }

    private static Sorter<Notification> readNotificationSorter(GrpcSortOption grpcSortOption) {
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
