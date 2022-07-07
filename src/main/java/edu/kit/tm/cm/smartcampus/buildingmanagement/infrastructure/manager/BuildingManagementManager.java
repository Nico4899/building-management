package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.BuildingConnector;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.commands.FilterCommand;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.commands.SequentialFilterCommand;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.BuildingRoomTypeFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.CampusLocationFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.ComponentTypeFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.RoomRoomTypeFilter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BuildingManagementManager {

  private static final String BIN_PATTERN = "b-\\d+";
  private static final String RIN_PATTERN = "r-\\d+";
  private static final String CIN_PATTERN = "c-\\d+";
  private static final String FIN_PATTERN = "f-\\d+";

  private final BuildingConnector buildingConnector;
  private final FavoriteRepository favoriteRepository;

  @Autowired
  public BuildingManagementManager(
      BuildingConnector buildingConnector, FavoriteRepository favoriteRepository) {
    this.favoriteRepository = favoriteRepository;
    this.buildingConnector = buildingConnector;
  }

  public Collection<Building> listBuildings(FilterOptions filterOptions) {
    Collection<Building> buildings = buildingConnector.listBuildings();
    applyBuildingFilters(filterOptions, buildings);
    return buildings;
  }

  public Collection<Room> listRooms(FilterOptions filterOptions, String identificationNumber) {
    if (identificationNumber.matches(BIN_PATTERN)) {
      Collection<Room> rooms = buildingConnector.listBuildingRooms(identificationNumber);
      applyRoomFilters(filterOptions, rooms);
      return rooms;
    }
    throw new InvalidArgumentsException();
  }

  public Collection<Component> listComponents(String identificationNumber) {
    if (identificationNumber.matches(BIN_PATTERN)) {
      return buildingConnector.listBuildingComponents(identificationNumber);
    } else if (identificationNumber.matches(RIN_PATTERN)) {
      return buildingConnector.listRoomComponents(identificationNumber);
    }
    throw new InvalidArgumentsException();
  }

  public Collection<Notification> listNotifications(String identificationNumber) {
    if (identificationNumber.matches(BIN_PATTERN)) {
      return buildingConnector.listBuildingNotifications(identificationNumber);
    } else if (identificationNumber.matches(RIN_PATTERN)) {
      return buildingConnector.listRoomNotifications(identificationNumber);
    } else if (identificationNumber.matches(CIN_PATTERN)) {
      return buildingConnector.listComponentNotifications(identificationNumber);
    }
    throw new InvalidArgumentsException();
  }

  public Collection<Component> listComponentFavorites(String owner) {
    Collection<Component> components = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findAll()) {
      if (favorite.getOwner().equals(owner)
          && favorite.getReferenceIdentificationNumber().matches(CIN_PATTERN)) {
        components.add(buildingConnector.getComponent(favorite.getReferenceIdentificationNumber()));
      }
    }
    return components;
  }

  public Collection<Room> listRoomFavorites(String owner) {
    Collection<Room> rooms = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findAll()) {
      if (favorite.getOwner().equals(owner)
          && favorite.getReferenceIdentificationNumber().matches(RIN_PATTERN)) {
        rooms.add(buildingConnector.getRoom(favorite.getReferenceIdentificationNumber()));
      }
    }
    return rooms;
  }

  public Collection<Building> listBuildingFavorites(String owner) {
    Collection<Building> buildings = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findAll()) {
      if (favorite.getOwner().equals(owner)
          && favorite.getReferenceIdentificationNumber().matches(BIN_PATTERN)) {
        buildings.add(buildingConnector.getBuilding(favorite.getReferenceIdentificationNumber()));
      }
    }
    return buildings;
  }

  public Building getBuilding(String identificationNumber) {
    return this.buildingConnector.getBuilding(identificationNumber);
  }

  public Room getRoom(String identificationNumber) {
    return this.buildingConnector.getRoom(identificationNumber);
  }

  public Component getComponent(String identificationNumber) {
    return this.buildingConnector.getComponent(identificationNumber);
  }

  public Building createBuilding(Building building) {
    return this.buildingConnector.createBuilding(building);
  }

  public Room createRoom(Room room) {
    if (room.getParentIdentificationNumber().matches(BIN_PATTERN)) {
      return this.buildingConnector.createBuildingRoom(room);
    }
    throw new InvalidArgumentsException();
  }

  public Component createComponent(Component component) {
    if (component.getParentIdentificationNumber().matches(BIN_PATTERN)) {
      return this.buildingConnector.createBuildingComponent(component);
    }
    if (component.getParentIdentificationNumber().matches(RIN_PATTERN)) {
      return this.buildingConnector.createRoomComponent(component);
    }
    throw new InvalidArgumentsException();
  }

  public void createFavorite(Favorite favorite) {
    this.favoriteRepository.save(favorite);
  }

  public Building updateBuilding(Building building) {
    return this.buildingConnector.updateBuilding(building);
  }

  public Room updateRoom(Room room) {
    return this.buildingConnector.updateRoom(room);
  }

  public Component updateComponent(Component component) {
    return this.buildingConnector.updateComponent(component);
  }

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
    if (identificationNumber.matches(FIN_PATTERN)) {
      this.favoriteRepository.deleteById(identificationNumber);
    }
    throw new InvalidArgumentsException();
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
