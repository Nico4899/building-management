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
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * Implementation of {@link BuildingConnector}, providing logical operations to call and consume
 * domain the "building" domain microservice. It uses {@link RestTemplate} to call and parse
 * responses from the "building" api.
 */
@org.springframework.stereotype.Component
public class ClientBuildingConnector implements BuildingConnector {

  private final RestTemplate restTemplate;

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

  @Value("${room.listRoomsUrl}")
  private String listRoomsUrl;

  @Value("${room.createBuildingRoomUrl}")
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
   * Constructs a new client building connector, which connects to the "building" domain api.
   *
   * @param restTemplate rest template used for calling and parsing purposes
   */
  @Autowired
  public ClientBuildingConnector(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Collection<Building> listBuildings() {
    return restTemplate
        .exchange(
            baseUrl + listBuildingsUrl,
            HttpMethod.GET,
            HttpEntity.EMPTY,
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
        .getForEntity(baseUrl + getBuildingUrl, Building.class, identificationNumber)
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
            HttpEntity.EMPTY,
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
            HttpMethod.POST,
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
            HttpEntity.EMPTY,
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
            HttpEntity.EMPTY,
            new ParameterizedTypeReference<Collection<Notification>>() {},
            identificationNumber)
        .getBody();
  }
}
