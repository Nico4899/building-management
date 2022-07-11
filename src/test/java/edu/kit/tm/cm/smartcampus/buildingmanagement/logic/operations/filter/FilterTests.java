package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest
public class FilterTests {

  // test object mappings
  private static Map<String, Component> testComponents;
  private static Map<String, Room> testRooms;
  private static Map<String, Building> testBuildings;

  @BeforeAll
  public static void setUp() {
    buildTestMappings();
  }

  //@ParameterizedTest
  //@ArgumentsSource()

  private static class FilterArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
      return Stream.of(


    }

  }

  private static void buildTestMappings() {

    // build components
    testComponents.put("elevatorComponent", new Component(null, null, null, null, ComponentType.ELEVATOR));
    testComponents.put("stairsComponent", new Component(null, null, null, null, ComponentType.STAIRS));

    // build rooms
    testRooms.put("lectureRoom", new Room(0, null, null, null, null, null, RoomType.LECTURE_ROOM));
    testRooms.put("seminarRoom", new Room(0, null, null, null, null, null, RoomType.SEMINAR_ROOM));
    testRooms.put("restRoom", new Room(0, null, null, null, null, null, RoomType.RESTROOM));
    testRooms.put("restRoomHandicapped", new Room(0, null, null, null, null, null, RoomType.RESTROOM_HANDICAPPED));
    testRooms.put("office", new Room(0, null, null, null, null, null, RoomType.OFFICE));
    testRooms.put("sports", new Room(0, null, null, null, null, null, RoomType.SPORTS));
    testRooms.put("library", new Room(0, null, null, null, null, null, RoomType.LIBRARY));
    testRooms.put("cafeteria", new Room(0, null, null, null, null, null, RoomType.CAFETERIA));

    // build buildings
    testBuildings.put("eastCampusBuilding", new Building(0, CampusLocation.EAST_CAMPUS, null, null, null, null));
    testBuildings.put("westCampusBuilding", new Building(0, CampusLocation.WEST_CAMPUS, null, null, null, null));
    testBuildings.put("southCampusBuilding", new Building(0, CampusLocation.SOUTH_CAMPUS, null, null, null, null));
    testBuildings.put("northCampusBuilding", new Building(0, CampusLocation.NORTH_CAMPUS, null, null, null, null));

  }
}
