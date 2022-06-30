package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings.Building;
import java.util.Collection;
import java.util.Set;

/**
 * A filter instance filtering buildings by accessibility.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public class AccessibilityFilter implements Filter<Building> {

  // Accesibility Keyword
  private static final String KEYWORD = "Building Accessibility";

  // Collection of buildings
  private final Collection<Building> buildings;

  /**
   * Instantiates a new accessibility filter.
   *
   * @param buildings the buildings to be filtered
   */
  public AccessibilityFilter(final Collection<Building> buildings) {
    this.buildings = buildings;
  }

  @Override
  public Collection<Building> filter() {
    Collection<Building> buildingsCopy = Set.copyOf(this.buildings);
    buildingsCopy.removeIf(building -> !building.isAccessible());
    return buildingsCopy;
    // TODO unmodifyable
  }

  @Override
  public String getKeyword() {
    return KEYWORD;
  }
}
