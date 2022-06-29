package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import io.grpc.stub.StreamObserver;

public class BuildingManagementController extends BuildingManagementGrpc.BuildingManagementImplBase {
    public BuildingManagementController() {}

    @Override
    public void getBuilding(IN request, StreamObserver<Building> responseObserver) {
        super.getBuilding(request, responseObserver);
    }

    @Override
    public void getRoom(IN request, StreamObserver<Room> responseObserver) {
        super.getRoom(request, responseObserver);
    }

    @Override
    public void getComponent(IN request, StreamObserver<Component> responseObserver) {
        super.getComponent(request, responseObserver);
    }

    @Override
    public void getBuildings(Empty request, StreamObserver<Buildings> responseObserver) {
        super.getBuildings(request, responseObserver);
    }

    @Override
    public void getFilteredBuildings(FilterValues request, StreamObserver<Buildings> responseObserver) {
        super.getFilteredBuildings(request, responseObserver);
    }

    @Override
    public void getRooms(IN request, StreamObserver<Rooms> responseObserver) {
        super.getRooms(request, responseObserver);
    }

    @Override
    public void getComponents(IN request, StreamObserver<Components> responseObserver) {
        super.getComponents(request, responseObserver);
    }

    @Override
    public void getNotifications(IN request, StreamObserver<Notifications> responseObserver) {
        super.getNotifications(request, responseObserver);
    }

    @Override
    public void getFavorites(Owner request, StreamObserver<Buildings> responseObserver) {
        super.getFavorites(request, responseObserver);
    }

    @Override
    public void addBuilding(Building request, StreamObserver<Building> responseObserver) {
        super.addBuilding(request, responseObserver);
    }

    @Override
    public void addRoom(Room request, StreamObserver<Room> responseObserver) {
        super.addRoom(request, responseObserver);
    }

    @Override
    public void addComponent(Component request, StreamObserver<Component> responseObserver) {
        super.addComponent(request, responseObserver);
    }

    @Override
    public void addFavorite(Owner request, StreamObserver<Empty> responseObserver) {
        super.addFavorite(request, responseObserver);
    }

    @Override
    public void updateBuilding(Building request, StreamObserver<Building> responseObserver) {
        super.updateBuilding(request, responseObserver);
    }

    @Override
    public void updateRoom(Room request, StreamObserver<Room> responseObserver) {
        super.updateRoom(request, responseObserver);
    }

    @Override
    public void updateComponent(Component request, StreamObserver<Component> responseObserver) {
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
