package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.connectors;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms.Room;

import java.util.Collection;

@org.springframework.stereotype.Component
public interface BuildingConnector {

    Building getBuilding(int in);

    Room getRoom(int in);

    Component getComponent(int in);

    Collection<Building> getBuildings();

    Collection<Room> getRooms(int in);

    Collection<Component> getComponents(int in);

    Collection<Notification> getNotifications(int in);

    Building addBuilding(Building building);

    Room addRoom(Room room);

    Component addComponent(Component component);

    Building updateBuilding(Building building);

    Room updateRoom(Room room);

    Component updateComponent(Component component);

    void delete(String in);



}
