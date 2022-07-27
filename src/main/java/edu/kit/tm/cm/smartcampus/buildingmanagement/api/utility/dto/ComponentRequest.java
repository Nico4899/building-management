package edu.kit.tm.cm.smartcampus.buildingmanagement.api.utility.dto;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Component;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@org.springframework.stereotype.Component
public class ComponentRequest {

    private String identificationNumber;

    private String parentIdentificationNumber;

    private Component.Type type;

    private String description;

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

    public ComponentRequest componentToComponentRequest(Component component) {
        ComponentRequest request = new ComponentRequest();
        request.setParentIdentificationNumber(component.getParentIdentificationNumber());
        request.setType(component.getType());
        request.setDescription(component.getComponentDescription());
        request.setGeographicalLocation(new GeographicalLocation(component.getLatitude(), component.getLongitude()));
        return request;
    }

    public Component componentRequestToComponent(ComponentRequest request) {
        Component component = new Component();
        component.setComponentDescription(request.getDescription());
        component.setIdentificationNumber(request.getIdentificationNumber());
        component.setLatitude(request.getGeographicalLocation().getLatitude());
        component.setLongitude(request.getGeographicalLocation().getLongitude());
        component.setParentIdentificationNumber(request.getParentIdentificationNumber());
        component.setType(request.getType());
        return component;
    }
}
