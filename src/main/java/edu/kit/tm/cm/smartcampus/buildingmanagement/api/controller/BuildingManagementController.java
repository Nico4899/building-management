package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import com.google.protobuf.Timestamp;
import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.error.BuildingManagementErrorHandler;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.BuildingManagementService;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOption;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Controller;

import java.util.Collection;

/**
 * This class represents the gRPC controller from server side. It provides clients with the building
 * management application microservice api. It uses a {@link BuildingManagementErrorHandler} to
 * handle exception occurrences.
 */
@Controller
public class BuildingManagementController
    extends BuildingManagementGrpc.BuildingManagementImplBase {

  private final BuildingManagementService buildingManagementService;

  public BuildingManagementController(BuildingManagementService buildingManagementManager) {
    this.buildingManagementService = buildingManagementManager;
  }

  @Override
  public void getBuilding(
      GetBuildingRequest request, StreamObserver<GetBuildingResponse> responseObserver) {
    BuildingManagementErrorHandler<GetBuildingRequest, GetBuildingResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    GetBuildingResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Building building = this.buildingManagementService.getBuilding(identificationNumber);
              GrpcBuilding grpcBuilding = this.writeBuilding(building);

              return GetBuildingResponse.newBuilder().setBuilding(grpcBuilding).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void getRoom(GetRoomRequest request, StreamObserver<GetRoomResponse> responseObserver) {
    BuildingManagementErrorHandler<GetRoomRequest, GetRoomResponse> buildingManagementErrorHandler =
        new BuildingManagementErrorHandler<>(responseObserver);
    GetRoomResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Room room = this.buildingManagementService.getRoom(identificationNumber);
              GrpcRoom grpcRoom = this.writeRoom(room);

              return GetRoomResponse.newBuilder().setRoom(grpcRoom).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void getComponent(
      GetComponentRequest request, StreamObserver<GetComponentResponse> responseObserver) {
    BuildingManagementErrorHandler<GetComponentRequest, GetComponentResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    GetComponentResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Component component =
                  this.buildingManagementService.getComponent(identificationNumber);
              GrpcComponent grpcComponent = this.writeComponent(component);

              return GetComponentResponse.newBuilder().setComponent(grpcComponent).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void createBuilding(
      CreateBuildingRequest request, StreamObserver<CreateBuildingResponse> responseObserver) {
    BuildingManagementErrorHandler<CreateBuildingRequest, CreateBuildingResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    CreateBuildingResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              GrpcBuilding grpcRequestBuilding = request.getBuilding();
              Building requestBuilding = this.readBuilding(grpcRequestBuilding);
              Building responseBuilding =
                  this.buildingManagementService.createBuilding(requestBuilding);
              GrpcBuilding grpcResponseBuilding = this.writeBuilding(responseBuilding);

              return CreateBuildingResponse.newBuilder().setBuilding(grpcResponseBuilding).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void createRoom(
      CreateRoomRequest request, StreamObserver<CreateRoomResponse> responseObserver) {
    BuildingManagementErrorHandler<CreateRoomRequest, CreateRoomResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    CreateRoomResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              GrpcRoom grpcRequestRoom = request.getRoom();
              Room requestRoom = this.readRoom(grpcRequestRoom);
              Room responseRoom = this.buildingManagementService.createRoom(requestRoom);
              GrpcRoom grpcResponseRoom = this.writeRoom(responseRoom);

              return CreateRoomResponse.newBuilder().setRoom(grpcResponseRoom).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void createComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {
    BuildingManagementErrorHandler<CreateComponentRequest, CreateComponentResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    CreateComponentResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              GrpcComponent grpcRequestComponent = request.getComponent();
              Component requestComponent = this.readComponent(grpcRequestComponent);
              Component responseComponent =
                  this.buildingManagementService.createComponent(requestComponent);

              GrpcComponent grpcResponseComponent = this.writeComponent(responseComponent);
              return CreateComponentResponse.newBuilder()
                  .setComponent(grpcResponseComponent)
                  .build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void createFavorite(
      CreateFavoriteRequest request, StreamObserver<CreateFavoriteResponse> responseObserver) {
    BuildingManagementErrorHandler<CreateFavoriteRequest, CreateFavoriteResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    CreateFavoriteResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              GrpcFavorite grpcRequestFavorite = request.getFavorite();
              Favorite requestFavorite = this.readFavorite(grpcRequestFavorite);
              this.buildingManagementService.createFavorite(requestFavorite);

              return CreateFavoriteResponse.newBuilder().build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void listBuildings(
      ListBuildingsRequest request, StreamObserver<ListBuildingsResponse> responseObserver) {
    BuildingManagementErrorHandler<ListBuildingsRequest, ListBuildingsResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    ListBuildingsResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              GrpcFilterOptions grpcFilterOptions = request.getGrpcFilterOptions();
              FilterOptions filterOptions = this.readFilterOptions(grpcFilterOptions);
              Collection<Building> buildings =
                  this.buildingManagementService.listBuildings(filterOptions);
              GrpcBuildings grpcBuildings = this.writeBuildings(buildings);

              return ListBuildingsResponse.newBuilder().setBuildings(grpcBuildings).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void listRooms(
      ListRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
    BuildingManagementErrorHandler<ListRoomsRequest, ListRoomsResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    ListRoomsResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              GrpcFilterOptions grpcFilterOptions = request.getGrpcFilterOptions();
              FilterOptions filterOptions = this.readFilterOptions(grpcFilterOptions);
              Collection<Room> rooms =
                  this.buildingManagementService.listRooms(filterOptions, identificationNumber);
              GrpcRooms grpcRooms = this.writeRooms(rooms);

              return ListRoomsResponse.newBuilder().setRooms(grpcRooms).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void listComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {
    BuildingManagementErrorHandler<ListComponentsRequest, ListComponentsResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    ListComponentsResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Collection<Component> components =
                  this.buildingManagementService.listComponents(identificationNumber);
              GrpcComponents grpcComponents = this.writeComponents(components);

              return ListComponentsResponse.newBuilder().setComponents(grpcComponents).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void listNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    BuildingManagementErrorHandler<ListNotificationsRequest, ListNotificationsResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    ListNotificationsResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Collection<Notification> notifications =
                  this.buildingManagementService.listNotifications(identificationNumber);
              GrpcNotifications grpcNotifications = this.writeNotifications(notifications);

              return ListNotificationsResponse.newBuilder()
                  .setNotifications(grpcNotifications)
                  .build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void listBuildingFavorites(
      ListBuildingFavoritesRequest request,
      StreamObserver<ListBuildingFavoritesResponse> responseObserver) {
    BuildingManagementErrorHandler<ListBuildingFavoritesRequest, ListBuildingFavoritesResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    ListBuildingFavoritesResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              String owner = request.getOwner();
              Collection<Building> buildings =
                  this.buildingManagementService.listBuildingFavorites(owner);
              GrpcBuildings grpcBuildings = this.writeBuildings(buildings);

              return ListBuildingFavoritesResponse.newBuilder().setBuildings(grpcBuildings).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void listRoomFavorites(
      ListRoomFavoritesRequest request,
      StreamObserver<ListRoomFavoritesResponse> responseObserver) {
    BuildingManagementErrorHandler<ListRoomFavoritesRequest, ListRoomFavoritesResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    ListRoomFavoritesResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              String owner = request.getOwner();
              Collection<Room> rooms = this.buildingManagementService.listRoomFavorites(owner);
              GrpcRooms grpcRooms = this.writeRooms(rooms);

              return ListRoomFavoritesResponse.newBuilder().setRooms(grpcRooms).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void listComponentFavorites(
      ListComponentFavoritesRequest request,
      StreamObserver<ListComponentFavoritesResponse> responseObserver) {
    BuildingManagementErrorHandler<ListComponentFavoritesRequest, ListComponentFavoritesResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    ListComponentFavoritesResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              String owner = request.getOwner();
              Collection<Component> components =
                  this.buildingManagementService.listComponentFavorites(owner);
              GrpcComponents grpcComponents = this.writeComponents(components);

              return ListComponentFavoritesResponse.newBuilder()
                  .setComponents(grpcComponents)
                  .build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void updateBuilding(
      UpdateBuildingRequest request, StreamObserver<UpdateBuildingResponse> responseObserver) {

    BuildingManagementErrorHandler<UpdateBuildingRequest, UpdateBuildingResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    UpdateBuildingResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              GrpcBuilding grpcRequestBuilding = request.getBuilding();
              Building requestBuilding = this.readBuilding(grpcRequestBuilding);
              Building responseBuilding =
                  this.buildingManagementService.updateBuilding(requestBuilding);
              GrpcBuilding grpcResponseBuilding = this.writeBuilding(responseBuilding);

              return UpdateBuildingResponse.newBuilder().setBuilding(grpcResponseBuilding).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void updateRoom(
      UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {
    BuildingManagementErrorHandler<UpdateRoomRequest, UpdateRoomResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    UpdateRoomResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              GrpcRoom grpcRequestRoom = request.getRoom();
              Room requestRoom = this.readRoom(grpcRequestRoom);
              Room responseRoom = this.buildingManagementService.updateRoom(requestRoom);
              GrpcRoom grpcResponseRoom = this.writeRoom(responseRoom);

              return UpdateRoomResponse.newBuilder().setRoom(grpcResponseRoom).build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void updateComponent(
      UpdateComponentRequest request, StreamObserver<UpdateComponentResponse> responseObserver) {

    BuildingManagementErrorHandler<UpdateComponentRequest, UpdateComponentResponse>
        buildingManagementErrorHandler = new BuildingManagementErrorHandler<>(responseObserver);
    UpdateComponentResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              GrpcComponent grpcRequestComponent = request.getComponent();
              Component requestComponent = this.readComponent(grpcRequestComponent);
              Component responseComponent =
                  this.buildingManagementService.updateComponent(requestComponent);
              GrpcComponent grpcResponseComponent = this.writeComponent(responseComponent);

              return UpdateComponentResponse.newBuilder()
                  .setComponent(grpcResponseComponent)
                  .build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  @Override
  public void remove(RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {

    BuildingManagementErrorHandler<RemoveRequest, RemoveResponse> buildingManagementErrorHandler =
        new BuildingManagementErrorHandler<>(responseObserver);
    RemoveResponse response =
        buildingManagementErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              this.buildingManagementService.remove(identificationNumber);

              return RemoveResponse.newBuilder().build();
            },
            request);
    buildingManagementErrorHandler.onNext(response);
    buildingManagementErrorHandler.onCompleted();
  }

  private Component readComponent(GrpcComponent grpcComponent) {

    String componentDescription = grpcComponent.getComponentDescription();
    String parentIdentificationNumber = grpcComponent.getParentIdentificationNumber();
    ComponentType componentType = this.readComponentType(grpcComponent.getComponentType());
    GeographicalLocation geographicalLocation =
        this.readGeographicalLocation(grpcComponent.getGeographicalLocation());

    return Component.builder()
        .componentDescription(componentDescription)
        .componentType(componentType)
        .geographicalLocation(geographicalLocation)
        .parentIdentificationNumber(parentIdentificationNumber)
        .build();
  }

  private Favorite readFavorite(GrpcFavorite grpcFavorite) {
    String owner = grpcFavorite.getOwner();
    String referenceIdentificationNumber = grpcFavorite.getReferenceIdentificationNumber();

    return Favorite.builder()
        .referenceIdentificationNumber(referenceIdentificationNumber)
        .owner(owner)
        .build();
  }

  private Building readBuilding(GrpcBuilding grpcBuilding) {
    String buildingName = grpcBuilding.getBuildingName();
    String buildingNumber = grpcBuilding.getBuildingNumber();
    GeographicalLocation geographicalLocation =
        this.readGeographicalLocation(grpcBuilding.getGeographicalLocation());
    CampusLocation campusLocation = this.readCampusLocation(grpcBuilding.getCampusLocation());
    int numFloors = grpcBuilding.getNumFloors();

    return Building.builder()
        .buildingName(buildingName)
        .buildingNumber(buildingNumber)
        .geographicalLocation(geographicalLocation)
        .campusLocation(campusLocation)
        .numFloors(numFloors)
        .build();
  }

  private Room readRoom(GrpcRoom grpcRoom) {
    String roomName = grpcRoom.getRoomName();
    String roomNumber = grpcRoom.getRoomNumber();
    RoomType roomType = readRoomType(grpcRoom.getRoomType());
    int floor = grpcRoom.getFloor();
    String parentIdentificationNumber = grpcRoom.getParentIdentificationNumber();
    GeographicalLocation geographicalLocation =
        readGeographicalLocation(grpcRoom.getGeographicalLocation());

    return Room.builder()
        .roomName(roomName)
        .roomType(roomType)
        .roomNumber(roomNumber)
        .floor(floor)
        .parentIdentificationNumber(parentIdentificationNumber)
        .geographicalLocation(geographicalLocation)
        .build();
  }

  private RoomType readRoomType(GrpcRoomType grpcRoomType) {
    return RoomType.forNumber(grpcRoomType.ordinal() + 1);
  }

  private CampusLocation readCampusLocation(GrpcCampusLocation grpcCampusLocation) {
    return CampusLocation.forNumber(grpcCampusLocation.ordinal() + 1);
  }

  private GeographicalLocation readGeographicalLocation(
      GrpcGeographicalLocation grpcGeographicalLocation) {
    double latitude = grpcGeographicalLocation.getLatitude();
    double longitude = grpcGeographicalLocation.getLongitude();

    return GeographicalLocation.builder().longitude(longitude).latitude(latitude).build();
  }

  private ComponentType readComponentType(GrpcComponentType grpcComponentType) {
    return ComponentType.forNumber(grpcComponentType.ordinal() + 1);
  }

  private FilterOptions readFilterOptions(GrpcFilterOptions grpcFilterOptions) {
    FilterOption<CampusLocation> campusLocationFilterOption =
        readCampusLocationFilterOption(grpcFilterOptions.getCampusLocationFilterMapping());
    FilterOption<ComponentType> componentTypeFilterOption =
        readComponentTypeFilterOption(grpcFilterOptions.getComponentTypeFilterMapping());
    FilterOption<RoomType> roomTypeFilterOption =
        readRoomTypeFilterOption(grpcFilterOptions.getRoomTypeFilterMapping());

    return FilterOptions.builder()
        .campusLocationFilterOption(campusLocationFilterOption)
        .roomTypeFilterOption(roomTypeFilterOption)
        .componentTypeFilterOption(componentTypeFilterOption)
        .build();
  }

  private FilterOption<ComponentType> readComponentTypeFilterOption(
      ComponentTypeFilterMapping componentTypeFilterMapping) {
    boolean selected = componentTypeFilterMapping.getSelected();
    Collection<ComponentType> filterValues =
        componentTypeFilterMapping.getComponentTypesList().stream()
            .map(this::readComponentType)
            .toList();

    return FilterOption.<ComponentType>builder()
        .selected(selected)
        .filterValues(filterValues)
        .build();
  }

  private FilterOption<CampusLocation> readCampusLocationFilterOption(
      CampusLocationFilterMapping campusLocationFilterMapping) {
    boolean selected = campusLocationFilterMapping.getSelected();
    Collection<CampusLocation> filterValues =
        campusLocationFilterMapping.getCampusLocationsList().stream()
            .map(this::readCampusLocation)
            .toList();

    return FilterOption.<CampusLocation>builder()
        .selected(selected)
        .filterValues(filterValues)
        .build();
  }

  private FilterOption<RoomType> readRoomTypeFilterOption(
      RoomTypeFilterMapping roomTypeFilterMapping) {
    boolean selected = roomTypeFilterMapping.getSelected();
    Collection<RoomType> filterValues =
        roomTypeFilterMapping.getRoomTypesList().stream().map(this::readRoomType).toList();

    return FilterOption.<RoomType>builder().selected(selected).filterValues(filterValues).build();
  }

  private GrpcComponent writeComponent(Component component) {
    String componentDescription = component.getComponentDescription();
    GrpcGeographicalLocation grpcGeographicalLocation =
        this.writeGeographicalLocation(component.getGeographicalLocation());
    String parentIdentificationNumber = component.getParentIdentificationNumber();
    String identificationNumber = component.getIdentificationNumber();

    return GrpcComponent.newBuilder()
        .setComponentDescription(componentDescription)
        .setGeographicalLocation(grpcGeographicalLocation)
        .setParentIdentificationNumber(parentIdentificationNumber)
        .setIdentificationNumber(identificationNumber)
        .build();
  }

  private GrpcRoom writeRoom(Room room) {
    int floor = room.getFloor();
    GrpcGeographicalLocation grpcGeographicalLocation =
        this.writeGeographicalLocation(room.getGeographicalLocation());
    String roomName = room.getRoomName();
    String roomNumber = room.getRoomNumber();
    String parentIdentificationNumber = room.getParentIdentificationNumber();
    String identificationNumber = room.getIdentificationNumber();
    GrpcRoomType grpcRoomType = this.writeRoomType(room.getRoomType());

    return GrpcRoom.newBuilder()
        .setFloor(floor)
        .setGeographicalLocation(grpcGeographicalLocation)
        .setRoomName(roomName)
        .setRoomNumber(roomNumber)
        .setParentIdentificationNumber(parentIdentificationNumber)
        .setRoomType(grpcRoomType)
        .setIdentificationNumber(identificationNumber)
        .build();
  }

  private GrpcBuilding writeBuilding(Building building) {
    String buildingName = building.getBuildingName();
    String buildingNumber = building.getBuildingNumber();
    String identificationNumber = building.getIdentificationNumber();
    GrpcCampusLocation grpcCampusLocation = this.writeCampusLocation(building.getCampusLocation());
    GrpcGeographicalLocation grpcGeographicalLocation =
        this.writeGeographicalLocation(building.getGeographicalLocation());
    int numfloors = building.getNumFloors();

    return GrpcBuilding.newBuilder()
        .setBuildingName(buildingName)
        .setBuildingNumber(buildingNumber)
        .setIdentificationNumber(identificationNumber)
        .setCampusLocation(grpcCampusLocation)
        .setGeographicalLocation(grpcGeographicalLocation)
        .setNumFloors(numfloors)
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
    double longitude = geographicalLocation.getLongitude();
    double latitude = geographicalLocation.getLatitude();

    return GrpcGeographicalLocation.newBuilder()
        .setLatitude(latitude)
        .setLongitude(longitude)
        .build();
  }

  private GrpcCampusLocation writeCampusLocation(CampusLocation campusLocation) {
    return GrpcCampusLocation.forNumber(campusLocation.ordinal() + 1);
  }

  private GrpcRoomType writeRoomType(RoomType roomType) {
    return GrpcRoomType.forNumber(roomType.ordinal() + 1);
  }
}
