package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * This class describes a notification model object.
 *
 * @version 1.0
 * @author Bastian Bacher
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Notification {
  private String identificationNumber;
  private String title;
  private String description;
  private Timestamp creationTime;
  private String parentIdentificationNumber;
}
