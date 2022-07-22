package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.print.attribute.standard.Media;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class RestTemplateTests {

    @Value("${building.baseUrl}")
    private String baseUrl;

    @Value("${building.listBuildingsUrl}")
    private String listBuildingsUrl;

    @Value("${building.createBuildingUrl}")
    private String createBuildingUrl;

    @Value("${building.getBuildingUrl}")
    private String getBuildingUrl;

    @Value("${building.updateBuildingUrl}")
    private String updateBuildingUrl;

    @Value("${building.removeBuildingUrl}")
    private String removeBuildingUrl;

    @Value("${room.listBuildingRoomsUrl}")
    private String listBuildingRoomsUrl;

    @Value("${room.createBuildingRoomUrl}")
    private String createBuildingRoomUrl;

    @Value("${room.getRoomUrl}")
    private String getRoomUrl;

    @Value("${room.updateRoomUrl}")
    private String updateRoomUrl;

    @Value("${room.removeRoomUrl}")
    private String removeRoomUrl;

    @Value("${component.listBuildingComponentsUrl}")
    private String listBuildingComponentsUrl;

    @Value("${component.createBuildingComponentUrl}")
    private String createBuildingComponentUrl;

    @Value("${component.listRoomComponentsUrl}")
    private String listRoomComponentsUrl;

    @Value("${component.createRoomComponentUrl}")
    private String createRoomComponentUrl;

    @Value("${component.getComponentUrl}")
    private String getComponentUrl;

    @Value("${component.updateComponentUrl}")
    private String updateComponentUrl;

    @Value("${component.removeComponentUrl}")
    private String removeComponentUrl;

    @Value("${notification.listBuildingNotificationsUrl}")
    private String listBuildingNotificationsUrl;

    @Value("${notification.listRoomNotificationsUrl}")
    private String listRoomNotificationsUrl;

    @Value("${notification.listComponentNotificationsUrl}")
    private String listComponentNotificationsUrl;

    @Autowired
    private RestTemplate restTemplate = mock(RestTemplate.class);

    @Autowired
    private BuildingConnector connector = new ClientBuildingConnector(restTemplate, baseUrl);

    private MockRestServiceServer server;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        server = MockRestServiceServer.createServer(restTemplate);
    }
    @Test
    public void testGetBuilding() throws Exception {
        server = MockRestServiceServer.createServer(restTemplate);
        Building building = new Building();
        building.setBuildingName("");
        building.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
        building.setBuildingNumber("");
        building.setLongitude(2.2);
        building.setLatitude(2.2);
        building.setIdentificationNumber("b-1");
        building.setNumFloors(0);

        server.expect(ExpectedCount.once(),
                MockRestRequestMatchers.requestTo(baseUrl + "/buildings/b-1"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(building)));

        Building responseBuilding = connector.getBuilding("b-1");
        server.verify();
        assertEquals(building, responseBuilding);
    }
}
