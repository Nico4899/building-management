package edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility.dto;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Room;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class RoomRequest {

    private String identificationNumber;

    private String parentIdentificationNumber;

    private String name;

    private String number;

    private int floor;

    private Room.Type type;

    private GeographicalLocation geographicalLocation;

    @Data
    public class GeographicalLocation {
        private Long id;
        private double latitude;
        private double longitude;

        GeographicalLocation(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    public RoomRequest roomToRoomRequest(Room room) {
        RoomRequest request = new RoomRequest();
        request.setParentIdentificationNumber(room.getParentIdentificationNumber());
        request.setName(room.getRoomName());
        request.setNumber(room.getRoomNumber());
        request.setFloor(room.getFloor());
        request.setType(room.getType());
        request.setGeographicalLocation(new GeographicalLocation(room.getLatitude(), room.getLongitude()));
        return request;
    }

    public Room roomRequestToRoom(RoomRequest request) {
        Room room = new Room();
        room.setFloor(request.getFloor());
        room.setRoomName(request.getName());
        room.setRoomNumber(request.getNumber());
        room.setIdentificationNumber(request.getIdentificationNumber());
        room.setParentIdentificationNumber(request.getParentIdentificationNumber());
        room.setLatitude(request.getGeographicalLocation().getLatitude());
        room.setLongitude(request.geographicalLocation.getLongitude());
        room.setType(request.getType());
        return room;
    }

}