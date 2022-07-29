package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.building.dto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;

import java.util.Collection;

/**
 * This class describes a building connector which is being used by {@link Service}* to provide an
 * interface for a REST connector to the building domain microservice.
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
   * @param clientCreateBuildingRequest the building to be created
   * @return created building
   */
  Building createBuilding(ClientCreateBuildingRequest clientCreateBuildingRequest);

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
   * @param clientUpdateBuildingRequest the building
   * @return the building
   */
  Building updateBuilding(ClientUpdateBuildingRequest clientUpdateBuildingRequest);

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
   * @param clientCreateRoomRequest the room
   * @return the room
   */
  Room createRoom(ClientCreateRoomRequest clientCreateRoomRequest);

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
   * @param clientUpdateRoomRequest the room
   * @return the room
   */
  Room updateRoom(ClientUpdateRoomRequest clientUpdateRoomRequest);

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
   * @param clientCreateComponentRequest the component
   * @return the component
   */
  Component createComponent(ClientCreateComponentRequest clientCreateComponentRequest);

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
   * @param clientUpdateComponentRequest the component
   * @return the component
   */
  Component updateComponent(ClientUpdateComponentRequest clientUpdateComponentRequest);

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
