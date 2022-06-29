package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filters;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.buildings.Building;

import java.util.Collection;
import java.util.Set;

public class AccessibilityFilter implements Filter<Building> {

    private static final String KEYWORD = "Accessibility";

    private final Collection<Building> buildings;

    public AccessibilityFilter(final Collection<Building> buildings) {
        this.buildings = buildings;
    }

    @Override
    public Collection<Building> filter() {
        Collection<Building> buildingsCopy = Set.copyOf(this.buildings);
        buildingsCopy.removeIf(building -> !building.isAccessible());
        return buildingsCopy;
        //TODO unmodifyable
    }

    @Override
    public String getKeyword() {
        return KEYWORD;
    }
}
