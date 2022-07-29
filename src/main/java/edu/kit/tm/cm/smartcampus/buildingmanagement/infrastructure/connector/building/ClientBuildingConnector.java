package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.dto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/** Implementation of {@link BuildingConnector}. */
@Service
public class ClientBuildingConnector implements BuildingConnector {

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
   * @param restTemplate rest template
   * @param baseUrl base url
   */
  @Autowired
  public ClientBuildingConnector(
      RestTemplate restTemplate, @Value("${building.baseUrl}") String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  @Override
  public Collection<Building> listBuildings() {
    return restTemplate
        .exchange(
            baseUrl + listBuildingsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Collection<Building>>() {})
        .getBody();
  }

  @Override
  public Building createBuilding(ClientCreateBuildingRequest clientCreateBuildingRequest) {
    return restTemplate
        .postForEntity(baseUrl + createBuildingUrl, clientCreateBuildingRequest, Building.class)
        .getBody();
  }

  @Override
  public Building getBuilding(String identificationNumber) {
    return restTemplate
        .getForEntity(baseUrl + getBuildingUrl, Building.class, identificationNumber)
        .getBody();
  }

  @Override
  public Building updateBuilding(ClientUpdateBuildingRequest clientUpdateBuildingRequest) {
    return restTemplate
        .postForEntity(baseUrl + createBuildingRoomUrl, clientUpdateBuildingRequest, Building.class)
        .getBody();
  }

  @Override
  public void removeBuilding(String identificationNumber) {
    restTemplate.delete(baseUrl + removeBuildingUrl, identificationNumber);
  }

  @Override
  public Collection<Room> listBuildingRooms(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + listBuildingRoomsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Collection<Room>>() {},
            identificationNumber)
        .getBody();
  }

  @Override
  public Room createRoom(ClientCreateRoomRequest clientCreateRoomRequest) {
    return restTemplate
        .postForEntity(baseUrl + createBuildingRoomUrl, clientCreateRoomRequest, Room.class)
        .getBody();
  }

  @Override
  public Room getRoom(String identificationNumber) {
    return restTemplate
        .getForEntity(baseUrl + getRoomUrl, Room.class, identificationNumber)
        .getBody();
  }

  @Override
  public Room updateRoom(ClientUpdateRoomRequest clientUpdateRoomRequest) {
    return restTemplate
        .postForEntity(baseUrl + updateRoomUrl, clientUpdateRoomRequest, Room.class)
        .getBody();
  }

  @Override
  public void removeRoom(String identificationNumber) {
    restTemplate.delete(baseUrl + removeRoomUrl, identificationNumber);
  }

  @Override
  public Collection<Component> listBuildingComponents(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + listBuildingComponentsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Collection<Component>>() {},
            identificationNumber)
        .getBody();
  }

  @Override
  public Collection<Component> listRoomComponents(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + listRoomComponentsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Collection<Component>>() {},
            identificationNumber)
        .getBody();
  }

  @Override
  public Component createComponent(ClientCreateComponentRequest clientCreateComponentRequest) {
    return restTemplate
        .postForEntity(
            baseUrl + createBuildingComponentUrl, clientCreateComponentRequest, Component.class)
        .getBody();
  }

  @Override
  public Component getComponent(String identificationNumber) {
    return restTemplate
        .getForEntity(baseUrl + getComponentUrl, Component.class, identificationNumber)
        .getBody();
  }

  @Override
  public Component updateComponent(ClientUpdateComponentRequest clientUpdateComponentRequest) {
    return restTemplate
        .postForEntity(baseUrl + updateComponentUrl, clientUpdateComponentRequest, Component.class)
        .getBody();
  }

  @Override
  public void removeComponent(String identificationNumber) {
    restTemplate.delete(baseUrl + removeComponentUrl, identificationNumber);
  }

  @Override
  public Collection<Notification> listBuildingNotifications(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + listBuildingNotificationsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Collection<Notification>>() {},
            identificationNumber)
        .getBody();
  }

  @Override
  public Collection<Notification> listRoomNotifications(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + listRoomNotificationsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Collection<Notification>>() {},
            identificationNumber)
        .getBody();
  }

  @Override
  public Collection<Notification> listComponentNotifications(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + listComponentNotificationsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Collection<Notification>>() {},
            identificationNumber)
        .getBody();
  }
}
