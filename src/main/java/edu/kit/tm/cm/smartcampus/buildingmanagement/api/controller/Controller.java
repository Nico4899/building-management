package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.settings.Settings;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.utility.Utils;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * This class represents the gRPC controller from server side. It provides clients with the building
 * management application microservice api.
 */
@GrpcService
public class Controller extends BuildingManagementGrpc.BuildingManagementImplBase {

  private final Service service;

  /**
   * Constructs a new building management controller.
   *
   * @param service building management service
   */
  @Autowired
  public Controller(Service service) {
    this.service = service;
  }

  @Override
  public void getBuilding(
      GetBuildingRequest request, StreamObserver<GetBuildingResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    Building building = this.service.getBuilding(identificationNumber);
    GrpcBuilding grpcBuilding = Utils.Writer.write(building);
    GetBuildingResponse response =
        GetBuildingResponse.newBuilder().setGrpcBuilding(grpcBuilding).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getRoom(GetRoomRequest request, StreamObserver<GetRoomResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    Room room = this.service.getRoom(identificationNumber);
    GrpcRoom grpcRoom = Utils.Writer.write(room);
    GetRoomResponse response = GetRoomResponse.newBuilder().setRoom(grpcRoom).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getComponent(
      GetComponentRequest request, StreamObserver<GetComponentResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    Component component = this.service.getComponent(identificationNumber);
    GrpcComponent grpcComponent = Utils.Writer.write(component);
    GetComponentResponse response =
        GetComponentResponse.newBuilder().setComponent(grpcComponent).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createBuilding(
      CreateBuildingRequest request, StreamObserver<CreateBuildingResponse> responseObserver) {
    GrpcBuilding grpcRequestBuilding = request.getBuilding();
    Building requestBuilding = Utils.Reader.read(grpcRequestBuilding);
    Building responseBuilding = this.service.createBuilding(requestBuilding);
    GrpcBuilding grpcResponseBuilding = Utils.Writer.write(responseBuilding);
    CreateBuildingResponse response =
        CreateBuildingResponse.newBuilder().setBuilding(grpcResponseBuilding).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createRoom(
      CreateRoomRequest request, StreamObserver<CreateRoomResponse> responseObserver) {
    GrpcRoom grpcRequestRoom = request.getRoom();
    Room requestRoom = Utils.Reader.read(grpcRequestRoom);
    Room responseRoom = this.service.createRoom(requestRoom);
    GrpcRoom grpcResponseRoom = Utils.Writer.write(responseRoom);
    CreateRoomResponse response = CreateRoomResponse.newBuilder().setRoom(grpcResponseRoom).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createBuildingComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {
    GrpcComponent grpcRequestComponent = request.getComponent();
    Component requestComponent = Utils.Reader.read(grpcRequestComponent);
    Component responseComponent = this.service.createComponent(requestComponent);
    GrpcComponent grpcResponseComponent = Utils.Writer.write(responseComponent);
    CreateComponentResponse response =
        CreateComponentResponse.newBuilder().setComponent(grpcResponseComponent).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createRoomComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {
    GrpcComponent grpcRequestComponent = request.getComponent();
    Component requestComponent = Utils.Reader.read(grpcRequestComponent);
    Component responseComponent = this.service.createRoomComponent(requestComponent);
    GrpcComponent grpcResponseComponent = Utils.Writer.write(responseComponent);
    CreateComponentResponse response =
        CreateComponentResponse.newBuilder().setComponent(grpcResponseComponent).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createFavorite(
      CreateFavoriteRequest request, StreamObserver<CreateFavoriteResponse> responseObserver) {
    GrpcFavorite grpcRequestFavorite = request.getFavorite();
    Favorite requestFavorite = Utils.Reader.read(grpcRequestFavorite);
    this.service.createFavorite(requestFavorite);
    CreateFavoriteResponse response = CreateFavoriteResponse.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildings(
      ListBuildingsRequest request, StreamObserver<ListBuildingsResponse> responseObserver) {
    GrpcListSettings grpcListSettings = request.getGrpcListSettings();
    Settings<Building> settings = Utils.Reader.readListBuildingSettings(grpcListSettings);
    Collection<Building> buildings = this.service.listBuildings(settings);
    Collection<GrpcBuilding> grpcBuildings = Utils.Writer.writeBuildings(buildings);
    ListBuildingsResponse response =
        ListBuildingsResponse.newBuilder().addAllBuildings(grpcBuildings).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRooms(
      ListRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    GrpcListSettings grpcListSettings = request.getGrpcListSettings();
    Settings<Room> settings = Utils.Reader.readListRoomsSettings(grpcListSettings);
    Collection<Room> rooms = this.service.listRooms(settings, identificationNumber);
    Collection<GrpcRoom> grpcRooms = Utils.Writer.writeRooms(rooms);
    ListRoomsResponse response = ListRoomsResponse.newBuilder().addAllRooms(grpcRooms).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildingComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    Collection<Component> components = this.service.listBuildingComponents(identificationNumber);
    Collection<GrpcComponent> grpcComponents = Utils.Writer.writeComponents(components);
    ListComponentsResponse response =
        ListComponentsResponse.newBuilder().addAllComponents(grpcComponents).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRoomComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    Collection<Component> components = this.service.listRoomComponents(identificationNumber);
    Collection<GrpcComponent> grpcComponents = Utils.Writer.writeComponents(components);
    ListComponentsResponse response =
        ListComponentsResponse.newBuilder().addAllComponents(grpcComponents).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildingNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    GrpcListSettings grpcListSettings = request.getGrpcListSettings();
    Settings<Notification> settings = Utils.Reader.readListNotificationsSettings(grpcListSettings);
    String identificationNumber = request.getIdentificationNumber();
    Collection<Notification> notifications =
        this.service.listBuildingNotifications(settings, identificationNumber);
    Collection<GrpcNotification> grpcNotifications = Utils.Writer.writeNotifications(notifications);
    ListNotificationsResponse response =
        ListNotificationsResponse.newBuilder().addAllNotifications(grpcNotifications).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRoomNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    GrpcListSettings grpcListSettings = request.getGrpcListSettings();
    Settings<Notification> settings = Utils.Reader.readListNotificationsSettings(grpcListSettings);
    String identificationNumber = request.getIdentificationNumber();
    Collection<Notification> notifications =
        this.service.listRoomNotifications(settings, identificationNumber);
    Collection<GrpcNotification> grpcNotifications = Utils.Writer.writeNotifications(notifications);
    ListNotificationsResponse response =
        ListNotificationsResponse.newBuilder().addAllNotifications(grpcNotifications).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listComponentNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    GrpcListSettings grpcListSettings = request.getGrpcListSettings();
    Settings<Notification> settings = Utils.Reader.readListNotificationsSettings(grpcListSettings);
    String identificationNumber = request.getIdentificationNumber();
    Collection<Notification> notifications =
        this.service.listComponentNotifications(settings, identificationNumber);
    Collection<GrpcNotification> grpcNotifications = Utils.Writer.writeNotifications(notifications);
    ListNotificationsResponse response =
        ListNotificationsResponse.newBuilder().addAllNotifications(grpcNotifications).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildingFavorites(
      ListBuildingFavoritesRequest request,
      StreamObserver<ListBuildingFavoritesResponse> responseObserver) {
    GrpcListSettings grpcListSettings = request.getGrpcListSettings();
    Settings<Building> settings = Utils.Reader.readListBuildingSettings(grpcListSettings);
    String owner = request.getOwner();
    Collection<Building> buildings = this.service.listBuildingFavorites(settings, owner);
    Collection<GrpcBuilding> grpcBuildings = Utils.Writer.writeBuildings(buildings);
    ListBuildingFavoritesResponse response =
        ListBuildingFavoritesResponse.newBuilder().addAllBuildings(grpcBuildings).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRoomFavorites(
      ListRoomFavoritesRequest request,
      StreamObserver<ListRoomFavoritesResponse> responseObserver) {
    GrpcListSettings grpcListSettings = request.getGrpcListSettings();
    Settings<Room> settings = Utils.Reader.readListRoomsSettings(grpcListSettings);
    String owner = request.getOwner();
    Collection<Room> rooms = this.service.listRoomFavorites(settings, owner);
    Collection<GrpcRoom> grpcRooms = Utils.Writer.writeRooms(rooms);
    ListRoomFavoritesResponse response =
        ListRoomFavoritesResponse.newBuilder().addAllRooms(grpcRooms).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listComponentFavorites(
      ListComponentFavoritesRequest request,
      StreamObserver<ListComponentFavoritesResponse> responseObserver) {
    String owner = request.getOwner();
    Collection<Component> components = this.service.listComponentFavorites(owner);
    Collection<GrpcComponent> grpcComponents = Utils.Writer.writeComponents(components);
    ListComponentFavoritesResponse response =
        ListComponentFavoritesResponse.newBuilder().addAllComponents(grpcComponents).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateBuilding(
      UpdateBuildingRequest request, StreamObserver<UpdateBuildingResponse> responseObserver) {
    GrpcBuilding grpcRequestBuilding = request.getBuilding();
    Building requestBuilding = Utils.Reader.read(grpcRequestBuilding);
    Building responseBuilding = this.service.updateBuilding(requestBuilding);
    GrpcBuilding grpcResponseBuilding = Utils.Writer.write(responseBuilding);
    UpdateBuildingResponse response =
        UpdateBuildingResponse.newBuilder().setBuilding(grpcResponseBuilding).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateRoom(
      UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {
    GrpcRoom grpcRequestRoom = request.getRoom();
    Room requestRoom = Utils.Reader.read(grpcRequestRoom);
    Room responseRoom = this.service.updateRoom(requestRoom);
    GrpcRoom grpcResponseRoom = Utils.Writer.write(responseRoom);
    UpdateRoomResponse response = UpdateRoomResponse.newBuilder().setRoom(grpcResponseRoom).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateComponent(
      UpdateComponentRequest request, StreamObserver<UpdateComponentResponse> responseObserver) {
    GrpcComponent grpcRequestComponent = request.getComponent();
    Component requestComponent = Utils.Reader.read(grpcRequestComponent);
    Component responseComponent = this.service.updateComponent(requestComponent);
    GrpcComponent grpcResponseComponent = Utils.Writer.write(responseComponent);
    UpdateComponentResponse response =
        UpdateComponentResponse.newBuilder().setComponent(grpcResponseComponent).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeBuilding(
      RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    this.service.removeBuilding(identificationNumber);
    RemoveResponse response = RemoveResponse.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeRoom(RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    this.service.removeRoom(identificationNumber);
    RemoveResponse response = RemoveResponse.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeComponent(
      RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    this.service.removeComponent(identificationNumber);
    RemoveResponse response = RemoveResponse.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeFavorite(
      RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();
    this.service.removeFavorite(identificationNumber);
    RemoveResponse response = RemoveResponse.newBuilder().build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
