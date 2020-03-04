package com.portfolio.project.google.mapper;

import com.portfolio.project.google.domain.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GoogleMapper {

    public GoogleDirections mapToDirections(GoogleDirectionsDto googleDirectionsDto) {
        return new GoogleDirections(
                googleDirectionsDto.getGeocodedWaypoints().stream()
                        .map(googleGeocodedWaypoints -> mapToGoogleGeocodedWaypoints(googleGeocodedWaypoints))
                        .collect(Collectors.toList()),
                googleDirectionsDto.getRoutes().stream()
                        .map(googleRoutes -> mapToGoogleRoutes(googleRoutes))
                        .collect(Collectors.toList()),
                googleDirectionsDto.getStatus()
                );
    }

    public GoogleRoutes mapToGoogleRoutes(GoogleRoutesDto googleRoutesDto) {
        return new GoogleRoutes(
                googleRoutesDto.getCopyrights(),
                googleRoutesDto.getLegs().stream()
                        .map(googleLegs -> mapToGoogleLegs(googleLegs))
                        .collect(Collectors.toList())
        );
    }

    public GoogleLegs mapToGoogleLegs(GoogleLegsDto googleLegsDto) {
        return new GoogleLegs(
                mapToGoogleDistance(googleLegsDto.getDistance()),
                mapToGoogleDuration(googleLegsDto.getDuration()),
                googleLegsDto.getEndAddress(),
                mapToGoogleLocation(googleLegsDto.getEndLocation()),
                googleLegsDto.getStartAddress(),
                mapToGoogleLocation(googleLegsDto.getStartLocation()),
                googleLegsDto.getSteps().stream()
                        .map(googleSteps -> mapToGoogleSteps(googleSteps))
                        .collect(Collectors.toList())
        );
    }

    public GoogleSteps mapToGoogleSteps(GoogleStepsDto googleStepsDto) {
        return new GoogleSteps(
                mapToGoogleDistance(googleStepsDto.getDistance()),
                mapToGoogleDuration(googleStepsDto.getDuration()),
                mapToGoogleLocation(googleStepsDto.getEndLocation()),
                mapToGoogleLocation(googleStepsDto.getStartLocation()),
                mapToGooglePolyline(googleStepsDto.getGooglePolyline())
        );
    }

    public GoogleStepsDto mapToGoogleStepsDto(GoogleSteps googleSteps) {
        return new GoogleStepsDto(
                mapToGoogleDistanceDto(googleSteps.getDistance()),
                mapToGoogleDurationDto(googleSteps.getDuration()),
                mapToGoogleLocationDto(googleSteps.getEndLocation()),
                mapToGoogleLocationDto(googleSteps.getStartLocation()),
                mapToGooglePolylineDto(googleSteps.getGooglePolyline())
        );
    }

    public GoogleDuration mapToGoogleDuration(GoogleDurationDto googleDurationDto) {
        return new GoogleDuration(googleDurationDto.getText(), googleDurationDto.getValue());
    }

    public GoogleDurationDto mapToGoogleDurationDto(GoogleDuration googleDuration) {
        return new GoogleDurationDto(googleDuration.getText(), googleDuration.getValue());
    }

    public GoogleGeocodedWaypoints mapToGoogleGeocodedWaypoints(GoogleGeocodedWaypointsDto googleGeocodedWaypointsDto) {
        return new GoogleGeocodedWaypoints(googleGeocodedWaypointsDto.getGeocoderStatus(), googleGeocodedWaypointsDto.getPlaceId());
    }

    public GoogleGeocodedWaypointsDto mapToGoogleGeocodedWaypointsDto(GoogleGeocodedWaypoints googleGeocodedWaypoints) {
        return new GoogleGeocodedWaypointsDto(googleGeocodedWaypoints.getGeocoderStatus(), googleGeocodedWaypoints.getPlaceId());
    }

    public GoogleLocation mapToGoogleLocation(GoogleLocationDto googleLocationDto) {
        return new GoogleLocation(googleLocationDto.getLat(), googleLocationDto.getLng());
    }

    public GoogleLocationDto mapToGoogleLocationDto(GoogleLocation googleLocation) {
        return new GoogleLocationDto(googleLocation.getLat(), googleLocation.getLng());
    }

    public GooglePolyline mapToGooglePolyline(GooglePolylineDto googlePolylineDTO) {
        return new GooglePolyline(googlePolylineDTO.getPoints());
    }

    public GooglePolylineDto mapToGooglePolylineDto(GooglePolyline googlePolyline) {
        return new GooglePolylineDto(googlePolyline.getPoints());
    }

    public GoogleDistance mapToGoogleDistance(GoogleDistanceDto googleDistanceDto) {
        return new GoogleDistance(googleDistanceDto.getText(), googleDistanceDto.getValue());
    }

    public GoogleDistanceDto mapToGoogleDistanceDto(GoogleDistance googleDistance) {
        return new GoogleDistanceDto(googleDistance.getText(), googleDistance.getValue());
    }
}
