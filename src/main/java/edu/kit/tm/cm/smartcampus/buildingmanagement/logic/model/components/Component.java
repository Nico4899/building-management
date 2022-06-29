package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notificatable;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notification;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils.IdentificationNumber;

import java.util.Collection;
import java.util.List;

public abstract class Component implements Notificatable {

    private Notification notification;

    private final IdentificationNumber identificationNumber;

    protected Component(final IdentificationNumber identificationNumber) {
        this.identificationNumber = identificationNumber;
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
    public IdentificationNumber getIdentificationNumber() {
        return identificationNumber;
    }

    // Abstract methods for subclasses
    public abstract String getDescription();
}
