package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.filters;

import java.util.Collection;

public interface Filter<S> {

    Collection<S> filter();
    String getKeyword();

    //TODO implement filters
}
