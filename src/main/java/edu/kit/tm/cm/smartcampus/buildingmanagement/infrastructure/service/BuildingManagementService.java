package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.BuildingConnector;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the building management application microservice manager. It holds and
 * manages all the microservice logic.
 */
@Service
public class BuildingManagementService {

  private static final String BIN_PATTERN = "b-\\d+";
  private static final String RIN_PATTERN = "r-\\d+";
  private static final String CIN_PATTERN = "c-\\d+";
  private static final String FIN_PATTERN = "f-\\d+";

  private final BuildingConnector buildingConnector;
  private final FavoriteRepository favoriteRepository;

  /**
   * Constructs a building management manager.
   *
   * @param buildingConnector the building connector of the microservice (constructor injection)
   * @param favoriteRepository the favorite repository (constructor injection)
   */
  @Autowired
  public BuildingManagementService(
      BuildingConnector buildingConnector, FavoriteRepository favoriteRepository) {
    this.favoriteRepository = favoriteRepository;
    this.buildingConnector = buildingConnector;
  }

  /**
   * Lists all buildings with filter applied.
   *
   * @param filterOptions options for filter
   * @return collection of all filtered buildings
   */
  public Collection<Building> listBuildings(FilterOptions filterOptions) {
    Collection<Building> buildings = buildingConnector.listBuildings();
    if (filterOptions.getCampusLocationFilterOption().isSelected()) {
      Filter<Building, CampusLocation> filter = new CLFilter();
      filter.setFilterValues(filterOptions.getCampusLocationFilterOption().getFilterValues());
      buildings = filter.filter(buildings);
    }
    if (filterOptions.getRoomTypeFilterOption().isSelected()) {
      for (Building building : buildings) {
        buildBuildingRooms(building);
      }
      Filter<Building, RoomType> filter = new BRTFilter();
      filter.setFilterValues(filterOptions.getRoomTypeFilterOption().getFilterValues());
      buildings = filter.filter(buildings);
    }
    if (filterOptions.getComponentTypeFilterOption().isSelected()) {
      for (Building building : buildings) {
        buildBuildingComponents(building);
      }
      Filter<Building, ComponentType> filter = new BCTFilter();
      filter.setFilterValues(filterOptions.getComponentTypeFilterOption().getFilterValues());
      buildings = filter.filter(buildings);
    }
    return buildings;
  }

  /**
   * Lists all building's rooms after filter is being applied.
   *
   * @param filterOptions options for filter
   * @param identificationNumber identification number of the building
   * @return collection of the building's rooms
   */
  public Collection<Room> listRooms(FilterOptions filterOptions, String identificationNumber) {
    if (identificationNumber.matches(BIN_PATTERN)) {
      Collection<Room> rooms = buildingConnector.listBuildingRooms(identificationNumber);
      if (filterOptions.getRoomTypeFilterOption().isSelected()) {
        Filter<Room, RoomType> filter = new RRTFilter();
        filter.setFilterValues(filterOptions.getRoomTypeFilterOption().getFilterValues());
        rooms = filter.filter(rooms);
      }
      if (filterOptions.getComponentTypeFilterOption().isSelected()) {
        for (Room room : rooms) {
          buildRoomComponents(room);
        }
        Filter<Room, ComponentType> filter = new RCTFilter();
        filter.setFilterValues(filterOptions.getComponentTypeFilterOption().getFilterValues());
        rooms = filter.filter(rooms);
      }
      return rooms;
    }
    throw new InvalidArgumentsException();
  }

  /**
   * Lists all room's / building's components.
   *
   * @param identificationNumber of the parent building or room
   * @return collection of room's / building's components
   */
  public Collection<Component> listComponents(String identificationNumber) {
    if (identificationNumber.matches(BIN_PATTERN)) {
      return buildingConnector.listBuildingComponents(identificationNumber);
    } else if (identificationNumber.matches(RIN_PATTERN)) {
      return buildingConnector.listRoomComponents(identificationNumber);
    }
    throw new InvalidArgumentsException();
  }

  /**
   * Lists all room's / building's / component's notifications.
   *
   * @param identificationNumber of the parent building or room
   * @return collection of room's / component's / building's notifications
   */
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

  /**
   * Lists all components marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of components marked as favorite
   */
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

  /**
   * Lists all rooms marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of rooms marked as favorite
   */
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

  /**
   * Lists all buildings marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of buildings marked as favorite
   */
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

  /**
   * Gets a building by identification number.
   *
   * @param identificationNumber identification number of the building
   * @return building belonging to the identification number provided
   */
  public Building getBuilding(String identificationNumber) {
    return this.buildingConnector.getBuilding(identificationNumber);
  }

  /**
   * Gets a room by identification number.
   *
   * @param identificationNumber identification number of the room
   * @return room belonging to the identification number provided
   */
  public Room getRoom(String identificationNumber) {
    return this.buildingConnector.getRoom(identificationNumber);
  }

  /**
   * Gets a component by identification number.
   *
   * @param identificationNumber identification number of the component
   * @return component belonging to the identification number provided
   */
  public Component getComponent(String identificationNumber) {
    return this.buildingConnector.getComponent(identificationNumber);
  }

  /**
   * Create a new building and add it to the building domain.
   *
   * @param building building object without identification number
   * @return created building
   */
  public Building createBuilding(Building building) {
    return this.buildingConnector.createBuilding(building);
  }

  /**
   * Create a new room and add it to the building domain.
   *
   * @param room room object without identification number
   * @return created room
   */
  public Room createRoom(Room room) {
    if (room.getParentIdentificationNumber().matches(BIN_PATTERN)) {
      return this.buildingConnector.createBuildingRoom(room);
    }
    throw new InvalidArgumentsException();
  }

  /**
   * Create a new component and add it to the building domain.
   *
   * @param component component object without identification number
   * @return created component
   */
  public Component createComponent(Component component) {
    if (component.getParentIdentificationNumber().matches(BIN_PATTERN)) {
      return this.buildingConnector.createBuildingComponent(component);
    }
    if (component.getParentIdentificationNumber().matches(RIN_PATTERN)) {
      return this.buildingConnector.createRoomComponent(component);
    }
    throw new InvalidArgumentsException();
  }

  /**
   * Create a new favorite and add it to the building domain.
   *
   * @param favorite favorite object without identification number
   */
  public void createFavorite(Favorite favorite) {
    this.favoriteRepository.save(favorite);
  }

  /**
   * Update a building.
   *
   * @param building building to be updated
   * @return updated building
   */
  public Building updateBuilding(Building building) {
    return this.buildingConnector.updateBuilding(building);
  }

  /**
   * Update a room.
   *
   * @param room room to be updated
   * @return updated room
   */
  public Room updateRoom(Room room) {
    return this.buildingConnector.updateRoom(room);
  }

  /**
   * Update a component.
   *
   * @param component component to be updated.
   * @return updated component.
   */
  public Component updateComponent(Component component) {
    return this.buildingConnector.updateComponent(component);
  }

  /**
   * Removes an object from building domain.
   *
   * @param identificationNumber identification number of the object to be removed
   */
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

  private void buildBuildingRooms(Building building) {
    Collection<Room> rooms =
        this.buildingConnector.listBuildingRooms(building.getIdentificationNumber());
    building.setRooms(rooms);
  }

  private void buildBuildingComponents(Building building) {
    Collection<Component> components =
        this.buildingConnector.listBuildingComponents(building.getIdentificationNumber());
    building.setComponents(components);
  }

  private void buildRoomComponents(Room room) {
    Collection<Component> components =
        this.buildingConnector.listRoomComponents(room.getIdentificationNumber());
    room.setComponents(components);
  }
}
