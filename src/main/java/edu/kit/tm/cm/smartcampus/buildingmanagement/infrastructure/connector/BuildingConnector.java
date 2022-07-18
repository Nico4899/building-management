package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.BuildingManagementService;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;

import java.util.Collection;

/**
 * This class describes a building connector which is being used by {@link
 * BuildingManagementService}* to provide an interface for a REST connector to the building domain
 * microservice.
 */
public interface BuildingConnector {

  /**
   * List buildings.
   *
   * @return buildings collection
   */
  Collection<Building> listBuildings();

  /**
   * Create building.
   *
   * @param building the building to be created
   * @return created building
   */
  Building createBuilding(Building building);

  /**
   * Gets building.
   *
   * @param identificationNumber the building's identification number
   * @return the building
   */
  Building getBuilding(String identificationNumber);

  /**
   * Update building.
   *
   * @param building the building
   * @return the building
   */
  Building updateBuilding(Building building);

  /**
   * Remove building.
   *
   * @param identificationNumber the identification number
   */
  void removeBuilding(String identificationNumber);

  /**
   * List building rooms collection.
   *
   * @param identificationNumber the identification number
   * @return the collection
   */
  Collection<Room> listBuildingRooms(String identificationNumber);

  /**
   * Create building room.
   *
   * @param room the room
   * @return the room
   */
  Room createBuildingRoom(Room room);

  /**
   * Gets room.
   *
   * @param identificationNumber the identification number
   * @return the room
   */
  Room getRoom(String identificationNumber);

  /**
   * Update room.
   *
   * @param room the room
   * @return the room
   */
  Room updateRoom(Room room);

  /**
   * Remove room.
   *
   * @param identificationNumber the identification number
   */
  void removeRoom(String identificationNumber);

  /**
   * List building components collection.
   *
   * @param identificationNumber the identification number
   * @return the collection
   */
  Collection<Component> listBuildingComponents(String identificationNumber);

  /**
   * List room components collection.
   *
   * @param identificationNumber the identification number
   * @return the collection
   */
  Collection<Component> listRoomComponents(String identificationNumber);

  /**
   * Create building component component.
   *
   * @param component the component
   * @return the component
   */
  Component createBuildingComponent(Component component);

  /**
   * Create room component component.
   *
   * @param component the component
   * @return the component
   */
  Component createRoomComponent(Component component);

  /**
   * Gets component.
   *
   * @param identificationNumber the identification number
   * @return the component
   */
  Component getComponent(String identificationNumber);

  /**
   * Update component component.
   *
   * @param component the component
   * @return the component
   */
  Component updateComponent(Component component);

  /**
   * Remove component.
   *
   * @param identificationNumber the identification number
   */
  void removeComponent(String identificationNumber);

  /**
   * List building notifications collection.
   *
   * @param identificationNumber the identification number
   * @return the collection
   */
  Collection<Notification> listBuildingNotifications(String identificationNumber);

  /**
   * List room notifications collection.
   *
   * @param identificationNumber the identification number
   * @return the collection
   */
  Collection<Notification> listRoomNotifications(String identificationNumber);

  /**
   * List component notifications collection.
   *
   * @param identificationNumber the identification number
   * @return the collection
   */
  Collection<Notification> listComponentNotifications(String identificationNumber);
}
