package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException {

  private static final String RESOURCE_NOT_FOUND_EXCEPTION_MESSAGE =
      "Resource to identification number: %s not found!";

  public ResourceNotFoundException() {
    super(RESOURCE_NOT_FOUND_EXCEPTION_MESSAGE);
  }
}
