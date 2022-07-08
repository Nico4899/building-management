package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception;

/** Exception thrown when resource was not found. */
public class ResourceNotFoundException extends RuntimeException {

  private static final String RESOURCE_NOT_FOUND_EXCEPTION_MESSAGE =
      "Resource not found. Maybe your request was wrong?";

  public ResourceNotFoundException() {
    super(RESOURCE_NOT_FOUND_EXCEPTION_MESSAGE);
  }
}
