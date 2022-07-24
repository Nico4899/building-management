package edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.settings.ListSettings;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.settings.Settings;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.Sorter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.sorters.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** The type Grpc object reader. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GrpcObjectReader {

  /**
   * Read component.
   *
   * @param grpcComponent the grpc component
   * @return the component
   */
  public static Component read(GrpcComponent grpcComponent) {
    Component component = new Component();
    component.setComponentDescription(grpcComponent.getComponentDescription());
    component.setIdentificationNumber(grpcComponent.getIdentificationNumber());
    component.setParentIdentificationNumber(grpcComponent.getParentIdentificationNumber());
    component.setLatitude(grpcComponent.getLatitude());
    component.setLongitude(grpcComponent.getLongitude());
    component.setType(read(grpcComponent.getComponentType()));
    return component;
  }

  /**
   * Read favorite.
   *
   * @param grpcFavorite the grpc favorite
   * @return the favorite
   */
  public static Favorite read(GrpcFavorite grpcFavorite) {
    Favorite favorite = new Favorite();
    favorite.setOwner(grpcFavorite.getOwner());
    favorite.setReferenceIdentificationNumber(grpcFavorite.getReferenceIdentificationNumber());
    return favorite;
  }

  /**
   * Read building.
   *
   * @param grpcBuilding the grpc building
   * @return the building
   */
  public static Building read(GrpcBuilding grpcBuilding) {
    Building building = new Building();
    building.setBuildingNumber(grpcBuilding.getBuildingNumber());
    building.setBuildingName(grpcBuilding.getBuildingName());
    building.setCampusLocation(read(grpcBuilding.getCampusLocation()));
    building.setNumFloors(grpcBuilding.getNumFloors());
    building.setIdentificationNumber(grpcBuilding.getIdentificationNumber());
    building.setLongitude(grpcBuilding.getLongitude());
    building.setLatitude(grpcBuilding.getLatitude());
    return building;
  }

  /**
   * Read room.
   *
   * @param grpcRoom the grpc room
   * @return the room
   */
  public static Room read(GrpcRoom grpcRoom) {
    Room room = new Room();
    room.setRoomNumber(grpcRoom.getRoomNumber());
    room.setRoomName(grpcRoom.getRoomName());
    room.setFloor(grpcRoom.getFloor());
    room.setParentIdentificationNumber(grpcRoom.getParentIdentificationNumber());
    room.setLatitude(grpcRoom.getLatitude());
    room.setLongitude(grpcRoom.getLongitude());
    room.setType(read(grpcRoom.getRoomType()));
    room.setIdentificationNumber(grpcRoom.getIdentificationNumber());
    return room;
  }

  /**
   * Read room type.
   *
   * @param grpcRoomType the grpc room type
   * @return the room type
   */
  public static Room.Type read(GrpcRoomType grpcRoomType) {
    return Enum.valueOf(Room.Type.class, grpcRoomType.name());
  }

  /**
   * Read campus location.
   *
   * @param grpcCampusLocation the grpc campus location
   * @return the campus location
   */
  public static Building.CampusLocation read(GrpcCampusLocation grpcCampusLocation) {
    return Enum.valueOf(Building.CampusLocation.class, grpcCampusLocation.name());
  }

  /**
   * Read component type.
   *
   * @param grpcComponentType the grpc component type
   * @return the component type
   */
  public static Component.Type read(GrpcComponentType grpcComponentType) {
    return Enum.valueOf(Component.Type.class, grpcComponentType.name());
  }

  /**
   * Read configuration.
   *
   * @param listBuildingConfiguration the grpc configuration object
   * @return model configuration
   */
  public static Settings<Building> read(ListBuildingConfiguration listBuildingConfiguration) {
    Collection<Filter<Building>> filters = new ArrayList<>();
    if (listBuildingConfiguration.getCampusLocationFilterMapping().getSelected()) {
      filters.add(
          new CampusLocationBuildingFilter(
              listBuildingConfiguration
                  .getCampusLocationFilterMapping()
                  .getCampusLocationsList()
                  .stream()
                  .map(GrpcObjectReader::read)
                  .toList()));
    }
    if (listBuildingConfiguration.getComponentTypeFilterMapping().getSelected()) {
      filters.add(
          new ComponentTypeBuildingFilter(
              listBuildingConfiguration
                  .getComponentTypeFilterMapping()
                  .getComponentTypesList()
                  .stream()
                  .map(GrpcObjectReader::read)
                  .toList()));
    }
    if (listBuildingConfiguration.getRoomTypeFilterMapping().getSelected()) {
      filters.add(
          new RoomTypeBuildingFilter(
              listBuildingConfiguration.getRoomTypeFilterMapping().getRoomTypesList().stream()
                  .map(GrpcObjectReader::read)
                  .toList()));
    }
    return new ListSettings<>(readBuildingSorter(listBuildingConfiguration.getGrpcSortOption()), filters);
  }

  /**
   * Read configuration.
   *
   * @param listRoomConfiguration the grpc configuration object
   * @return model configuration
   */
  public static Settings<Room> read(ListRoomConfiguration listRoomConfiguration) {
    Collection<Filter<Room>> filters = new ArrayList<>();
    if (listRoomConfiguration.getRoomFloorFilterMapping().getSelected()) {
      filters.add(
        new FloorRoomFilter(listRoomConfiguration.getRoomFloorFilterMapping().getFloorsList()));
    }
    if (listRoomConfiguration.getComponentTypeFilterMapping().getSelected()) {
      filters.add(
        new ComponentTypeRoomFilter(
          listRoomConfiguration
            .getComponentTypeFilterMapping()
            .getComponentTypesList()
            .stream()
            .map(GrpcObjectReader::read)
            .toList()));
    }
    if (listRoomConfiguration.getRoomTypeFilterMapping().getSelected()) {
      filters.add(
        new RoomTypeRoomFilter(
          listRoomConfiguration.getRoomTypeFilterMapping().getRoomTypesList().stream()
            .map(GrpcObjectReader::read)
            .toList()));
    }
    return new ListSettings<>(readRoomSorter(listRoomConfiguration.getGrpcSortOption()), filters);
  }

  /**
   * Read configuration.
   *
   * @param listComponentConfiguration the grpc configuration object
   * @return model configuration
   */
  public static Settings<Component> read(ListComponentConfiguration listComponentConfiguration) {
    return new ListSettings<>(readComponentSorter(listComponentConfiguration.getGrpcSortOption()), List.of());
  }

  /**
   * Read configuration.
   *
   * @param listNotificationConfiguration the grpc configuration object
   * @return model configuration
   */
  public static Settings<Notification> read(
      ListNotificationConfiguration listNotificationConfiguration) {
    return new ListSettings<>(readNotificationSorter((listNotificationConfiguration.getGrpcSortOption())), List.of());
  }

  /**
   * Read a grpc object and return a model object.
   *
   * @param grpcSortOption grpc problem sort option.
   * @return model problem sorter object
   */
  public static Sorter<Building> readBuildingSorter(GrpcSortOption grpcSortOption) {
    return switch (grpcSortOption) {
      case NAME_LEXICOGRAPHIC -> new LexicographicNameBuildingSorter();
      case NUMBER_LEXICOGRAPHIC -> new LexicographicNumberBuildingSorter();
      default -> new DefaultSorter<>();
    };
  }

  /**
   * Read a grpc object and return a model object.
   *
   * @param grpcSortOption grpc problem sort option.
   * @return model problem sorter object
   */
  public static Sorter<Room> readRoomSorter(GrpcSortOption grpcSortOption) {
    return switch (grpcSortOption) {
      case NAME_LEXICOGRAPHIC -> new LexicographicNameRoomSorter();
      case NUMBER_LEXICOGRAPHIC -> new LexicographicNumberRoomSorter();
      default -> new DefaultSorter<>();
    };
  }

  /**
   * Read a grpc object and return a model object.
   *
   * @param grpcSortOption grpc problem sort option.
   * @return model problem sorter object
   */
  public static Sorter<Component> readComponentSorter(GrpcSortOption grpcSortOption) {
    return new DefaultSorter<>();
  }

  /**
   * Read a grpc object and return a model object.
   *
   * @param grpcSortOption grpc problem sort option.
   * @return model problem sorter object
   */
  public static Sorter<Notification> readNotificationSorter(GrpcSortOption grpcSortOption) {
    return switch (grpcSortOption) {
      case ASCENDING_TIME_STAMP -> new AscendingTimeStampNotificationSorter();
      case DESCENDING_TIME_STAMP -> new DescendingTimeStampNotificationSorter();
      default -> new DefaultSorter<>();
    };
  }
}
