package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms.Room;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.connectors.BuildingConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

public class RestTemplateBuildingConnector implements BuildingConnector {

    private final String baseUrl;

    private final RestTemplate restTemplate;

    public RestTemplateBuildingConnector( RestTemplate restTemplate, @Value("http://localhost:8080") String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public Building getBuilding(String in) {
        return null;
    }

    @Override
    public Room getRoom(String in) {
        return null;
    }

    @Override
    public Component getComponent(String in) {
        return null;
    }

    @Override
    public Collection<Building> getBuildings() {
        return null;
    }

    @Override
    public Collection<Room> getRooms(String in) {
        return null;
    }

    @Override
    public Collection<Component> getComponents(String in) {
        return null;
    }

    @Override
    public Collection<Notification> getNotifications(String in) {
        return null;
    }

    @Override
    public Building addBuilding(Building building) {
        return null;
    }

    @Override
    public Room addRoom(Room room) {
        return null;
    }

    @Override
    public Component addComponent(Component component) {
        return null;
    }

    @Override
    public Building updateBuilding(Building building, String in) {
        return null;
    }

    @Override
    public Room updateRoom(Room room, String in) {
        return null;
    }

    @Override
    public Component updateComponent(Component component, String in) {
        return null;
    }

    @Override
    public void delete(String in) {

    }
}
