package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notification;

import java.util.Collection;

/**
 * The interface Notificatable, describing an object's maintenance of notifications,
 * (e.g., @Room, @Building or @Component) .
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public interface Notificatable {

  /**
   * Add notification to a notificatable instance.
   *
   * @param notification the notification to be added
   */
  void addNotification(final Notification notification);

  /**
   * Remove notification from a notificatable instance.
   *
   * @param notification the notification to be removed
   */
  void removeNotification(final Notification notification);

  /**
   * Update notification in a notificatable instance.
   *
   * @param notification the notification to be updated
   */
  void updateNotification(final Notification notification);

  /**
   * Gets notifications.
   *
   * @return the notifications from the notificatable instance.
   */
  Collection<Notification> getNotifications();
}
