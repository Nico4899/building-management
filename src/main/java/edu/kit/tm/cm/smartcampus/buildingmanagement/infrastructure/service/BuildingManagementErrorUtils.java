package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;

import java.util.Collection;
import java.util.List;

// TODO ultra hässliche klasse, muss man noch aufräumen, tut aber ihren job

public final class BuildingManagementErrorUtils {

  private static final String BIN_PATTERN = "b-\\d+";
  private static final String RIN_PATTERN = "r-\\d+";
  private static final String CIN_PATTERN = "c-\\d+";

  private BuildingManagementErrorUtils() {}

  public static void checkForError(Room room, boolean newRoom) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean errorOccurred = false;
    if (!room.getIdentificationNumber().matches(RIN_PATTERN) && !newRoom) {
      errorOccurred = true;
      invalidArgumentsException.appendWrongArguments(
          "identitfication_number",
          room.getIdentificationNumber(),
          "should match: " + BIN_PATTERN,
          true);
    }
    if (!room.getIdentificationNumber().isBlank() && newRoom) {
      errorOccurred = true;
      invalidArgumentsException.appendWrongArguments(
          "identification_number", room.getIdentificationNumber(), "should be blank", true);
    }
    if (room.getRoomType().ordinal() == 0) {
      invalidArgumentsException.appendWrongArguments(
          "room_type", room.getRoomType().name(), "invalid type", true);
    }
    if (room.getGeographicalLocation().getLatitude() > 90
        || room.getGeographicalLocation().getLongitude() < -90
        || room.getGeographicalLocation().getLongitude() > 180
        || room.getGeographicalLocation().getLongitude() < -180) {
      errorOccurred = true;
      invalidArgumentsException.appendWrongArguments(
          "geographical_location", "", "out of bounds", true);
    }
    if (!room.getParentIdentificationNumber().matches(BIN_PATTERN)) {
      {
        errorOccurred = true;
        invalidArgumentsException.appendWrongArguments(
            "identification_number",
            room.getIdentificationNumber(),
            "should match: " + BIN_PATTERN,
            true);
      }
    }
    if (errorOccurred) {
      throw invalidArgumentsException;
    }
  }

  public static void checkForError(Component component, boolean newComponent) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean errorOccurred = false;
    if (!component.getIdentificationNumber().matches(CIN_PATTERN) && !newComponent) {
      errorOccurred = true;
      invalidArgumentsException.appendWrongArguments(
          "identitfication_number",
          component.getIdentificationNumber(),
          "should match: " + CIN_PATTERN,
          true);
    }
    if (!component.getIdentificationNumber().isBlank() && newComponent) {
      errorOccurred = true;
      invalidArgumentsException.appendWrongArguments(
          "identification_number", component.getIdentificationNumber(), "should be blank", true);
    }
    if (component.getComponentType().ordinal() == 0) {
      invalidArgumentsException.appendWrongArguments(
          "component_type", component.getComponentType().name(), "invalid type", true);
    }
    if (component.getGeographicalLocation().getLatitude() > 90
        || component.getGeographicalLocation().getLongitude() < -90
        || component.getGeographicalLocation().getLongitude() > 180
        || component.getGeographicalLocation().getLongitude() < -180) {
      errorOccurred = true;
      invalidArgumentsException.appendWrongArguments(
          "geographical_location", "", "out of bounds", true);
    }
    if (!component.getParentIdentificationNumber().matches(BIN_PATTERN)
        || !component.getParentIdentificationNumber().matches(RIN_PATTERN)) {
      {
        errorOccurred = true;
        invalidArgumentsException.appendWrongArguments(
            "identification_number",
            component.getIdentificationNumber(),
            "should match: " + BIN_PATTERN + "/ " + RIN_PATTERN,
            true);
      }
    }
    if (errorOccurred) {
      throw invalidArgumentsException;
    }
  }

  public static void checkForError(Favorite favorite) {
    checkIdentificationNumber(
        favorite.getReferenceIdentificationNumber(),
        List.of(BIN_PATTERN, RIN_PATTERN, CIN_PATTERN));
  }

  public static void checkForError(Building building, boolean newBuilding) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean errorOccurred = false;
    if (building.getCampusLocation() == null) {
      errorOccurred = true;
      invalidArgumentsException.appendWrongArguments(
          "campus_location", "", "should not be null", true);
    }
    if (!building.getIdentificationNumber().matches(BIN_PATTERN) && !newBuilding) {
      errorOccurred = true;
      invalidArgumentsException.appendWrongArguments(
          "identitfication_number",
          building.getIdentificationNumber(),
          "should match: " + BIN_PATTERN,
          true);
    }
    if (!building.getIdentificationNumber().isBlank() && newBuilding) {
      errorOccurred = true;
      invalidArgumentsException.appendWrongArguments(
          "identification_number", building.getIdentificationNumber(), "should be blank", true);
    }
    if (building.getGeographicalLocation().getLatitude() > 90
        || building.getGeographicalLocation().getLongitude() < -90
        || building.getGeographicalLocation().getLongitude() > 180
        || building.getGeographicalLocation().getLongitude() < -180) {
      errorOccurred = true;
      invalidArgumentsException.appendWrongArguments(
          "geographical_location", "", "out of bounds", true);
    }
    if (building.getCampusLocation().ordinal() == 0) {
      invalidArgumentsException.appendWrongArguments(
          "campus_location", building.getCampusLocation().name(), "invalid type", true);
    }
    if (building.getNumFloors() < 0) {
      invalidArgumentsException.appendWrongArguments(
          "num_floors", String.valueOf(building.getNumFloors()), "to few floors", true);
    }
    if (errorOccurred) {
      throw invalidArgumentsException;
    }
  }

  public static void checkIdentificationNumber(
      String identificationNumber, Collection<String> patterns) {
    boolean matched = false;
    for (String pattern : patterns) {
      if (identificationNumber.matches(pattern)) {
        matched = true;
        break;
      }
    }
    if (!matched) {
      StringBuilder stringBuilder = new StringBuilder();
      String prefix = "";
      for (String pattern : patterns) {
        stringBuilder.append(prefix);
        prefix = "/ ";
        stringBuilder.append(pattern);
      }
      throw new InvalidArgumentsException(
          "identification_number", identificationNumber, "should match: " + stringBuilder, true);
    }
  }
}
