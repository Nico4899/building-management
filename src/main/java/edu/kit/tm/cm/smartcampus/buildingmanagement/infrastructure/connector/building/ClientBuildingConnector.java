package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.dto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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

  @Value("${building.listRoomsUrl}")
  private String listRoomsUrl;

  @Value("${building.createRoomUrl}")
  private String createRoomUrl;

  @Value("${building.getRoomUrl}")
  private String getRoomUrl;

  @Value("${building.updateRoomUrl}")
  private String updateRoomUrl;

  @Value("${building.removeRoomUrl}")
  private String removeRoomUrl;

  @Value("${building.listComponentsUrl}")
  private String listComponentsUrl;

  @Value("${building.createComponentUrl}")
  private String createComponentUrl;

  @Value("${building.getComponentUrl}")
  private String getComponentUrl;

  @Value("${building.updateComponentUrl}")
  private String updateComponentUrl;

  @Value("${building.removeComponentUrl}")
  private String removeComponentUrl;

  @Value("${building.listNotificationsUrl}")
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
        .exchange(
            baseUrl + createBuildingUrl,
            HttpMethod.POST,
            new HttpEntity<>(clientCreateBuildingRequest),
            Building.class)
        .getBody();
  }

  @Override
  public Building getBuilding(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + getBuildingUrl,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            Building.class,
            identificationNumber)
        .getBody();
  }

  @Override
  public Building updateBuilding(ClientUpdateBuildingRequest clientUpdateBuildingRequest) {
    return restTemplate
        .exchange(
            baseUrl + updateBuildingUrl,
            HttpMethod.PUT,
            new HttpEntity<>(clientUpdateBuildingRequest),
            Building.class)
        .getBody();
  }

  @Override
  public void removeBuilding(String identificationNumber) {
    restTemplate.exchange(
        baseUrl + removeBuildingUrl,
        HttpMethod.DELETE,
        HttpEntity.EMPTY,
        Void.class,
        identificationNumber);
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
        .exchange(
            baseUrl + createRoomUrl,
            HttpMethod.POST,
            new HttpEntity<>(clientCreateRoomRequest),
            Room.class)
        .getBody();
  }

  @Override
  public Room getRoom(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + getRoomUrl,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            Room.class,
            identificationNumber)
        .getBody();
  }

  @Override
  public Room updateRoom(ClientUpdateRoomRequest clientUpdateRoomRequest) {
    return restTemplate
        .exchange(
            baseUrl + updateRoomUrl,
            HttpMethod.PUT,
            new HttpEntity<>(clientUpdateRoomRequest),
            Room.class)
        .getBody();
  }

  @Override
  public void removeRoom(String identificationNumber) {
    restTemplate.exchange(
        baseUrl + removeRoomUrl,
        HttpMethod.DELETE,
        HttpEntity.EMPTY,
        Void.class,
        identificationNumber);
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
        .exchange(
            baseUrl + createComponentUrl,
            HttpMethod.POST,
            new HttpEntity<>(clientCreateComponentRequest),
            Component.class)
        .getBody();
  }

  @Override
  public Component getComponent(String identificationNumber) {
    return restTemplate
        .exchange(
            baseUrl + getComponentUrl,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            Component.class,
            identificationNumber)
        .getBody();
  }

  @Override
  public Component updateComponent(ClientUpdateComponentRequest clientUpdateComponentRequest) {
    return restTemplate
        .exchange(
            baseUrl + updateComponentUrl,
            HttpMethod.PUT,
            new HttpEntity<>(clientUpdateComponentRequest),
            Component.class)
        .getBody();
  }

  @Override
  public void removeComponent(String identificationNumber) {
    restTemplate.exchange(
        baseUrl + removeComponentUrl,
        HttpMethod.DELETE,
        HttpEntity.EMPTY,
        Void.class,
        identificationNumber);
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
