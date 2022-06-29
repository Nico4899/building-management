package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.components.Component;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notificatable;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.notifications.Notification;

import java.util.Collection;
import java.util.HashSet;

public abstract class AccessibleObject implements Notificatable {

    private String name;
    private String aoNumber;

    private final Collection<Component> components;
    private final Collection<Notification> notifications;

    private final GeographicalLocation geographicalLocation;
    private final IdentificationNumber identificationNumber;


    protected AccessibleObject(final IdentificationNumber identificationNumber, final GeographicalLocation geographicalLocation) {
        this.components = new HashSet<>();
        this.notifications = new HashSet<>();
        this.identificationNumber = identificationNumber;
        this.geographicalLocation = geographicalLocation;
    }

    // Component maintenance methods
    public void addComponent(final Component component) {
        this.components.add(component);
    }

    public void updateComponent(final Component component) {
        this.removeComponent(component);
        this.addComponent(component);
    }

    public void removeComponent(final Component component) {
        this.components.remove(component);
    }

    public Collection<Component> getComponents() {
        return this.components;
    }

    // Setters
    public void setName(final String name) {
        this.name = name;
    }

    public void setAoNumber(final String aoNumber) {
        this.aoNumber = aoNumber;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public String getAoNumber() {
        return this.aoNumber;
    }

    public GeographicalLocation getGeographicalLocation() {
        return geographicalLocation;
    }

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
    public abstract boolean isAccessible();
}
