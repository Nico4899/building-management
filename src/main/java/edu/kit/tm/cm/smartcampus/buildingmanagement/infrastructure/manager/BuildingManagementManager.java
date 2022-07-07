package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.BuildingConnector;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents the building management application microservice manager. It holds and
 * manages all the microservice logic.
 */
@Service
@RestController
@RequestMapping
public class BuildingManagementManager {

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
  public BuildingManagementManager(
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
    filterOptions.setBuildingComponentMap(mapBuildingComponents(buildings));
    filterOptions.setBuildingRoomMap(mapBuildingRooms(buildings));
    filterOptions.filterBuildings(buildings);
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
      filterOptions.setRoomComponentMap(mapRoomComponents(rooms));
      filterOptions.filterRooms(rooms);
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
  @GetMapping("/favorites/{owner}")
  public Collection<Component> listComponentFavorites(@PathVariable String owner) {

    Collection<Component> components = new ArrayList<>();
    Optional<Favorite> favorites = favoriteRepository.findById(owner);

    components = favorites.stream()
            .map(f -> buildingConnector.getComponent(f.getReferenceIdentificationNumber()))
            .collect(Collectors.toList());
    return components;
  }

  /**
   * Lists all rooms marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of rooms marked as favorite
   */
  @GetMapping("/favorites/{owner}")
  public Collection<Room> listRoomFavorites(@PathVariable String owner) {
    Collection<Room> rooms = new ArrayList<>();
    Optional<Favorite> favorites = favoriteRepository.findById(owner);

    rooms = favorites.stream()
            .map(f -> buildingConnector.getRoom(f.getReferenceIdentificationNumber()))
            .collect(Collectors.toList());
    return rooms;
  }

  /**
   * Lists all buildings marked as favorite by owner.
   *
   * @param owner owner of the favorites list
   * @return collection of buildings marked as favorite
   */
  @GetMapping("/favorites/{owner}")
  public Collection<Building> listBuildingFavorites(@PathVariable String owner) {
    Collection<Building> buildings = new ArrayList<>();
    Optional<Favorite> favorites = favoriteRepository.findById(owner);

    buildings = favorites.stream()
            .map(f -> buildingConnector.getBuilding(f.getReferenceIdentificationNumber()))
            .collect(Collectors.toList());
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
  @PostMapping("/favorites")
  public void createFavorite(Favorite favorite) {

    // check if inputs are valid
    if (favorite.getOwner() == null || favorite.getReferenceIdentificationNumber() == null) {
      throw new InvalidArgumentsException();
    }

    // save the provided favorite to the favorite database
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
  @DeleteMapping("favorites/{identificationNumber}")
  public void remove(@PathVariable String identificationNumber) {

    // if the given identification number matches bin pattern remove the corresponding building in
    // the building connector
    if (identificationNumber.matches(BIN_PATTERN)) {
      this.buildingConnector.removeBuilding(identificationNumber);
    }

    // if the given identification number matches rin pattern remove the corresponding room in the
    // building connector
    if (identificationNumber.matches(RIN_PATTERN)) {
      this.buildingConnector.removeRoom(identificationNumber);
    }

    // if the given identification number matches cin pattern remove the corresponding component in
    // the building connector
    if (identificationNumber.matches(CIN_PATTERN)) {
      this.buildingConnector.removeComponent(identificationNumber);
    }

    // if the given identification number matches fin pattern remove the corresponding building from
    // the favorite database
    if (identificationNumber.matches(FIN_PATTERN)) {
      this.favoriteRepository.deleteById(identificationNumber);
    }

    throw new InvalidArgumentsException();
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
