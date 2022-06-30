package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notificatable;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;
import java.util.Collection;
import java.util.List;

/**
 * This class describes an abstract Component, it holds a notification. It delivers some core
 * functionality of the BuildingManagement microservice.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public abstract class Component implements Notificatable {

  // Notification held by this component
  private Notification notification;

  // Unique identification number
  private final IdentificationNumber identificationNumber;

  // Components parent
  private final IdentificationNumber parent;

  /**
   * Instantiates component with following parameters, it implements @Notificatable.
   *
   * @param identificationNumber the unique component identification number (format: "c-(int)")
   * @param parent the unique component's parent's identification number (format: "r-(int)" or
   *     "b-(int)")
   */
  protected Component(
      final IdentificationNumber identificationNumber, IdentificationNumber parent) {
    this.identificationNumber = identificationNumber;
    this.parent = parent;
  }

  // Implemented methods from @Notificatable
  @Override
  public void addNotification(final Notification notification) {
    this.notification = notification;
  }

  @Override
  public void updateNotification(final Notification notification) {
    this.addNotification(notification);
  }

  @Override
  public void removeNotification(final Notification notification) {
    this.notification = null;
  }

  @Override
  public Collection<Notification> getNotifications() {
    return List.of(this.notification);
  }

  // Getters

  /**
   * Gets the component's identification number.
   *
   * @return identification number the component possesses
   */
  public IdentificationNumber getIdentificationNumber() {
    return identificationNumber;
  }

  /**
   * Gets the component's parent object.
   *
   * @return component's parent's unique identification number
   */
  public IdentificationNumber getParent() {
    return parent;
  }

  // Abstract methods for subclasses

  /**
   * Gets description of this component.
   *
   * @return description of this component
   */
  public abstract String getDescription();
}
