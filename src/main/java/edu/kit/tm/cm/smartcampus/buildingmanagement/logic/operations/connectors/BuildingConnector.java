package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.connectors;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms.Room;

import java.util.Collection;

@org.springframework.stereotype.Component
public interface BuildingConnector {

    Building getBuilding(String in);

    Room getRoom(String in);

    Component getComponent(String in);

    Collection<Building> getBuildings();

    Collection<Room> getRooms(String in);

    Collection<Component> getComponents(String in);

    Collection<Notification> getNotifications(String in);

    Building addBuilding(Building building);

    Room addRoom(Room room);

    Component addComponent(Component component);

    Building updateBuilding(Building building, String in);

    Room updateRoom(Room room, String in);

    Component updateComponent(Component component, String in);

    void delete(String in);



}
