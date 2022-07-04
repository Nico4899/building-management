package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager.BuildingManagementManager;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.CampusLocation;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.GeographicalLocation;

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
  }

  @Override
  public void getComponent(GetComponentRequest request, StreamObserver<GetRoomResponse> responseObserver) {
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

  private GrpcGeographicalLocation writeGeographicalLocation(GeographicalLocation geographicalLocation) {
    GrpcGeographicalLocation.Builder builder = GrpcGeographicalLocation.newBuilder();
    builder.setLatitude(geographicalLocation.getLatitude());
    builder.setLongitude(geographicalLocation.getLatitude());
    return builder.build();
  }

  private GrpcCampusLocation writeCampusLocation (CampusLocation campusLocation) {
    return GrpcCampusLocation.forNumber(campusLocation.ordinal() + 1);
 }

}
