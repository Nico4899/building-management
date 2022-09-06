package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.utility.DataTransferUtils;
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
    Building building =
      DataTransferUtils.ServerRequestReader.readGetBuildingRequest(request, service);
    GetBuildingResponse response =
      DataTransferUtils.ServerResponseWriter.writeGetBuildingResponse(building);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getRoom(GetRoomRequest request, StreamObserver<GetRoomResponse> responseObserver) {
    Room room = DataTransferUtils.ServerRequestReader.readGetRoomRequest(request, service);
    GetRoomResponse response = DataTransferUtils.ServerResponseWriter.writeGetRoomResponse(room);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getComponent(
    GetComponentRequest request, StreamObserver<GetComponentResponse> responseObserver) {
    Component component =
      DataTransferUtils.ServerRequestReader.readGetComponentRequest(request, service);
    GetComponentResponse response =
      DataTransferUtils.ServerResponseWriter.writeGetComponentResponse(component);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createBuilding(
    CreateBuildingRequest request, StreamObserver<CreateBuildingResponse> responseObserver) {
    Building building =
      DataTransferUtils.ServerRequestReader.readCreateBuildingRequest(request, service);
    CreateBuildingResponse response =
      DataTransferUtils.ServerResponseWriter.writeCreateBuildingResponse(building);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createRoom(
    CreateRoomRequest request, StreamObserver<CreateRoomResponse> responseObserver) {
    Room room = DataTransferUtils.ServerRequestReader.readCreateRoomRequest(request, service);
    CreateRoomResponse response =
      DataTransferUtils.ServerResponseWriter.writeCreateRoomResponse(room);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createComponent(
    CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {
    Component component =
      DataTransferUtils.ServerRequestReader.readCreateComponentRequest(request, service);
    CreateComponentResponse response =
      DataTransferUtils.ServerResponseWriter.writeCreateComponentResponse(component);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void createFavorite(
    CreateFavoriteRequest request, StreamObserver<CreateFavoriteResponse> responseObserver) {
    DataTransferUtils.ServerRequestReader.readCreateFavoriteRequest(request, service);
    CreateFavoriteResponse response =
      DataTransferUtils.ServerResponseWriter.writeCreateFavoriteResponse();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildings(
    ListBuildingsRequest request, StreamObserver<ListBuildingsResponse> responseObserver) {
    Collection<Building> buildings =
      DataTransferUtils.ServerRequestReader.readListBuildingsRequest(request, service);
    ListBuildingsResponse response =
      DataTransferUtils.ServerResponseWriter.writeListBuildingsResponse(buildings);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listRooms(
    ListRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
    Collection<Room> rooms =
      DataTransferUtils.ServerRequestReader.readListRoomsRequest(request, service);
    ListRoomsResponse response =
      DataTransferUtils.ServerResponseWriter.writeListRoomsResponse(rooms);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listComponents(
    ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {
    Collection<Component> components =
      DataTransferUtils.ServerRequestReader.readListComponentsRequest(request, service);
    ListComponentsResponse response =
      DataTransferUtils.ServerResponseWriter.writeListComponentsResponse(components);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listNotifications(
    ListNotificationsRequest request,
    StreamObserver<ListNotificationsResponse> responseObserver) {
    Collection<Notification> notifications =
      DataTransferUtils.ServerRequestReader.readListNotificationsRequest(request, service);
    ListNotificationsResponse response =
      DataTransferUtils.ServerResponseWriter.writeListNotificationsResponse(notifications);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listFavoriteBuildings(
    ListFavoriteBuildingsRequest request,
    StreamObserver<ListFavoriteBuildingsResponse> responseObserver) {
    Collection<Building> buildings =
      DataTransferUtils.ServerRequestReader.readListFavoriteBuildings(request, service);
    ListFavoriteBuildingsResponse response =
      DataTransferUtils.ServerResponseWriter.writeListFavoriteBuildingsResponse(buildings);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listFavoriteRooms(
    ListFavoriteRoomsRequest request,
    StreamObserver<ListFavoriteRoomsResponse> responseObserver) {
    Collection<Room> rooms =
      DataTransferUtils.ServerRequestReader.readListFavoriteRooms(request, service);
    ListFavoriteRoomsResponse response =
      DataTransferUtils.ServerResponseWriter.writeListFavoriteRoomsResponse(rooms);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listFavoriteComponents(
    ListFavoriteComponentsRequest request,
    StreamObserver<ListFavoriteComponentsResponse> responseObserver) {
    Collection<Component> components =
      DataTransferUtils.ServerRequestReader.readListFavoriteComponentsRequest(request, service);
    ListFavoriteComponentsResponse response =
      DataTransferUtils.ServerResponseWriter.writeListFavoriteComponentsResponse(components);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateBuilding(
    UpdateBuildingRequest request, StreamObserver<UpdateBuildingResponse> responseObserver) {
    Building building =
      DataTransferUtils.ServerRequestReader.readUpdateBuildingRequest(request, service);
    UpdateBuildingResponse response =
      DataTransferUtils.ServerResponseWriter.writeUpdateBuildingResponse(building);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateRoom(
    UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {
    Room room = DataTransferUtils.ServerRequestReader.readUpdateRoomRequest(request, service);
    UpdateRoomResponse response =
      DataTransferUtils.ServerResponseWriter.writeUpdateRoomResponse(room);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateComponent(
    UpdateComponentRequest request, StreamObserver<UpdateComponentResponse> responseObserver) {
    Component component =
      DataTransferUtils.ServerRequestReader.readUpdateComponentRequest(request, service);
    UpdateComponentResponse response =
      DataTransferUtils.ServerResponseWriter.writeUpdateComponentResponse(component);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeBuilding(
    RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    DataTransferUtils.ServerRequestReader.readRemoveBuildingRequest(request, service);
    RemoveResponse response = DataTransferUtils.ServerResponseWriter.writeRemoveResponse();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeRoom(RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    DataTransferUtils.ServerRequestReader.readRemoveRoomRequest(request, service);
    RemoveResponse response = DataTransferUtils.ServerResponseWriter.writeRemoveResponse();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeComponent(
    RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
    DataTransferUtils.ServerRequestReader.readRemoveComponentRequest(request, service);
    RemoveResponse response = DataTransferUtils.ServerResponseWriter.writeRemoveResponse();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void removeFavorite(
    RemoveFavoriteRequest request, StreamObserver<RemoveResponse> responseObserver) {
    DataTransferUtils.ServerRequestReader.readRemoveFavoriteRequest(request, service);
    RemoveResponse response = DataTransferUtils.ServerResponseWriter.writeRemoveResponse();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
