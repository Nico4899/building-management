package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.error.GrpcServerErrorHandler;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.utilitly.GrpcObjectReader;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.utilitly.GrpcObjectWriter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.BuildingManagementService;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;

/**
 * This class represents the gRPC controller from server side. It provides clients with the building
 * management application microservice api. In order to ensure the input and error control flow, a
 * custom wrapper for {@link StreamObserver} is used. {@link GrpcServerErrorHandler} allows the
 * application to catch and handle all thrown exceptions and ensures to provide a client with proper
 * information.
 */
@Controller
public class BuildingManagementController
    extends BuildingManagementGrpc.BuildingManagementImplBase {

  private final GrpcObjectWriter grpcObjectWriter;
  private final GrpcObjectReader grpcObjectReader;
  private final BuildingManagementService buildingManagementService;

  /**
   * Constructs a new building management controller.
   *
   * @param grpcObjectWriter grpc object writer
   * @param grpcObjectReader grpc object reader
   * @param buildingManagementService building management service
   */
  @Autowired
  public BuildingManagementController(
      GrpcObjectWriter grpcObjectWriter,
      GrpcObjectReader grpcObjectReader,
      BuildingManagementService buildingManagementService) {
    this.grpcObjectWriter = grpcObjectWriter;
    this.grpcObjectReader = grpcObjectReader;
    this.buildingManagementService = buildingManagementService;
  }

  @Override
  public void getBuilding(
      GetBuildingRequest request, StreamObserver<GetBuildingResponse> responseObserver) {
    GrpcServerErrorHandler<GetBuildingRequest, GetBuildingResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    GetBuildingResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Building building = this.buildingManagementService.getBuilding(identificationNumber);
              GrpcBuilding grpcBuilding = this.grpcObjectWriter.write(building);

              return GetBuildingResponse.newBuilder().setBuilding(grpcBuilding).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void getRoom(GetRoomRequest request, StreamObserver<GetRoomResponse> responseObserver) {
    GrpcServerErrorHandler<GetRoomRequest, GetRoomResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    GetRoomResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Room room = this.buildingManagementService.getRoom(identificationNumber);
              GrpcRoom grpcRoom = this.grpcObjectWriter.write(room);

              return GetRoomResponse.newBuilder().setRoom(grpcRoom).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void getComponent(
      GetComponentRequest request, StreamObserver<GetComponentResponse> responseObserver) {
    GrpcServerErrorHandler<GetComponentRequest, GetComponentResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    GetComponentResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Component component =
                  this.buildingManagementService.getComponent(identificationNumber);
              GrpcComponent grpcComponent = this.grpcObjectWriter.write(component);

              return GetComponentResponse.newBuilder().setComponent(grpcComponent).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void createBuilding(
      CreateBuildingRequest request, StreamObserver<CreateBuildingResponse> responseObserver) {
    GrpcServerErrorHandler<CreateBuildingRequest, CreateBuildingResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    CreateBuildingResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              GrpcBuilding grpcRequestBuilding = request.getBuilding();
              Building requestBuilding = this.grpcObjectReader.read(grpcRequestBuilding);
              Building responseBuilding =
                  this.buildingManagementService.createBuilding(requestBuilding);
              GrpcBuilding grpcResponseBuilding = this.grpcObjectWriter.write(responseBuilding);

              return CreateBuildingResponse.newBuilder().setBuilding(grpcResponseBuilding).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void createRoom(
      CreateRoomRequest request, StreamObserver<CreateRoomResponse> responseObserver) {
    GrpcServerErrorHandler<CreateRoomRequest, CreateRoomResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    CreateRoomResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              GrpcRoom grpcRequestRoom = request.getRoom();
              Room requestRoom = this.grpcObjectReader.read(grpcRequestRoom);
              Room responseRoom = this.buildingManagementService.createRoom(requestRoom);
              GrpcRoom grpcResponseRoom = this.grpcObjectWriter.write(responseRoom);

              return CreateRoomResponse.newBuilder().setRoom(grpcResponseRoom).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void createBuildingComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {
    GrpcServerErrorHandler<CreateComponentRequest, CreateComponentResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    CreateComponentResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              GrpcComponent grpcRequestComponent = request.getComponent();
              Component requestComponent = this.grpcObjectReader.read(grpcRequestComponent);
              Component responseComponent =
                  this.buildingManagementService.createBuildingComponent(requestComponent);

              GrpcComponent grpcResponseComponent = this.grpcObjectWriter.write(responseComponent);
              return CreateComponentResponse.newBuilder()
                  .setComponent(grpcResponseComponent)
                  .build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void createRoomComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {
    GrpcServerErrorHandler<CreateComponentRequest, CreateComponentResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    CreateComponentResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              GrpcComponent grpcRequestComponent = request.getComponent();
              Component requestComponent = this.grpcObjectReader.read(grpcRequestComponent);
              Component responseComponent =
                  this.buildingManagementService.createRoomComponent(requestComponent);

              GrpcComponent grpcResponseComponent = this.grpcObjectWriter.write(responseComponent);
              return CreateComponentResponse.newBuilder()
                  .setComponent(grpcResponseComponent)
                  .build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void createFavorite(
      CreateFavoriteRequest request, StreamObserver<CreateFavoriteResponse> responseObserver) {
    GrpcServerErrorHandler<CreateFavoriteRequest, CreateFavoriteResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    CreateFavoriteResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              GrpcFavorite grpcRequestFavorite = request.getFavorite();
              Favorite requestFavorite = this.grpcObjectReader.read(grpcRequestFavorite);
              this.buildingManagementService.createFavorite(requestFavorite);

              return CreateFavoriteResponse.newBuilder().build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void listBuildings(
      ListBuildingsRequest request, StreamObserver<ListBuildingsResponse> responseObserver) {
    GrpcServerErrorHandler<ListBuildingsRequest, ListBuildingsResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    ListBuildingsResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              GrpcFilterOptions grpcFilterOptions = request.getGrpcFilterOptions();
              FilterOptions filterOptions = this.grpcObjectReader.read(grpcFilterOptions);
              Collection<Building> buildings =
                  this.buildingManagementService.listBuildings(filterOptions);
              Collection<GrpcBuilding> grpcBuildings =
                  this.grpcObjectWriter.writeBuildings(buildings);

              return ListBuildingsResponse.newBuilder().addAllBuildings(grpcBuildings).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void listRooms(
      ListRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
    GrpcServerErrorHandler<ListRoomsRequest, ListRoomsResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    ListRoomsResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              GrpcFilterOptions grpcFilterOptions = request.getGrpcFilterOptions();
              FilterOptions filterOptions = this.grpcObjectReader.read(grpcFilterOptions);
              Collection<Room> rooms =
                  this.buildingManagementService.listRooms(filterOptions, identificationNumber);

              Collection<GrpcRoom> grpcRooms = this.grpcObjectWriter.writeRooms(rooms);

              return ListRoomsResponse.newBuilder().addAllRooms(grpcRooms).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void listBuildingComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {
    GrpcServerErrorHandler<ListComponentsRequest, ListComponentsResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    ListComponentsResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Collection<Component> components =
                  this.buildingManagementService.listBuildingComponents(identificationNumber);
              Collection<GrpcComponent> grpcComponents =
                  this.grpcObjectWriter.writeComponents(components);

              return ListComponentsResponse.newBuilder().addAllComponents(grpcComponents).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void listRoomComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {
    GrpcServerErrorHandler<ListComponentsRequest, ListComponentsResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    ListComponentsResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Collection<Component> components =
                  this.buildingManagementService.listRoomComponents(identificationNumber);
              Collection<GrpcComponent> grpcComponents =
                  this.grpcObjectWriter.writeComponents(components);

              return ListComponentsResponse.newBuilder().addAllComponents(grpcComponents).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void listBuildingNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    GrpcServerErrorHandler<ListNotificationsRequest, ListNotificationsResponse>
        grpcServerErrorHandler = new GrpcServerErrorHandler<>(responseObserver);
    ListNotificationsResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Collection<Notification> notifications =
                  this.buildingManagementService.listBuildingNotifications(identificationNumber);
              Collection<GrpcNotification> grpcNotifications =
                  this.grpcObjectWriter.writeNotifications(notifications);

              return ListNotificationsResponse.newBuilder()
                  .addAllNotifications(grpcNotifications)
                  .build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void listRoomNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    GrpcServerErrorHandler<ListNotificationsRequest, ListNotificationsResponse>
        grpcServerErrorHandler = new GrpcServerErrorHandler<>(responseObserver);
    ListNotificationsResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Collection<Notification> notifications =
                  this.buildingManagementService.listRoomNotifications(identificationNumber);
              Collection<GrpcNotification> grpcNotifications =
                  this.grpcObjectWriter.writeNotifications(notifications);

              return ListNotificationsResponse.newBuilder()
                  .addAllNotifications(grpcNotifications)
                  .build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void listComponentNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {
    GrpcServerErrorHandler<ListNotificationsRequest, ListNotificationsResponse>
        grpcServerErrorHandler = new GrpcServerErrorHandler<>(responseObserver);
    ListNotificationsResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              Collection<Notification> notifications =
                  this.buildingManagementService.listComponentNotifications(identificationNumber);
              Collection<GrpcNotification> grpcNotifications =
                  this.grpcObjectWriter.writeNotifications(notifications);

              return ListNotificationsResponse.newBuilder()
                  .addAllNotifications(grpcNotifications)
                  .build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void listBuildingFavorites(
      ListBuildingFavoritesRequest request,
      StreamObserver<ListBuildingFavoritesResponse> responseObserver) {
    GrpcServerErrorHandler<ListBuildingFavoritesRequest, ListBuildingFavoritesResponse>
        grpcServerErrorHandler = new GrpcServerErrorHandler<>(responseObserver);
    ListBuildingFavoritesResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String owner = request.getOwner();
              Collection<Building> buildings =
                  this.buildingManagementService.listBuildingFavorites(owner);
              Collection<GrpcBuilding> grpcBuildings =
                  this.grpcObjectWriter.writeBuildings(buildings);

              return ListBuildingFavoritesResponse.newBuilder()
                  .addAllBuildings(grpcBuildings)
                  .build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void listRoomFavorites(
      ListRoomFavoritesRequest request,
      StreamObserver<ListRoomFavoritesResponse> responseObserver) {
    GrpcServerErrorHandler<ListRoomFavoritesRequest, ListRoomFavoritesResponse>
        grpcServerErrorHandler = new GrpcServerErrorHandler<>(responseObserver);
    ListRoomFavoritesResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String owner = request.getOwner();
              Collection<Room> rooms = this.buildingManagementService.listRoomFavorites(owner);
              Collection<GrpcRoom> grpcRooms = this.grpcObjectWriter.writeRooms(rooms);

              return ListRoomFavoritesResponse.newBuilder().addAllRooms(grpcRooms).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void listComponentFavorites(
      ListComponentFavoritesRequest request,
      StreamObserver<ListComponentFavoritesResponse> responseObserver) {
    GrpcServerErrorHandler<ListComponentFavoritesRequest, ListComponentFavoritesResponse>
        grpcServerErrorHandler = new GrpcServerErrorHandler<>(responseObserver);
    ListComponentFavoritesResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String owner = request.getOwner();
              Collection<Component> components =
                  this.buildingManagementService.listComponentFavorites(owner);
              Collection<GrpcComponent> grpcComponents =
                  this.grpcObjectWriter.writeComponents(components);

              return ListComponentFavoritesResponse.newBuilder()
                  .addAllComponents(grpcComponents)
                  .build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void updateBuilding(
      UpdateBuildingRequest request, StreamObserver<UpdateBuildingResponse> responseObserver) {
    GrpcServerErrorHandler<UpdateBuildingRequest, UpdateBuildingResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    UpdateBuildingResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              GrpcBuilding grpcRequestBuilding = request.getBuilding();
              Building requestBuilding = this.grpcObjectReader.read(grpcRequestBuilding);
              Building responseBuilding =
                  this.buildingManagementService.updateBuilding(requestBuilding);
              GrpcBuilding grpcResponseBuilding = this.grpcObjectWriter.write(responseBuilding);

              return UpdateBuildingResponse.newBuilder().setBuilding(grpcResponseBuilding).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void updateRoom(
      UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {
    GrpcServerErrorHandler<UpdateRoomRequest, UpdateRoomResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    UpdateRoomResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              GrpcRoom grpcRequestRoom = request.getRoom();
              Room requestRoom = this.grpcObjectReader.read(grpcRequestRoom);
              Room responseRoom = this.buildingManagementService.updateRoom(requestRoom);
              GrpcRoom grpcResponseRoom = this.grpcObjectWriter.write(responseRoom);

              return UpdateRoomResponse.newBuilder().setRoom(grpcResponseRoom).build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void updateComponent(
      UpdateComponentRequest request, StreamObserver<UpdateComponentResponse> responseObserver) {
    GrpcServerErrorHandler<UpdateComponentRequest, UpdateComponentResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    UpdateComponentResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              GrpcComponent grpcRequestComponent = request.getComponent();
              Component requestComponent = this.grpcObjectReader.read(grpcRequestComponent);
              Component responseComponent =
                  this.buildingManagementService.updateComponent(requestComponent);
              GrpcComponent grpcResponseComponent = this.grpcObjectWriter.write(responseComponent);

              return UpdateComponentResponse.newBuilder()
                  .setComponent(grpcResponseComponent)
                  .build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void removeBuilding(
      RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    GrpcServerErrorHandler<RemoveRequest, RemoveResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    RemoveResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              this.buildingManagementService.removeBuilding(identificationNumber);

              return RemoveResponse.newBuilder().build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void removeRoom(RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    GrpcServerErrorHandler<RemoveRequest, RemoveResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    RemoveResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              this.buildingManagementService.removeRoom(identificationNumber);

              return RemoveResponse.newBuilder().build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void removeComponent(
      RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    GrpcServerErrorHandler<RemoveRequest, RemoveResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    RemoveResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              this.buildingManagementService.removeComponent(identificationNumber);

              return RemoveResponse.newBuilder().build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }

  @Override
  public void removeFavorite(
      RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    GrpcServerErrorHandler<RemoveRequest, RemoveResponse> grpcServerErrorHandler =
        new GrpcServerErrorHandler<>(responseObserver);
    RemoveResponse response =
        grpcServerErrorHandler.execute(
            x -> {
              String identificationNumber = request.getIdentificationNumber();
              this.buildingManagementService.removeFavorite(identificationNumber);

              return RemoveResponse.newBuilder().build();
            },
            request);
    grpcServerErrorHandler.onNext(response);
    grpcServerErrorHandler.onCompleted();
  }
}
