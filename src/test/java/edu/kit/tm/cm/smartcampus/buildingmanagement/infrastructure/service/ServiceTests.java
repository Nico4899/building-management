package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.BuildingConnector;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.validator.InputValidator;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceTests {

   private BuildingConnector connector = mock(BuildingConnector.class);

   private FavoriteRepository repository = mock(FavoriteRepository.class);

   private InputValidator validator = mock(InputValidator.class);

    private BuildingManagementService service = new BuildingManagementService(connector, repository, validator);

   private ArrayList<Building> buildings = new ArrayList<>();

   @Test
   public void testGetBuilding() {
       Building building = new Building();
       building.setBuildingName("Audimax");
       building.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
       building.setBuildingNumber("22.22");
       building.setLongitude(2.2);
       building.setLatitude(2.2);
       building.setIdentificationNumber("b-1");
       building.setNumFloors(1);
       when(connector.getBuilding("b-1")).thenReturn(building);
       Building responseBuilding = service.getBuilding("b-1");
       assertEquals(responseBuilding, building);
   }

   @Test
    public void testGetRoom() {
       Room room = new Room();
       room.setRoomName("");
       room.setType(Room.Type.LECTURE_ROOM);
       room.setIdentificationNumber("r-1");
       room.setLatitude(2.2);
       room.setLongitude(2.2);
       room.setFloor(0);
       room.setRoomNumber("");
       room.setParentIdentificationNumber("");
       when(connector.getRoom("r-1")).thenReturn(room);
       Room responseRoom = service.getRoom("r-1");
       assertEquals(responseRoom, room);
   }

   @Test
    public void testGetComponent() {
       Component component = new Component();
       component.setComponentDescription("");
       component.setType(Component.Type.STAIRS);
       component.setLatitude(2.2);
       component.setLongitude(2.2);
       component.setIdentificationNumber("c-1");
       component.setParentIdentificationNumber("");
       when(connector.getComponent("c-1")).thenReturn(component);
       Component responseComponent = service.getComponent("c-1");
       assertEquals(responseComponent, component);
   }

   @Test
    public void testCreateBuilding() {
       Building building = new Building();
       building.setBuildingName("Audimax");
       building.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
       building.setBuildingNumber("22.22");
       building.setLongitude(2.2);
       building.setLatitude(2.2);
       building.setIdentificationNumber("b-1");
       building.setNumFloors(1);
       when(connector.createBuilding(building)).thenReturn(building);
       Building responseBuilding = service.createBuilding(building);
       assertEquals(responseBuilding, building);
   }

   @Test
    public void testCreateRoom() {
       Room room = new Room();
       room.setRoomName("");
       room.setType(Room.Type.LECTURE_ROOM);
       room.setIdentificationNumber("r-1");
       room.setLatitude(2.2);
       room.setLongitude(2.2);
       room.setFloor(0);
       room.setRoomNumber("");
       room.setParentIdentificationNumber("");
       when(connector.createBuildingRoom(room)).thenReturn(room);
       Room responseRoom = service.createRoom(room);
       assertEquals(responseRoom, room);
   }

   @Test
    public void testCreateBuildingComponent() {
       Component component = new Component();
       component.setComponentDescription("");
       component.setType(Component.Type.STAIRS);
       component.setLatitude(2.2);
       component.setLongitude(2.2);
       component.setIdentificationNumber("c-1");
       component.setParentIdentificationNumber("");
       when(connector.createBuildingComponent(component)).thenReturn(component);
       Component responseComponent = service.createBuildingComponent(component);
       assertEquals(responseComponent, component);
   }

   @Test
    public void testCreateRoomComponent() {
       Component component = new Component();
       component.setComponentDescription("");
       component.setType(Component.Type.STAIRS);
       component.setLatitude(2.2);
       component.setLongitude(2.2);
       component.setIdentificationNumber("c-1");
       component.setParentIdentificationNumber("");
       when(connector.createRoomComponent(component)).thenReturn(component);
       Component responseComponent = service.createRoomComponent(component);
       assertEquals(responseComponent, component);
   }

   @Test
    public void testCreateFavorite() {
       Favorite favorite = new Favorite();
       favorite.setOwner("me");
       favorite.setIdentificationNumber("f-1");
       favorite.setReferenceIdentificationNumber("b-1");
       assertDoesNotThrow(() -> service.createFavorite(favorite));
   }

   @Test
   public void testUpdateBuilding() {
      Building building = new Building();
      building.setBuildingName("Audimax");
      building.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
      building.setBuildingNumber("22.22");
      building.setLongitude(2.2);
      building.setLatitude(2.2);
      building.setIdentificationNumber("b-1");
      building.setNumFloors(1);
      when(connector.updateBuilding(building)).thenReturn(building);
      Building responseBuilding = service.updateBuilding(building);
      assertEquals(responseBuilding, building);
   }

   @Test
   public void testUpdateRoom() {
      Room room = new Room();
      room.setRoomName("");
      room.setType(Room.Type.LECTURE_ROOM);
      room.setIdentificationNumber("r-1");
      room.setLatitude(2.2);
      room.setLongitude(2.2);
      room.setFloor(0);
      room.setRoomNumber("");
      room.setParentIdentificationNumber("");
      when(connector.updateRoom(room)).thenReturn(room);
      Room responseRoom = service.updateRoom(room);
      assertEquals(responseRoom, room);
   }

   @Test
   public void testUpdateComponent() {
      Component component = new Component();
      component.setComponentDescription("");
      component.setType(Component.Type.STAIRS);
      component.setLatitude(2.2);
      component.setLongitude(2.2);
      component.setIdentificationNumber("c-1");
      component.setParentIdentificationNumber("");
      when(connector.updateComponent(component)).thenReturn(component);
      Component responseComponent = service.updateComponent(component);
      assertEquals(responseComponent, component);
   }

   @Test
   public void testRemoveBuilding() {
      assertDoesNotThrow(() -> service.removeBuilding("b-1"));
   }

   @Test
   public void testRemoveRoom() {
      assertDoesNotThrow(() -> service.removeRoom("r-1"));
   }

   @Test
   public void testRemoveComponent() {
      assertDoesNotThrow(() -> service.removeComponent("c-1"));
   }

   @Test
   public void testRemoveFavorite() {
      when(repository.existsById("f-1")).thenReturn(true);
      assertDoesNotThrow(() -> service.removeFavorite("f-1"));
   }


}
