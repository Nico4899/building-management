package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.connectors;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.rooms.Room;

import java.util.Collection;

public interface BuildingConnector {

    Building getBuilding(int in);

    Room getRoom(int in);

    Component getComponent(int in);

    Collection<Building> getBuildings();

    Collection<Building> getFilteredBuildings

}
