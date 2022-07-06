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

    // using constructor injection
    this.favoriteRepository = favoriteRepository;
    this.buildingConnector = buildingConnector;
  }

  // list elements

  public Collection<Building> listBuildings(FilterOptions filterOptions) {

    // retrieve all buildings from connector
    Collection<Building> buildings = buildingConnector.listBuildings();

    // apply filters, selected in filter options, on retrieved buildings
    applyBuildingFilters(filterOptions, buildings);

    // return buildings
    return buildings;
  }

  public Collection<Room> listRooms(FilterOptions filterOptions, String identificationNumber) {

    // check if valid input
    if (matchesNoIdentificationNumberPattern(identificationNumber)) {
      throw new InvalidArgumentsException();
    }

    // instantiate empty list of rooms
    Collection<Room> rooms = Collections.emptyList();

    // if identification number matches a bin pattern, fill rooms with the building's rooms, and
    // apply
    // filters, selected in filter options, on retrieved rooms
    if (identificationNumber.matches(BIN_PATTERN)) {
      rooms = buildingConnector.listBuildingRooms(identificationNumber);
      applyRoomFilters(filterOptions, rooms);
    }

    // return rooms
    return rooms;
  }

  public Collection<Component> listComponents(String identificationNumber) {

    // instantiate empty list of components
    Collection<Component> components = Collections.emptyList();

    // check if valid input
    if (matchesNoIdentificationNumberPattern(identificationNumber)) {
      throw new InvalidArgumentsException();
    }

    // if identification number matches a bin pattern, fill components with the building's
    // components
    if (identificationNumber.matches(BIN_PATTERN)) {
      components = buildingConnector.listBuildingComponents(identificationNumber);
    }

    // if identification number matches a rin pattern, fill components with the room's components
    if (identificationNumber.matches(RIN_PATTERN)) {
      components = buildingConnector.listRoomComponents(identificationNumber);
    }

    // return components
    return components;
  }

  public Collection<Notification> listNotifications(String identificationNumber) {

    // check if valid input
    if (matchesNoIdentificationNumberPattern(identificationNumber)) {
      throw new InvalidArgumentsException();
    }

    // instantiate empty list of notifications
    Collection<Notification> notifications = Collections.emptyList();

    // if identification number matches a rin pattern, fill notifications with the building's
    // notifications
    if (identificationNumber.matches(BIN_PATTERN)) {
      notifications = buildingConnector.listBuildingNotifications(identificationNumber);
    }

    // if identification number matches a rin pattern, fill notifications with the room's
    // notifications
    if (identificationNumber.matches(RIN_PATTERN)) {
      notifications = buildingConnector.listRoomNotifications(identificationNumber);
    }

    // if identification number matches a rin pattern, fill notifications with the component's
    // notifications
    if (identificationNumber.matches(CIN_PATTERN)) {
      notifications = buildingConnector.listComponentNotifications(identificationNumber);
    }

    // return notifications
    return notifications;
  }

  public Collection<Component> listComponentFavorites(String owner) {

    // check if inputs are valid
    if (owner == null) {
      throw new InvalidArgumentsException();
    }

    // instantiate component list
    Collection<Component> components = new ArrayList<>();

    // find all favorites in system and add only components belonging to the "owner" to the
    // component list
    for (Favorite favorite : favoriteRepository.findAll()) {
      if (favorite.getOwner().equals(owner)
          && favorite.getReferenceIdentificationNumber().matches(CIN_PATTERN)) {
        components.add(buildingConnector.getComponent(favorite.getReferenceIdentificationNumber()));
      }
    }

    // return components
    return components;
  }

  public Collection<Room> listRoomFavorites(String owner) {

    // check if inputs are valid
    if (owner == null) {
      throw new InvalidArgumentsException();
    }

    // instantiate room list
    Collection<Room> rooms = new ArrayList<>();

    // find all favorites in system and add only rooms belonging to the "owner" to the rooms list
    for (Favorite favorite : favoriteRepository.findAll()) {
      if (favorite.getOwner().equals(owner)
          && favorite.getReferenceIdentificationNumber().matches(RIN_PATTERN)) {
        rooms.add(buildingConnector.getRoom(favorite.getReferenceIdentificationNumber()));
      }
    }

    // return rooms
    return rooms;
  }

  public Collection<Building> listBuildingFavorites(String owner) {

    // check if inputs are valid
    if (owner == null) {
      throw new InvalidArgumentsException();
    }

    // instantiate building list
    Collection<Building> buildings = new ArrayList<>();

    // find all favorites in system and add only buildings belonging to the "owner" to the building
    // list
    for (Favorite favorite : favoriteRepository.findAll()) {
      if (favorite.getOwner().equals(owner)
          && favorite.getReferenceIdentificationNumber().matches(BIN_PATTERN)) {
        buildings.add(buildingConnector.getBuilding(favorite.getReferenceIdentificationNumber()));
      }
    }

    // return buildings
    return buildings;
  }

  // get single element

  public Building getBuilding(String identificationNumber) {

    // return the building retrieved from the building connector
    return this.buildingConnector.getBuilding(identificationNumber);
  }

  public Room getRoom(String identificationNumber) {

    // return the room retrieved from the building connector
    return this.buildingConnector.getRoom(identificationNumber);
  }

  public Component getComponent(String identificationNumber) {

    // return the component retrieved from the building connector
    return this.buildingConnector.getComponent(identificationNumber);
  }

  // create element

  public Building createBuilding(Building building) {

    // create building in the building connector, and return the created building as response
    return this.buildingConnector.createBuilding(building);
  }

  public Room createRoom(Room room) {

    // if room's parent identification number matches bin pattern, create a building's room in the
    // building connector,
    // and return the created room as response
    if (room.getParentIdentificationNumber().matches(BIN_PATTERN)) {
      return this.buildingConnector.createBuildingRoom(room);
    }

    // return nothing if none of the previous cases fit
    return null;
  }

  public Component createComponent(Component component) {

    // if component's parent identification number matches bin pattern, create a building's
    // component in the building connector,
    // and return the created component as response
    if (component.getParentIdentificationNumber().matches(BIN_PATTERN)) {
      return this.buildingConnector.createBuildingComponent(component);
    }

    // if component's parent identification number matches rin pattern, create a room's component in
    // the building connector,
    // and return the created component as response
    if (component.getParentIdentificationNumber().matches(RIN_PATTERN)) {
      return this.buildingConnector.createRoomComponent(component);
    }

    // return nothing if none of the previous cases fit
    return null;
  }

  public void createFavorite(Favorite favorite) {

    // check if inputs are valid
    if (favorite.getOwner() == null || favorite.getReferenceIdentificationNumber() == null) {
      throw new InvalidArgumentsException();
    }

    // save the provided favorite to the favorite database
    this.favoriteRepository.save(favorite);
  }

  // update element

  public Building updateBuilding(Building building) {

    // update the provided building in the building connector and return the updated building
    return this.buildingConnector.updateBuilding(building);
  }

  public Room updateRoom(Room room) {

    // update the provided room in the building connector and return the updated room
    return this.buildingConnector.updateRoom(room);
  }

  public Component updateComponent(Component component) {

    // update the provided component in the building connector and return the updated component
    return this.buildingConnector.updateComponent(component);
  }

  // remove element

  public void remove(String identificationNumber) {

    // check if matching some pattern
    if (matchesNoIdentificationNumberPattern(identificationNumber)) {
      throw new InvalidArgumentsException();
    }

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

  // private methods for filter procedures

  private void applyBuildingFilters(FilterOptions filterOptions, Collection<Building> buildings) {

    // create a filter command with generic building, add all selected filter options and add
    // filters to the command
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

    // execute the filter which filters the collection reference in place
    filterCommand.execute();
  }

  private void applyRoomFilters(FilterOptions filterOptions, Collection<Room> rooms) {

    // create a filter command with generic room, add all selected filter options and add filters to
    // the command
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

    // execute the filter which filters the collection reference in place
    filterCommand.execute();
  }

  // collection to child mappers

  private Map<Building, Collection<Room>> mapBuildingRooms(Collection<Building> buildings) {

    // create a map which maps a building to its child rooms
    Map<Building, Collection<Room>> buildingRoomsMap = new HashMap<>();

    // map each building's rooms to its parents key
    for (Building building : buildings) {
      buildingRoomsMap.put(
          building, buildingConnector.listBuildingRooms(building.getIdentificationNumber()));
    }

    // return the building rooms mapping
    return buildingRoomsMap;
  }

  private Map<Building, Collection<Component>> mapBuildingComponents(
      Collection<Building> buildings) {

    // create a map which maps a building to its child components
    Map<Building, Collection<Component>> buildingComponentsMap = new HashMap<>();

    // map each building's components to its parents key
    for (Building building : buildings) {
      buildingComponentsMap.put(
          building, buildingConnector.listBuildingComponents(building.getIdentificationNumber()));
    }

    // return the building components map
    return buildingComponentsMap;
  }

  private Map<Room, Collection<Component>> mapRoomComponents(Collection<Room> rooms) {

    // create a map which maps a building to its child components
    Map<Room, Collection<Component>> buildingComponentsMap = new HashMap<>();

    // map each room's components to its parents key
    for (Room room : rooms) {
      buildingComponentsMap.put(
          room, buildingConnector.listRoomComponents(room.getIdentificationNumber()));
    }

    // return the room component's map
    return buildingComponentsMap;
  }

  private boolean matchesNoIdentificationNumberPattern(String string) {
    return (string == null)
      || (!string.matches(BIN_PATTERN))
      || (!string.matches(RIN_PATTERN))
      || (!string.matches(CIN_PATTERN))
      || (!string.matches(FIN_PATTERN));
  }
}
