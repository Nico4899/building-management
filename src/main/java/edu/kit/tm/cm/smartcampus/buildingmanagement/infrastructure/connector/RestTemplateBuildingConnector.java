package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@org.springframework.stereotype.Component
public class RestTemplateBuildingConnector implements BuildingConnector{

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

  private final String baseUrl;
  private final RestTemplate restTemplate;

  @Autowired
  public RestTemplateBuildingConnector(RestTemplate restTemplate, @Value("${building.baseUrl}") String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }


  @Override
  public Collection<Building> listBuildings() {
    return null;
  }

  @Override
  public Building createBuilding(Building building){
    return null;
  }

  @Override
  public Building getBuilding(String identificationNumber) {
    return null;
  }

  @Override
  public Building updateBuilding(Building building) {
    return null;
  }

  @Override
  public void removeBuilding(String identificationNumber) {
  }

  @Override
  public Collection<Room> listBuildingRooms(String identificationNumber) {
    return null;
  }

  @Override
  public Room createBuildingRoom(Room room){
    return null;
  }

  @Override
  public Room getRoom(String identificationNumber) {
    return null;
  }

  @Override
  public Room updateRoom(Room room) {
    return null;
  }

  @Override
  public void removeRoom(String identificationNumber) {
  }

  @Override
  public Collection<Component> listBuildingComponents(String identificationNumber) {
    return null;
  }

  @Override
  public Collection<Component> listRoomComponents(String identificationNumber) {
    return null;
  }

  @Override
  public Component createBuildingComponent(Component component){
    return null;
  }

  @Override
  public Component createRoomComponent(Component component){
    return null;
  }

  @Override
  public Component getComponent(String identificationNumber) {
    return null;
  }

  @Override
  public Component updateComponent(Component component) {
    return null;
  }

  @Override
  public void removeComponent(String identificationNumber) {
  }

  @Override
  public Collection<Notification> listBuildingNotifications(String identificationNumber) {
    return null;
  }

  @Override
  public Collection<Notification> listRoomNotifications(String identificationNumber) {
    return null;
  }

  @Override
  public Collection<Notification> listComponentNotifications(String identificationNumber) {
    return null;
  }
}
