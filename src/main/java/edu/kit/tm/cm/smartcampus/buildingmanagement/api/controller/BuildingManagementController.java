package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.connectors.BuildingManager;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class BuildingManagementController extends BuildingManagementGrpc.BuildingManagementImplBase {

    private final BuildingManager buildingManager;

    @Autowired
    public BuildingManagementController(BuildingManager buildingManager) {
        this.buildingManager = buildingManager;
    }

    @Override
    public void getBuilding(IN request, StreamObserver<Building> responseObserver) {
        String bin = request.getIn();

        /*
        Building building = buildingManager.getBuilding(in);

        BuildingResponse response = BuildingResponse.newBuilder()
            .setBuilding(building).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
         */
    }

    @Override
    public void getRoom(IN request, StreamObserver<Room> responseObserver) {
    }

    @Override
    public void getComponent(IN request, StreamObserver<Component> responseObserver) {
    }

    @Override
    public void getBuildings(Empty request, StreamObserver<Buildings> responseObserver) {
    }

    @Override
    public void getFilteredBuildings(FilterValues request, StreamObserver<Buildings> responseObserver) {
    }

    @Override
    public void getRooms(IN request, StreamObserver<Rooms> responseObserver) {
    }

    @Override
    public void getComponents(IN request, StreamObserver<Components> responseObserver) {
    }

    @Override
    public void getNotifications(IN request, StreamObserver<Notifications> responseObserver) {
    }

    @Override
    public void getFavorites(Owner request, StreamObserver<Buildings> responseObserver) {
    }

    @Override
    public void addBuilding(Building request, StreamObserver<Building> responseObserver) {
    }

    @Override
    public void addRoom(Room request, StreamObserver<Room> responseObserver) {
    }

    @Override
    public void addComponent(Component request, StreamObserver<Component> responseObserver) {
    }

    @Override
    public void addFavorite(Owner request, StreamObserver<Empty> responseObserver) {
    }

    @Override
    public void updateBuilding(Building request, StreamObserver<Building> responseObserver) {
    }

    @Override
    public void updateRoom(Room request, StreamObserver<Room> responseObserver) {
    }

    @Override
    public void updateComponent(Component request, StreamObserver<Component> responseObserver) {
    }

    @Override
    public void delete(IN request, StreamObserver<Empty> responseObserver) {
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
