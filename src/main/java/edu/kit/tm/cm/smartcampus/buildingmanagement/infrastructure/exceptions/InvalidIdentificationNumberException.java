package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exceptions;

/**
 * Exception in case a given identification number is invalid.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public class InvalidIdentificationNumberException extends Exception {

  /**
   * Instantiates a new invalid identification number exception.
   *
   * @param message exception message
   */
  public InvalidIdentificationNumberException(String message) {
    super(message);
  }
}
