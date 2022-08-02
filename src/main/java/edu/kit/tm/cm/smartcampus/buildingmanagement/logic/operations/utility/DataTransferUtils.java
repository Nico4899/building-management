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
 * @author Bastian Bacher, Dennis Fadeev
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataTransferUtils {

  /**
   * This class represents a writing class which translates model objects to gRPC or REST data
   * transfer objects.
   *
   * @author Bastian Bacher, Dennis Fadeev
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ServerResponseWriter {

    /**
     * Write a response from {@link Building} to {@link GetBuildingResponse} and return it.
     *
     * @param building the building to be parsed into a response
     * @return the get building response
     */
    public static GetBuildingResponse writeGetBuildingResponse(Building building) {
      return GetBuildingResponse.newBuilder().setGrpcBuilding(writeGrpcBuilding(building)).build();
    }

    /**
     * Write a response from {@link Room} to {@link GetRoomResponse} and return it.
     *
     * @param room the room to be parsed into a response
     * @return the get room response
     */
    public static GetRoomResponse writeGetRoomResponse(Room room) {
      return GetRoomResponse.newBuilder().setRoom(writeGrpcRoom(room)).build();
    }

    /**
     * Write a response from {@link Component} to {@link GetComponentResponse} and return it.
     *
     * @param component the component to be parsed into the response
     * @return the get component response
     */
    public static GetComponentResponse writeGetComponentResponse(Component component) {
      return GetComponentResponse.newBuilder().setComponent(writeGrpcComponent(component)).build();
    }

    /**
     * Write a response from {@link Building} to {@link CreateBuildingResponse} and return it.
     *
     * @param building the building to be parsed into the response
     * @return the create building response
     */
    public static CreateBuildingResponse writeCreateBuildingResponse(Building building) {
      return CreateBuildingResponse.newBuilder().setBuilding(writeGrpcBuilding(building)).build();
    }

    /**
     * Write a response from {@link Room} to {@link GetRoomResponse} and return it.
     *
     * @param room the room to be parsed into the response
     * @return the create room response
     */
    public static CreateRoomResponse writeCreateRoomResponse(Room room) {
      return CreateRoomResponse.newBuilder().setRoom(writeGrpcRoom(room)).build();
    }

    /**
     * Write a response from {@link Component} to {@link CreateComponentResponse} and return it.
     *
     * @param component the component to be parsed into the response
     * @return the create component response
     */
    public static CreateComponentResponse writeCreateComponentResponse(Component component) {
      return CreateComponentResponse.newBuilder()
          .setComponent(writeGrpcComponent(component))
          .build();
    }

    /**
     * Write a response from {@link Void} to {@link CreateComponentResponse} and return it.
     *
     * @return the create favorite response
     */
    public static CreateFavoriteResponse writeCreateFavoriteResponse() {
      return CreateFavoriteResponse.newBuilder().build();
    }

    /**
     * Write a response from {@link Building} to {@link UpdateBuildingResponse} and return it.
     *
     * @param building the building to be parsed into the response
     * @return the update building response
     */
    public static UpdateBuildingResponse writeUpdateBuildingResponse(Building building) {
      return UpdateBuildingResponse.newBuilder().setBuilding(writeGrpcBuilding(building)).build();
    }

    /**
     * Write a response from {@link Room} to {@link UpdateRoomResponse} and return it.
     *
     * @param room the room to be parsed into the response
     * @return the update room response
     */
    public static UpdateRoomResponse writeUpdateRoomResponse(Room room) {
      return UpdateRoomResponse.newBuilder().setRoom(writeGrpcRoom(room)).build();
    }

    /**
     * Write a response from {@link Component} to {@link UpdateComponentResponse} and return it.
     *
     * @param component the component to be parsed into the response
     * @return the update component response
     */
    public static UpdateComponentResponse writeUpdateComponentResponse(Component component) {
      return UpdateComponentResponse.newBuilder()
          .setComponent(writeGrpcComponent(component))
          .build();
    }

    /**
     * Write a response from {@link Collection<Building>} to {@link ListBuildingsResponse} and
     * return it.
     *
     * @param buildings the buildings to be parsed into the response
     * @return the list buildings response
     */
    public static ListBuildingsResponse writeListBuildingsResponse(Collection<Building> buildings) {
      return ListBuildingsResponse.newBuilder()
          .addAllBuildings(buildings.stream().map(ServerResponseWriter::writeGrpcBuilding).toList())
          .build();
    }

    /**
     * Write a response from {@link Collection<Room>} to {@link ListRoomsResponse} and return it.
     *
     * @param rooms the rooms to be parsed into the response
     * @return the list rooms response
     */
    public static ListRoomsResponse writeListRoomsResponse(Collection<Room> rooms) {
      return ListRoomsResponse.newBuilder()
          .addAllRooms(rooms.stream().map(ServerResponseWriter::writeGrpcRoom).toList())
          .build();
    }

    /**
     * Write a response from {@link Collection<Component>} to {@link ListComponentsResponse} and
     * return it.
     *
     * @param components the components to be parsed into the response
     * @return the list components response
     */
    public static ListComponentsResponse writeListComponentsResponse(
        Collection<Component> components) {
      return ListComponentsResponse.newBuilder()
          .addAllComponents(
              components.stream().map(ServerResponseWriter::writeGrpcComponent).toList())
          .build();
    }

    /**
     * Write a response from {@link Collection<Notification>} to {@link ListNotificationsResponse}
     * and return it.
     *
     * @param notifications the notifications to be parsed into the response
     * @return the list notifications response
     */
    public static ListNotificationsResponse writeListNotificationsResponse(
        Collection<Notification> notifications) {
      return ListNotificationsResponse.newBuilder()
          .addAllNotifications(
              notifications.stream().map(ServerResponseWriter::writeGrpcNotification).toList())
          .build();
    }

    /**
     * Write a response from {@link Collection<Building>} to {@link ListFavoriteBuildingsResponse}
     * and return it.
     *
     * @param buildings the buildings
     * @return the list favorite buildings response to be parsed into the response
     */
    public static ListFavoriteBuildingsResponse writeListFavoriteBuildingsResponse(
        Collection<Building> buildings) {
      return ListFavoriteBuildingsResponse.newBuilder()
          .addAllBuildings(buildings.stream().map(ServerResponseWriter::writeGrpcBuilding).toList())
          .build();
    }

    /**
     * Write a response from {@link Collection<Room>} to {@link ListFavoriteRoomsResponse} and
     * return it.
     *
     * @param rooms the rooms
     * @return the list favorite rooms response to be parsed into the response
     */
    public static ListFavoriteRoomsResponse writeListFavoriteRoomsResponse(Collection<Room> rooms) {
      return ListFavoriteRoomsResponse.newBuilder()
          .addAllRooms(rooms.stream().map(ServerResponseWriter::writeGrpcRoom).toList())
          .build();
    }

    /**
     * Write a response from {@link Collection<Component>} to {@link ListFavoriteComponentsResponse}
     * and return it.
     *
     * @param components the components
     * @return the list favorite components response to be parsed into the response
     */
    public static ListFavoriteComponentsResponse writeListFavoriteComponentsResponse(
        Collection<Component> components) {
      return ListFavoriteComponentsResponse.newBuilder()
          .addAllComponents(
              components.stream().map(ServerResponseWriter::writeGrpcComponent).toList())
          .build();
    }

    /**
     * Write a response from {@link Void} to {@link RemoveResponse} and return it.
     *
     * @return the remove response
     */
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
   * @author Bastian Bacher, Dennis Fadeev
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ServerRequestReader {

    /**
     * Read get building request building.
     *
     * @param getBuildingRequest the get building request
     * @param service the service
     * @return the building
     */
    public static Building readGetBuildingRequest(
        GetBuildingRequest getBuildingRequest, Service service) {
      return service.getBuilding(getBuildingRequest.getIdentificationNumber());
    }

    /**
     * Read get room request room.
     *
     * @param getRoomRequest the get room request
     * @param service the service
     * @return the room
     */
    public static Room readGetRoomRequest(GetRoomRequest getRoomRequest, Service service) {
      return service.getRoom(getRoomRequest.getIdentificationNumber());
    }

    /**
     * Read get component request component.
     *
     * @param getComponentRequest the get component request
     * @param service the service
     * @return the component
     */
    public static Component readGetComponentRequest(
        GetComponentRequest getComponentRequest, Service service) {
      return service.getComponent(getComponentRequest.getIdentificationNumber());
    }

    /**
     * Read create building request building.
     *
     * @param createBuildingRequest the create building request
     * @param service the service
     * @return the building
     */
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

    /**
     * Read update building request building.
     *
     * @param updateBuildingRequest the update building request
     * @param service the service
     * @return the building
     */
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

    /**
     * Read create room request room.
     *
     * @param createRoomRequest the create room request
     * @param service the service
     * @return the room
     */
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

    /**
     * Read update room request room.
     *
     * @param updateRoomRequest the update room request
     * @param service the service
     * @return the room
     */
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

    /**
     * Read create component request component.
     *
     * @param createComponentRequest the create component request
     * @param service the service
     * @return the component
     */
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

    /**
     * Read update component request component.
     *
     * @param updateComponentRequest the update component request
     * @param service the service
     * @return the component
     */
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

    /**
     * Read create favorite request.
     *
     * @param createFavoriteRequest the create favorite request
     * @param service the service
     */
    public static void readCreateFavoriteRequest(
        CreateFavoriteRequest createFavoriteRequest, Service service) {
      Favorite favorite = new Favorite();
      favorite.setOwner(createFavoriteRequest.getOwner());
      favorite.setReferenceIdentificationNumber(
          createFavoriteRequest.getReferenceIdentificationNumber());
      service.createFavorite(favorite);
    }

    /**
     * Read list buildings request collection.
     *
     * @param listBuildingsRequest the list buildings request
     * @param service the service
     * @return the collection
     */
    public static Collection<Building> readListBuildingsRequest(
        ListBuildingsRequest listBuildingsRequest, Service service) {
      return service.listBuildings(
          readListBuildingSettings(listBuildingsRequest.getGrpcListSettings()));
    }

    /**
     * Read list rooms request collection.
     *
     * @param listRoomsRequest the list rooms request
     * @param service the service
     * @return the collection
     */
    public static Collection<Room> readListRoomsRequest(
        ListRoomsRequest listRoomsRequest, Service service) {
      return service.listRooms(
          readListRoomsSettings(listRoomsRequest.getGrpcListSettings()),
          listRoomsRequest.getIdentificationNumber());
    }

    /**
     * Read list components request collection.
     *
     * @param listComponentsRequest the list components request
     * @param service the service
     * @return the collection
     */
    public static Collection<Component> readListComponentsRequest(
        ListComponentsRequest listComponentsRequest, Service service) {
      return service.listComponents(listComponentsRequest.getIdentificationNumber());
    }

    /**
     * Read list notifications request collection.
     *
     * @param listNotificationsRequest the list notifications request
     * @param service the service
     * @return the collection
     */
    public static Collection<Notification> readListNotificationsRequest(
        ListNotificationsRequest listNotificationsRequest, Service service) {
      return service.listNotifications(
          readListNotificationsSettings(listNotificationsRequest.getGrpcListSettings()),
          listNotificationsRequest.getIdentificationNumber());
    }

    /**
     * Read list favorite buildings collection.
     *
     * @param listFavoriteBuildingsRequest the list favorite buildings request
     * @param service the service
     * @return the collection
     */
    public static Collection<Building> readListFavoriteBuildings(
        ListFavoriteBuildingsRequest listFavoriteBuildingsRequest, Service service) {
      return service.listFavoriteBuildings(
          readListBuildingSettings(listFavoriteBuildingsRequest.getGrpcListSettings()),
          listFavoriteBuildingsRequest.getOwner());
    }

    /**
     * Read list favorite rooms collection.
     *
     * @param listFavoriteRoomsRequest the list favorite rooms request
     * @param service the service
     * @return the collection
     */
    public static Collection<Room> readListFavoriteRooms(
        ListFavoriteRoomsRequest listFavoriteRoomsRequest, Service service) {
      return service.listFavoriteRooms(
          readListRoomsSettings(listFavoriteRoomsRequest.getGrpcListSettings()),
          listFavoriteRoomsRequest.getOwner());
    }

    /**
     * Read list favorite components request collection.
     *
     * @param listFavoriteComponentsRequest the list favorite components request
     * @param service the service
     * @return the collection
     */
    public static Collection<Component> readListFavoriteComponentsRequest(
        ListFavoriteComponentsRequest listFavoriteComponentsRequest, Service service) {
      return service.listFavoriteComponents(listFavoriteComponentsRequest.getOwner());
    }

    /**
     * Read remove building request.
     *
     * @param removeRequest the remove request
     * @param service the service
     */
    public static void readRemoveBuildingRequest(RemoveRequest removeRequest, Service service) {
      service.removeBuilding(removeRequest.getIdentificationNumber());
    }

    /**
     * Read remove component request.
     *
     * @param removeRequest the remove request
     * @param service the service
     */
    public static void readRemoveComponentRequest(RemoveRequest removeRequest, Service service) {
      service.removeComponent(removeRequest.getIdentificationNumber());
    }

    /**
     * Read remove room request.
     *
     * @param removeRequest the remove request
     * @param service the service
     */
    public static void readRemoveRoomRequest(RemoveRequest removeRequest, Service service) {
      service.removeRoom(removeRequest.getIdentificationNumber());
    }

    /**
     * Read remove favorite request.
     *
     * @param removeRequest the remove request
     * @param service the service
     */
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

  /**
   * This class represents a writing class which translates model objects to data transfer objects
   * for this REST client.
   *
   * @author Bastian Bacher, Dennis Fadeev
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ClientRequestWriter {

    /**
     * Write client create building request client create building request.
     *
     * @param building the building
     * @return the client create building request
     */
    public static ClientCreateBuildingRequest writeClientCreateBuildingRequest(Building building) {
      ClientCreateBuildingRequest clientCreateBuildingRequest = new ClientCreateBuildingRequest();
      clientCreateBuildingRequest.setName(building.getName());
      clientCreateBuildingRequest.setFloors(building.getFloors());
      clientCreateBuildingRequest.setNumber(building.getNumber());
      clientCreateBuildingRequest.setCampusLocation(building.getCampusLocation());
      clientCreateBuildingRequest.setGeographicalLocation(building.getGeographicalLocation());
      return clientCreateBuildingRequest;
    }

    /**
     * Write client update building request client update building request.
     *
     * @param building the building
     * @return the client update building request
     */
    public static ClientUpdateBuildingRequest writeClientUpdateBuildingRequest(Building building) {
      ClientUpdateBuildingRequest clientUpdateBuildingRequest = new ClientUpdateBuildingRequest();
      clientUpdateBuildingRequest.setName(building.getName());
      clientUpdateBuildingRequest.setFloors(building.getFloors());
      clientUpdateBuildingRequest.setNumber(building.getNumber());
      clientUpdateBuildingRequest.setCampusLocation(building.getCampusLocation());
      clientUpdateBuildingRequest.setGeographicalLocation(building.getGeographicalLocation());
      clientUpdateBuildingRequest.setIdentificationNumber(building.getIdentificationNumber());
      return clientUpdateBuildingRequest;
    }

    /**
     * Write client create room request client create room request.
     *
     * @param room the room
     * @return the client create room request
     */
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

    /**
     * Write client update room request client update room request.
     *
     * @param room the room
     * @return the client update room request
     */
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

    /**
     * Write client create component request client create component request.
     *
     * @param component the component
     * @return the client create component request
     */
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

    /**
     * Write client update component request client update component request.
     *
     * @param component the component
     * @return the client update component request
     */
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
