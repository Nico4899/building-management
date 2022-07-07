package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import com.google.protobuf.Timestamp;
import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager.BuildingManagementManager;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOption;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * This class represents the gRPC controller from server side. It provides clients with the building
 * management application microservice api.
 */
@Service
public class BuildingManagementController
    extends BuildingManagementGrpc.BuildingManagementImplBase {

  private static final String SUCCESSFUL_MESSAGE = "Operation was successful!";

  private static final boolean SUCCESSFUL = true;
  private static final boolean UNSUCCESSFUL = false;

  private static final String GET_BUILDING = "GetBuilding";
  private static final String GET_COMPONENT = "GetComponent";
  private static final String GET_ROOM = "GetRoom";
  private static final String CREATE_BUILDING = "CreateBuilding";
  private static final String CREATE_ROOM = "CreateRoom";
  private static final String CREATE_COMPONENT = "CreateComponent";
  private static final String CREATE_FAVORITE = "CreateFavorite";
  private static final String LIST_BUILDINGS = "ListBuildings";
  private static final String LIST_ROOMS = "ListRooms";
  private static final String LIST_COMPONENTS = "ListComponents";
  private static final String LIST_NOTIFICATIONS = "ListNotifications";
  private static final String LIST_BUILDING_FAVORITES = "ListBuildingFavorites";
  private static final String LIST_ROOM_FAVORITES = "ListRoomFavorites";
  private static final String LIST_COMPONENT_FAVORITES = "ListComponentFavorites";
  private static final String UPDATE_BUILDING = "UpdateBuilding";
  private static final String UPDATE_ROOM = "UpdateRoom";
  private static final String UPDATE_COMPONENT = "UpdateComponent";
  private static final String REMOVE_OBJECT = "Remove";

  private final BuildingManagementManager buildingManagementManager;

  public BuildingManagementController(BuildingManagementManager buildingManagementManager) {
    this.buildingManagementManager = buildingManagementManager;
  }

  @Override
  public void getBuilding(
      GetBuildingRequest request, StreamObserver<GetBuildingResponse> responseObserver) {

    String identificationNumber = request.getIdentificationNumber();

    try {

      Building building = this.buildingManagementManager.getBuilding(identificationNumber);
      GrpcBuilding grpcBuilding = this.writeBuilding(building);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      GetBuildingResponse response =
          GetBuildingResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setBuilding(grpcBuilding)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), GET_BUILDING);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      GetBuildingResponse response =
          GetBuildingResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (ResourceNotFoundException resourceNotFoundException) {

      String message = String.format(resourceNotFoundException.getMessage(), identificationNumber);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      GetBuildingResponse response =
          GetBuildingResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void getRoom(GetRoomRequest request, StreamObserver<GetRoomResponse> responseObserver) {

    String identificationNumber = request.getIdentificationNumber();

    try {

      Room room = this.buildingManagementManager.getRoom(identificationNumber);
      GrpcRoom grpcRoom = this.writeRoom(room);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      GetRoomResponse response =
          GetRoomResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setRoom(grpcRoom)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), GET_ROOM);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      GetRoomResponse response =
          GetRoomResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (ResourceNotFoundException resourceNotFoundException) {

      String message = String.format(resourceNotFoundException.getMessage(), identificationNumber);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      GetRoomResponse response =
          GetRoomResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void getComponent(
      GetComponentRequest request, StreamObserver<GetComponentResponse> responseObserver) {

    String identificationNumber = request.getIdentificationNumber();

    try {

      Component component = this.buildingManagementManager.getComponent(identificationNumber);
      GrpcComponent grpcComponent = this.writeComponent(component);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      GetComponentResponse response =
          GetComponentResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setComponent(grpcComponent)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), GET_COMPONENT);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      GetComponentResponse response =
          GetComponentResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (ResourceNotFoundException resourceNotFoundException) {

      String message = String.format(resourceNotFoundException.getMessage(), identificationNumber);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      GetComponentResponse response =
          GetComponentResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void createBuilding(
      CreateBuildingRequest request, StreamObserver<CreateBuildingResponse> responseObserver) {

    try {

      GrpcBuilding grpcRequestBuilding = request.getBuilding();
      Building requestBuilding = this.readBuilding(grpcRequestBuilding);
      Building responseBuilding = this.buildingManagementManager.createBuilding(requestBuilding);
      GrpcBuilding grpcResponseBuilding = this.writeBuilding(responseBuilding);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      CreateBuildingResponse response =
          CreateBuildingResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setBuilding(grpcResponseBuilding)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), CREATE_BUILDING);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      CreateBuildingResponse response =
          CreateBuildingResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void createRoom(
      CreateRoomRequest request, StreamObserver<CreateRoomResponse> responseObserver) {

    try {

      GrpcRoom grpcRequestRoom = request.getRoom();
      Room requestRoom = this.readRoom(grpcRequestRoom);
      Room responseRoom = this.buildingManagementManager.createRoom(requestRoom);
      GrpcRoom grpcResponseRoom = this.writeRoom(responseRoom);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      CreateRoomResponse response =
          CreateRoomResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setRoom(grpcResponseRoom)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), CREATE_ROOM);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      CreateRoomResponse response =
          CreateRoomResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void createComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {

    try {

      GrpcComponent grpcRequestComponent = request.getComponent();
      Component requestComponent = this.readComponent(grpcRequestComponent);
      Component responseComponent =
          this.buildingManagementManager.createComponent(requestComponent);
      GrpcComponent grpcResponseComponent = this.writeComponent(responseComponent);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      CreateComponentResponse response =
          CreateComponentResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setComponent(grpcResponseComponent)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), CREATE_COMPONENT);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      CreateComponentResponse response =
          CreateComponentResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void createFavorite(
      CreateFavoriteRequest request, StreamObserver<CreateFavoriteResponse> responseObserver) {

    try {

      GrpcFavorite grpcRequestFavorite = request.getFavorite();
      Favorite requestFavorite = this.readFavorite(grpcRequestFavorite);
      this.buildingManagementManager.createFavorite(requestFavorite);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      CreateFavoriteResponse response =
          CreateFavoriteResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), CREATE_FAVORITE);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      CreateFavoriteResponse response =
          CreateFavoriteResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void listBuildings(
      ListBuildingsRequest request, StreamObserver<ListBuildingsResponse> responseObserver) {

    try {

      BuildingFilterOptions buildingFilterOptions = request.getBuildingFilterOptions();
      FilterOptions filterOptions = this.readBuildingFilterOptions(buildingFilterOptions);
      Collection<Building> buildings = this.buildingManagementManager.listBuildings(filterOptions);
      GrpcBuildings grpcBuildings = this.writeBuildings(buildings);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      ListBuildingsResponse response =
          ListBuildingsResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setBuildings(grpcBuildings)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), LIST_BUILDINGS);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      ListBuildingsResponse response =
          ListBuildingsResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void listRooms(
      ListRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {

    String identificationNumber = request.getIdentificationNumber();

    try {

      RoomFilterOptions roomFilterOptions = request.getRoomFilterOptions();
      FilterOptions filterOptions = this.readRoomFilterOptions(roomFilterOptions);
      Collection<Room> rooms =
          this.buildingManagementManager.listRooms(filterOptions, identificationNumber);
      GrpcRooms grpcRooms = this.writeRooms(rooms);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      ListRoomsResponse response =
          ListRoomsResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setRooms(grpcRooms)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), LIST_ROOMS);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      ListRoomsResponse response =
          ListRoomsResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (ResourceNotFoundException resourceNotFoundException) {

      String message = String.format(resourceNotFoundException.getMessage(), identificationNumber);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      ListRoomsResponse response =
          ListRoomsResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void listComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {

    String identificationNumber = request.getIdentificationNumber();

    try {

      Collection<Component> components =
          this.buildingManagementManager.listComponents(identificationNumber);
      GrpcComponents grpcComponents = this.writeComponents(components);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      ListComponentsResponse response =
          ListComponentsResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setComponents(grpcComponents)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), LIST_COMPONENTS);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      ListComponentsResponse response =
          ListComponentsResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (ResourceNotFoundException resourceNotFoundException) {

      String message = String.format(resourceNotFoundException.getMessage(), identificationNumber);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      ListComponentsResponse response =
          ListComponentsResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void listNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {

    String identificationNumber = request.getIdentificationNumber();

    try {

      Collection<Notification> notifications =
          this.buildingManagementManager.listNotifications(identificationNumber);
      GrpcNotifications grpcNotifications = this.writeNotifications(notifications);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      ListNotificationsResponse response =
          ListNotificationsResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setNotifications(grpcNotifications)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), LIST_NOTIFICATIONS);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      ListNotificationsResponse response =
          ListNotificationsResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (ResourceNotFoundException resourceNotFoundException) {

      String message = String.format(resourceNotFoundException.getMessage(), identificationNumber);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      ListNotificationsResponse response =
          ListNotificationsResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void listBuildingFavorites(
      ListBuildingFavoritesRequest request,
      StreamObserver<ListBuildingFavoritesResponse> responseObserver) {

    try {

      String owner = request.getOwner();
      Collection<Building> buildings = this.buildingManagementManager.listBuildingFavorites(owner);
      GrpcBuildings grpcBuildings = this.writeBuildings(buildings);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      ListBuildingFavoritesResponse response =
          ListBuildingFavoritesResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setBuildings(grpcBuildings)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message =
          String.format(invalidArgumentsException.getMessage(), LIST_BUILDING_FAVORITES);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      ListBuildingFavoritesResponse response =
          ListBuildingFavoritesResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void listRoomFavorites(
      ListRoomFavoritesRequest request,
      StreamObserver<ListRoomFavoritesResponse> responseObserver) {

    try {

      String owner = request.getOwner();
      Collection<Room> rooms = this.buildingManagementManager.listRoomFavorites(owner);
      GrpcRooms grpcRooms = this.writeRooms(rooms);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      ListRoomFavoritesResponse response =
          ListRoomFavoritesResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setRooms(grpcRooms)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), LIST_ROOM_FAVORITES);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      ListRoomFavoritesResponse response =
          ListRoomFavoritesResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void listComponentFavorites(
      ListComponentFavoritesRequest request,
      StreamObserver<ListComponentFavoritesResponse> responseObserver) {

    try {

      String owner = request.getOwner();
      Collection<Component> components =
          this.buildingManagementManager.listComponentFavorites(owner);
      GrpcComponents grpcComponents = this.writeComponents(components);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      ListComponentFavoritesResponse response =
          ListComponentFavoritesResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setComponents(grpcComponents)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message =
          String.format(invalidArgumentsException.getMessage(), LIST_COMPONENT_FAVORITES);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      ListComponentFavoritesResponse response =
          ListComponentFavoritesResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void updateBuilding(
      UpdateBuildingRequest request, StreamObserver<UpdateBuildingResponse> responseObserver) {

    GrpcBuilding grpcRequestBuilding = request.getBuilding();

    try {

      Building requestBuilding = this.readBuilding(grpcRequestBuilding);
      Building responseBuilding = this.buildingManagementManager.updateBuilding(requestBuilding);
      GrpcBuilding grpcResponseBuilding = this.writeBuilding(responseBuilding);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      UpdateBuildingResponse response =
          UpdateBuildingResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setBuilding(grpcResponseBuilding)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), UPDATE_BUILDING);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      UpdateBuildingResponse response =
          UpdateBuildingResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (ResourceNotFoundException resourceNotFoundException) {

      String message =
          String.format(
              resourceNotFoundException.getMessage(),
              grpcRequestBuilding.getIdentificationNumber());
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      UpdateBuildingResponse response =
          UpdateBuildingResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void updateRoom(
      UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {

    GrpcRoom grpcRequestRoom = request.getRoom();

    try {

      Room requestRoom = this.readRoom(grpcRequestRoom);
      Room responseRoom = this.buildingManagementManager.updateRoom(requestRoom);
      GrpcRoom grpcResponseRoom = this.writeRoom(responseRoom);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      UpdateRoomResponse response =
          UpdateRoomResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setRoom(grpcResponseRoom)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), UPDATE_ROOM);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      UpdateRoomResponse response =
          UpdateRoomResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (ResourceNotFoundException resourceNotFoundException) {

      String message =
          String.format(
              resourceNotFoundException.getMessage(), grpcRequestRoom.getIdentificationNumber());
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      UpdateRoomResponse response =
          UpdateRoomResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void updateComponent(
      UpdateComponentRequest request, StreamObserver<UpdateComponentResponse> responseObserver) {

    GrpcComponent grpcRequestComponent = request.getComponent();

    try {

      Component requestComponent = this.readComponent(grpcRequestComponent);
      Component responseComponent =
          this.buildingManagementManager.updateComponent(requestComponent);
      GrpcComponent grpcResponseComponent = this.writeComponent(responseComponent);
      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);

      UpdateComponentResponse response =
          UpdateComponentResponse.newBuilder()
              .setResponseMessage(responseMessage)
              .setComponent(grpcResponseComponent)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), UPDATE_COMPONENT);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      UpdateComponentResponse response =
          UpdateComponentResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (ResourceNotFoundException resourceNotFoundException) {

      String message =
          String.format(
              resourceNotFoundException.getMessage(),
              grpcRequestComponent.getIdentificationNumber());
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      UpdateComponentResponse response =
          UpdateComponentResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }

  @Override
  public void remove(RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {

    String identificationNumber = request.getIdentificationNumber();

    try {

      this.buildingManagementManager.remove(identificationNumber);

      ResponseMessage responseMessage = this.writeResponseMessage(SUCCESSFUL_MESSAGE, SUCCESSFUL);
      RemoveResponse response =
          RemoveResponse.newBuilder().setResponseMessage(responseMessage).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (InvalidArgumentsException invalidArgumentsException) {

      String message = String.format(invalidArgumentsException.getMessage(), REMOVE_OBJECT);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      RemoveResponse response =
          RemoveResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (ResourceNotFoundException resourceNotFoundException) {

      String message = String.format(resourceNotFoundException.getMessage(), identificationNumber);
      ResponseMessage responseMessage = this.writeResponseMessage(message, UNSUCCESSFUL);

      RemoveResponse response =
          RemoveResponse.newBuilder().setResponseMessage(responseMessage).build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
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

  private FilterOptions readBuildingFilterOptions(BuildingFilterOptions buildingFilterOptions) {
    FilterOption<CampusLocation> campusLocationFilterOption =
        readCampusLocationFilterOption(buildingFilterOptions.getCampusLocationFilterMapping());
    FilterOption<ComponentType> componentTypeFilterOption =
        readComponentTypeFilterOption(buildingFilterOptions.getComponentTypeFilterMapping());
    FilterOption<RoomType> roomTypeFilterOption =
        readRoomTypeFilterOption(buildingFilterOptions.getRoomTypeFilterMapping());

    return FilterOptions.builder()
        .campusLocationFilterOption(campusLocationFilterOption)
        .roomTypeFilterOption(roomTypeFilterOption)
        .componentTypeFilterOption(componentTypeFilterOption)
        .build();
  }

  private FilterOptions readRoomFilterOptions(RoomFilterOptions roomFilterOptions) {
    FilterOption<ComponentType> componentTypeFilterOption =
        readComponentTypeFilterOption(roomFilterOptions.getComponentTypeFilterMapping());
    FilterOption<RoomType> roomTypeFilterOption =
        readRoomTypeFilterOption(roomFilterOptions.getRoomTypeFilterMapping());

    return FilterOptions.builder()
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

  private ResponseMessage writeResponseMessage(String message, boolean successful) {
    return ResponseMessage.newBuilder().setMessage(message).setSuccessful(successful).build();
  }
}
