package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.Favorite;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BuildingManager {
  private final BuildingConnector buildingConnector;

  // Repositories for Favorite and Building, all operations correlate to them
  private final Map<IdentificationNumber, Building> buildingRepository = new HashMap<>();
  private final Map<IdentificationNumber, Favorite<Building>> favoriteRepository = new HashMap<>();

  @Autowired
  public BuildingManager(BuildingConnector buildingConnector) {
    this.buildingConnector = buildingConnector;
  }

  //TODO maybe change name of "add" to "create"

  // create{x} -> REST Call create{x} -> response to object -> and add to buildingRepository at wanted location

  // Montag müssen wir schauen ob sie tipps zu events haben? sollten Kafka streams oder so sein

  public void getBuilding() {}

  public void getRoom() {}

  public void getComponent() {}

  public void getBuildings() {}

  public void getFilteredBuildings() {}

  public void getRooms() {}

  public void getComponents() {}

  public void getNotifications() {}

  public void getFavorites() {}

  public void addBuilding() {}

  public void addRoom() {}

  public void addComponent() {}

  public void addFavorite() {}

  public void updateBuilding() {}

  public void updateRoom() {}

  public void updateComponent() {}

  public void delete() {}

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
