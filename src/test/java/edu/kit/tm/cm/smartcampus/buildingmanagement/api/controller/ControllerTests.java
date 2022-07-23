package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InternalServerErrorException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.UnauthorizedAccessException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.BuildingManagementService;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTests {

  private static final BuildingManagementService service = mock(BuildingManagementService.class);
  private static final ServerController controller = new ServerController(service);
  private static GrpcBuilding grpcTestBuilding;
  private static GrpcRoom grpcTestRoom;
  private static GrpcComponent grpcTestComponent;
  private static GrpcFavorite grpcTestFavorite;
  private static Building testBuilding;
  private static Room testRoom;
  private static Component testComponent;
  private static Favorite testFavorite;

  @BeforeAll
  public static void beforeAll() {
    initializeResources();
    System.out.println("started");
  }

  private static void initializeResources() {
    grpcTestRoom =
        GrpcRoom.newBuilder()
            .setRoomName("")
            .setRoomType(GrpcRoomType.LECTURE_ROOM)
            .setIdentificationNumber("r-1")
            .setLatitude(2.2)
            .setLatitude(2.2)
            .setFloor(0)
            .setRoomNumber("")
            .setParentIdentificationNumber("")
            .build();

    grpcTestBuilding =
        GrpcBuilding.newBuilder()
            .setCampusLocation(GrpcCampusLocation.SOUTH_CAMPUS)
            .setBuildingName("")
            .setBuildingNumber("")
            .setLongitude(2.2)
            .setLatitude(2.2)
            .setIdentificationNumber("b-1")
            .setNumFloors(0)
            .build();

    grpcTestComponent =
        GrpcComponent.newBuilder()
            .setComponentDescription("")
            .setComponentType(GrpcComponentType.STAIRS)
            .setLatitude(2.2)
            .setLongitude(2.2)
            .setIdentificationNumber("c-1")
            .setParentIdentificationNumber("")
            .build();

    grpcTestFavorite =
        GrpcFavorite.newBuilder().setOwner("me").setReferenceIdentificationNumber("").build();

    testBuilding = new Building();
    testBuilding.setBuildingName("");
    testBuilding.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
    testBuilding.setBuildingNumber("");
    testBuilding.setLongitude(2.2);
    testBuilding.setLatitude(2.2);
    testBuilding.setIdentificationNumber("b-1");
    testBuilding.setNumFloors(0);

    testRoom = new Room();
    testRoom.setRoomName("");
    testRoom.setType(Room.Type.LECTURE_ROOM);
    testRoom.setIdentificationNumber("r-1");
    testRoom.setLatitude(2.2);
    testRoom.setLongitude(2.2);
    testRoom.setFloor(0);
    testRoom.setRoomNumber("");
    testRoom.setParentIdentificationNumber("b-1");

    testComponent = new Component();
    testComponent.setComponentDescription("");
    testComponent.setType(Component.Type.STAIRS);
    testComponent.setLatitude(2.2);
    testComponent.setLongitude(2.2);
    testComponent.setIdentificationNumber("c-1");
    testComponent.setParentIdentificationNumber("b-1");

    testFavorite = new Favorite();
    testFavorite.setIdentificationNumber("f-1");
    testFavorite.setOwner("me");
    testFavorite.setReferenceIdentificationNumber("");

    when(service.updateComponent(any())).thenReturn(testComponent);
    when(service.getBuilding(any())).thenReturn(testBuilding);
    when(service.getRoom(any())).thenReturn(testRoom);
    when(service.getComponent(any())).thenReturn(testComponent);
    when(service.updateRoom(any())).thenReturn(testRoom);
    when(service.updateBuilding(any())).thenReturn(testBuilding);
    when(service.createRoomComponent(any())).thenReturn(testComponent);
    when(service.createBuildingComponent(any())).thenReturn(testComponent);
    when(service.createRoom(any())).thenReturn(testRoom);
    when(service.createBuilding(any())).thenReturn(testBuilding);
  }

  @Test
  void testGetBuilding() throws Exception {
    GetBuildingRequest request =
        GetBuildingRequest.newBuilder().setIdentificationNumber("b-1").build();
    StreamRecorder<GetBuildingResponse> responseObserver = StreamRecorder.create();
    controller.getBuilding(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
    List<GetBuildingResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    GetBuildingResponse response = results.get(0);
    assertEquals(
        grpcTestBuilding.getIdentificationNumber(),
        response.getBuilding().getIdentificationNumber());
  }

  @Test
  void testGetRoom() throws Exception {
    testRoom.setParentIdentificationNumber("");
    GetRoomRequest request = GetRoomRequest.newBuilder().setIdentificationNumber("r-1").build();
    StreamRecorder<GetRoomResponse> responseObserver = StreamRecorder.create();
    controller.getRoom(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
    List<GetRoomResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    GetRoomResponse response = results.get(0);
    assertEquals(
        grpcTestRoom.getIdentificationNumber(), response.getRoom().getIdentificationNumber());
  }

  @Test
  void testGetComponent() throws Exception {
    GetComponentRequest request =
        GetComponentRequest.newBuilder().setIdentificationNumber("c-1").build();
    StreamRecorder<GetComponentResponse> responseObserver = StreamRecorder.create();
    controller.getComponent(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
    List<GetComponentResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    GetComponentResponse response = results.get(0);
    assertEquals(
        grpcTestComponent.getIdentificationNumber(),
        response.getComponent().getIdentificationNumber());
  }

  @Test
  void testCreateBuilding() throws Exception {
    CreateBuildingRequest request =
        CreateBuildingRequest.newBuilder().setBuilding(grpcTestBuilding).build();
    StreamRecorder<CreateBuildingResponse> responseObserver = StreamRecorder.create();
    controller.createBuilding(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
    List<CreateBuildingResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    CreateBuildingResponse response = results.get(0);
    assertEquals(
        request.getBuilding().getIdentificationNumber(),
        response.getBuilding().getIdentificationNumber());
  }

  @Test
  void testCreateRoom() throws Exception {
    CreateRoomRequest request = CreateRoomRequest.newBuilder().setRoom(grpcTestRoom).build();
    StreamRecorder<CreateRoomResponse> responseObserver = StreamRecorder.create();
    controller.createRoom(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
    List<CreateRoomResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    CreateRoomResponse response = results.get(0);
    assertEquals(
        request.getRoom().getIdentificationNumber(), response.getRoom().getIdentificationNumber());
  }

  @Test
  void testCreateBuildingComponent() throws Exception {
    CreateComponentRequest request =
        CreateComponentRequest.newBuilder().setComponent(grpcTestComponent).build();
    StreamRecorder<CreateComponentResponse> responseObserver = StreamRecorder.create();
    controller.createBuildingComponent(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
    List<CreateComponentResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    CreateComponentResponse response = results.get(0);
    assertEquals(
        request.getComponent().getIdentificationNumber(),
        response.getComponent().getIdentificationNumber());
  }

  @Test
  void testCreateRoomComponent() throws Exception {
    CreateComponentRequest request =
        CreateComponentRequest.newBuilder().setComponent(grpcTestComponent).build();
    StreamRecorder<CreateComponentResponse> responseObserver = StreamRecorder.create();
    controller.createRoomComponent(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
    List<CreateComponentResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    CreateComponentResponse response = results.get(0);
    assertEquals(
        request.getComponent().getIdentificationNumber(),
        response.getComponent().getIdentificationNumber());
  }

  @Test
  void testCreateFavorite() throws Exception {
    CreateFavoriteRequest request =
        CreateFavoriteRequest.newBuilder().setFavorite(grpcTestFavorite).build();
    StreamRecorder<CreateFavoriteResponse> responseObserver = StreamRecorder.create();
    controller.createFavorite(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
  }

  @Test
  void testUpdateBuilding() throws Exception {
    UpdateBuildingRequest request =
        UpdateBuildingRequest.newBuilder().setBuilding(grpcTestBuilding).build();
    StreamRecorder<UpdateBuildingResponse> responseObserver = StreamRecorder.create();
    controller.updateBuilding(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
    List<UpdateBuildingResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    UpdateBuildingResponse response = results.get(0);
    assertEquals(
        request.getBuilding().getIdentificationNumber(),
        response.getBuilding().getIdentificationNumber());
  }

  @Test
  void testUpdateRoom() throws Exception {
    UpdateRoomRequest request = UpdateRoomRequest.newBuilder().setRoom(grpcTestRoom).build();
    StreamRecorder<UpdateRoomResponse> responseObserver = StreamRecorder.create();
    controller.updateRoom(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
    List<UpdateRoomResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    UpdateRoomResponse response = results.get(0);
    assertEquals(
        request.getRoom().getIdentificationNumber(), response.getRoom().getIdentificationNumber());
  }

  @Test
  void testUpdateComponent() throws Exception {
    UpdateComponentRequest request =
        UpdateComponentRequest.newBuilder().setComponent(grpcTestComponent).build();
    StreamRecorder<UpdateComponentResponse> responseObserver = StreamRecorder.create();
    controller.updateComponent(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
    List<UpdateComponentResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    UpdateComponentResponse response = results.get(0);
    assertEquals(
        request.getComponent().getIdentificationNumber(),
        response.getComponent().getIdentificationNumber());
  }

  @Test
  void testRemoveBuilding() throws Exception {
    RemoveRequest request = RemoveRequest.newBuilder().setIdentificationNumber("b-1").build();
    StreamRecorder<RemoveResponse> responseObserver = StreamRecorder.create();
    controller.removeBuilding(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
  }

  @Test
  void testRemoveRoom() throws Exception {
    RemoveRequest request = RemoveRequest.newBuilder().setIdentificationNumber("r-1").build();
    StreamRecorder<RemoveResponse> responseObserver = StreamRecorder.create();
    controller.removeRoom(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
  }

  @Test
  void testRemoveComponent() throws Exception {
    RemoveRequest request = RemoveRequest.newBuilder().setIdentificationNumber("c-1").build();
    StreamRecorder<RemoveResponse> responseObserver = StreamRecorder.create();
    controller.removeComponent(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
  }

  @Test
  void testRemoveFavorite() throws Exception {
    RemoveRequest request = RemoveRequest.newBuilder().setIdentificationNumber("f-1").build();
    StreamRecorder<RemoveResponse> responseObserver = StreamRecorder.create();
    controller.removeFavorite(request, responseObserver);
    assertTrue(responseObserver.awaitCompletion(5, TimeUnit.SECONDS));
    assertNull(responseObserver.getError());
  }

  @Disabled
  // @ParameterizedTest
  // @ArgumentsSource(ExceptionArgumentProvider.class)
  public void whenExceptionThrown_thenInterceptorReturnsErrorResponse() {
    when(service.getBuilding("b-1")).thenThrow(InvalidArgumentsException.class);
    GetBuildingRequest request =
        GetBuildingRequest.newBuilder().setIdentificationNumber("b-1").build();
    StreamRecorder<GetBuildingResponse> responseObserver = StreamRecorder.create();
    controller.getBuilding(request, responseObserver);
    Assertions.assertNotNull(responseObserver.getError());
  }

  private static class ExceptionArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
      return Stream.of(
          Arguments.of(InternalServerErrorException.class),
          Arguments.of(InvalidArgumentsException.class),
          Arguments.of(ResourceNotFoundException.class),
          Arguments.of(UnauthorizedAccessException.class));
    }
  }
}
