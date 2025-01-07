package com.geotraveljournal.app.dto.route;

public class RouteResponseDto {
    String route;

    public RouteResponseDto(String route) {
        this.route = route;
    }

    public String getRoute() { return route; }

    public void setRoute(String newRoute) { this.route = newRoute; }
}
