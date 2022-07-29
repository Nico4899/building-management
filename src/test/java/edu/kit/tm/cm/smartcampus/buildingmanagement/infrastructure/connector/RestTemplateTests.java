package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class RestTemplateTests {

  /*
  private final ObjectMapper mapper = new ObjectMapper();

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

  @Autowired private RestTemplate restTemplate = mock(RestTemplate.class);

  @Autowired
  private BuildingConnector connector = new ClientBuildingConnector(restTemplate, baseUrl);



  private MockRestServiceServer server;

  @BeforeEach
  public void setup() {
    server = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void testGetBuilding() throws Exception {
    server = MockRestServiceServer.createServer(restTemplate);
    Building building = new Building();
    building.setName("");
    building.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
    building.setNumber("");
    GeographicalLocation geographicalLocation = new GeographicalLocation();
    geographicalLocation.setLongitude(1.1);
    geographicalLocation.setLatitude(1.1);
    building.setGeographicalLocation(geographicalLocation);
    building.setIdentificationNumber("b-1");
    Floors floors = new Floors();
    floors.setHighestFloor(1);
    floors.setLowestFloor(0);

    server
        .expect(ExpectedCount.once(), MockRestRequestMatchers.requestTo(baseUrl + "/buildings/b-1"))
        .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
        .andRespond(
            MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(building)));

    //Building responseBuilding = connector.getBuilding("b-1");
    server.verify();
    //assertEquals(building, responseBuilding);
  }
  */
}
