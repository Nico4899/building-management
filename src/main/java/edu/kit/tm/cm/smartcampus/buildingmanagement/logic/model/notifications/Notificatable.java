package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications;

import java.util.Collection;

public interface Notificatable {

    void addNotification(final Notification notification);
    void removeNotification(final Notification notification);
    void updateNotification(final Notification notification);
    Collection<Notification> getNotifications();

}
