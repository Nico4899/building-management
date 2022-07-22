package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility.GrpcObjectReader;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility.GrpcObjectWriter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.BuildingManagementService;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.settings.Settings;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * This class represents the gRPC controller from server side. It provides clients with the building
 * management application microservice api.
 */
@GrpcService
public class ServerController extends BuildingManagementGrpc.BuildingManagementImplBase {

  private final BuildingManagementService buildingManagementService;

  /**
   * Constructs a new building management controller.
   *
   * @param buildingManagementService building management service
   */
  @Autowired
  public ServerController(BuildingManagementService buildingManagementService) {
    this.buildingManagementService = buildingManagementService;
  }

  @Override
  public void getBuilding(
      GetBuildingRequest request, StreamObserver<GetBuildingResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    Building building = this.buildingManagementService.getBuilding(identificationNumber);
    GrpcBuilding grpcBuilding = GrpcObjectWriter.write(building);
    GetBuildingResponse response =
        GetBuildingResponse.newBuilder().setBuilding(grpcBuilding).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getRoom(GetRoomRequest request, StreamObserver<GetRoomResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    Room room = this.buildingManagementService.getRoom(identificationNumber);
    GrpcRoom grpcRoom = GrpcObjectWriter.write(room);
    GetRoomResponse response = GetRoomResponse.newBuilder().setRoom(grpcRoom).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getComponent(
      GetComponentRequest request, StreamObserver<GetComponentResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    Component component = this.buildingManagementService.getComponent(identificationNumber);
    GrpcComponent grpcComponent = GrpcObjectWriter.write(component);
    GetComponentResponse response =
        GetComponentResponse.newBuilder().setComponent(grpcComponent).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createBuilding(
      CreateBuildingRequest request, StreamObserver<CreateBuildingResponse> responseObserver) {
    GrpcBuilding grpcRequestBuilding = request.getBuilding();
    Building requestBuilding = GrpcObjectReader.read(grpcRequestBuilding);
    Building responseBuilding = this.buildingManagementService.createBuilding(requestBuilding);
    GrpcBuilding grpcResponseBuilding = GrpcObjectWriter.write(responseBuilding);
    CreateBuildingResponse response =
        CreateBuildingResponse.newBuilder().setBuilding(grpcResponseBuilding).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createRoom(
      CreateRoomRequest request, StreamObserver<CreateRoomResponse> responseObserver) {
    GrpcRoom grpcRequestRoom = request.getRoom();
    Room requestRoom = GrpcObjectReader.read(grpcRequestRoom);
    Room responseRoom = this.buildingManagementService.createRoom(requestRoom);
    GrpcRoom grpcResponseRoom = GrpcObjectWriter.write(responseRoom);
    CreateRoomResponse response = CreateRoomResponse.newBuilder().setRoom(grpcResponseRoom).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createBuildingComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {
    GrpcComponent grpcRequestComponent = request.getComponent();
    Component requestComponent = GrpcObjectReader.read(grpcRequestComponent);
    Component responseComponent =
        this.buildingManagementService.createBuildingComponent(requestComponent);
    GrpcComponent grpcResponseComponent = GrpcObjectWriter.write(responseComponent);
    CreateComponentResponse response =
        CreateComponentResponse.newBuilder().setComponent(grpcResponseComponent).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createRoomComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {
    GrpcComponent grpcRequestComponent = request.getComponent();
    Component requestComponent = GrpcObjectReader.read(grpcRequestComponent);
    Component responseComponent =
        this.buildingManagementService.createRoomComponent(requestComponent);
    GrpcComponent grpcResponseComponent = GrpcObjectWriter.write(responseComponent);
    CreateComponentResponse response =
        CreateComponentResponse.newBuilder().setComponent(grpcResponseComponent).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createFavorite(
      CreateFavoriteRequest request, StreamObserver<CreateFavoriteResponse> responseObserver) {
    GrpcFavorite grpcRequestFavorite = request.getFavorite();
    Favorite requestFavorite = GrpcObjectReader.read(grpcRequestFavorite);
    this.buildingManagementService.createFavorite(requestFavorite);
    CreateFavoriteResponse response = CreateFavoriteResponse.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildings(
      ListBuildingsRequest request, StreamObserver<ListBuildingsResponse> responseObserver) {
    ListBuildingConfiguration listBuildingConfiguration = request.getListBuildingConfiguration();
    Settings<Building> settings = GrpcObjectReader.read(listBuildingConfiguration);
    Collection<Building> buildings = this.buildingManagementService.listBuildings(settings);
    Collection<GrpcBuilding> grpcBuildings = GrpcObjectWriter.writeBuildings(buildings);
    ListBuildingsResponse response =
        ListBuildingsResponse.newBuilder().addAllBuildings(grpcBuildings).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRooms(
      ListRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    ListRoomConfiguration listRoomConfiguration = request.getListRoomConfiguration();
    Settings<Room> settings = GrpcObjectReader.read(listRoomConfiguration);
    Collection<Room> rooms =
        this.buildingManagementService.listRooms(settings, identificationNumber);
    Collection<GrpcRoom> grpcRooms = GrpcObjectWriter.writeRooms(rooms);
    ListRoomsResponse response = ListRoomsResponse.newBuilder().addAllRooms(grpcRooms).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildingComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {
    ListComponentConfiguration listComponentConfiguration = request.getListComponentConfiguration();
    Settings<Component> settings = GrpcObjectReader.read(listComponentConfiguration);
    String identificationNumber = request.getIdentificationNumber();
    Collection<Component> components =
        this.buildingManagementService.listBuildingComponents(settings, identificationNumber);
    Collection<GrpcComponent> grpcComponents = GrpcObjectWriter.writeComponents(components);
    ListComponentsResponse response =
        ListComponentsResponse.newBuilder().addAllComponents(grpcComponents).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRoomComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {
    ListComponentConfiguration listComponentConfiguration = request.getListComponentConfiguration();
    Settings<Component> settings = GrpcObjectReader.read(listComponentConfiguration);
    String identificationNumber = request.getIdentificationNumber();
    Collection<Component> components =
        this.buildingManagementService.listRoomComponents(settings, identificationNumber);
    Collection<GrpcComponent> grpcComponents = GrpcObjectWriter.writeComponents(components);
    ListComponentsResponse response =
        ListComponentsResponse.newBuilder().addAllComponents(grpcComponents).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildingNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    ListNotificationConfiguration listNotificationConfiguration =
        request.getListNotificationConfiguration();
    Settings<Notification> settings = GrpcObjectReader.read(listNotificationConfiguration);
    String identificationNumber = request.getIdentificationNumber();
    Collection<Notification> notifications =
        this.buildingManagementService.listBuildingNotifications(settings, identificationNumber);
    Collection<GrpcNotification> grpcNotifications =
        GrpcObjectWriter.writeNotifications(notifications);
    ListNotificationsResponse response =
        ListNotificationsResponse.newBuilder().addAllNotifications(grpcNotifications).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRoomNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    ListNotificationConfiguration listNotificationConfiguration =
        request.getListNotificationConfiguration();
    Settings<Notification> settings = GrpcObjectReader.read(listNotificationConfiguration);
    String identificationNumber = request.getIdentificationNumber();
    Collection<Notification> notifications =
        this.buildingManagementService.listRoomNotifications(settings, identificationNumber);
    Collection<GrpcNotification> grpcNotifications =
        GrpcObjectWriter.writeNotifications(notifications);
    ListNotificationsResponse response =
        ListNotificationsResponse.newBuilder().addAllNotifications(grpcNotifications).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listComponentNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    ListNotificationConfiguration listNotificationConfiguration =
        request.getListNotificationConfiguration();
    Settings<Notification> settings = GrpcObjectReader.read(listNotificationConfiguration);
    String identificationNumber = request.getIdentificationNumber();
    Collection<Notification> notifications =
        this.buildingManagementService.listComponentNotifications(settings, identificationNumber);
    Collection<GrpcNotification> grpcNotifications =
        GrpcObjectWriter.writeNotifications(notifications);
    ListNotificationsResponse response =
        ListNotificationsResponse.newBuilder().addAllNotifications(grpcNotifications).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildingFavorites(
      ListBuildingFavoritesRequest request,
      StreamObserver<ListBuildingFavoritesResponse> responseObserver) {
    ListBuildingConfiguration listBuildingConfiguration = request.getListBuildingConfiguration();
    Settings<Building> settings = GrpcObjectReader.read(listBuildingConfiguration);
    String owner = request.getOwner();
    Collection<Building> buildings =
        this.buildingManagementService.listBuildingFavorites(settings, owner);
    Collection<GrpcBuilding> grpcBuildings = GrpcObjectWriter.writeBuildings(buildings);
    ListBuildingFavoritesResponse response =
        ListBuildingFavoritesResponse.newBuilder().addAllBuildings(grpcBuildings).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRoomFavorites(
      ListRoomFavoritesRequest request,
      StreamObserver<ListRoomFavoritesResponse> responseObserver) {
    ListRoomConfiguration listRoomConfiguration = request.getListRoomConfiguration();
    Settings<Room> settings = GrpcObjectReader.read(listRoomConfiguration);
    String owner = request.getOwner();
    Collection<Room> rooms = this.buildingManagementService.listRoomFavorites(settings, owner);
    Collection<GrpcRoom> grpcRooms = GrpcObjectWriter.writeRooms(rooms);
    ListRoomFavoritesResponse response =
        ListRoomFavoritesResponse.newBuilder().addAllRooms(grpcRooms).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listComponentFavorites(
      ListComponentFavoritesRequest request,
      StreamObserver<ListComponentFavoritesResponse> responseObserver) {
    ListComponentConfiguration listComponentConfiguration = request.getListComponentConfiguration();
    Settings<Component> settings = GrpcObjectReader.read(listComponentConfiguration);
    String owner = request.getOwner();
    Collection<Component> components =
        this.buildingManagementService.listComponentFavorites(settings, owner);
    Collection<GrpcComponent> grpcComponents = GrpcObjectWriter.writeComponents(components);
    ListComponentFavoritesResponse response =
        ListComponentFavoritesResponse.newBuilder().addAllComponents(grpcComponents).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateBuilding(
      UpdateBuildingRequest request, StreamObserver<UpdateBuildingResponse> responseObserver) {
    GrpcBuilding grpcRequestBuilding = request.getBuilding();
    Building requestBuilding = GrpcObjectReader.read(grpcRequestBuilding);
    Building responseBuilding = this.buildingManagementService.updateBuilding(requestBuilding);
    GrpcBuilding grpcResponseBuilding = GrpcObjectWriter.write(responseBuilding);
    UpdateBuildingResponse response =
        UpdateBuildingResponse.newBuilder().setBuilding(grpcResponseBuilding).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateRoom(
      UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {
    GrpcRoom grpcRequestRoom = request.getRoom();
    Room requestRoom = GrpcObjectReader.read(grpcRequestRoom);
    Room responseRoom = this.buildingManagementService.updateRoom(requestRoom);
    GrpcRoom grpcResponseRoom = GrpcObjectWriter.write(responseRoom);
    UpdateRoomResponse response = UpdateRoomResponse.newBuilder().setRoom(grpcResponseRoom).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateComponent(
      UpdateComponentRequest request, StreamObserver<UpdateComponentResponse> responseObserver) {
    GrpcComponent grpcRequestComponent = request.getComponent();
    Component requestComponent = GrpcObjectReader.read(grpcRequestComponent);
    Component responseComponent = this.buildingManagementService.updateComponent(requestComponent);
    GrpcComponent grpcResponseComponent = GrpcObjectWriter.write(responseComponent);
    UpdateComponentResponse response =
        UpdateComponentResponse.newBuilder().setComponent(grpcResponseComponent).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeBuilding(
      RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    this.buildingManagementService.removeBuilding(identificationNumber);
    RemoveResponse response = RemoveResponse.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeRoom(RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    this.buildingManagementService.removeRoom(identificationNumber);
    RemoveResponse response = RemoveResponse.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeComponent(
      RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    this.buildingManagementService.removeComponent(identificationNumber);
    RemoveResponse response = RemoveResponse.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeFavorite(
      RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    this.buildingManagementService.removeFavorite(identificationNumber);
    RemoveResponse response = RemoveResponse.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
