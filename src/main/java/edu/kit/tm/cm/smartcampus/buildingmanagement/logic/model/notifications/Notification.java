package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

import java.util.Date;

public record Notification(String title, String description, Date date, IdentificationNumber identificationNumber){

}
