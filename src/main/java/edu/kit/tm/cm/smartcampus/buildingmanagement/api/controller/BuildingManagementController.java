package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager.BuildingManagementManager;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;

import io.grpc.stub.StreamObserver;

public class BuildingManagementController extends BuildingManagementGrpc.BuildingManagementImplBase {

  private final BuildingManagementManager buildingManagementManager;

  public BuildingManagementController(BuildingManagementManager buildingManagementManager) {
    this.buildingManagementManager = buildingManagementManager;
  }

  @Override
  public void getBuilding(GetBuildingRequest request, StreamObserver<GetBuildingResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();

    Building building = buildingManagementManager.getBuilding(identificationNumber);

    GetBuildingResponse response = GetBuildingResponse.newBuilder()
            .setBuilding(restBuildingToGrpcBuilding(building))
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getRoom(GetRoomRequest request, StreamObserver<GetRoomResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();

    Room room = buildingManagementManager.getRoom(identificationNumber);

    GetRoomResponse response = GetRoomResponse.newBuilder()
            .setRoom(restRoomToGrpcRoom(room))
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getComponent(GetComponentRequest request, StreamObserver<GetComponentResponse> responseObserver) {
    String identificationNumber = request.getIdentificationNumber();

    Component component = buildingManagementManager.getComponent(identificationNumber);

    GetComponentResponse response = GetComponentResponse.newBuilder()
            .setComponent(restComponentToGrpcComponent(component))
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listBuildings(ListBuildingsRequest request, StreamObserver<ListBuildingsResponse> responseObserver) {
  }

  @Override
  public void listRooms(ListRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
  }

  @Override
  public void listComponents(ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {
  }

  @Override
  public void listNotifications(ListNotificationsRequest request, StreamObserver<ListNotificationsResponse> responseObserver) {
  }

  @Override
  public void listFavorites(ListFavoritesRequest request, StreamObserver<ListFavoritesResponse> responseObserver) {
  }

  @Override
  public void createBuilding(CreateBuildingRequest request, StreamObserver<CreateBuildingResponse> responseObserver) {
  }

  @Override
  public void createRoom(CreateRoomRequest request, StreamObserver<CreateRoomResponse> responseObserver) {
  }

  @Override
  public void createComponent(CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {
  }

  @Override
  public void createFavorite(CreateFavoriteRequest request, StreamObserver<CreateFavoriteResponse> responseObserver) {
  }

  @Override
  public void updateBuilding(UpdateBuildingRequest request, StreamObserver<UpdateBuildingResponse> responseObserver) {
  }

  @Override
  public void updateRoom(UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {
  }

  @Override
  public void updateComponent(UpdateComponentRequest request, StreamObserver<UpdateComponentResponse> responseObserver) {
  }

  @Override
  public void remove(RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public String toString() {
    return super.toString();
  }

  private GrpcBuilding restBuildingToGrpcBuilding(Building building) {
    GrpcBuilding.Builder builder = GrpcBuilding.newBuilder();
    if(building.getBuildingName() != null) builder.setBuildingName(building.getBuildingName());
    if(building.getBuildingNumber() != null) builder.setBuildingNumber(building.getBuildingNumber());
    if(building.getIdentificationNumber() != null) builder.setIdentificationNumber(building.getIdentificationNumber());
    if(building.getCampusLocation() != null) builder.setCampusLocation(writeCampusLocation(building.getCampusLocation()));
    if(building.getGeographicalLocation() != null) builder.setGeographicalLocation(writeGeographicalLocation(building.getGeographicalLocation()));
    builder.setNumFloors(building.getNumFloors());

    return builder.build();
  }

  private GrpcRoom restRoomToGrpcRoom(Room room) {
    GrpcRoom.Builder builder = GrpcRoom.newBuilder();
    builder.setFloor(room.getFloor());
    builder.setRoomName(room.getRoomName());
    builder.setRoomNumber(room.getRoomNumber());
    builder.setIdentificationNumber(room.getIdentificationNumber());
    builder.setParentIdentificationNumber(room.getParentIdentificationNumber());
    builder.setGeographicalLocation(writeGeographicalLocation(room.getGeographicalLocation()));
    builder.setRoomType(writeRoomType(room.getRoomType()));
    return builder.build();
  }

  private GrpcComponent restComponentToGrpcComponent(Component component) {
    GrpcComponent.Builder builder = GrpcComponent.newBuilder();
    builder.setComponentDescription(component.getComponentDescription());
    builder.setIdentificationNumber(component.getIdentificationNumber());
    builder.setGeographicalLocation(writeGeographicalLocation(component.getGeographicalLocation()));
    builder.setParentIdentificationNumber(component.getParentIdentificationNumber());
    return builder.build();

  }

  private GrpcGeographicalLocation writeGeographicalLocation(GeographicalLocation geographicalLocation) {
    GrpcGeographicalLocation.Builder builder = GrpcGeographicalLocation.newBuilder();
    builder.setLatitude(geographicalLocation.getLatitude());
    builder.setLongitude(geographicalLocation.getLatitude());
    return builder.build();
  }

  private GrpcCampusLocation writeCampusLocation (CampusLocation campusLocation) {
    return GrpcCampusLocation.forNumber(campusLocation.ordinal() + 1);
 }

 private GrpcRoomType writeRoomType(RoomType roomType) {
    return GrpcRoomType.forNumber(roomType.ordinal() + 1);
 }

}
