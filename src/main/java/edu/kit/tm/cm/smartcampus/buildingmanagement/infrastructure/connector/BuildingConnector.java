package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.BuildingManagementService;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;

import java.util.Collection;

/**
 * This class describes a building connector which is being used by {@link
 * BuildingManagementService} to provide an interface for a REST connector to the building domain
 * microservice.
 */
public interface BuildingConnector {

  Collection<Building> listBuildings();

  Building createBuilding(Building building);

  Building getBuilding(String identificationNumber);

  Building updateBuilding(Building building);

  void removeBuilding(String identificationNumber);

  Collection<Room> listBuildingRooms(String identificationNumber);

  Room createBuildingRoom(Room room);

  Room getRoom(String identificationNumber);

  Room updateRoom(Room room);

  void removeRoom(String identificationNumber);

  Collection<Component> listBuildingComponents(String identificationNumber);

  Collection<Component> listRoomComponents(String identificationNumber);

  Component createBuildingComponent(Component component);

  Component createRoomComponent(Component component);

  Component getComponent(String identificationNumber);

  Component updateComponent(Component component);

  void removeComponent(String identificationNumber);

  Collection<Notification> listBuildingNotifications(String identificationNumber);

  Collection<Notification> listRoomNotifications(String identificationNumber);

  Collection<Notification> listComponentNotifications(String identificationNumber);
}
