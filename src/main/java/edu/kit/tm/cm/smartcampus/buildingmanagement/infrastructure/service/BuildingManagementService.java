package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.BuildingConnector;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.validator.InputValidator;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * This class represents the building management application microservice service. It holds and
 * manages all the microservice logic.
 */
@org.springframework.stereotype.Component
public class BuildingManagementService {

  public static final String IDENTIFICATION_NUMBER_NAME = "identification_number";
  public static final String OWNER_NAME = "owner";
  private static final String BIN_PATTERN = "b-\\d+";
  private static final String RIN_PATTERN = "r-\\d+";
  private static final String CIN_PATTERN = "c-\\d+";
  public static final String REFERENCE_PATTERN =
      BIN_PATTERN + "|" + RIN_PATTERN + "|" + CIN_PATTERN;
  private static final String FIN_PATTERN = "f-\\d+";
  private static final String BIN_SQL_PATTERN = "b-%";
  private static final String RIN_SQL_PATTERN = "r-%";
  private static final String CIN_SQL_PATTERN = "c-%";
  private final BuildingConnector buildingConnector;
  private final FavoriteRepository favoriteRepository;
  private final InputValidator inputValidator;

  /**
   * Constructs a building management manager.
   *
   * @param buildingConnector the building connector of the microservice (constructor injection)
   * @param favoriteRepository the favorite repository (constructor injection)
   * @param inputValidator the input validator (constructor injection)
   */
  @Autowired
  public BuildingManagementService(
      BuildingConnector buildingConnector,
      FavoriteRepository favoriteRepository,
      InputValidator inputValidator) {
    this.favoriteRepository = favoriteRepository;
    this.buildingConnector = buildingConnector;
    this.inputValidator = inputValidator;
  }

  /**
   * Lists all buildings with filter applied.
   *
   * @param configuration options for filter
   * @return collection of all filtered buildings
   */
  public Collection<Building> listBuildings(Configuration<Building> configuration) {
    Collection<Building> buildings = this.buildingConnector.listBuildings();
    for (Building building : buildings) {
      this.buildBuildingRooms(building);
      this.buildBuildingComponents(building);
    }
    return configuration.run(buildings);
  }

  /**
   * Lists all building's rooms after filter is being applied.
   *
   * @param configuration options for filter
   * @param identificationNumber identification number of the building
   * @return collection of the building's rooms
   */
  public Collection<Room> listRooms(
      Configuration<Room> configuration, String identificationNumber) {
    Collection<Room> rooms = this.buildingConnector.listBuildingRooms(identificationNumber);
    for (Room room : rooms) {
      this.buildRoomComponents(room);
    }
    return configuration.run(rooms);
  }

  /**
   * Lists all building's components.
   *
   * @param identificationNumber of the parent building or room
   * @return collection of components
   */
  public Collection<Component> listBuildingComponents(
      Configuration<Component> configuration, String identificationNumber) {
    return configuration.run(this.buildingConnector.listBuildingComponents(identificationNumber));
  }

  /**
   * Lists all room's components.
   *
   * @param identificationNumber of the parent building or room
   * @return collection of components
   */
  public Collection<Component> listRoomComponents(
      Configuration<Component> configuration, String identificationNumber) {
    return configuration.run(this.buildingConnector.listRoomComponents(identificationNumber));
  }

  /**
   * Lists all building's notifications.
   *
   * @param identificationNumber of the parent building or room
   * @return collection of notifications
   */
  public Collection<Notification> listBuildingNotifications(
      Configuration<Notification> configuration, String identificationNumber) {
    return configuration.run(
        this.buildingConnector.listBuildingNotifications(identificationNumber));
  }

  /**
   * Lists all room's notifications.
   *
   * @param identificationNumber of the parent building or room
   * @return collection of notifications
   */
  public Collection<Notification> listRoomNotifications(
      Configuration<Notification> configuration, String identificationNumber) {
    return configuration.run(this.buildingConnector.listRoomNotifications(identificationNumber));
  }

  /**
   * Lists all component's notifications.
   *
   * @param identificationNumber of the parent building or room
   * @return collection of notifications
   */
  public Collection<Notification> listComponentNotifications(
      Configuration<Notification> configuration, String identificationNumber) {
    return configuration.run(
        this.buildingConnector.listComponentNotifications(identificationNumber));
  }

  /**
   * Lists all components marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of components marked as favorite
   */
  public Collection<Component> listComponentFavorites(
      Configuration<Component> configuration, String owner) {
    Collection<Component> components = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findByOwnerAndRegex(owner, CIN_SQL_PATTERN)) {
      components.add(buildingConnector.getComponent(favorite.getReferenceIdentificationNumber()));
    }
    return configuration.run(components);
  }

  /**
   * Lists all rooms marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of rooms marked as favorite
   */
  public Collection<Room> listRoomFavorites(Configuration<Room> configuration, String owner) {
    Collection<Room> rooms = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findByOwnerAndRegex(owner, RIN_SQL_PATTERN)) {
      rooms.add(buildingConnector.getRoom(favorite.getReferenceIdentificationNumber()));
    }
    for (Room room : rooms) {
      this.buildRoomComponents(room);
    }
    return configuration.run(rooms);
  }

  /**
   * Lists all buildings marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of buildings marked as favorite
   */
  public Collection<Building> listBuildingFavorites(
      Configuration<Building> configuration, String owner) {
    Collection<Building> buildings = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findByOwnerAndRegex(owner, BIN_SQL_PATTERN)) {
      buildings.add(buildingConnector.getBuilding(favorite.getReferenceIdentificationNumber()));
    }
    for (Building building : buildings) {
      this.buildBuildingRooms(building);
      this.buildBuildingComponents(building);
    }
    return configuration.run(buildings);
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
    return this.buildingConnector.createBuildingRoom(room);
  }

  /**
   * Create a building's new component and add it to the building domain.
   *
   * @param component component object without identification number
   * @return created component
   */
  public Component createBuildingComponent(Component component) {
    return this.buildingConnector.createBuildingComponent(component);
  }

  /**
   * Create a room's new component and add it to the building domain.
   *
   * @param component component object without identification number
   * @return created component
   */
  public Component createRoomComponent(Component component) {
    return this.buildingConnector.createRoomComponent(component);
  }

  /**
   * Create a new favorite and add it to the building domain.
   *
   * @param favorite favorite object without identification number
   */
  public void createFavorite(Favorite favorite) {
    inputValidator.validateMatchesRegex(
        Map.of(
            IDENTIFICATION_NUMBER_NAME,
            Pair.of(favorite.getReferenceIdentificationNumber(), REFERENCE_PATTERN)));
    inputValidator.validateNotEmpty(Map.of(OWNER_NAME, favorite.getOwner()));
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
   * Removes a building from building domain.
   *
   * @param identificationNumber identification number of the building to be removed
   */
  public void removeBuilding(String identificationNumber) {
    this.buildingConnector.removeBuilding(identificationNumber);
  }

  /**
   * Removes a room from building domain.
   *
   * @param identificationNumber identification number of the room to be removed
   */
  public void removeRoom(String identificationNumber) {
    this.buildingConnector.removeRoom(identificationNumber);
  }

  /**
   * Removes a component from building domain.
   *
   * @param identificationNumber identification number of the component to be removed
   */
  public void removeComponent(String identificationNumber) {
    this.buildingConnector.removeComponent(identificationNumber);
  }

  /**
   * Removes a favorite from the application database.
   *
   * @param identificationNumber identification number of the favorite to be removed
   */
  public void removeFavorite(String identificationNumber) {
    inputValidator.validateMatchesRegex(
        Map.of(IDENTIFICATION_NUMBER_NAME, Pair.of(identificationNumber, FIN_PATTERN)));
    if (!this.favoriteRepository.existsById(identificationNumber)) {
      throw new ResourceNotFoundException();
    }
    this.favoriteRepository.deleteById(identificationNumber);
  }

  private void buildBuildingRooms(Building building) {
    Collection<Room> rooms =
        this.buildingConnector.listBuildingRooms(building.getIdentificationNumber());
    building.getRooms().addAll(rooms);
  }

  private void buildBuildingComponents(Building building) {
    Collection<Component> components =
        this.buildingConnector.listBuildingComponents(building.getIdentificationNumber());
    building.getComponents().addAll(components);
  }

  private void buildRoomComponents(Room room) {
    Collection<Component> components =
        this.buildingConnector.listRoomComponents(room.getIdentificationNumber());
    room.getComponents().addAll(components);
  }
}
