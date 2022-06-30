package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.utils;

/**
 * This interface provides a definition of the functionality of an algorithm used in this project.
 *
 * @param <T> return value type
 * @author Bastian Bacher
 * @version 1.0
 */
public interface Algorithm<T> {
  /**
   * Compute the return values of the algorithm.
   *
   * @return outcome
   */
  T compute();
}
