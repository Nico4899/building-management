package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FilterTests {

  public static final String ELEVATOR_COMPONENT = "elevatorComponent";
  public static final String STAIRS_COMPONENT = "stairsComponent";
  public static final String LECTURE_ROOM = "lectureRoom";
  public static final String SEMINAR_ROOM = "seminarRoom";
  public static final String REST_ROOM = "restRoom";
  public static final String REST_ROOM_HANDICAPPED = "restRoomHandicapped";
  public static final String OFFICE = "office";
  public static final String SPORTS = "sports";
  public static final String LIBRARY = "library";
  public static final String CAFETERIA = "cafeteria";
  public static final String EAST_CAMPUS_BUILDING = "eastCampusBuilding";
  public static final String WEST_CAMPUS_BUILDING = "westCampusBuilding";
  public static final String SOUTH_CAMPUS_BUILDING = "southCampusBuilding";
  public static final String NORTH_CAMPUS_BUILDING = "northCampusBuilding";

  private static final Collection<Room.Type> ALL_ROOM_TYPES = List.of(Room.Type.values());
  private static final Collection<Room.Type> SOME_ROOM_TYPES =
      List.of(Room.Type.OFFICE, Room.Type.LIBRARY, Room.Type.RESTROOM);
  private static final Collection<Room.Type> NO_ROOM_TYPES = List.of();

  private static final Collection<Component.Type> ALL_COMPONENT_TYPES =
      List.of(Component.Type.values());
  private static final Collection<Component.Type> SOME_COMPONENT_TYPES =
      List.of(Component.Type.STAIRS);
  private static final Collection<Component.Type> NO_COMPONENT_TYPES = List.of();

  private static final Collection<Building.CampusLocation> ALL_CAMPUS_LOCATIONS =
      List.of(Building.CampusLocation.values());
  private static final Collection<Building.CampusLocation> SOME_CAMPUS_LOCATIONS =
      List.of(Building.CampusLocation.EAST_CAMPUS, Building.CampusLocation.NORTH_CAMPUS);
  private static final Collection<Building.CampusLocation> NO_CAMPUS_LOCATIONS = List.of();

  private static final Map<String, Component> testComponentsMap = new HashMap<>();
  private static final Map<String, Room> testRoomsMap = new HashMap<>();
  private static final Map<String, Building> testBuildingsMap = new HashMap<>();

  // test object collections
  private static Collection<Building> testBuildings;
  private static Collection<Room> testRooms;

  @BeforeAll
  public static void setUp() {
    buildTestMappings();
  }

  private static void buildTestMappings() {

    Component component1 = new Component();
    component1.setType(Component.Type.ELEVATOR);
    Component component2 = new Component();
    component2.setType(Component.Type.STAIRS);

    // build components
    testComponentsMap.put(ELEVATOR_COMPONENT, component1);
    testComponentsMap.put(STAIRS_COMPONENT, component2);

    Room room1 = new Room();
    room1.setType(Room.Type.LECTURE_ROOM);
    Room room2 = new Room();
    room2.setType(Room.Type.SEMINAR_ROOM);
    Room room3 = new Room();
    room3.setType(Room.Type.RESTROOM);
    Room room4 = new Room();
    room4.setType(Room.Type.RESTROOM_HANDICAPPED);
    Room room5 = new Room();
    room5.setType(Room.Type.OFFICE);
    Room room6 = new Room();
    room6.setType(Room.Type.SPORTS);
    Room room7 = new Room();
    room7.setType(Room.Type.LIBRARY);
    Room room8 = new Room();
    room8.setType(Room.Type.CAFETERIA);

    // build rooms
    testRoomsMap.put(LECTURE_ROOM, room1);
    testRoomsMap.put(SEMINAR_ROOM, room2);
    testRoomsMap.put(REST_ROOM, room3);
    testRoomsMap.put(REST_ROOM_HANDICAPPED, room4);
    testRoomsMap.put(OFFICE, room5);
    testRoomsMap.put(SPORTS, room6);
    testRoomsMap.put(LIBRARY, room7);
    testRoomsMap.put(CAFETERIA, room8);

    testRoomsMap.get(LECTURE_ROOM).getComponents().add(testComponentsMap.get(ELEVATOR_COMPONENT));
    testRoomsMap.get(SEMINAR_ROOM).getComponents().add(testComponentsMap.get(ELEVATOR_COMPONENT));
    testRoomsMap.get(REST_ROOM).getComponents().add(testComponentsMap.get(ELEVATOR_COMPONENT));
    testRoomsMap.get(SPORTS).getComponents().add(testComponentsMap.get(STAIRS_COMPONENT));
    testRoomsMap.get(LIBRARY).getComponents().add(testComponentsMap.get(STAIRS_COMPONENT));

    Building building1 = new Building();
    building1.setCampusLocation(Building.CampusLocation.EAST_CAMPUS);
    Building building2 = new Building();
    building2.setCampusLocation(Building.CampusLocation.WEST_CAMPUS);
    Building building3 = new Building();
    building3.setCampusLocation(Building.CampusLocation.SOUTH_CAMPUS);
    Building building4 = new Building();
    building4.setCampusLocation(Building.CampusLocation.NORTH_CAMPUS);

    // build buildings
    testBuildingsMap.put(EAST_CAMPUS_BUILDING, building1);
    testBuildingsMap.put(WEST_CAMPUS_BUILDING, building2);
    testBuildingsMap.put(SOUTH_CAMPUS_BUILDING, building3);
    testBuildingsMap.put(NORTH_CAMPUS_BUILDING, building4);

    testBuildingsMap
        .get(EAST_CAMPUS_BUILDING)
        .getRooms()
        .addAll(List.of(testRoomsMap.get(LECTURE_ROOM), testRoomsMap.get(SEMINAR_ROOM)));
    testBuildingsMap
        .get(WEST_CAMPUS_BUILDING)
        .getRooms()
        .addAll(List.of(testRoomsMap.get(REST_ROOM), testRoomsMap.get(REST_ROOM_HANDICAPPED)));
    testBuildingsMap
        .get(WEST_CAMPUS_BUILDING)
        .getComponents()
        .add(testComponentsMap.get(STAIRS_COMPONENT));
    testBuildingsMap
        .get(NORTH_CAMPUS_BUILDING)
        .getRooms()
        .addAll(List.of(testRoomsMap.get(OFFICE), testRoomsMap.get(SPORTS)));
    testBuildingsMap
        .get(SOUTH_CAMPUS_BUILDING)
        .getRooms()
        .addAll(List.of(testRoomsMap.get(LIBRARY), testRoomsMap.get(CAFETERIA)));
    testBuildingsMap
        .get(SOUTH_CAMPUS_BUILDING)
        .getComponents()
        .add(testComponentsMap.get(ELEVATOR_COMPONENT));

    // build collections
    testBuildings = testBuildingsMap.values();
    testRooms = testRoomsMap.values();
  }

  @ParameterizedTest
  @ArgumentsSource(BuildingFilterArgumentsProvider.class)
  void filterBuildingCollectionAndValuesResultTest(
      Collection<Building> expected,
      Filter<Building> filter,
      Collection<Building> collection,
      Collection<?> values) {
    Assertions.assertTrue(expected.containsAll(filter.filter(collection, values)));
  }

  @ParameterizedTest
  @ArgumentsSource(RoomFilterArgumentsProvider.class)
  void filterRoomCollectionAndValuesResultTest(
      Collection<Room> expected,
      Filter<Room> filter,
      Collection<Room> collection,
      Collection<?> values) {
    Assertions.assertTrue(expected.containsAll(filter.filter(collection, values)));
  }

  private static class RoomFilterArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
      return Stream.of(
          Arguments.of(testRooms, RoomFilter.ROOM_TYPE_FILTER, testRooms, ALL_ROOM_TYPES),
          Arguments.of(List.of(), RoomFilter.ROOM_TYPE_FILTER, testRooms, NO_ROOM_TYPES),
          Arguments.of(
              List.of(
                  testRoomsMap.get(OFFICE), testRoomsMap.get(LIBRARY), testRoomsMap.get(REST_ROOM)),
              RoomFilter.ROOM_TYPE_FILTER,
              testRooms,
              SOME_ROOM_TYPES),
          Arguments.of(
              List.of(
                  testRoomsMap.get(LECTURE_ROOM),
                  testRoomsMap.get(SEMINAR_ROOM),
                  testRoomsMap.get(REST_ROOM),
                  testRoomsMap.get(SPORTS),
                  testRoomsMap.get(LIBRARY)),
              RoomFilter.COMPONENT_TYPE_FILTER,
              testRooms,
              ALL_COMPONENT_TYPES),
          Arguments.of(List.of(), RoomFilter.COMPONENT_TYPE_FILTER, testRooms, NO_COMPONENT_TYPES),
          Arguments.of(
              List.of(testRoomsMap.get(LIBRARY), testRoomsMap.get(SPORTS)),
              RoomFilter.COMPONENT_TYPE_FILTER,
              testRooms,
              SOME_COMPONENT_TYPES));
    }
  }

  private static class BuildingFilterArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
      return Stream.of(
          Arguments.of(
              testBuildings, BuildingFilter.ROOM_TYPE_FILTER, testBuildings, ALL_ROOM_TYPES),
          Arguments.of(List.of(), BuildingFilter.ROOM_TYPE_FILTER, testBuildings, NO_ROOM_TYPES),
          Arguments.of(
              List.of(
                  testBuildingsMap.get(WEST_CAMPUS_BUILDING),
                  testBuildingsMap.get(NORTH_CAMPUS_BUILDING),
                  testBuildingsMap.get(SOUTH_CAMPUS_BUILDING)),
              BuildingFilter.ROOM_TYPE_FILTER,
              testBuildings,
              SOME_ROOM_TYPES),
          Arguments.of(
              List.of(
                  testBuildingsMap.get(WEST_CAMPUS_BUILDING),
                  testBuildingsMap.get(SOUTH_CAMPUS_BUILDING)),
              BuildingFilter.COMPONENT_TYPE_FILTER,
              testBuildings,
              ALL_COMPONENT_TYPES),
          Arguments.of(
              List.of(), BuildingFilter.COMPONENT_TYPE_FILTER, testBuildings, NO_COMPONENT_TYPES),
          Arguments.of(
              List.of(testBuildingsMap.get(WEST_CAMPUS_BUILDING)),
              BuildingFilter.COMPONENT_TYPE_FILTER,
              testBuildings,
              SOME_COMPONENT_TYPES),
          Arguments.of(
              List.of(
                  testBuildingsMap.get(EAST_CAMPUS_BUILDING),
                  testBuildingsMap.get(NORTH_CAMPUS_BUILDING)),
              BuildingFilter.CAMPUS_LOCATION_FILTER,
              testBuildings,
              SOME_CAMPUS_LOCATIONS),
          Arguments.of(
              testBuildings,
              BuildingFilter.CAMPUS_LOCATION_FILTER,
              testBuildings,
              ALL_CAMPUS_LOCATIONS),
          Arguments.of(
              List.of(),
              BuildingFilter.CAMPUS_LOCATION_FILTER,
              testBuildings,
              NO_CAMPUS_LOCATIONS));
    }
  }
}
