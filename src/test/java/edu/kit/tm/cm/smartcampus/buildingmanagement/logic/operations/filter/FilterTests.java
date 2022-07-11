package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.*;
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

  private static final Collection<RoomType> ALL_ROOM_TYPES = List.of(RoomType.values());
  private static final Collection<RoomType> SOME_ROOM_TYPES =
      List.of(RoomType.OFFICE, RoomType.LIBRARY, RoomType.RESTROOM);
  private static final Collection<RoomType> NO_ROOM_TYPES = List.of();

  private static final Collection<ComponentType> ALL_COMPONENT_TYPES =
      List.of(ComponentType.values());
  private static final Collection<ComponentType> SOME_COMPONENT_TYPES =
      List.of(ComponentType.STAIRS);
  private static final Collection<ComponentType> NO_COMPONENT_TYPES = List.of();

  private static final Collection<CampusLocation> ALL_CAMPUS_LOCATIONS =
      List.of(CampusLocation.values());
  private static final Collection<CampusLocation> SOME_CAMPUS_LOCATIONS =
      List.of(CampusLocation.EAST_CAMPUS, CampusLocation.NORTH_CAMPUS);
  private static final Collection<CampusLocation> NO_CAMPUS_LOCATIONS = List.of();

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

    // build components
    testComponentsMap.put(
        ELEVATOR_COMPONENT, new Component(null, null, null, null, ComponentType.ELEVATOR));
    testComponentsMap.put(
        STAIRS_COMPONENT, new Component(null, null, null, null, ComponentType.STAIRS));

    // build rooms
    testRoomsMap.put(
        LECTURE_ROOM, new Room(0, null, null, null, null, null, RoomType.LECTURE_ROOM));
    testRoomsMap.put(
        SEMINAR_ROOM, new Room(0, null, null, null, null, null, RoomType.SEMINAR_ROOM));
    testRoomsMap.put(REST_ROOM, new Room(0, null, null, null, null, null, RoomType.RESTROOM));
    testRoomsMap.put(
        REST_ROOM_HANDICAPPED,
        new Room(0, null, null, null, null, null, RoomType.RESTROOM_HANDICAPPED));
    testRoomsMap.put(OFFICE, new Room(0, null, null, null, null, null, RoomType.OFFICE));
    testRoomsMap.put(SPORTS, new Room(0, null, null, null, null, null, RoomType.SPORTS));
    testRoomsMap.put(LIBRARY, new Room(0, null, null, null, null, null, RoomType.LIBRARY));
    testRoomsMap.put(CAFETERIA, new Room(0, null, null, null, null, null, RoomType.CAFETERIA));

    testRoomsMap.get(LECTURE_ROOM).getComponents().add(testComponentsMap.get(ELEVATOR_COMPONENT));
    testRoomsMap.get(SEMINAR_ROOM).getComponents().add(testComponentsMap.get(ELEVATOR_COMPONENT));
    testRoomsMap.get(REST_ROOM).getComponents().add(testComponentsMap.get(ELEVATOR_COMPONENT));
    testRoomsMap.get(SPORTS).getComponents().add(testComponentsMap.get(STAIRS_COMPONENT));
    testRoomsMap.get(LIBRARY).getComponents().add(testComponentsMap.get(STAIRS_COMPONENT));

    // build buildings
    testBuildingsMap.put(
        EAST_CAMPUS_BUILDING, new Building(0, CampusLocation.EAST_CAMPUS, null, null, null, null));
    testBuildingsMap.put(
        WEST_CAMPUS_BUILDING, new Building(0, CampusLocation.WEST_CAMPUS, null, null, null, null));
    testBuildingsMap.put(
        SOUTH_CAMPUS_BUILDING,
        new Building(0, CampusLocation.SOUTH_CAMPUS, null, null, null, null));
    testBuildingsMap.put(
        NORTH_CAMPUS_BUILDING,
        new Building(0, CampusLocation.NORTH_CAMPUS, null, null, null, null));

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
  public void filterBuildingCollectionAndValuesResultTest(
      Collection<Building> expected, Filter<Building> filter, Collection<Building> collection) {
    Assertions.assertTrue(expected.containsAll(filter.filter(collection)));
  }

  @ParameterizedTest
  @ArgumentsSource(RoomFilterArgumentsProvider.class)
  public void filterRoomCollectionAndValuesResultTest(
      Collection<Room> expected, Filter<Room> filter, Collection<Room> collection) {
    Assertions.assertTrue(expected.containsAll(filter.filter(collection)));
  }

  private static class RoomFilterArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
      return Stream.of(
          Arguments.of(testRooms, new RRTFilter(ALL_ROOM_TYPES), testRooms),
          Arguments.of(List.of(), new RRTFilter(NO_ROOM_TYPES), testRooms),
          Arguments.of(
              List.of(
                  testRoomsMap.get(OFFICE), testRoomsMap.get(LIBRARY), testRoomsMap.get(REST_ROOM)),
              new RRTFilter(SOME_ROOM_TYPES),
              testRooms),
          Arguments.of(
              List.of(
                  testRoomsMap.get(LECTURE_ROOM),
                  testRoomsMap.get(SEMINAR_ROOM),
                  testRoomsMap.get(REST_ROOM),
                  testRoomsMap.get(SPORTS),
                  testRoomsMap.get(LIBRARY)),
              new RCTFilter(ALL_COMPONENT_TYPES),
              testRooms),
          Arguments.of(List.of(), new RCTFilter(NO_COMPONENT_TYPES), testRooms),
          Arguments.of(
              List.of(testRoomsMap.get(LIBRARY), testRoomsMap.get(SPORTS)),
              new RCTFilter(SOME_COMPONENT_TYPES),
              testRooms));
    }
  }

  private static class BuildingFilterArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
      return Stream.of(
          Arguments.of(testBuildings, new BRTFilter(ALL_ROOM_TYPES), testBuildings),
          Arguments.of(List.of(), new BRTFilter(NO_ROOM_TYPES), testBuildings),
          Arguments.of(
              List.of(
                  testBuildingsMap.get(WEST_CAMPUS_BUILDING),
                  testBuildingsMap.get(NORTH_CAMPUS_BUILDING),
                  testBuildingsMap.get(SOUTH_CAMPUS_BUILDING)),
              new BRTFilter(SOME_ROOM_TYPES),
              testBuildings),
          Arguments.of(
              List.of(
                  testBuildingsMap.get(WEST_CAMPUS_BUILDING),
                  testBuildingsMap.get(SOUTH_CAMPUS_BUILDING)),
              new BCTFilter(ALL_COMPONENT_TYPES),
              testBuildings),
          Arguments.of(List.of(), new BCTFilter(NO_COMPONENT_TYPES), testBuildings),
          Arguments.of(
              List.of(testBuildingsMap.get(WEST_CAMPUS_BUILDING)),
              new BCTFilter(SOME_COMPONENT_TYPES),
              testBuildings),
          Arguments.of(
              List.of(
                  testBuildingsMap.get(EAST_CAMPUS_BUILDING),
                  testBuildingsMap.get(NORTH_CAMPUS_BUILDING)),
              new CLFilter(SOME_CAMPUS_LOCATIONS),
              testBuildings),
          Arguments.of(testBuildings, new CLFilter(ALL_CAMPUS_LOCATIONS), testBuildings),
          Arguments.of(List.of(), new CLFilter(NO_CAMPUS_LOCATIONS), testBuildings));
    }
  }
}
