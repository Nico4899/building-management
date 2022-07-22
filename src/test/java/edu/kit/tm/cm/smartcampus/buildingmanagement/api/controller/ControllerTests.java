package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility.GrpcObjectReader;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility.GrpcObjectWriter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.BuildingManagementService;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTests {

    private GrpcObjectWriter writer = new GrpcObjectWriter();

    private GrpcObjectReader reader = mock(GrpcObjectReader.class);

    private BuildingManagementService service = mock(BuildingManagementService.class);

    private ServerController controller = new ServerController(writer, reader, service);

    GrpcBuilding grpcBuilding = GrpcBuilding.newBuilder()
            .setCampusLocation(GrpcCampusLocation.SOUTH_CAMPUS)
                .setBuildingName("")
                .setBuildingNumber("")
                .setLongitude(2.2)
                .setLatitude(2.2)
                .setIdentificationNumber("b-1")
                .setNumFloors(0)
                .build();

    GrpcRoom grpcRoom = GrpcRoom.newBuilder()
            .setRoomName("")
            .setRoomType(GrpcRoomType.LECTURE_ROOM)
            .setIdentificationNumber("r-1")
            .setLatitude(2.2)
            .setLatitude(2.2)
            .setFloor(0)
            .setRoomNumber("")
            .setParentIdentificationNumber("")
            .build();

    GrpcComponent grpcComponent = GrpcComponent.newBuilder()
            .setComponentDescription("")
            .setComponentType(GrpcComponentType.STAIRS)
            .setLatitude(2.2)
            .setLongitude(2.2)
            .setIdentificationNumber("c-1")
            .setParentIdentificationNumber("")
            .build();

    GrpcFavorite grpcFavorite = GrpcFavorite.newBuilder()
            .setOwner("me")
            .setReferenceIdentificationNumber("")
            .build();

    /*
    TODO: tests failen wenn Objekte in BeforeAll/BeforeEach
     */
    @BeforeAll
    public void setup() {

    }

    @Test
    public void testGetBuilding() throws Exception {
        Building building = new Building();
        building.setBuildingName("");
        building.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
        building.setBuildingNumber("");
        building.setLongitude(2.2);
        building.setLatitude(2.2);
        building.setIdentificationNumber("b-1");
        building.setNumFloors(0);
        when(service.getBuilding("b-1")).thenReturn(building);
        GetBuildingRequest request = GetBuildingRequest.newBuilder()
                .setIdentificationNumber("b-1")
                .build();
        StreamRecorder<GetBuildingResponse> responseObserver = StreamRecorder.create();
        controller.getBuilding(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
        List<GetBuildingResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        GetBuildingResponse response = results.get(0);
        assertEquals(grpcBuilding.getIdentificationNumber(), response.getBuilding().getIdentificationNumber());
    }

    @Test
    public void testGetRoom() throws Exception {
        Room room = new Room();
        room.setRoomName("");
        room.setType(Room.Type.LECTURE_ROOM);
        room.setIdentificationNumber("r-1");
        room.setLatitude(2.2);
        room.setLongitude(2.2);
        room.setFloor(0);
        room.setRoomNumber("");
        room.setParentIdentificationNumber("");
        when(service.getRoom("r-1")).thenReturn(room);
        GetRoomRequest request = GetRoomRequest.newBuilder()
                .setIdentificationNumber("r-1")
                .build();
        StreamRecorder<GetRoomResponse> responseObserver = StreamRecorder.create();
        controller.getRoom(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
        List<GetRoomResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        GetRoomResponse response = results.get(0);
        assertEquals(grpcRoom.getIdentificationNumber(), response.getRoom().getIdentificationNumber());
    }

    @Test
    public void testGetComponent() throws Exception {
        Component component = new Component();
        component.setComponentDescription("");
        component.setType(Component.Type.STAIRS);
        component.setLatitude(2.2);
        component.setLongitude(2.2);
        component.setIdentificationNumber("c-1");
        component.setParentIdentificationNumber("");
        when(service.getComponent("c-1")).thenReturn(component);
        GetComponentRequest request = GetComponentRequest.newBuilder()
                .setIdentificationNumber("c-1")
                .build();
        StreamRecorder<GetComponentResponse> responseObserver = StreamRecorder.create();
        controller.getComponent(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
        List<GetComponentResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        GetComponentResponse response = results.get(0);
        assertEquals(grpcComponent.getIdentificationNumber(), response.getComponent().getIdentificationNumber());
    }

    @Test
    public void testCreateBuilding() throws Exception {
        Building building = new Building();
        building.setBuildingName("");
        building.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
        building.setBuildingNumber("");
        building.setLongitude(2.2);
        building.setLatitude(2.2);
        building.setIdentificationNumber("b-1");
        building.setNumFloors(0);
        when(reader.read(grpcBuilding)).thenReturn(building);
        when(service.createBuilding(building)).thenReturn(building);
        CreateBuildingRequest request = CreateBuildingRequest.newBuilder()
                .setBuilding(grpcBuilding)
                .build();
        StreamRecorder<CreateBuildingResponse> responseObserver = StreamRecorder.create();
        controller.createBuilding(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
        List<CreateBuildingResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        CreateBuildingResponse response = results.get(0);
        assertEquals(request.getBuilding().getIdentificationNumber(), response.getBuilding().getIdentificationNumber());
    }

    @Test
    public void testCreateRoom() throws Exception {
        Room room = new Room();
        room.setRoomName("");
        room.setType(Room.Type.LECTURE_ROOM);
        room.setIdentificationNumber("r-1");
        room.setLatitude(2.2);
        room.setLongitude(2.2);
        room.setFloor(0);
        room.setRoomNumber("");
        room.setParentIdentificationNumber("");
        when(service.createRoom(room)).thenReturn(room);
        when(reader.read(grpcRoom)).thenReturn(room);
        CreateRoomRequest request = CreateRoomRequest.newBuilder()
                .setRoom(grpcRoom)
                .build();
        StreamRecorder<CreateRoomResponse> responseObserver = StreamRecorder.create();
        controller.createRoom(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
        List<CreateRoomResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        CreateRoomResponse response = results.get(0);
        assertEquals(request.getRoom().getIdentificationNumber(), response.getRoom().getIdentificationNumber());
    }

    @Test
    public void testCreateBuildingComponent() throws Exception {
        Component component = new Component();
        component.setComponentDescription("");
        component.setType(Component.Type.STAIRS);
        component.setLatitude(2.2);
        component.setLongitude(2.2);
        component.setIdentificationNumber("c-1");
        component.setParentIdentificationNumber("");
        when(service.createBuildingComponent(component)).thenReturn(component);
        when(reader.read(grpcComponent)).thenReturn(component);
        CreateComponentRequest request = CreateComponentRequest.newBuilder()
                .setComponent(grpcComponent)
                .build();
        StreamRecorder<CreateComponentResponse> responseObserver = StreamRecorder.create();
        controller.createBuildingComponent(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
        List<CreateComponentResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        CreateComponentResponse response = results.get(0);
        assertEquals(request.getComponent().getIdentificationNumber(), response.getComponent().getIdentificationNumber());
    }

    @Test
    public void testCreateRoomComponent() throws Exception {
        Component component = new Component();
        component.setComponentDescription("");
        component.setType(Component.Type.STAIRS);
        component.setLatitude(2.2);
        component.setLongitude(2.2);
        component.setIdentificationNumber("c-1");
        component.setParentIdentificationNumber("");
        when(service.createRoomComponent(component)).thenReturn(component);
        when(reader.read(grpcComponent)).thenReturn(component);
        CreateComponentRequest request = CreateComponentRequest.newBuilder()
                .setComponent(grpcComponent)
                .build();
        StreamRecorder<CreateComponentResponse> responseObserver = StreamRecorder.create();
        controller.createRoomComponent(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
        List<CreateComponentResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        CreateComponentResponse response = results.get(0);
        assertEquals(request.getComponent().getIdentificationNumber(), response.getComponent().getIdentificationNumber());
    }

    @Test
    public void testCreateFavorite() throws Exception {
        Favorite favorite = new Favorite();
        favorite.setOwner("me");
        favorite.setReferenceIdentificationNumber("");
        when(reader.read(grpcFavorite)).thenReturn(favorite);
        CreateFavoriteRequest request = CreateFavoriteRequest.newBuilder()
                .setFavorite(grpcFavorite)
                .build();
        StreamRecorder<CreateFavoriteResponse> responseObserver = StreamRecorder.create();
        controller.createFavorite(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
    }

    @Test
    public void testUpdateBuilding() throws Exception {
        Building building = new Building();
        building.setBuildingName("");
        building.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
        building.setBuildingNumber("");
        building.setLongitude(2.2);
        building.setLatitude(2.2);
        building.setIdentificationNumber("b-1");
        building.setNumFloors(0);
        when(reader.read(grpcBuilding)).thenReturn(building);
        when(service.updateBuilding(building)).thenReturn(building);
        UpdateBuildingRequest request = UpdateBuildingRequest.newBuilder()
                .setBuilding(grpcBuilding)
                .build();
        StreamRecorder<UpdateBuildingResponse> responseObserver = StreamRecorder.create();
        controller.updateBuilding(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
        List<UpdateBuildingResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        UpdateBuildingResponse response = results.get(0);
        assertEquals(request.getBuilding().getIdentificationNumber(), response.getBuilding().getIdentificationNumber());
    }

    @Test
    public void testUpdateRoom() throws Exception {
        Room room = new Room();
        room.setRoomName("");
        room.setType(Room.Type.LECTURE_ROOM);
        room.setIdentificationNumber("r-1");
        room.setLatitude(2.2);
        room.setLongitude(2.2);
        room.setFloor(0);
        room.setRoomNumber("");
        room.setParentIdentificationNumber("");
        when(service.updateRoom(room)).thenReturn(room);
        when(reader.read(grpcRoom)).thenReturn(room);
        UpdateRoomRequest request = UpdateRoomRequest.newBuilder()
                .setRoom(grpcRoom)
                .build();
        StreamRecorder<UpdateRoomResponse> responseObserver = StreamRecorder.create();
        controller.updateRoom(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
        List<UpdateRoomResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        UpdateRoomResponse response = results.get(0);
        assertEquals(request.getRoom().getIdentificationNumber(), response.getRoom().getIdentificationNumber());
    }

    @Test
    public void testUpdateComponent() throws Exception{
        Component component = new Component();
        component.setComponentDescription("");
        component.setType(Component.Type.STAIRS);
        component.setLatitude(2.2);
        component.setLongitude(2.2);
        component.setIdentificationNumber("c-1");
        component.setParentIdentificationNumber("");
        when(service.updateComponent(component)).thenReturn(component);
        when(reader.read(grpcComponent)).thenReturn(component);
        UpdateComponentRequest request = UpdateComponentRequest.newBuilder()
                .setComponent(grpcComponent)
                .build();
        StreamRecorder<UpdateComponentResponse> responseObserver = StreamRecorder.create();
        controller.updateComponent(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
        List<UpdateComponentResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        UpdateComponentResponse response = results.get(0);
        assertEquals(request.getComponent().getIdentificationNumber(), response.getComponent().getIdentificationNumber());
    }

    @Test
    public void testRemoveBuilding() throws Exception {
        RemoveRequest request = RemoveRequest.newBuilder().setIdentificationNumber("b-1").build();
        StreamRecorder<RemoveResponse> responseObserver = StreamRecorder.create();
        controller.removeBuilding(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
    }

    @Test
    public void testRemoveRoom() throws Exception {
        RemoveRequest request = RemoveRequest.newBuilder().setIdentificationNumber("r-1").build();
        StreamRecorder<RemoveResponse> responseObserver = StreamRecorder.create();
        controller.removeRoom(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
    }

    @Test
    public void testRemoveComponent() throws Exception {
        RemoveRequest request = RemoveRequest.newBuilder().setIdentificationNumber("c-1").build();
        StreamRecorder<RemoveResponse> responseObserver = StreamRecorder.create();
        controller.removeComponent(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
    }

    @Test
    public void testRemoveFavorite() throws Exception {
        RemoveRequest request = RemoveRequest.newBuilder().setIdentificationNumber("f-1").build();
        StreamRecorder<RemoveResponse> responseObserver = StreamRecorder.create();
        controller.removeFavorite(request, responseObserver);
        assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
        assertNull(responseObserver.getError());
    }
}
