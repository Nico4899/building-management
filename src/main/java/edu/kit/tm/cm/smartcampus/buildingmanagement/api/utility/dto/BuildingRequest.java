package edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility.dto;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Building;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class BuildingRequest {

    private String name;

    private String number;

    private Building.CampusLocation campusLocation;

    private GeographicalLocation geographicalLocation;

    private Floors floors;

    private String identificationNumber;

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

    @Data
    public class Floors {
        private Long id;
        private int highestFloor;
        private int lowestFloor;

        public Floors(int highestFloor, int lowestFloor) {
            this.highestFloor = highestFloor;
            this.lowestFloor = lowestFloor;
        }
    }

    public BuildingRequest buildingToBuildingRequest(Building building) {
        BuildingRequest request = new BuildingRequest();
        request.setName(building.getBuildingName());
        request.setNumber(building.getBuildingNumber());
        request.setCampusLocation(building.getCampusLocation());
        request.setGeographicalLocation(new GeographicalLocation(building.getLatitude(), building.getLongitude()));
        request.setFloors(new Floors(building.getHighestFloor(), building.getLowestFloor()));
        return request;
    }

    public Building buildingRequestToBuilding(BuildingRequest request) {
        Building building = new Building();
        building.setLowestFloor(request.getFloors().getLowestFloor());
        building.setHighestFloor(request.getFloors().getHighestFloor());
        building.setCampusLocation(request.getCampusLocation());
        building.setBuildingName(request.getName());
        building.setBuildingNumber(request.getNumber());
        building.setIdentificationNumber(request.getIdentificationNumber());
        building.setLongitude(request.getGeographicalLocation().getLongitude());
        building.setLatitude(request.getGeographicalLocation().getLatitude());
        return building;
    }
}
