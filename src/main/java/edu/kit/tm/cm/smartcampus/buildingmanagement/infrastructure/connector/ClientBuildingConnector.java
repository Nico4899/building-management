package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector;

import edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility.dto.BuildingRequest;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility.dto.ComponentRequest;
import edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility.dto.RoomRequest;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;

/** Implementation of {@link BuildingConnector}. */
@Service
public class ClientBuildingConnector implements BuildingConnector {

  private final BuildingRequest buildingRequest;

  private final RoomRequest roomRequest;

  private final ComponentRequest componentRequest;

  private final String baseUrl;
  private final RestTemplate restTemplate;

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

  /**
   * Constructs a new rest template building connector.
   *
   * @param buildingRequest
   * @param roomRequest
   * @param componentRequest
   * @param restTemplate        rest template
   * @param baseUrl             base url
   */

  @Autowired
  public ClientBuildingConnector(
          BuildingRequest buildingRequest, RoomRequest roomRequest, ComponentRequest componentRequest, RestTemplate restTemplate, @Value("${building.baseUrl}") String baseUrl) {
    this.buildingRequest = buildingRequest;
    this.roomRequest = roomRequest;
    this.componentRequest = componentRequest;
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  @Override
  public Collection<Building> listBuildings() {
    ResponseEntity<Collection<Building>> responseEntity;

    responseEntity =
        restTemplate.exchange(
            baseUrl + listBuildingsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {});

    if (responseEntity.getStatusCode() == HttpStatus.OK) {
      return responseEntity.getBody();
    }

    return Collections.emptyList();
  }

  @Override
  public Building createBuilding(Building building) {
    ResponseEntity<BuildingRequest> responseEntity;
    BuildingRequest request = buildingRequest.buildingToBuildingRequest(building);

    responseEntity =
        restTemplate.postForEntity(baseUrl + createBuildingUrl, request, BuildingRequest.class);

    return buildingRequest.buildingRequestToBuilding(responseEntity.getBody());
  }

  @Override
  public Building getBuilding(String identificationNumber) {
    ResponseEntity<Building> responseEntity;

    responseEntity =
        restTemplate.getForEntity(baseUrl + getBuildingUrl, Building.class, identificationNumber);
    return responseEntity.getBody();
  }

  @Override
  public Building updateBuilding(Building building) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    BuildingRequest request = buildingRequest.buildingToBuildingRequest(building);
    HttpEntity<BuildingRequest> entity = new HttpEntity<>(request, headers);
    ResponseEntity<BuildingRequest> responseEntity;

    responseEntity = restTemplate.exchange(
        baseUrl + updateBuildingUrl,
        HttpMethod.PUT,
        entity,
        BuildingRequest.class,
        building.getIdentificationNumber());
    return buildingRequest.buildingRequestToBuilding(responseEntity.getBody());
  }

  @Override
  public void removeBuilding(String identificationNumber) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(identificationNumber, headers);

    restTemplate.exchange(
        baseUrl + removeBuildingUrl, HttpMethod.DELETE, entity, Void.class, identificationNumber);
  }

  @Override
  public Collection<Room> listBuildingRooms(String identificationNumber) {
    ResponseEntity<Collection<Room>> responseEntity;

    responseEntity =
        restTemplate.exchange(
            baseUrl + listBuildingRoomsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {});

    return responseEntity.getBody();
  }

  @Override
  public Room createBuildingRoom(Room room) {
    ResponseEntity<RoomRequest> responseEntity;

    RoomRequest request = roomRequest.roomToRoomRequest(room);

    responseEntity = restTemplate.postForEntity(baseUrl + createBuildingRoomUrl, request, RoomRequest.class);

    Room response = roomRequest.roomRequestToRoom(responseEntity.getBody());

    return response;
  }

  @Override
  public Room getRoom(String identificationNumber) {
    ResponseEntity<Room> responseEntity;

    responseEntity =
        restTemplate.getForEntity(baseUrl + getRoomUrl, Room.class, identificationNumber);

    return responseEntity.getBody();
  }

  @Override
  public Room updateRoom(Room room) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    RoomRequest request = roomRequest.roomToRoomRequest(room);
    HttpEntity<RoomRequest> entity = new HttpEntity<>(request, headers);
    ResponseEntity<RoomRequest> responseEntity;

    responseEntity = restTemplate.exchange(
        baseUrl + updateRoomUrl,
        HttpMethod.PUT,
        entity,
        RoomRequest.class,
        room.getIdentificationNumber());
    return roomRequest.roomRequestToRoom(responseEntity.getBody());
  }

  @Override
  public void removeRoom(String identificationNumber) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(identificationNumber, headers);

    restTemplate.exchange(
        baseUrl + removeRoomUrl, HttpMethod.DELETE, entity, Void.class, identificationNumber);
  }

  @Override
  public Collection<Component> listBuildingComponents(String identificationNumber) {
    ResponseEntity<Collection<Component>> responseEntity;

    responseEntity =
        restTemplate.exchange(
            baseUrl + listBuildingComponentsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {},
            identificationNumber);

    return responseEntity.getBody();
  }

  @Override
  public Collection<Component> listRoomComponents(String identificationNumber) {
    ResponseEntity<Collection<Component>> responseEntity;

    responseEntity =
        restTemplate.exchange(
            baseUrl + listRoomComponentsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {},
            identificationNumber);

    return responseEntity.getBody();
  }

  @Override
  public Component createBuildingComponent(Component component) {
    ResponseEntity<ComponentRequest> responseEntity;

    ComponentRequest request = componentRequest.componentToComponentRequest(component);

    responseEntity =
        restTemplate.postForEntity(
            baseUrl + createBuildingComponentUrl, request, ComponentRequest.class);

    Component response = componentRequest.componentRequestToComponent(responseEntity.getBody());

    return response;
  }

  @Override
  public Component createRoomComponent(Component component) {
    ResponseEntity<ComponentRequest> responseEntity;

    ComponentRequest request = componentRequest.componentToComponentRequest(component);

    responseEntity =
        restTemplate.postForEntity(baseUrl + createRoomComponentUrl, request, ComponentRequest.class);

    return componentRequest.componentRequestToComponent(responseEntity.getBody());
  }

  @Override
  public Component getComponent(String identificationNumber) {
    ResponseEntity<Component> responseEntity;

    responseEntity =
        restTemplate.getForEntity(baseUrl + getComponentUrl, Component.class, identificationNumber);

    return responseEntity.getBody();
  }

  @Override
  public Component updateComponent(Component component) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    ComponentRequest request = componentRequest.componentToComponentRequest(component);
    HttpEntity<ComponentRequest> entity = new HttpEntity<>(request, headers);
    ResponseEntity<ComponentRequest> responseEntity;

    responseEntity = restTemplate.exchange(
        baseUrl + updateComponentUrl,
        HttpMethod.PUT,
        entity,
        ComponentRequest.class,
        component.getIdentificationNumber());
    return componentRequest.componentRequestToComponent(responseEntity.getBody());
  }

  @Override
  public void removeComponent(String identificationNumber) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(identificationNumber, headers);

    restTemplate.exchange(
        baseUrl + removeComponentUrl, HttpMethod.DELETE, entity, Void.class, identificationNumber);
  }

  @Override
  public Collection<Notification> listBuildingNotifications(String identificationNumber) {
    ResponseEntity<Collection<Notification>> responseEntity;

    responseEntity =
        restTemplate.exchange(
            baseUrl + listBuildingNotificationsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {},
            identificationNumber);

    return responseEntity.getBody();
  }

  @Override
  public Collection<Notification> listRoomNotifications(String identificationNumber) {
    ResponseEntity<Collection<Notification>> responseEntity;

    responseEntity =
        restTemplate.exchange(
            baseUrl + listRoomNotificationsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {},
            identificationNumber);

    return responseEntity.getBody();
  }

  @Override
  public Collection<Notification> listComponentNotifications(String identificationNumber) {
    ResponseEntity<Collection<Notification>> responseEntity;

    responseEntity =
        restTemplate.exchange(
            baseUrl + listComponentNotificationsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {},
            identificationNumber);

    return responseEntity.getBody();
  }
}
