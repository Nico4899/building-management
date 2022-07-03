package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.BuildingConnector;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;

@org.springframework.stereotype.Component
public class BuildingManagementManager {

  private static final String BIN_PATTERN = "b-\\d+";
  private static final String RIN_PATTERN = "r-\\d+";
  private static final String CIN_PATTERN = "c-\\d+";

  private final BuildingConnector buildingConnector;

  @Autowired
  public BuildingManagementManager(BuildingConnector buildingConnector) {
    this.buildingConnector = buildingConnector;
  }

  // list elements

  public Collection<Building> listBuildings() {
    return this.buildingConnector.listBuildings();
  }

  public Collection<Room> listRooms(String identificationNumber) {
    if (identificationNumber.matches(BIN_PATTERN)) {
      return this.buildingConnector.listBuildingRooms(identificationNumber);
    }
    return Collections.emptyList();
  }

  public Collection<Component> listComponents(String identificationNumber) {
    if (identificationNumber.matches(BIN_PATTERN)) {
      return this.buildingConnector.listBuildingComponents(identificationNumber);
    }
    if (identificationNumber.matches(RIN_PATTERN)) {
      return this.buildingConnector.listRoomComponents(identificationNumber);
    }
    return Collections.emptyList();
  }

  public Collection<Notification> listNotifications(String identificationNumber) {
    if (identificationNumber.matches(BIN_PATTERN)) {
      return this.buildingConnector.listBuildingNotifications(identificationNumber);
    }
    if (identificationNumber.matches(RIN_PATTERN)) {
      return this.buildingConnector.listRoomNotifications(identificationNumber);
    }
    if (identificationNumber.matches(CIN_PATTERN)) {
      return this.buildingConnector.listComponentNotifications(identificationNumber);
    }
    return Collections.emptyList();
  }

  // get single element

  public Building getBuilding(String identificationNumber) {
    return this.buildingConnector.getBuilding(identificationNumber);
  }

  public Room getRoom(String identificationNumber) {
    return this.buildingConnector.getRoom(identificationNumber);
  }

  public Component getComponent(String identificationNumber) {
    return this.buildingConnector.getComponent(identificationNumber);
  }

  // create element

  public Building createBuilding(Building building) {
    return this.buildingConnector.createBuilding(building);
  }

  public Room createRoom(Room room) {
    if (room.getIdentificationNumber().matches(BIN_PATTERN)) {
      return this.buildingConnector.createBuildingRoom(room);
    }
    return null;
  }

  public Component createComponent(Component component) {
    if (component.getIdentificationNumber().matches(BIN_PATTERN)) {
      return this.buildingConnector.createBuildingComponent(component);
    }
    if (component.getIdentificationNumber().matches(RIN_PATTERN)) {
      return this.buildingConnector.createRoomComponent(component);
    }
    return null;
  }

  public Favorite createFavorite(Favorite favorite) {
    return null;
    //TODO close to own database implementation
  }

  // update element

  public Building updateBuilding(Building building) {
    return this.buildingConnector.updateBuilding(building);
  }

  public Room updateRoom(Room room) {
    return this.buildingConnector.updateRoom(room);
  }

  public Component updateComponent(Component component) {
    return this.buildingConnector.updateComponent(component);
  }

  // remove element

  public void remove(String identificationNumber) {
    if (identificationNumber.matches(BIN_PATTERN)) {
      this.buildingConnector.removeBuilding(identificationNumber);
    }
    if (identificationNumber.matches(RIN_PATTERN)) {
      this.buildingConnector.removeRoom(identificationNumber);
    }
    if (identificationNumber.matches(CIN_PATTERN)) {
      this.buildingConnector.removeComponent(identificationNumber);
    }
  }

}

