package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.BuildingConnector;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.commands.FilterCommand;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.commands.SequentialFilterCommand;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.BuildingRoomTypeFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.CampusLocationFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.ComponentTypeFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.RoomRoomTypeFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Component
public class BuildingManagementManager {

  private static final String BIN_PATTERN = "b-\\d+";
  private static final String RIN_PATTERN = "r-\\d+";
  private static final String CIN_PATTERN = "c-\\d+";
  private static final String FIN_PATTERN = "f-\\d+";

  private final BuildingConnector buildingConnector;

  private final FavoriteRepository favoriteRepository;

  @Autowired
  public BuildingManagementManager(BuildingConnector buildingConnector, FavoriteRepository favoriteRepository) {
    this.favoriteRepository = favoriteRepository;
    this.buildingConnector = buildingConnector;
  }

  // list elements

  public Collection<Building> listBuildings(FilterOptions filterOptions) {
    Collection<Building> buildings = buildingConnector.listBuildings();
    applyBuildingFilters(filterOptions, buildings);
    return buildings;
  }

  public Collection<Room> listRooms(FilterOptions filterOptions, String identificationNumber) {
    Collection<Room> rooms = Collections.emptyList();
    if (identificationNumber.matches(BIN_PATTERN)) {
       rooms = buildingConnector.listBuildingRooms(identificationNumber);
      applyRoomFilters(filterOptions, rooms);
    }
    return rooms;
  }

  public Collection<Component> listComponents(String identificationNumber) {
    Collection<Component> components = Collections.emptyList();
    if (identificationNumber.matches(BIN_PATTERN)) {
      components = buildingConnector.listBuildingComponents(identificationNumber);
    }
    if (identificationNumber.matches(RIN_PATTERN)) {
      components = buildingConnector.listRoomComponents(identificationNumber);
    }
    return components;
  }

  public Collection<Notification> listNotifications(String identificationNumber) {
    Collection<Notification> notifications = Collections.emptyList();
    if (identificationNumber.matches(BIN_PATTERN)) {
      notifications = buildingConnector.listBuildingNotifications(identificationNumber);
    }
    if (identificationNumber.matches(RIN_PATTERN)) {
      notifications = buildingConnector.listRoomNotifications(identificationNumber);
    }
    if (identificationNumber.matches(CIN_PATTERN)) {
      notifications = buildingConnector.listComponentNotifications(identificationNumber);
    }
    return notifications;
  }

  public Collection<Component> listComponentFavorites(String owner) {
    Collection<Component> components = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findAll()) {
      if (favorite.getOwner().equals(owner) && favorite.getReferenceIdentificationNumber().matches(CIN_PATTERN)) {
        components.add(buildingConnector.getComponent(favorite.getReferenceIdentificationNumber()));
      }
    }
    return components;
  }

  public Collection<Room> listRoomFavorites(String owner) {
    Collection<Room> rooms = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findAll()) {
      if (favorite.getOwner().equals(owner) && favorite.getReferenceIdentificationNumber().matches(RIN_PATTERN)) {
        rooms.add(buildingConnector.getRoom(favorite.getReferenceIdentificationNumber()));
      }
    }
    return rooms;
  }

  public Collection<Building> listBuildingFavorites(String owner) {
    Collection<Building> buildings = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findAll()) {
        if (favorite.getOwner().equals(owner) && favorite.getReferenceIdentificationNumber().matches(BIN_PATTERN)) {
          buildings.add(buildingConnector.getBuilding(favorite.getReferenceIdentificationNumber()));
        }
    }
    return buildings;
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

  public void createFavorite(Favorite favorite) {
    this.favoriteRepository.save(favorite);
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
    if(identificationNumber.matches(FIN_PATTERN)) {
      this.favoriteRepository.deleteById(identificationNumber);
    }
  }

  private void applyBuildingFilters(FilterOptions filterOptions, Collection<Building> buildings) {
    FilterCommand<Building> filterCommand = new SequentialFilterCommand<>();
    if (filterOptions.getCampusLocationFilterOption().isSelected()) {
      filterCommand.addFilter(
          new CampusLocationFilter(
              buildings, filterOptions.getCampusLocationFilterOption().getFilterValues()));
    }
    if (filterOptions.getComponentTypeFilterOption().isSelected()) {
      filterCommand.addFilter(
          new ComponentTypeFilter<>(
              mapBuildingComponents(buildings),
              filterOptions.getComponentTypeFilterOption().getFilterValues(),
              buildings));
    }
    if (filterOptions.getRoomTypeFilterOption().isSelected()) {
      filterCommand.addFilter(
          new BuildingRoomTypeFilter(
              mapBuildingRooms(buildings),
              filterOptions.getRoomTypeFilterOption().getFilterValues(),
              buildings));
    }
    filterCommand.execute();
  }

  private void applyRoomFilters(FilterOptions filterOptions, Collection<Room> rooms) {
    FilterCommand<Room> filterCommand = new SequentialFilterCommand<>();
    if (filterOptions.getRoomTypeFilterOption().isSelected()) {
      filterCommand.addFilter(
          new RoomRoomTypeFilter(rooms, filterOptions.getRoomTypeFilterOption().getFilterValues()));
    }
    if (filterOptions.getComponentTypeFilterOption().isSelected()) {
      filterCommand.addFilter(
          new ComponentTypeFilter<>(
              mapRoomComponents(rooms),
              filterOptions.getComponentTypeFilterOption().getFilterValues(),
              rooms));
    }
    filterCommand.execute();
  }

  // collection to child mappers

  private Map<Building, Collection<Room>> mapBuildingRooms(Collection<Building> buildings) {
    Map<Building, Collection<Room>> buildingRoomsMap = new HashMap<>();
    for (Building building : buildings) {
      buildingRoomsMap.put(
          building, buildingConnector.listBuildingRooms(building.getIdentificationNumber()));
    }
    return buildingRoomsMap;
  }

  private Map<Building, Collection<Component>> mapBuildingComponents(
      Collection<Building> buildings) {
    Map<Building, Collection<Component>> buildingComponentsMap = new HashMap<>();
    for (Building building : buildings) {
      buildingComponentsMap.put(
          building, buildingConnector.listBuildingComponents(building.getIdentificationNumber()));
    }
    return buildingComponentsMap;
  }

  private Map<Room, Collection<Component>> mapRoomComponents(Collection<Room> rooms) {
    Map<Room, Collection<Component>> buildingComponentsMap = new HashMap<>();
    for (Room room : rooms) {
      buildingComponentsMap.put(
          room, buildingConnector.listRoomComponents(room.getIdentificationNumber()));
    }
    return buildingComponentsMap;
  }
}
