package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * This class represents a floors object, defining a {@link Building}'s highest and lowest floor, to
 * later determine if a {@link Room} has valid floor.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
@Getter
@Setter
@NoArgsConstructor
public class Floors {
  private int highestFloor;
  private int lowestFloor;
}
