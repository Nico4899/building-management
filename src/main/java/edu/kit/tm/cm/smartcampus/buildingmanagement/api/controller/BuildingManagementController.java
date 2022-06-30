package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.connectors.BuildingManager;
import io.grpc.stub.StreamObserver;

public class BuildingManagementController extends BuildingManagementGrpc.BuildingManagementImplBase {

    private final BuildingManager buildingManager;

    public BuildingManagementController(BuildingManager buildingManager) {
        this.buildingManager = buildingManager;
    }

    @Override
    public void getBuilding(IN request, StreamObserver<BuildingResponse> responseObserver) {
        String bin = request.getIn();

        Building building = buildingManager.getBuilding(bin);
        BuildingResponse response = BuildingResponse.newBuilder()
                //.setBuilding(building) TODO: wird nicht erkannt?
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void getRoom(IN request, StreamObserver<RoomResponse> responseObserver) {
        super.getRoom(request, responseObserver);
    }

    @Override
    public void getComponent(IN request, StreamObserver<ComponentResponse> responseObserver) {
        super.getComponent(request, responseObserver);
    }

    @Override
    public void getBuildings(Empty request, StreamObserver<BuildingsResponse> responseObserver) {
        super.getBuildings(request, responseObserver);
    }

    @Override
    public void getFilteredBuildings(FilterValuesRequest request, StreamObserver<BuildingsResponse> responseObserver) {
        super.getFilteredBuildings(request, responseObserver);
    }

    @Override
    public void getRooms(IN request, StreamObserver<RoomsResponse> responseObserver) {
        super.getRooms(request, responseObserver);
    }

    @Override
    public void getComponents(IN request, StreamObserver<ComponentsResponse> responseObserver) {
        super.getComponents(request, responseObserver);
    }

    @Override
    public void getNotifications(IN request, StreamObserver<NotificationsResponse> responseObserver) {
        super.getNotifications(request, responseObserver);
    }

    @Override
    public void getFavorites(Owner request, StreamObserver<BuildingsResponse> responseObserver) {
        super.getFavorites(request, responseObserver);
    }

    @Override
    public void addBuilding(BuildingRequest request, StreamObserver<BuildingResponse> responseObserver) {
        super.addBuilding(request, responseObserver);
    }

    @Override
    public void addRoom(RoomRequest request, StreamObserver<RoomResponse> responseObserver) {
        super.addRoom(request, responseObserver);
    }

    @Override
    public void addComponent(ComponentRequest request, StreamObserver<ComponentResponse> responseObserver) {
        super.addComponent(request, responseObserver);
    }

    @Override
    public void addFavorite(Owner request, StreamObserver<Empty> responseObserver) {
        super.addFavorite(request, responseObserver);
    }

    @Override
    public void updateBuilding(BuildingRequest request, StreamObserver<BuildingResponse> responseObserver) {
        super.updateBuilding(request, responseObserver);
    }

    @Override
    public void updateRoom(RoomRequest request, StreamObserver<RoomResponse> responseObserver) {
        super.updateRoom(request, responseObserver);
    }

    @Override
    public void updateComponent(ComponentRequest request, StreamObserver<ComponentResponse> responseObserver) {
        super.updateComponent(request, responseObserver);
    }

    @Override
    public void delete(IN request, StreamObserver<Empty> responseObserver) {
        super.delete(request, responseObserver);
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
}
