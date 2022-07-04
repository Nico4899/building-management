package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import com.google.protobuf.Timestamp;
import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager.BuildingManagementManager;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import io.grpc.stub.StreamObserver;

import java.util.Collection;

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
  public void getRoom(GetRoomRequest request, StreamObserver<GetRoomResponse> responseObserver) {}

  @Override
  public void getComponent(
      GetComponentRequest request, StreamObserver<GetComponentResponse> responseObserver) {}

  @Override
  public void listBuildings(
      ListBuildingsRequest request, StreamObserver<ListBuildingsResponse> responseObserver) {}

  @Override
  public void listRooms(
      ListRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {}

  @Override
  public void listComponents(
      ListComponentsRequest request, StreamObserver<ListComponentsResponse> responseObserver) {}

  @Override
  public void listNotifications(
      ListNotificationsRequest request,
      StreamObserver<ListNotificationsResponse> responseObserver) {}

  @Override
  public void listFavorites(
      ListFavoritesRequest request, StreamObserver<ListFavoritesResponse> responseObserver) {}

  @Override
  public void createBuilding(
      CreateBuildingRequest request, StreamObserver<CreateBuildingResponse> responseObserver) {}

  @Override
  public void createRoom(
      CreateRoomRequest request, StreamObserver<CreateRoomResponse> responseObserver) {}

  @Override
  public void createComponent(
      CreateComponentRequest request, StreamObserver<CreateComponentResponse> responseObserver) {}

  @Override
  public void createFavorite(
      CreateFavoriteRequest request, StreamObserver<CreateFavoriteResponse> responseObserver) {}

  @Override
  public void updateBuilding(
      UpdateBuildingRequest request, StreamObserver<UpdateBuildingResponse> responseObserver) {}

  @Override
  public void updateRoom(
      UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {}

  @Override
  public void updateComponent(
      UpdateComponentRequest request, StreamObserver<UpdateComponentResponse> responseObserver) {}

  @Override
  public void remove(RemoveRequest request, StreamObserver<RemoveResponse> responseObserver) {}

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
    favorite.setReverenceIdentificationNumber(grpcFavorite.getIdentificationNumber());
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
    return RoomType.valueOf(grpcRoomType.name());
  }

  private CampusLocation readCampusLocation(GrpcCampusLocation grpcCampusLocation) {
    return CampusLocation.valueOf(grpcCampusLocation.name());
  }

  private GeographicalLocation readGeographicalLocation(
      GrpcGeographicalLocation grpcGeographicalLocation) {
    GeographicalLocation geographicalLocation = new GeographicalLocation();
    geographicalLocation.setLatitude(grpcGeographicalLocation.getLatitude());
    geographicalLocation.setLongitude(grpcGeographicalLocation.getLongitude());
    return geographicalLocation;
  }

  private ComponentType readComponentType(GrpcComponentType grpcComponentType) {
    return ComponentType.valueOf(grpcComponentType.name());
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
