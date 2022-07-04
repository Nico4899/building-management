package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.BuildingConnector;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.util.BuildingFilterOptions;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.util.RoomFilterOptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

  public Collection<Building> listBuildings(BuildingFilterOptions buildingFilterOptions) {
    return filterBuildingByOptions(buildingFilterOptions, this.buildingConnector.listBuildings());
  }

  public Collection<Room> listRooms(
      RoomFilterOptions roomFilterOptions, String identificationNumber) {
    if (identificationNumber.matches(BIN_PATTERN)) {
      return filterRoomByOptions(
          roomFilterOptions, this.buildingConnector.listBuildingRooms(identificationNumber));
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
    // TODO close to own database implementation
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

  private Collection<Room> filterRoomByOptions(
      RoomFilterOptions roomFilterOptions, Collection<Room> rooms) {
    Collection<Room> collectionToFilter = rooms;
    if (roomFilterOptions.getRoomTypeFilterOption().isSelected()) {
      Filter<Room> filter =
          new RoomRoomTypeFilter(
              rooms, roomFilterOptions.getRoomTypeFilterOption().getFilterValues());
      collectionToFilter = filter.filter();
    }
    if (roomFilterOptions.getComponentTypeFilterOption().isSelected()) {
      Map<Room, Collection<Component>> roomComponentsMap = new HashMap<>();
      for (Room room : rooms) {
        roomComponentsMap.put(
            room, buildingConnector.listRoomComponents(room.getIdentificationNumber()));
      }
      Filter<Room> filter =
          new RoomComponentTypeFilter(
              roomComponentsMap,
              roomFilterOptions.getComponentTypeFilterOption().getFilterValues());
      collectionToFilter = filter.filter();
    }
    return collectionToFilter;
  }

  private Collection<Building> filterBuildingByOptions(
      BuildingFilterOptions buildingFilterOptions, Collection<Building> buildings) {
    Collection<Building> collectionToFilter = buildings;
    if (buildingFilterOptions.getCampusLocationFilterOption().isSelected()) {
      Filter<Building> filter =
          new BuildingCampusLocationFilter(
              collectionToFilter,
              buildingFilterOptions.getCampusLocationFilterOption().getFilterValues());
      collectionToFilter = filter.filter();
    }
    if (buildingFilterOptions.getRoomTypeFilterOption().isSelected()) {
      Map<Building, Collection<Room>> buildingRoomsMap = new HashMap<>();
      for (Building building : collectionToFilter) {
        buildingRoomsMap.put(
            building, buildingConnector.listBuildingRooms(building.getIdentificationNumber()));
      }
      Filter<Building> filter =
          new BuildingRoomTypeFilter(
              buildingRoomsMap, buildingFilterOptions.getRoomTypeFilterOption().getFilterValues());
      collectionToFilter = filter.filter();
    }
    if (buildingFilterOptions.getComponentTypeFilterOption().isSelected()) {
      Map<Building, Collection<Component>> buildingComponentsMap = new HashMap<>();
      for (Building building : collectionToFilter) {
        buildingComponentsMap.put(
            building, buildingConnector.listBuildingComponents(building.getIdentificationNumber()));
      }
      Filter<Building> filter =
          new BuildingComponentTypeFilter(
              buildingComponentsMap,
              buildingFilterOptions.getComponentTypeFilterOption().getFilterValues());
      collectionToFilter = filter.filter();
    }
    return collectionToFilter;
  }
}
