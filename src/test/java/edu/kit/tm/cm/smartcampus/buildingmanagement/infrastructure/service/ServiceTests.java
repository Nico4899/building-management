package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service;

public class ServiceTests {

  /*
  private static Building building;
  private static Room room;
  private static Component component;
  private static Favorite favorite;
  private final BuildingConnector connector = mock(BuildingConnector.class);
  private final FavoriteRepository repository = mock(FavoriteRepository.class);
  private final FavoriteValidator favoriteValidator = mock(FavoriteValidator.class);
  private final Service service = new Service(connector, repository, favoriteValidator);

  @BeforeAll
  static void setup() {
    building = new Building();
    building.setBuildingName("Audimax");
    building.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
    building.setBuildingNumber("22.22");
    building.setLongitude(2.2);
    building.setLatitude(2.2);
    building.setIdentificationNumber("b-1");
    building.setLowestFloor(0);
    building.setHighestFloor(1);

    room = new Room();
    room.setRoomName("");
    room.setType(Room.Type.LECTURE_ROOM);
    room.setIdentificationNumber("r-1");
    room.setLatitude(2.2);
    room.setLongitude(2.2);
    room.setFloor(0);
    room.setRoomNumber("");
    room.setParentIdentificationNumber("");

    component = new Component();
    component.setComponentDescription("");
    component.setType(Component.Type.STAIRS);
    component.setLatitude(2.2);
    component.setLongitude(2.2);
    component.setIdentificationNumber("c-1");
    component.setParentIdentificationNumber("");

    favorite = new Favorite();
    favorite.setOwner("me");
    favorite.setIdentificationNumber("f-1");
    favorite.setReferenceIdentificationNumber("b-1");
  }

  @Test
  public void testGetBuilding() {
    when(connector.getBuilding("b-1")).thenReturn(building);
    Building responseBuilding = service.getBuilding("b-1");
    assertEquals(responseBuilding, building);
  }

  @Test
  public void testGetRoom() {
    when(connector.getRoom("r-1")).thenReturn(room);
    Room responseRoom = service.getRoom("r-1");
    assertEquals(responseRoom, room);
  }

  @Test
  public void testGetComponent() {
    when(connector.getComponent("c-1")).thenReturn(component);
    Component responseComponent = service.getComponent("c-1");
    assertEquals(responseComponent, component);
  }

  @Test
  public void testCreateBuilding() {
    when(connector.createBuilding(building)).thenReturn(building);
    Building responseBuilding = service.createBuilding(building);
    assertEquals(responseBuilding, building);
  }

  @Test
  public void testCreateRoom() {
    when(connector.createBuildingRoom(room)).thenReturn(room);
    Room responseRoom = service.createRoom(room);
    assertEquals(responseRoom, room);
  }

  @Test
  public void testCreateBuildingComponent() {
    when(connector.createBuildingComponent(component)).thenReturn(component);
    Component responseComponent = service.createBuildingComponent(component);
    assertEquals(responseComponent, component);
  }

  @Test
  public void testCreateRoomComponent() {
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
    when(connector.updateBuilding(building)).thenReturn(building);
    Building responseBuilding = service.updateBuilding(building);
    assertEquals(responseBuilding, building);
  }

  @Test
  public void testUpdateRoom() {
    when(connector.updateRoom(room)).thenReturn(room);
    Room responseRoom = service.updateRoom(room);
    assertEquals(responseRoom, room);
  }

  @Test
  public void testUpdateComponent() {
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
   */
}
