package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions;

/**
 * This exception is thrown whenever a requested resource doesn't exist, it provides a proper error
 * message.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
public class ResourceNotFoundException extends RuntimeException {

  public static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource %s does not exist.";

  /**
   * Constructs a {@link ResourceNotFoundException}.
   *
   * @param message error message
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
