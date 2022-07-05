package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import com.google.protobuf.Timestamp;
import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager.BuildingManagementManager;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOption;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;
import io.grpc.stub.StreamObserver;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class BuildingManagementController
    extends BuildingManagementGrpc.BuildingManagementImplBase {

  private final BuildingManagementManager buildingManagementManager;

  public BuildingManagementController(BuildingManagementManager buildingManagementManager) {
    this.buildingManagementManager = buildingManagementManager;
  }

  @Override
  public void getBuilding(
      GetBuildingRequest request, StreamObserver<GetBuildingResponse> responseObserver) {

    GetBuildingResponse response =
        GetBuildingResponse.newBuilder()
            .setBuilding(
                writeBuilding(
                    buildingManagementManager.getBuilding(request.getIdentificationNumber())))
            .setResponseMessage(writeResponseMessage("hello", true))
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getRoom(GetRoomRequest request, StreamObserver<GetRoomResponse> responseObserver) {
    GetRoomResponse response =
        GetRoomResponse.newBuilder()
            .setRoom(
                writeRoom(buildingManagementManager.getRoom(request.getIdentificationNumber())))
            .setResponseMessage(writeResponseMessage("hello", true))
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getComponent(
      GetComponentRequest request, StreamObserver<GetComponentResponse> responseObserver) {

    GetComponentResponse response =
        GetComponentResponse.newBuilder()
            .setComponent(
                writeComponent(
                    buildingManagementManager.getComponent(request.getIdentificationNumber())))
            .setResponseMessage(writeResponseMessage("hello", true))
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createBuilding(
      CreateBuildingRequest request, StreamObserver<CreateBuildingResponse> responseObserver) {
    Building building = readBuilding(request.getBuilding());

    CreateBuildingResponse response =
        CreateBuildingResponse.newBuilder()
            .setBuilding(writeBuilding(buildingManagementManager.createBuilding(building)))
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createRoom(
      CreateRoomRequest request, StreamObserver<CreateRoomResponse> responseObserver) {
    Room room = readRoom(request.getRoom());

    CreateRoomResponse response =
        CreateRoomResponse.newBuilder()
            .setRoom(writeRoom(buildingManagementManager.createRoom(room)))
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {
    Component component = readComponent(request.getComponent());

    CreateComponentResponse response =
        CreateComponentResponse.newBuilder()
            .setComponent(writeComponent(buildingManagementManager.createComponent(component)))
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createFavorite(
      CreateFavoriteRequest request, StreamObserver<CreateFavoriteResponse> responseObserver) {
    Favorite favorite = readFavorite(request.getFavorite());

    CreateFavoriteResponse response =
        CreateFavoriteResponse.newBuilder()
            .setResponseMessage(writeResponseMessage("hello", true))
            .setFavorite(writeFavorite(buildingManagementManager.createFavorite(favorite)))
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildings(
      ListBuildingsRequest request, StreamObserver<ListBuildingsResponse> responseObserver) {
    FilterOptions filterOptions = readBuildingFilterOptions(request.getBuildingFilterOptions());

    ListBuildingsResponse response =
        ListBuildingsResponse.newBuilder()
            .setResponseMessage(writeResponseMessage("hello", true))
            .setBuildings(writeBuildings(buildingManagementManager.listBuildings(filterOptions)))
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRooms(
      ListRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
    FilterOptions filterOptions = readRoomFilterOptions(request.getRoomFilterOptions());

    ListRoomsResponse response =
        ListRoomsResponse.newBuilder()
            .setResponseMessage(writeResponseMessage("hello", true))
            .setRooms(
                writeRooms(
                    buildingManagementManager.listRooms(
                        filterOptions, request.getIdentificationNumber())))
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {
    ListComponentsResponse response =
        ListComponentsResponse.newBuilder()
            .setResponseMessage(writeResponseMessage("hello", true))
            .setComponents(
                writeComponents(
                    buildingManagementManager.listComponents(request.getIdentificationNumber())))
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    ListNotificationsResponse response =
        ListNotificationsResponse.newBuilder()
            .setResponseMessage(writeResponseMessage("hello", true))
            .setNotifications(
                writeNotifications(
                    buildingManagementManager.listNotifications(request.getIdentificationNumber())))
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildingFavorites(
      ListFavoritesRequest request, StreamObserver<ListFavoritesResponse> responseObserver) {
    ListFavoritesResponse response =
        ListFavoritesResponse.newBuilder()
            .setResponseMessage(writeResponseMessage("hello", true))
            .setBuildings(
                writeBuildings(buildingManagementManager.listBuildingFavorites(request.getOwner())))
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateBuilding(
      UpdateBuildingRequest request, StreamObserver<UpdateBuildingResponse> responseObserver) {
    UpdateBuildingResponse response =
        UpdateBuildingResponse.newBuilder()
            .setResponseMessage(writeResponseMessage("hello", true))
            .setBuilding(
                writeBuilding(
                    buildingManagementManager.updateBuilding(readBuilding(request.getBuilding()))))
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateRoom(
      UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {
    UpdateRoomResponse response =
        UpdateRoomResponse.newBuilder()
            .setResponseMessage(writeResponseMessage("hello", true))
            .setRoom(writeRoom(buildingManagementManager.updateRoom(readRoom(request.getRoom()))))
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateComponent(
      UpdateComponentRequest request, StreamObserver<UpdateComponentResponse> responseObserver) {
    UpdateComponentResponse response =
        UpdateComponentResponse.newBuilder()
            .setResponseMessage(writeResponseMessage("hello", true))
            .setComponent(
                writeComponent(
                    buildingManagementManager.updateComponent(
                        readComponent(request.getComponent()))))
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void remove(RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    RemoveResponse response =
        RemoveResponse.newBuilder().setResponseMessage(writeResponseMessage("hello", true)).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  // read model object from grpc object

  private Component readComponent(GrpcComponent grpcComponent) {
    Component component = new Component();
    component.setComponentDescription(grpcComponent.getComponentDescription());
    component.setParentIdentificationNumber(grpcComponent.getParentIdentificationNumber());
    component.setComponentType(readComponentType(grpcComponent.getComponentType()));
    component.setGeographicalLocation(
        readGeographicalLocation(grpcComponent.getGeographicalLocation()));
    return component;
  }

  private Favorite readFavorite(GrpcFavorite grpcFavorite) {
    Favorite favorite = new Favorite();
    favorite.setOwner(grpcFavorite.getOwner());
    favorite.setReferenceIdentificationNumber(grpcFavorite.getIdentificationNumber());
    return favorite;
  }

  private Building readBuilding(GrpcBuilding grpcBuilding) {
    Building building = new Building();
    building.setBuildingName(grpcBuilding.getBuildingName());
    building.setBuildingNumber(grpcBuilding.getBuildingNumber());
    building.setGeographicalLocation(
        readGeographicalLocation(grpcBuilding.getGeographicalLocation()));
    building.setCampusLocation(readCampusLocation(grpcBuilding.getCampusLocation()));
    building.setNumFloors(grpcBuilding.getNumFloors());
    return building;
  }

  private Room readRoom(GrpcRoom grpcRoom) {
    Room room = new Room();
    room.setRoomName(grpcRoom.getRoomName());
    room.setRoomNumber(grpcRoom.getRoomNumber());
    room.setRoomType(readRoomType(grpcRoom.getRoomType()));
    room.setFloor(grpcRoom.getFloor());
    room.setParentIdentificationNumber(grpcRoom.getParentIdentificationNumber());
    room.setGeographicalLocation(readGeographicalLocation(grpcRoom.getGeographicalLocation()));
    return room;
  }

  private RoomType readRoomType(GrpcRoomType grpcRoomType) {
    return RoomType.forNumber(grpcRoomType.ordinal() + 1);
  }

  private CampusLocation readCampusLocation(GrpcCampusLocation grpcCampusLocation) {
    return CampusLocation.forNumber(grpcCampusLocation.ordinal() + 1);
  }

  private GeographicalLocation readGeographicalLocation(
      GrpcGeographicalLocation grpcGeographicalLocation) {
    GeographicalLocation geographicalLocation = new GeographicalLocation();
    geographicalLocation.setLatitude(grpcGeographicalLocation.getLatitude());
    geographicalLocation.setLongitude(grpcGeographicalLocation.getLongitude());
    return geographicalLocation;
  }

  private ComponentType readComponentType(GrpcComponentType grpcComponentType) {
    return ComponentType.forNumber(grpcComponentType.ordinal() + 1);
  }

  private FilterOptions readBuildingFilterOptions(BuildingFilterOptions buildingFilterOptions) {
    FilterOptions filterOptions = new FilterOptions();
    filterOptions.setCampusLocationFilterOption(
        readCampusLocationFilterOption(buildingFilterOptions.getCampusLocationFilterMapping()));
    filterOptions.setComponentTypeFilterOption(
        readComponentTypeFilterOption(buildingFilterOptions.getComponentTypeFilterMapping()));
    filterOptions.setRoomTypeFilterOption(
        readRoomTypeFilterOption(buildingFilterOptions.getRoomTypeFilterMapping()));
    return filterOptions;
  }

  private FilterOptions readRoomFilterOptions(RoomFilterOptions roomFilterOptions) {
    FilterOptions filterOptions = new FilterOptions();
    filterOptions.setRoomTypeFilterOption(
        readRoomTypeFilterOption(roomFilterOptions.getRoomTypeFilterMapping()));
    filterOptions.setComponentTypeFilterOption(
        readComponentTypeFilterOption(roomFilterOptions.getComponentTypeFilterMapping()));
    return filterOptions;
  }

  private FilterOption<ComponentType> readComponentTypeFilterOption(
      ComponentTypeFilterMapping componentTypeFilterMapping) {
    FilterOption<ComponentType> filterOption = new FilterOption<>();
    filterOption.setSelected(componentTypeFilterMapping.getSelected());
    filterOption.setFilterValues(
        new LinkedList<>(
            componentTypeFilterMapping.getComponentTypesList().stream()
                .map(this::readComponentType)
                .collect(Collectors.toList())));
    return filterOption;
  }

  private FilterOption<CampusLocation> readCampusLocationFilterOption(
      CampusLocationFilterMapping campusLocationFilterMapping) {
    FilterOption<CampusLocation> filterOption = new FilterOption<>();
    filterOption.setSelected(campusLocationFilterMapping.getSelected());
    filterOption.setFilterValues(
        new LinkedList<>(
            campusLocationFilterMapping.getCampusLocationsList().stream()
                .map(this::readCampusLocation)
                .collect(Collectors.toList())));
    return filterOption;
  }

  private FilterOption<RoomType> readRoomTypeFilterOption(
      RoomTypeFilterMapping roomTypeFilterMapping) {
    FilterOption<RoomType> filterOption = new FilterOption<>();
    filterOption.setSelected(roomTypeFilterMapping.getSelected());
    filterOption.setFilterValues(
        new LinkedList<>(
            roomTypeFilterMapping.getRoomTypesList().stream()
                .map(this::readRoomType)
                .collect(Collectors.toList())));
    return filterOption;
  }

  // write model object to grpc object

  private GrpcComponent writeComponent(Component component) {
    return GrpcComponent.newBuilder()
        .setComponentDescription(component.getComponentDescription())
        .setGeographicalLocation(writeGeographicalLocation(component.getGeographicalLocation()))
        .setParentIdentificationNumber(component.getParentIdentificationNumber())
        .setIdentificationNumber(component.getIdentificationNumber())
        .build();
  }

  private GrpcRoom writeRoom(Room room) {
    return GrpcRoom.newBuilder()
        .setFloor(room.getFloor())
        .setGeographicalLocation(writeGeographicalLocation(room.getGeographicalLocation()))
        .setRoomName(room.getRoomName())
        .setRoomNumber(room.getRoomNumber())
        .setParentIdentificationNumber(room.getParentIdentificationNumber())
        .setRoomType(writeRoomType(room.getRoomType()))
        .setIdentificationNumber(room.getIdentificationNumber())
        .build();
  }

  private GrpcBuilding writeBuilding(Building building) {
    return GrpcBuilding.newBuilder()
        .setBuildingName(building.getBuildingName())
        .setBuildingNumber(building.getBuildingNumber())
        .setIdentificationNumber(building.getIdentificationNumber())
        .setCampusLocation(writeCampusLocation(building.getCampusLocation()))
        .setGeographicalLocation(writeGeographicalLocation(building.getGeographicalLocation()))
        .setNumFloors(building.getNumFloors())
        .build();
  }

  private GrpcNotification writeNotification(Notification notification) {
    return GrpcNotification.newBuilder()
        .setNotificationTitle(notification.getNotificationTitle())
        .setNotificationDescription(notification.getNotificationDescription())
        .setParentIdentificationNumber(notification.getParentIdentificationNumber())
        .setIdentificationNumber(notification.getIdentificationNumber())
        .setCreationTime(
            Timestamp.newBuilder().setNanos(notification.getCreationTime().getNanos()).build())
        .build();
  }

  private GrpcFavorite writeFavorite(Favorite favorite) {
    return GrpcFavorite.newBuilder()
        .setOwner(favorite.getOwner())
        .setIdentificationNumber(favorite.getIdentificationNumber())
        .setReferenceIdentificationNumber(favorite.getReferenceIdentificationNumber())
        .build();
  }

  private GrpcBuildings writeBuildings(Collection<Building> buildings) {
    return GrpcBuildings.newBuilder()
        .addAllBuildings(buildings.stream().map(this::writeBuilding).toList())
        .build();
  }

  private GrpcComponents writeComponents(Collection<Component> components) {
    return GrpcComponents.newBuilder()
        .addAllComponents(components.stream().map(this::writeComponent).toList())
        .build();
  }

  private GrpcRooms writeRooms(Collection<Room> rooms) {
    return GrpcRooms.newBuilder().addAllRooms(rooms.stream().map(this::writeRoom).toList()).build();
  }

  private GrpcNotifications writeNotifications(Collection<Notification> notifications) {
    return GrpcNotifications.newBuilder()
        .addAllNotifications(notifications.stream().map(this::writeNotification).toList())
        .build();
  }

  private GrpcGeographicalLocation writeGeographicalLocation(
      GeographicalLocation geographicalLocation) {
    return GrpcGeographicalLocation.newBuilder()
        .setLatitude(geographicalLocation.getLatitude())
        .setLongitude(geographicalLocation.getLongitude())
        .build();
  }

  private GrpcCampusLocation writeCampusLocation(CampusLocation campusLocation) {
    return GrpcCampusLocation.forNumber(campusLocation.ordinal() + 1);
  }

  private GrpcRoomType writeRoomType(RoomType roomType) {
    return GrpcRoomType.forNumber(roomType.ordinal() + 1);
  }

  private ResponseMessage writeResponseMessage(String message, boolean successful) {
    return ResponseMessage.newBuilder().setMessage(message).setSuccessful(successful).build();
  }
}
