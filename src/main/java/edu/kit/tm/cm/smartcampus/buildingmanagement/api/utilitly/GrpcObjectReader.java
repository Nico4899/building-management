package edu.kit.tm.cm.smartcampus.buildingmanagement.api.utilitly;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.configuration.Configuration;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.configuration.ListConfiguration;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.Filter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.filters.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.Sorter;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.sorter.sorters.*;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** The type Grpc object reader. */
@AllArgsConstructor
@org.springframework.stereotype.Component
public class GrpcObjectReader {

  /**
   * Read component.
   *
   * @param grpcComponent the grpc component
   * @return the component
   */
  public Component read(GrpcComponent grpcComponent) {
    return Component.builder()
        .componentDescription(grpcComponent.getComponentDescription())
        .componentType(read(grpcComponent.getComponentType()))
        .geographicalLocation(read(grpcComponent.getGeographicalLocation()))
        .parentIdentificationNumber(grpcComponent.getParentIdentificationNumber())
        .build();
  }

  /**
   * Read favorite.
   *
   * @param grpcFavorite the grpc favorite
   * @return the favorite
   */
  public Favorite read(GrpcFavorite grpcFavorite) {
    return Favorite.builder()
        .referenceIdentificationNumber(grpcFavorite.getReferenceIdentificationNumber())
        .owner(grpcFavorite.getOwner())
        .build();
  }

  /**
   * Read building.
   *
   * @param grpcBuilding the grpc building
   * @return the building
   */
  public Building read(GrpcBuilding grpcBuilding) {
    return Building.builder()
        .buildingName(grpcBuilding.getBuildingName())
        .buildingNumber(grpcBuilding.getBuildingNumber())
        .geographicalLocation(read(grpcBuilding.getGeographicalLocation()))
        .campusLocation(read(grpcBuilding.getCampusLocation()))
        .numFloors(grpcBuilding.getNumFloors())
        .build();
  }

  /**
   * Read room.
   *
   * @param grpcRoom the grpc room
   * @return the room
   */
  public Room read(GrpcRoom grpcRoom) {
    return Room.builder()
        .roomName(grpcRoom.getRoomName())
        .roomType(read(grpcRoom.getRoomType()))
        .roomNumber(grpcRoom.getRoomNumber())
        .floor(grpcRoom.getFloor())
        .parentIdentificationNumber(grpcRoom.getParentIdentificationNumber())
        .geographicalLocation(read(grpcRoom.getGeographicalLocation()))
        .build();
  }

  /**
   * Read room type.
   *
   * @param grpcRoomType the grpc room type
   * @return the room type
   */
  public RoomType read(GrpcRoomType grpcRoomType) {
    return RoomType.forNumber(grpcRoomType.ordinal() + 1);
  }

  /**
   * Read campus location.
   *
   * @param grpcCampusLocation the grpc campus location
   * @return the campus location
   */
  public CampusLocation read(GrpcCampusLocation grpcCampusLocation) {
    return CampusLocation.forNumber(grpcCampusLocation.ordinal() + 1);
  }

  /**
   * Read geographical location.
   *
   * @param grpcGeographicalLocation the grpc geographical location
   * @return the geographical location
   */
  public GeographicalLocation read(GrpcGeographicalLocation grpcGeographicalLocation) {
    return GeographicalLocation.builder()
        .longitude(grpcGeographicalLocation.getLongitude())
        .latitude(grpcGeographicalLocation.getLatitude())
        .build();
  }

  /**
   * Read component type.
   *
   * @param grpcComponentType the grpc component type
   * @return the component type
   */
  public ComponentType read(GrpcComponentType grpcComponentType) {
    return ComponentType.forNumber(grpcComponentType.ordinal() + 1);
  }

  /**
   * Read configuration.
   *
   * @param listBuildingConfiguration the grpc configuration object
   * @return model configuration
   */
  public Configuration<Building> read(ListBuildingConfiguration listBuildingConfiguration) {
    Collection<Filter<Building>> filters = new ArrayList<>();
    if (listBuildingConfiguration.getCampusLocationFilterMapping().getSelected()) {
      filters.add(
          new CampusLocationBuildingFilter(
              listBuildingConfiguration
                  .getCampusLocationFilterMapping()
                  .getCampusLocationsList()
                  .stream()
                  .map(this::read)
                  .toList()));
    }
    if (listBuildingConfiguration.getComponentTypeFilterMapping().getSelected()) {
      filters.add(
          new ComponentTypeBuildingFilter(
              listBuildingConfiguration
                  .getComponentTypeFilterMapping()
                  .getComponentTypesList()
                  .stream()
                  .map(this::read)
                  .toList()));
    }
    if (listBuildingConfiguration.getRoomTypeFilterMapping().getSelected()) {
      filters.add(
          new RoomTypeBuildingFilter(
              listBuildingConfiguration.getRoomTypeFilterMapping().getRoomTypesList().stream()
                  .map(this::read)
                  .toList()));
    }
    return new ListConfiguration<>(readBuildingSorter(listBuildingConfiguration.getGrpcSortOption()), filters);
  }

  /**
   * Read configuration.
   *
   * @param listRoomConfiguration the grpc configuration object
   * @return model configuration
   */
  public Configuration<Room> read(ListRoomConfiguration listRoomConfiguration) {
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
            .map(this::read)
            .toList()));
    }
    if (listRoomConfiguration.getRoomTypeFilterMapping().getSelected()) {
      filters.add(
        new RoomTypeRoomFilter(
          listRoomConfiguration.getRoomTypeFilterMapping().getRoomTypesList().stream()
            .map(this::read)
            .toList()));
    }
    return new ListConfiguration<>(readRoomSorter(listRoomConfiguration.getGrpcSortOption()), filters);
  }

  /**
   * Read configuration.
   *
   * @param listComponentConfiguration the grpc configuration object
   * @return model configuration
   */
  public Configuration<Component> read(ListComponentConfiguration listComponentConfiguration) {
    return new ListConfiguration<>(readComponentSorter(listComponentConfiguration.getGrpcSortOption()), List.of());
  }

  /**
   * Read configuration.
   *
   * @param listNotificationConfiguration the grpc configuration object
   * @return model configuration
   */
  public Configuration<Notification> read(
      ListNotificationConfiguration listNotificationConfiguration) {
    return new ListConfiguration<>(readNotificationSorter((listNotificationConfiguration.getGrpcSortOption())), List.of());
  }

  /**
   * Read a grpc object and return a model object.
   *
   * @param grpcSortOption grpc problem sort option.
   * @return model problem sorter object
   */
  public Sorter<Building> readBuildingSorter(GrpcSortOption grpcSortOption) {
    return switch (grpcSortOption) {
      case NAME_LEXICOGRAPHIC -> new LexicographicNameBuildingSorter();
      case NUMBER_LEXICOGRAPHIC -> new LexicographicNumberBuildingSorter();
      case CAMPUS_LOCATION -> new CampusLocationBuildingSorter();
      default -> new DefaultSorter<>();
    };
  }

  /**
   * Read a grpc object and return a model object.
   *
   * @param grpcSortOption grpc problem sort option.
   * @return model problem sorter object
   */
  public Sorter<Room> readRoomSorter(GrpcSortOption grpcSortOption) {
    return switch (grpcSortOption) {
      case NAME_LEXICOGRAPHIC -> new LexicographicNameRoomSorter();
      case NUMBER_LEXICOGRAPHIC -> new LexicographicNumberRoomSorter();
      case ROOM_TYPE -> new RoomTypeRoomSorter();
      default -> new DefaultSorter<>();
    };
  }

  /**
   * Read a grpc object and return a model object.
   *
   * @param grpcSortOption grpc problem sort option.
   * @return model problem sorter object
   */
  public Sorter<Component> readComponentSorter(GrpcSortOption grpcSortOption) {
    return switch (grpcSortOption) {
      case COMPONENT_TYPE -> new ComponentTypeComponentSorter();
      default -> new DefaultSorter<>();
    };
  }

  /**
   * Read a grpc object and return a model object.
   *
   * @param grpcSortOption grpc problem sort option.
   * @return model problem sorter object
   */
  public Sorter<Notification> readNotificationSorter(GrpcSortOption grpcSortOption) {
    return switch (grpcSortOption) {
      case ASCENDING_TIME_STAMP -> new AscendingTimeStampNotificationSorter();
      case DESCENDING_TIME_STAMP -> new DescendingTimeStampNotificationSorter();
      default -> new DefaultSorter<>();
    };
  }
}
