package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;
import java.util.Date;

/**
 * A notification record, holding information about a certain notification.
 *
 * @param title the notification's title
 * @param description the notification's description
 * @param date the notification's creation date
 * @param identificationNumber the notification's unique identification number (format: "n-(int)")
 * @param parent the notification's parent's unique identification number (formats: "b-(int)",
 *              "c-(int)" or "r-(int)")
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public record Notification(IdentificationNumber parent, String title, String description,
                           Date date, IdentificationNumber identificationNumber){

}
