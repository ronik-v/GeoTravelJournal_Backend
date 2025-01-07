package com.geotraveljournal.app.dto.route;

import java.util.Map;
import java.util.List;

public class RouteRequestDto {
    private CoordinatesDto userCoordinatesDto;
    private Map<Integer, List<CoordinatesDto>> routes;

    public CoordinatesDto getUserCoordinates() {
        return userCoordinatesDto;
    }

    public void setUserCoordinates(CoordinatesDto userCoordinatesDto) {
        this.userCoordinatesDto = userCoordinatesDto;
    }

    public Map<Integer, List<CoordinatesDto>> getRoutes() {
        return routes;
    }

    public void setRoutes(Map<Integer, List<CoordinatesDto>> routes) {
        this.routes = routes;
    }
}
