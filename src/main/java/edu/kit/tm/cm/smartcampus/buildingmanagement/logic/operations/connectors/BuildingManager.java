package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.connectors;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filters.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@org.springframework.stereotype.Component
public class BuildingManager {
    private final BuildingConnector buildingConnector;

@Autowired
    public BuildingManager(BuildingConnector buildingConnector) {
        this.buildingConnector = buildingConnector;
    }

    public Building getBuilding(String in) {
        Building building = buildingConnector.getBuilding(in);
        return building;
    }

    public Room getRoom(String in) {
        Room room = buildingConnector.getRoom(in);
        return room;
    }

    public Component getComponent(String in) {
        Component component = buildingConnector.getComponent(in);
        return component;
    }
    public Collection<Building> getBuildings() {
        Collection<Building> buildings = buildingConnector.getBuildings();
        return buildings;
    }

    public Collection<Building> getFilteredBuildings() {
    Collection<Building> buildings = buildingConnector.getBuildings();

    return buildings.stream()
            //.filter()
            .collect(Collectors.toList());
    }

    public Collection<Room> getRooms(String in) {
    Collection<Room> rooms = buildingConnector.getRooms(in);
    return rooms;
    }

    public Collection<Component> getComponents(String in) {
    Collection<Component> components = buildingConnector.getComponents(in);
    return components;
    }

    public Collection<Notification> getNotifications(String in) {
    Collection<Notification> notifications = buildingConnector.getNotifications(in);
    return notifications;
    }

    public void getFavorites() {
    }

    public Building addBuilding(Building building) {
    return buildingConnector.addBuilding(building);
    }

    public Room addRoom(Room room) {
    return buildingConnector.addRoom(room);
    }

    public Component addComponent(Component component) {
    return buildingConnector.addComponent(component);
    }

    public void addFavorite() {
    }

    public Building updateBuilding(Building newBuilding, String in) {
    Building building = buildingConnector.getBuilding(in);
    building = newBuilding;
    return buildingConnector.addBuilding(building);


    }

    public Room updateRoom(Room newRoom, String in) {
    Room room = buildingConnector.getRoom(in);
    room = newRoom;
    return buildingConnector.addRoom(room);
    }

    public Component updateComponent(Component newComponent, String in) {
    Component component = buildingConnector.getComponent(in);
    component = newComponent;
    return buildingConnector.addComponent(component);
    }

    public void delete(String in) {
    buildingConnector.delete(in);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
