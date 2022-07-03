package edu.kit.tm.cm.smartcampus.buildingmanagement.api.controller;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.manager.BuildingManagementManager;

public class BuildingManagementController {

  private final BuildingManagementManager buildingManagementManager;

  public BuildingManagementController(BuildingManagementManager buildingManagementManager) {
    this.buildingManagementManager = buildingManagementManager;
  }


}
