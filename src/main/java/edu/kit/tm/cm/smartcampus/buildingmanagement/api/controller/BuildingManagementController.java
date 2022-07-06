package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import com.google.protobuf.Timestamp;
import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager.BuildingManagementManager;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOption;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.LinkedList;

@Controller
public class BuildingManagementController
    extends BuildingManagementGrpc.BuildingManagementImplBase {

  // retrieve building management manager via constructor injection
  private final BuildingManagementManager buildingManagementManager;

  public BuildingManagementController(BuildingManagementManager buildingManagementManager) {
    this.buildingManagementManager = buildingManagementManager;
  }

  @Override
  public void getBuilding(
      GetBuildingRequest request, StreamObserver<GetBuildingResponse> responseObserver) {

    // retrieve identification number from request
    String identificationNumber = request.getIdentificationNumber();

    // fetch building from manager with given identification number
    Building building = this.buildingManagementManager.getBuilding(identificationNumber);

    // write response building
    GrpcBuilding grpcBuilding = this.writeBuilding(building);

    // build response
    GetBuildingResponse response =
        GetBuildingResponse.newBuilder().setBuilding(grpcBuilding).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getRoom(GetRoomRequest request, StreamObserver<GetRoomResponse> responseObserver) {

    // retrieve identification number from request
    String identificationNumber = request.getIdentificationNumber();

    // fetch room from manager with given identification number
    Room room = this.buildingManagementManager.getRoom(identificationNumber);

    // write response room
    GrpcRoom grpcRoom = this.writeRoom(room);

    // build response
    GetRoomResponse response = GetRoomResponse.newBuilder().setRoom(grpcRoom).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getComponent(
      GetComponentRequest request, StreamObserver<GetComponentResponse> responseObserver) {

    // retrieve identification number from request
    String identificationNumber = request.getIdentificationNumber();

    // fetch component from manager with given identification number
    Component component = this.buildingManagementManager.getComponent(identificationNumber);

    // write response component
    GrpcComponent grpcComponent = this.writeComponent(component);

    // build response
    GetComponentResponse response =
        GetComponentResponse.newBuilder().setComponent(grpcComponent).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createBuilding(
      CreateBuildingRequest request, StreamObserver<CreateBuildingResponse> responseObserver) {

    // fetch grpc building to create from request
    GrpcBuilding grpcRequestBuilding = request.getBuilding();

    // read building from grpc building request
    Building requestBuilding = this.readBuilding(grpcRequestBuilding);

    // create and get created building from building management manager
    Building responseBuilding = this.buildingManagementManager.createBuilding(requestBuilding);

    // write building to grpc response building
    GrpcBuilding grpcResponseBuilding = this.writeBuilding(responseBuilding);

    // build response
    CreateBuildingResponse response =
        CreateBuildingResponse.newBuilder().setBuilding(grpcResponseBuilding).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createRoom(
      CreateRoomRequest request, StreamObserver<CreateRoomResponse> responseObserver) {

    // fetch grpc room to create from request
    GrpcRoom grpcRequestRoom = request.getRoom();

    // read room from grpc room request
    Room requestRoom = this.readRoom(grpcRequestRoom);

    // create and get created room from building management manager
    Room responseRoom = this.buildingManagementManager.createRoom(requestRoom);

    // write room to grpc response room
    GrpcRoom grpcResponseRoom = this.writeRoom(responseRoom);

    // build response
    CreateRoomResponse response = CreateRoomResponse.newBuilder().setRoom(grpcResponseRoom).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {

    // fetch grpc component to create from request
    GrpcComponent grpcRequestComponent = request.getComponent();

    // read component from grpc component request
    Component requestComponent = this.readComponent(grpcRequestComponent);

    // create and get created component from building management manager
    Component responseComponent = this.buildingManagementManager.createComponent(requestComponent);

    // write component to grpc response component
    GrpcComponent grpcResponseComponent = this.writeComponent(responseComponent);

    // build response
    CreateComponentResponse response =
        CreateComponentResponse.newBuilder().setComponent(grpcResponseComponent).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createFavorite(
      CreateFavoriteRequest request, StreamObserver<CreateFavoriteResponse> responseObserver) {

    // fetch grpc favorite to create from request
    GrpcFavorite grpcRequestFavorite = request.getFavorite();

    // read favorite from grpc favorite request
    Favorite requestFavorite = this.readFavorite(grpcRequestFavorite);

    // create favorite in building management manager
    this.buildingManagementManager.createFavorite(requestFavorite);

    // build response
    CreateFavoriteResponse response = CreateFavoriteResponse.newBuilder().build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildings(
      ListBuildingsRequest request, StreamObserver<ListBuildingsResponse> responseObserver) {

    // fetch grpc building filter options to create from request
    BuildingFilterOptions buildingFilterOptions = request.getBuildingFilterOptions();

    // read filter options from grpc building filter options
    FilterOptions filterOptions = this.readBuildingFilterOptions(buildingFilterOptions);

    // list buildings with filter options from building management manager
    Collection<Building> buildings = this.buildingManagementManager.listBuildings(filterOptions);

    // write buildings to grpc buildings
    GrpcBuildings grpcBuildings = this.writeBuildings(buildings);

    // build response
    ListBuildingsResponse response =
        ListBuildingsResponse.newBuilder().setBuildings(grpcBuildings).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRooms(
      ListRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {

    // fetch room filter options from request
    RoomFilterOptions roomFilterOptions = request.getRoomFilterOptions();

    // fetch identification number from request
    String identificationNumber = request.getIdentificationNumber();

    // read filter options from room filter options
    FilterOptions filterOptions = this.readRoomFilterOptions(roomFilterOptions);

    // list rooms by identification number and filter options from building management manager
    Collection<Room> rooms =
        this.buildingManagementManager.listRooms(filterOptions, identificationNumber);

    // write grpc rooms from rooms
    GrpcRooms grpcRooms = this.writeRooms(rooms);

    // build response
    ListRoomsResponse response = ListRoomsResponse.newBuilder().setRooms(grpcRooms).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {

    // fetch identification number from request
    String identificationNumber = request.getIdentificationNumber();

    // list components by identification number from building management manager
    Collection<Component> components =
        this.buildingManagementManager.listComponents(identificationNumber);

    // write grpc components from components
    GrpcComponents grpcComponents = this.writeComponents(components);

    // build response
    ListComponentsResponse response =
        ListComponentsResponse.newBuilder().setComponents(grpcComponents).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {

    // fetch identification number from request
    String identificationNumber = request.getIdentificationNumber();

    // list notifications by identification number from building management manager
    Collection<Notification> notifications =
        this.buildingManagementManager.listNotifications(identificationNumber);

    // write grpc notifications from notifications
    GrpcNotifications grpcNotifications = this.writeNotifications(notifications);

    // build response
    ListNotificationsResponse response =
        ListNotificationsResponse.newBuilder().setNotifications(grpcNotifications).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildingFavorites(
      ListBuildingFavoritesRequest request,
      StreamObserver<ListBuildingFavoritesResponse> responseObserver) {

    // fetch owner from request
    String owner = request.getOwner();

    // list building favorites by identification number from building management manager
    Collection<Building> buildings = this.buildingManagementManager.listBuildingFavorites(owner);

    // write grpc buildings from buildings
    GrpcBuildings grpcBuildings = this.writeBuildings(buildings);

    // build response
    ListBuildingFavoritesResponse response =
        ListBuildingFavoritesResponse.newBuilder().setBuildings(grpcBuildings).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRoomFavorites(
      ListRoomFavoritesRequest request,
      StreamObserver<ListRoomFavoritesResponse> responseObserver) {

    // fetch owner from request
    String owner = request.getOwner();

    // list room favorites by identification number from building management manager
    Collection<Room> rooms = this.buildingManagementManager.listRoomFavorites(owner);

    // write grpc rooms from rooms
    GrpcRooms grpcRooms = this.writeRooms(rooms);

    // build response
    ListRoomFavoritesResponse response =
        ListRoomFavoritesResponse.newBuilder().setRooms(grpcRooms).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listComponentFavorites(
      ListComponentFavoritesRequest request,
      StreamObserver<ListComponentFavoritesResponse> responseObserver) {

    // fetch owner from request
    String owner = request.getOwner();

    // list component favorites by identification number from building management manager
    Collection<Component> components = this.buildingManagementManager.listComponentFavorites(owner);

    // write grpc components from components
    GrpcComponents grpcComponents = this.writeComponents(components);

    // build response
    ListComponentFavoritesResponse response =
        ListComponentFavoritesResponse.newBuilder().setComponents(grpcComponents).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateBuilding(
      UpdateBuildingRequest request, StreamObserver<UpdateBuildingResponse> responseObserver) {

    // fetch grpc building to be updated from request
    GrpcBuilding grpcRequestBuilding = request.getBuilding();

    // read  request building from grpc request building
    Building requestBuilding = this.readBuilding(grpcRequestBuilding);

    // get and update building in building management manager with request building
    Building responseBuilding = this.buildingManagementManager.updateBuilding(requestBuilding);

    // write grpc response building from response building
    GrpcBuilding grpcResponseBuilding = this.writeBuilding(responseBuilding);

    // build response
    UpdateBuildingResponse response =
        UpdateBuildingResponse.newBuilder().setBuilding(grpcResponseBuilding).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateRoom(
      UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {

    // fetch grpc room to be updated from request
    GrpcRoom grpcRequestRoom = request.getRoom();

    // read  request room from grpc request room
    Room requestRoom = this.readRoom(grpcRequestRoom);

    // get and update room in building management manager with request room
    Room responseRoom = this.buildingManagementManager.updateRoom(requestRoom);

    // write grpc response room from response room
    GrpcRoom grpcResponseRoom = this.writeRoom(responseRoom);

    // build response
    UpdateRoomResponse response = UpdateRoomResponse.newBuilder().setRoom(grpcResponseRoom).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateComponent(
      UpdateComponentRequest request, StreamObserver<UpdateComponentResponse> responseObserver) {

    // fetch grpc component to be updated from request
    GrpcComponent grpcRequestComponent = request.getComponent();

    // read  request component from grpc request component
    Component requestComponent = this.readComponent(grpcRequestComponent);

    // get and update component in building management manager with request component
    Component responseComponent = this.buildingManagementManager.updateComponent(requestComponent);

    // write grpc response component from response component
    GrpcComponent grpcResponseComponent = this.writeComponent(responseComponent);

    // build response
    UpdateComponentResponse response =
        UpdateComponentResponse.newBuilder().setComponent(grpcResponseComponent).build();

    // complete response call procedure
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void remove(RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {

    // fetch identification number from request
    String identificationNumber = request.getIdentificationNumber();

    // remove piece belonging to identification number in building management manager
    this.buildingManagementManager.remove(identificationNumber);

    // build response
    RemoveResponse response =
        RemoveResponse.newBuilder().build();

    // complete response call procedure
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
                .toList()));
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
                .toList()));
    return filterOption;
  }

  private FilterOption<RoomType> readRoomTypeFilterOption(
      RoomTypeFilterMapping roomTypeFilterMapping) {
    FilterOption<RoomType> filterOption = new FilterOption<>();
    filterOption.setSelected(roomTypeFilterMapping.getSelected());
    filterOption.setFilterValues(
        new LinkedList<>(
            roomTypeFilterMapping.getRoomTypesList().stream().map(this::readRoomType).toList()));
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
