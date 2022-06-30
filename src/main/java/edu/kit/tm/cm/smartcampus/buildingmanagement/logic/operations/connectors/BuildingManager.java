package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.connectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingManager {
    private final BuildingConnector buildingConnector;

    @Autowired
    public BuildingManager(BuildingConnector buildingConnector) {
        this.buildingConnector = buildingConnector;
    }

    public void getBuilding() {
    }

    public void getRoom() {
    }

    public void getComponent() {
    }
    public void getBuildings() {
    }

    public void getFilteredBuildings() {
    }

    public void getRooms() {
    }

    public void getComponents() {
    }

    public void getNotifications() {
    }

    public void getFavorites() {
    }

    public void addBuilding() {
    }

    public void addRoom() {
    }

    public void addComponent() {
    }

    public void addFavorite() {
    }

    public void updateBuilding() {
    }

    public void updateRoom() {
    }

    public void updateComponent() {
    }

    public void delete() {
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
