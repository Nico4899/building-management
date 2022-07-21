package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.parser.GrpcObjectReader;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.parser.GrpcObjectWriter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.BuildingManagementService;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTests {
    private ServerController controller;

    private GrpcObjectWriter writer = new GrpcObjectWriter();

    private GrpcObjectReader reader = new GrpcObjectReader();
    @BeforeEach
    public void setup() {

    }
    @Test
    public void testGetBuilding() throws Exception {
        BuildingManagementService service = mock(BuildingManagementService.class);
        when(service.getBuilding("b-1")).thenReturn(writeGetBuilding());
        controller = new ServerController(writer, reader, service);
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
        assertEquals(writeGetBuildingGrpc().getIdentificationNumber(), response.getBuilding().getIdentificationNumber());
    }

    public GrpcBuilding writeGetBuildingGrpc() {
        return GrpcBuilding.newBuilder()
                .setCampusLocation(GrpcCampusLocation.SOUTH_CAMPUS)
                .setBuildingName("")
                .setBuildingNumber("")
                .setLongitude(2.2)
                .setLatitude(2.2)
                .setIdentificationNumber("b-1")
                .setNumFloors(0)
                .build();
    }

    public Building writeGetBuilding() {
        Building building = new Building();
        building.setBuildingName("");
        building.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
        building.setBuildingNumber("");
        building.setLongitude(2.2);
        building.setLatitude(2.2);
        building.setIdentificationNumber("b-1");
        building.setNumFloors(0);
        return building;
    }
}
