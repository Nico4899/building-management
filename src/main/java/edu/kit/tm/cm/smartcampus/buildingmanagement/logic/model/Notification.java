package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
  private String identificationNumber;
  private String notificationTitle;
  private String notificationDescription;
  private Timestamp creationTime;
  private String parentIdentificationNumber;
}
