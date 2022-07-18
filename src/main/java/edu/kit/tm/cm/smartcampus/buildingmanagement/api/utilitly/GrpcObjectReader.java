package edu.kit.tm.cm.smartcampus.buildingmanagement.api.utilitly;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOption;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;

/** The type Grpc object reader. */
@org.springframework.stereotype.Component
public class GrpcObjectReader {

  /** Instantiates a new Grpc object reader. */
  public GrpcObjectReader() {}

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
   * Read filter options.
   *
   * @param grpcFilterOptions the grpc filter options
   * @return the filter options
   */
  public FilterOptions read(GrpcFilterOptions grpcFilterOptions) {
    return FilterOptions.builder()
        .campusLocationFilterOption(read(grpcFilterOptions.getCampusLocationFilterMapping()))
        .roomTypeFilterOption(read(grpcFilterOptions.getRoomTypeFilterMapping()))
        .componentTypeFilterOption(read(grpcFilterOptions.getComponentTypeFilterMapping()))
        .build();
  }

  /**
   * Read filter option.
   *
   * @param componentTypeFilterMapping the component type filter mapping
   * @return the filter option
   */
  public FilterOption<ComponentType> read(ComponentTypeFilterMapping componentTypeFilterMapping) {
    return FilterOption.<ComponentType>builder()
        .selected(componentTypeFilterMapping.getSelected())
        .filterValues(
            componentTypeFilterMapping.getComponentTypesList().stream().map(this::read).toList())
        .build();
  }

  /**
   * Read filter option.
   *
   * @param campusLocationFilterMapping the campus location filter mapping
   * @return the filter option
   */
  public FilterOption<CampusLocation> read(
      CampusLocationFilterMapping campusLocationFilterMapping) {
    return FilterOption.<CampusLocation>builder()
        .selected(campusLocationFilterMapping.getSelected())
        .filterValues(
            campusLocationFilterMapping.getCampusLocationsList().stream().map(this::read).toList())
        .build();
  }

  /**
   * Read filter option.
   *
   * @param roomTypeFilterMapping the room type filter mapping
   * @return the filter option
   */
  public FilterOption<RoomType> read(RoomTypeFilterMapping roomTypeFilterMapping) {
    return FilterOption.<RoomType>builder()
        .selected(roomTypeFilterMapping.getSelected())
        .filterValues(roomTypeFilterMapping.getRoomTypesList().stream().map(this::read).toList())
        .build();
  }
}
