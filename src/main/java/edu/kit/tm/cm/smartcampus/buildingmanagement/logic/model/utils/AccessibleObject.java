package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notificatable;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notification;
import java.util.Collection;
import java.util.HashSet;

/**
 * An accessible object acts as superclass for @Building and @Room, as it collects their redundant
 * actions. It implements @Notificatable.
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public abstract class AccessibleObject implements Notificatable {

  // Name and AccessibleObjectNumber of the accessible object
  private String name;
  private String accessibleObjectNumber;

  // Components and Notifications
  private final Collection<Component> components;
  private final Collection<Notification> notifications;

  // Geographical location and identification number
  private final GeographicalLocation geographicalLocation;
  private final IdentificationNumber identificationNumber;

  /**
   * Instantiates an accessible object with the following parameters.
   *
   * @param identificationNumber and accessible object's unique identification number (formats:
   *     "r-(int)" or "b-(int)")
   * @param geographicalLocation the geographical location of the accessible object
   */
  protected AccessibleObject(
      final IdentificationNumber identificationNumber,
      final GeographicalLocation geographicalLocation) {
    this.components = new HashSet<>();
    this.notifications = new HashSet<>();
    this.identificationNumber = identificationNumber;
    this.geographicalLocation = geographicalLocation;
  }

  /**
   * Add component to accessible object.
   *
   * @param component the component to be added
   */
  // Component maintenance methods
  public void addComponent(final Component component) {
    this.components.add(component);
  }

  /**
   * Update component in accessible object.
   *
   * @param component the component to be updated
   */
  public void updateComponent(final Component component) {
    this.removeComponent(component);
    this.addComponent(component);
  }

  /**
   * Remove component from accessible object.
   *
   * @param component the component to be removed
   */
  public void removeComponent(final Component component) {
    this.components.remove(component);
  }

  /**
   * Gets components from accessible object.
   *
   * @return the components the accessible object possesses
   */
  public Collection<Component> getComponents() {
    return this.components;
  }

  /**
   * Sets name of the accessible object.
   *
   * @param name the name to be set
   */
  // Setters
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Sets accessible object number of the accessible object.
   *
   * @param accessibleObjectNumber the accessible object number to be set
   */
  public void setAccessibleObjectNumber(final String accessibleObjectNumber) {
    this.accessibleObjectNumber = accessibleObjectNumber;
  }

  // Getters

  /**
   * Gets the accessible object's name.
   *
   * @return the name of the accessible object
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets accessible object number.
   *
   * @return the accessible object number
   */
  public String getAccessibleObjectNumber() {
    return this.accessibleObjectNumber;
  }

  /**
   * Gets geographical location.
   *
   * @return the geographical location
   */
  public GeographicalLocation getGeographicalLocation() {
    return geographicalLocation;
  }

  /**
   * Gets the accessible object's identification number.
   *
   * @return the identification number of the accessible object
   */
  public IdentificationNumber getIdentificationNumber() {
    return identificationNumber;
  }

  // Implemented methods from @Notificatable
  @Override
  public void addNotification(final Notification notification) {
    this.notifications.add(notification);
  }

  @Override
  public void updateNotification(final Notification notification) {
    this.addNotification(notification);
    this.removeNotification(notification);
  }

  @Override
  public void removeNotification(final Notification notification) {
    this.notifications.remove(notification);
  }

  @Override
  public Collection<Notification> getNotifications() {
    return this.notifications;
  }

  // Inherited methods

  /**
   * Is accessible boolean. Describes if this accessible object fulfills the accessibility
   * restrictions.
   *
   * @return is accessible boolean
   */
  public abstract boolean isAccessible();
}
