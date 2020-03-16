package com.portfolio.project.controller;

import com.portfolio.project.domain.weather.*;
import com.portfolio.project.mapper.PointsMapper;
import com.portfolio.project.service.PointsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PointsService pointsService;

    @MockBean
    private PointsMapper pointsMapper;

    @Test
    public void getAllPoints() throws Exception {
        //Given
        Points points = new Points();
        points.getListOfAllPoints().add(new Point(20.0452, 23.4325));
        points.getListOfAllPoints().add(new Point(32.531234, 43.1235634));
        points.getListOfAllPoints().add(new Point(64.21234, 35.2346));

        PointsDto pointsDto = new PointsDto();
        pointsDto.getListOfAllPoints().add(new PointDto(20.0452, 23.4325,0, 0, new WeatherDto()));
        pointsDto.getListOfAllPoints().add(new PointDto(32.531234, 43.1235634,0, 0, new WeatherDto()));
        pointsDto.getListOfAllPoints().add(new PointDto(64.21234, 35.2346,0, 0, new WeatherDto()));

        String start = "start";
        String end = "end";

        when(pointsService.getAllPoints(start, end, "sessionKey", 1L)).thenReturn(points);
        when(pointsMapper.mapToPointsDto(points)).thenReturn(pointsDto);

        //When & Then
        mockMvc.perform(get("/v1/weather/getPoints/" + start + "/" + end + "/").contentType(MediaType.APPLICATION_JSON)
                .param("userSessionKey","sessionKey")
                .param("userId", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.listOfAllPoints", hasSize(3)))
                .andExpect(jsonPath("$.listOfAllPoints[0].lat", is(20.0452)))
                .andExpect(jsonPath("$.listOfAllPoints[0].lng", is(23.4325)))
                .andExpect(jsonPath("$.listOfAllPoints[1].lat", is(32.531234)))
                .andExpect(jsonPath("$.listOfAllPoints[1].lng", is(43.1235634)))
                .andExpect(jsonPath("$.listOfAllPoints[2].lat", is(64.21234)))
                .andExpect(jsonPath("$.listOfAllPoints[2].lng", is(35.2346)));
    }

    @Test
    public void getPointsDistance() throws Exception {
        //Given
        Points points = new Points();
        Point point1 = new Point(51.110456, 17.026066); // start
        Point point2 = new Point(51.111467, 17.022032); // 300m
        Point point3 = new Point(51.112686, 17.015123); // 500m
        Point point4 = new Point(51.112713, 17.012881); // 150m
        Point point5 = new Point(51.113124, 17.005918); //end
        points.getListOfAllPoints().add(point1);
        points.getListOfAllPoints().add(point2);
        points.getListOfAllPoints().add(point3);
        points.getListOfAllPoints().add(point4);
        points.getListOfAllPoints().add(point5);

        PointsDto pointsDto = new PointsDto();
        pointsDto.getListOfAllPoints().add(new PointDto(51.110456, 17.026066, 0, 0, new WeatherDto()));
        pointsDto.getListOfAllPoints().add(new PointDto(51.112686, 17.015123, 0, 0, new WeatherDto()));
        pointsDto.getListOfAllPoints().add(new PointDto(51.113124, 17.005918, 0, 0, new WeatherDto()));

        String start = "start";
        String end = "end";
        String distance = "350";

        when(pointsService.getPointsWithDistanceAndTimeBetween(start, end, Integer.parseInt(distance), "sessionKey", 1L)).thenReturn(points);
        when(pointsMapper.mapToPointsDto(points)).thenReturn(pointsDto);

        //When & Then
        mockMvc.perform(get("/v1/weather/getPoints/" + start + "/" + end + "/" + distance).contentType(MediaType.APPLICATION_JSON)
                .param("userSessionKey","sessionKey")
                .param("userId", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.listOfAllPoints", hasSize(3)))
                .andExpect(jsonPath("$.listOfAllPoints[0].lat", is(51.110456)))
                .andExpect(jsonPath("$.listOfAllPoints[0].lng", is(17.026066)))
                .andExpect(jsonPath("$.listOfAllPoints[1].lat", is(51.112686)))
                .andExpect(jsonPath("$.listOfAllPoints[1].lng", is(17.015123)))
                .andExpect(jsonPath("$.listOfAllPoints[2].lat", is(51.113124)))
                .andExpect(jsonPath("$.listOfAllPoints[2].lng", is(17.005918)));
    }

    @Test
    public void getPointsWithArrivalTime() throws Exception {
        //Given
        Points points = new Points();
        Point point1 = new Point(51.110456, 17.026066); // start
        Point point2 = new Point(51.111467, 17.022032); // 300m
        Point point3 = new Point(51.112686, 17.015123); // 500m
        Point point4 = new Point(51.112713, 17.012881); // 150m
        Point point5 = new Point(51.113124, 17.005918); //end
        points.getListOfAllPoints().add(point1);
        points.getListOfAllPoints().add(point2);
        points.getListOfAllPoints().add(point3);
        points.getListOfAllPoints().add(point4);
        points.getListOfAllPoints().add(point5);

        PointsDto pointsDto = new PointsDto();
        pointsDto.getListOfAllPoints().add(new PointDto(51.110456, 17.026066, 0, 0, new WeatherDto()));
        pointsDto.getListOfAllPoints().add(new PointDto(51.112686, 17.015123, 3, 131, new WeatherDto()));
        pointsDto.getListOfAllPoints().add(new PointDto(51.113124, 17.005918, 6, 323, new WeatherDto()));

        String start = "start";
        String end = "end";
        String distance = "350";

        when(pointsService.getPointsWithArrivalTime(start, end, Integer.parseInt(distance), 0, "sessionKey", 1L)).thenReturn(points);
        when(pointsMapper.mapToPointsDto(points)).thenReturn(pointsDto);


        //When & Then
        mockMvc.perform(get("/v1/weather/getPoints/" + start + "/" + end + "/" + distance + "/0").contentType(MediaType.APPLICATION_JSON)
                .param("userSessionKey","sessionKey")
                .param("userId", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.listOfAllPoints", hasSize(3)))
                .andExpect(jsonPath("$.listOfAllPoints[0].lat", is(51.110456)))
                .andExpect(jsonPath("$.listOfAllPoints[0].lng", is(17.026066)))
                .andExpect(jsonPath("$.listOfAllPoints[0].timeFromLastPoint", is(0)))
                .andExpect(jsonPath("$.listOfAllPoints[0].arrivalTime", is(0)))
                .andExpect(jsonPath("$.listOfAllPoints[1].lat", is(51.112686)))
                .andExpect(jsonPath("$.listOfAllPoints[1].lng", is(17.015123)))
                .andExpect(jsonPath("$.listOfAllPoints[1].timeFromLastPoint", is(3)))
                .andExpect(jsonPath("$.listOfAllPoints[1].arrivalTime", is(131)))
                .andExpect(jsonPath("$.listOfAllPoints[2].lat", is(51.113124)))
                .andExpect(jsonPath("$.listOfAllPoints[2].lng", is(17.005918)))
                .andExpect(jsonPath("$.listOfAllPoints[2].timeFromLastPoint", is(6)))
                .andExpect(jsonPath("$.listOfAllPoints[2].arrivalTime", is(323)));
    }

    @Test
    public void getWeather() throws Exception {
        //Given
        Points points = new Points();
        Point point1 = new Point(51.110456, 17.026066); // start
        Point point2 = new Point(51.111467, 17.022032); // 300m
        Point point3 = new Point(51.112686, 17.015123); // 500m
        Point point4 = new Point(51.112713, 17.012881); // 150m
        Point point5 = new Point(51.113124, 17.005918); //end
        points.getListOfAllPoints().add(point1);
        points.getListOfAllPoints().add(point2);
        points.getListOfAllPoints().add(point3);
        points.getListOfAllPoints().add(point4);
        points.getListOfAllPoints().add(point5);

        PointsDto pointsDto = new PointsDto();
        WeatherDto weather1 = new WeatherDto(1.0, 1.0, 1.0, 1.0, "icon1", 1.0, 1.0, 1.0, "type1", 1.0, "summary1", 1.0, 10, 1.0, 1.0, 1.0, 10);
        WeatherDto weather2 = new WeatherDto(2.0, 2.0, 2.0, 2.0, "icon2", 2.0, 2.0, 2.0, "type2", 2.0, "summary2", 2.0, 20, 2.0, 2.0, 2.0, 20);
        WeatherDto weather3 = new WeatherDto(3.0, 3.0, 3.0, 3.0, "icon3", 3.0, 3.0, 3.0, "type3", 3.0, "summary3", 3.0, 30, 3.0, 3.0, 3.0, 30);


        pointsDto.getListOfAllPoints().add(new PointDto(51.110456, 17.026066, 0, 0, weather1));
        pointsDto.getListOfAllPoints().add(new PointDto(51.112686, 17.015123, 3, 131, weather2));
        pointsDto.getListOfAllPoints().add(new PointDto(51.113124, 17.005918, 6, 323, weather3));

        String start = "start";
        String end = "end";
        String distance = "350";

        when(pointsService.getPointsWithWeather(start, end, Integer.parseInt(distance), 0, "sessionKey", 1L)).thenReturn(points);
        when(pointsMapper.mapToPointsDto(points)).thenReturn(pointsDto);


        //When & Then
        mockMvc.perform(get("/v1/weather/getPointsWeather/" + start + "/" + end + "/" + distance + "/0").contentType(MediaType.APPLICATION_JSON)
                .param("userSessionKey","sessionKey")
                .param("userId", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.listOfAllPoints", hasSize(3)))
                .andExpect(jsonPath("$.listOfAllPoints[0].weatherDto.apparentTemperature", is(1.0)))
                .andExpect(jsonPath("$.listOfAllPoints[1].weatherDto.apparentTemperature", is(2.0)))
                .andExpect(jsonPath("$.listOfAllPoints[2].weatherDto.apparentTemperature", is(3.0)))
                .andExpect(jsonPath("$.listOfAllPoints[0].weatherDto.icon", is("icon1")))
                .andExpect(jsonPath("$.listOfAllPoints[1].weatherDto.icon", is("icon2")))
                .andExpect(jsonPath("$.listOfAllPoints[2].weatherDto.icon", is("icon3")))
                .andExpect(jsonPath("$.listOfAllPoints[0].weatherDto.precipType", is("type1")))
                .andExpect(jsonPath("$.listOfAllPoints[1].weatherDto.precipType", is("type2")))
                .andExpect(jsonPath("$.listOfAllPoints[2].weatherDto.precipType", is("type3")))
                .andExpect(jsonPath("$.listOfAllPoints[0].weatherDto.summary", is("summary1")))
                .andExpect(jsonPath("$.listOfAllPoints[1].weatherDto.summary", is("summary2")))
                .andExpect(jsonPath("$.listOfAllPoints[2].weatherDto.summary", is("summary3")))
                .andExpect(jsonPath("$.listOfAllPoints[0].weatherDto.time", is(10)))
                .andExpect(jsonPath("$.listOfAllPoints[1].weatherDto.time", is(20)))
                .andExpect(jsonPath("$.listOfAllPoints[2].weatherDto.time", is(30)));
    }
}