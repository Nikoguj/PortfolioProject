package com.portfolio.project.service;

import com.portfolio.project.component.PolylineDecoder;
import com.portfolio.project.domain.user.SessionKey;
import com.portfolio.project.domain.user.Users;
import com.portfolio.project.domain.weather.Point;
import com.portfolio.project.domain.weather.Points;
import com.portfolio.project.exception.UserNotFoundException;
import com.portfolio.project.google.domain.*;
import com.portfolio.project.google.mapper.GoogleMapper;
import com.portfolio.project.google.service.GoogleService;
import com.portfolio.project.repository.user.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PointsServiceTest {

    @InjectMocks
    private PointsService pointsService;

    @Mock
    private GoogleService googleService;

    @Mock
    private GoogleMapper googleMapper;

    @Mock
    private PolylineDecoder polylineDecoder;

    @Mock
    UserRepository userRepository;

    @Before
    public void before() {
        ArrayList<GoogleGeocodedWaypointsDto> googleGeocodedWaypointsDtoArrayList = new ArrayList<>();
        googleGeocodedWaypointsDtoArrayList.add(new GoogleGeocodedWaypointsDto("ok", "placeId1"));
        googleGeocodedWaypointsDtoArrayList.add(new GoogleGeocodedWaypointsDto("ok", "placeId2"));

        GoogleDistanceDto distance = new GoogleDistanceDto("1 km", 1000);
        GoogleDurationDto duration = new GoogleDurationDto("10 min", 600);
        String endAddress = "EndAddress1";
        GoogleLocationDto endLocation = new GoogleLocationDto(50.001, 50.002);
        String startAddress = "StartAddress2";
        GoogleLocationDto startLocation = new GoogleLocationDto(50.003, 50.004);

        GoogleDistanceDto distanceStep1 = new GoogleDistanceDto("2 km", 2000);
        GoogleDurationDto durationStep1 = new GoogleDurationDto("20 min", 1200);
        GoogleLocationDto endLocationStep1 = new GoogleLocationDto(50.005, 50.006);
        GoogleLocationDto startLocationStep1 = new GoogleLocationDto(50.007, 50.008);
        GooglePolylineDto googlePolylineStep1 = new GooglePolylineDto("o}xuHoetfCTj@");

        ArrayList<GoogleStepsDto> googleStepsDtos = new ArrayList<>();
        GoogleStepsDto googleStepsDto = new GoogleStepsDto(distanceStep1, durationStep1, endLocationStep1, startLocationStep1, googlePolylineStep1);
        googleStepsDtos.add(googleStepsDto);

        ArrayList<GoogleLegsDto> googleLegsDtos = new ArrayList<>();
        GoogleLegsDto googleLegsDto = new GoogleLegsDto(distance, duration, endAddress, endLocation, startAddress, startLocation, googleStepsDtos);
        googleLegsDtos.add(googleLegsDto);

        ArrayList<GoogleRoutesDto> googleRoutesDtoArrayList = new ArrayList<>();
        GoogleRoutesDto googleRoutesDto = new GoogleRoutesDto("Map data ©2020 Google", googleLegsDtos);
        googleRoutesDtoArrayList.add(googleRoutesDto);

        GoogleDirectionsDto googleDirectionsDto = new GoogleDirectionsDto(googleGeocodedWaypointsDtoArrayList, googleRoutesDtoArrayList, "ok");


        ArrayList<GoogleGeocodedWaypoints> googleGeocodedWaypointsDtoArrayListMapp = new ArrayList<>();
        googleGeocodedWaypointsDtoArrayListMapp.add(new GoogleGeocodedWaypoints("ok", "placeId1"));
        googleGeocodedWaypointsDtoArrayListMapp.add(new GoogleGeocodedWaypoints("ok", "placeId2"));

        GoogleDistance distanceMapp = new GoogleDistance("1 km", 1000);
        GoogleDuration durationMapp = new GoogleDuration("10 min", 600);
        GoogleLocation endLocationMapp = new GoogleLocation(50.001, 50.002);
        GoogleLocation startLocationMapp = new GoogleLocation(50.003, 50.004);

        GoogleDistance distanceStep1Mapp = new GoogleDistance("2 km", 2000);
        GoogleDuration durationStep1Mapp = new GoogleDuration("20 min", 1200);
        GoogleLocation endLocationStep1Mapp = new GoogleLocation(50.005, 50.006);
        GoogleLocation startLocationStep1Mapp = new GoogleLocation(50.007, 50.008);
        GooglePolyline googlePolylineStep1Mapp = new GooglePolyline("o}xuHoetfCTj@");

        ArrayList<GoogleSteps> googleStepsDtosMapp = new ArrayList<>();
        GoogleSteps googleStepsMapp = new GoogleSteps(distanceStep1Mapp, durationStep1Mapp, endLocationStep1Mapp, startLocationStep1Mapp, googlePolylineStep1Mapp);
        googleStepsDtosMapp.add(googleStepsMapp);

        ArrayList<GoogleLegs> googleLegsDtosMapp = new ArrayList<>();
        GoogleLegs googleLegsMapp = new GoogleLegs(distanceMapp, durationMapp, endAddress, endLocationMapp, startAddress, startLocationMapp, googleStepsDtosMapp);
        googleLegsDtosMapp.add(googleLegsMapp);

        ArrayList<GoogleRoutes> googleRoutesDtoArrayListMapp = new ArrayList<>();
        GoogleRoutes googleRoutesMapp = new GoogleRoutes("Map data ©2020 Google", googleLegsDtosMapp);
        googleRoutesDtoArrayListMapp.add(googleRoutesMapp);

        GoogleDirections googleDirectionsDtoMapp = new GoogleDirections(googleGeocodedWaypointsDtoArrayListMapp, googleRoutesDtoArrayListMapp, "ok");

        String start = "start";
        String end = "end";

        when(googleService.fetchGoogleDirections(end, start)).thenReturn(googleDirectionsDto);
        when(googleMapper.mapToDirections(googleDirectionsDto)).thenReturn(googleDirectionsDtoMapp);

        List<Point> pointList = new ArrayList<>();
        Point point = new Point(50.009, 50.010);
        pointList.add(point);
        when(polylineDecoder.decode("o}xuHoetfCTj@")).thenReturn(pointList);
    }

    @Test
    public void getAllPoints() throws UserNotFoundException {
        //Given
        String start = "start";
        String end = "end";

        Point point1 = new Point(50.007, 50.008);
        point1.setTimeFromLastPoint(0);
        Point point2 = new Point(50.009, 50.01);
        point2.setTimeFromLastPoint(0);
        Point point3 = new Point(50.005, 50.006);
        point2.setTimeFromLastPoint(0);

        Users users = new Users();

        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("userSessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(1000));
        users.setSessionKey(sessionKey);
        //When
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(users));
        Points points = pointsService.getAllPoints(start, end, "userSessionKey", 1L);

        //Then
        Assert.assertNotNull(points);
        Assert.assertEquals(3, points.getListOfAllPoints().size());
        Assert.assertEquals(point1, points.getListOfAllPoints().get(0));
        Assert.assertEquals(point2, points.getListOfAllPoints().get(1));
        Assert.assertEquals(point3, points.getListOfAllPoints().get(2));
    }

}