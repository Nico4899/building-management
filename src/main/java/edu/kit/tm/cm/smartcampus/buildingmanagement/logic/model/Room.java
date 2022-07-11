package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents a room which can hold {@link Component} and is being held by {@link
 * Building}.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private int floor;
    private String roomName;
    private String roomNumber;
    private String identificationNumber;
    private String parentIdentificationNumber;
    private GeographicalLocation geographicalLocation;
    private RoomType roomType;

    public Room(int floor, String roomName, String roomNumber, String identificationNumber, String parentIdentificationNumber, GeographicalLocation geographicalLocation, RoomType roomType) {
        this.floor = floor;
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.identificationNumber = identificationNumber;
        this.parentIdentificationNumber = parentIdentificationNumber;
        this.geographicalLocation = geographicalLocation;
        this.roomType = roomType;
    }

    private Collection<Component> components = new ArrayList<>();
}
