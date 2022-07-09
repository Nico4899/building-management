package edu.kit.tm.cm.smartcampus.buildingmanagement.api.utilitly;

import edu.kit.tm.cm.proto.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.*;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOption;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filter.options.FilterOptions;

public final class GrpcObjectReader {

  private GrpcObjectReader() {}

  public static Component read(GrpcComponent grpcComponent) {
    return Component.builder()
        .componentDescription(grpcComponent.getComponentDescription())
        .componentType(read(grpcComponent.getComponentType()))
        .geographicalLocation(read(grpcComponent.getGeographicalLocation()))
        .parentIdentificationNumber(grpcComponent.getParentIdentificationNumber())
        .build();
  }

  public static Favorite read(GrpcFavorite grpcFavorite) {
    return Favorite.builder()
        .referenceIdentificationNumber(grpcFavorite.getReferenceIdentificationNumber())
        .owner(grpcFavorite.getOwner())
        .build();
  }

  public static Building read(GrpcBuilding grpcBuilding) {
    return Building.builder()
        .buildingName(grpcBuilding.getBuildingName())
        .buildingNumber(grpcBuilding.getBuildingNumber())
        .geographicalLocation(read(grpcBuilding.getGeographicalLocation()))
        .campusLocation(read(grpcBuilding.getCampusLocation()))
        .numFloors(grpcBuilding.getNumFloors())
        .build();
  }

  public static Room read(GrpcRoom grpcRoom) {
    return Room.builder()
        .roomName(grpcRoom.getRoomName())
        .roomType(read(grpcRoom.getRoomType()))
        .roomNumber(grpcRoom.getRoomNumber())
        .floor(grpcRoom.getFloor())
        .parentIdentificationNumber(grpcRoom.getParentIdentificationNumber())
        .geographicalLocation(read(grpcRoom.getGeographicalLocation()))
        .build();
  }

  public static RoomType read(GrpcRoomType grpcRoomType) {
    return RoomType.forNumber(grpcRoomType.ordinal() + 1);
  }

  public static CampusLocation read(GrpcCampusLocation grpcCampusLocation) {
    return CampusLocation.forNumber(grpcCampusLocation.ordinal() + 1);
  }

  public static GeographicalLocation read(GrpcGeographicalLocation grpcGeographicalLocation) {
    return GeographicalLocation.builder()
        .longitude(grpcGeographicalLocation.getLongitude())
        .latitude(grpcGeographicalLocation.getLatitude())
        .build();
  }

  public static ComponentType read(GrpcComponentType grpcComponentType) {
    return ComponentType.forNumber(grpcComponentType.ordinal() + 1);
  }

  public static FilterOptions read(GrpcFilterOptions grpcFilterOptions) {
    return FilterOptions.builder()
        .campusLocationFilterOption(read(grpcFilterOptions.getCampusLocationFilterMapping()))
        .roomTypeFilterOption(read(grpcFilterOptions.getRoomTypeFilterMapping()))
        .componentTypeFilterOption(read(grpcFilterOptions.getComponentTypeFilterMapping()))
        .build();
  }

  public static FilterOption<ComponentType> read(
      ComponentTypeFilterMapping componentTypeFilterMapping) {
    return FilterOption.<ComponentType>builder()
        .selected(componentTypeFilterMapping.getSelected())
        .filterValues(
            componentTypeFilterMapping.getComponentTypesList().stream()
                .map(GrpcObjectReader::read)
                .toList())
        .build();
  }

  public static FilterOption<CampusLocation> read(
      CampusLocationFilterMapping campusLocationFilterMapping) {
    return FilterOption.<CampusLocation>builder()
        .selected(campusLocationFilterMapping.getSelected())
        .filterValues(
            campusLocationFilterMapping.getCampusLocationsList().stream()
                .map(GrpcObjectReader::read)
                .toList())
        .build();
  }

  public static FilterOption<RoomType> read(RoomTypeFilterMapping roomTypeFilterMapping) {
    return FilterOption.<RoomType>builder()
        .selected(roomTypeFilterMapping.getSelected())
        .filterValues(
            roomTypeFilterMapping.getRoomTypesList().stream().map(GrpcObjectReader::read).toList())
        .build();
  }
}
