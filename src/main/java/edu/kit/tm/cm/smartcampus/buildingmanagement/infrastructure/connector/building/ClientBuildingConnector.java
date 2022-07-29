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

  @Value("${room.listRoomsUrl}")
  private String listRoomsUrl;

  @Value("${room.createRoomUrl}")
  private String createRoomUrl;

  @Value("${room.getRoomUrl}")
  private String getRoomUrl;

  @Value("${room.updateRoomUrl}")
  private String updateRoomUrl;

  @Value("${room.removeRoomUrl}")
  private String removeRoomUrl;

  @Value("${component.listComponentsUrl}")
  private String listComponentsUrl;

  @Value("${component.createComponentUrl}")
  private String createComponentUrl;

  @Value("${component.getComponentUrl}")
  private String getComponentUrl;

  @Value("${component.updateComponentUrl}")
  private String updateComponentUrl;

  @Value("${component.removeComponentUrl}")
  private String removeComponentUrl;

  @Value("${notification.listNotificationsUrl}")
  private String listNotificationsUrl;

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
        .postForEntity(baseUrl + updateBuildingUrl, clientUpdateBuildingRequest, Building.class)
        .getBody();
  }

  @Override
  public void removeBuilding(String identificationNumber) {
    restTemplate.delete(baseUrl + removeBuildingUrl, identificationNumber);
  }

  @Override
  public Collection<Room> listRooms(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + listRoomsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Collection<Room>>() {},
            identificationNumber)
        .getBody();
  }

  @Override
  public Room createRoom(ClientCreateRoomRequest clientCreateRoomRequest) {
    return restTemplate
        .postForEntity(baseUrl + createRoomUrl, clientCreateRoomRequest, Room.class)
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
  public Collection<Component> listComponents(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + listComponentsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Collection<Component>>() {},
            identificationNumber)
        .getBody();
  }

  @Override
  public Component createComponent(ClientCreateComponentRequest clientCreateComponentRequest) {
    return restTemplate
        .postForEntity(baseUrl + createComponentUrl, clientCreateComponentRequest, Component.class)
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
  public Collection<Notification> listNotifications(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + listNotificationsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Collection<Notification>>() {},
            identificationNumber)
        .getBody();
  }
}
