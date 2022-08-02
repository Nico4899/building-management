package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.BuildingConnector;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.repository.favorite.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.validator.FavoriteValidator;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.settings.Settings;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.utility.DataTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the building management application microservice service. It holds and
 * manages all the microservice logic.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
@org.springframework.stereotype.Component
public class Service {

  private static final String BIN_SQL_PATTERN = "b-%";
  private static final String RIN_SQL_PATTERN = "r-%";
  private static final String CIN_SQL_PATTERN = "c-%";

  private final BuildingConnector buildingConnector;
  private final FavoriteRepository favoriteRepository;
  private final FavoriteValidator favoriteValidator;

  /**
   * Constructs a building management manager.
   *
   * @param buildingConnector the building connector of the microservice (constructor injection)
   * @param favoriteRepository the favorite repository (constructor injection)
   * @param favoriteValidator the input validator (constructor injection)
   */
  @Autowired
  public Service(
      BuildingConnector buildingConnector,
      FavoriteRepository favoriteRepository,
      FavoriteValidator favoriteValidator) {
    this.favoriteRepository = favoriteRepository;
    this.buildingConnector = buildingConnector;
    this.favoriteValidator = favoriteValidator;
  }

  /**
   * Lists all buildings with filter applied.
   *
   * @param settings options for filter
   * @return collection of all filtered buildings
   */
  public Collection<Building> listBuildings(Settings<Building> settings) {
    Collection<Building> buildings = this.buildingConnector.listBuildings();
    for (Building building : buildings) {
      this.buildBuildingRooms(building);
      this.buildBuildingComponents(building);
    }
    return settings.apply(buildings);
  }

  /**
   * Lists all building's rooms after filter is being applied.
   *
   * @param settings options for filter
   * @param identificationNumber identification number of the building
   * @return collection of the building's rooms
   */
  public Collection<Room> listRooms(Settings<Room> settings, String identificationNumber) {
    Collection<Room> rooms = this.buildingConnector.listRooms(identificationNumber);
    for (Room room : rooms) {
      this.buildRoomComponents(room);
    }
    return settings.apply(rooms);
  }

  /**
   * Lists all room's components.
   *
   * @param identificationNumber of the parent building or room
   * @return collection of components
   */
  public Collection<Component> listComponents(String identificationNumber) {
    return this.buildingConnector.listComponents(identificationNumber);
  }

  /**
   * Lists all building's notifications.
   *
   * @param identificationNumber of the parent building or room
   * @return collection of notifications
   */
  public Collection<Notification> listNotifications(
      Settings<Notification> settings, String identificationNumber) {
    return settings.apply(this.buildingConnector.listNotifications(identificationNumber));
  }

  /**
   * Lists all components marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of components marked as favorite
   */
  public Collection<Component> listFavoriteComponents(String owner) {
    Collection<Component> components = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findByOwnerAndRegex(owner, CIN_SQL_PATTERN)) {
      components.add(buildingConnector.getComponent(favorite.getReferenceIdentificationNumber()));
    }
    return components;
  }

  /**
   * Lists all rooms marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of rooms marked as favorite
   */
  public Collection<Room> listFavoriteRooms(Settings<Room> settings, String owner) {
    Collection<Room> rooms = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findByOwnerAndRegex(owner, RIN_SQL_PATTERN)) {
      rooms.add(buildingConnector.getRoom(favorite.getReferenceIdentificationNumber()));
    }
    for (Room room : rooms) {
      this.buildRoomComponents(room);
    }
    return settings.apply(rooms);
  }

  /**
   * Lists all buildings marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of buildings marked as favorite
   */
  public Collection<Building> listFavoriteBuildings(Settings<Building> settings, String owner) {
    Collection<Building> buildings = new ArrayList<>();
    for (Favorite favorite : favoriteRepository.findByOwnerAndRegex(owner, BIN_SQL_PATTERN)) {
      buildings.add(buildingConnector.getBuilding(favorite.getReferenceIdentificationNumber()));
    }
    for (Building building : buildings) {
      this.buildBuildingRooms(building);
      this.buildBuildingComponents(building);
    }
    return settings.apply(buildings);
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
    return this.buildingConnector.createBuilding(
        DataTransferUtils.ClientRequestWriter.writeClientCreateBuildingRequest(building));
  }

  /**
   * Create a new room and add it to the building domain.
   *
   * @param room room object without identification number
   * @return created room
   */
  public Room createRoom(Room room) {
    return this.buildingConnector.createRoom(
        DataTransferUtils.ClientRequestWriter.writeClientCreateRoomRequest(room));
  }

  /**
   * Create a new component and add it to the building domain.
   *
   * @param component component object without identification number
   * @return created component
   */
  public Component createComponent(Component component) {
    return this.buildingConnector.createComponent(
        DataTransferUtils.ClientRequestWriter.writeClientCreateComponentRequest(component));
  }

  /**
   * Create a new favorite and add it to the building domain.
   *
   * @param favorite favorite object without identification number
   */
  public void createFavorite(Favorite favorite) {
    this.favoriteValidator.validateCreate(favorite);
    this.favoriteRepository.save(favorite);
  }

  /**
   * Update a building.
   *
   * @param building building to be updated
   * @return updated building
   */
  public Building updateBuilding(Building building) {
    return this.buildingConnector.updateBuilding(
        DataTransferUtils.ClientRequestWriter.writeClientUpdateBuildingRequest(building));
  }

  /**
   * Update a room.
   *
   * @param room room to be updated
   * @return updated room
   */
  public Room updateRoom(Room room) {
    return this.buildingConnector.updateRoom(
        DataTransferUtils.ClientRequestWriter.writeClientUpdateRoomRequest(room));
  }

  /**
   * Update a component.
   *
   * @param component component to be updated.
   * @return updated component.
   */
  public Component updateComponent(Component component) {
    return this.buildingConnector.updateComponent(
        DataTransferUtils.ClientRequestWriter.writeClientUpdateComponentRequest(component));
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
    this.favoriteValidator.validate(identificationNumber);
    this.favoriteRepository.deleteById(identificationNumber);
  }

  private void buildBuildingRooms(Building building) {
    Collection<Room> rooms = this.buildingConnector.listRooms(building.getIdentificationNumber());
    building.getRooms().addAll(rooms);
  }

  private void buildBuildingComponents(Building building) {
    Collection<Component> components =
        this.buildingConnector.listComponents(building.getIdentificationNumber());
    building.getComponents().addAll(components);
  }

  private void buildRoomComponents(Room room) {
    Collection<Component> components =
        this.buildingConnector.listComponents(room.getIdentificationNumber());
    room.getComponents().addAll(components);
  }
}
