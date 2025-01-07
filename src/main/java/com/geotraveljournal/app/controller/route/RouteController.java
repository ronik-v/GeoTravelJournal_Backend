package com.geotraveljournal.app.controller.route;

import com.geotraveljournal.app.dto.route.RouteRequestDto;
import com.geotraveljournal.app.dto.route.RouteResponseDto;
import com.geotraveljournal.app.responses.core.GoodResponse;
import com.geotraveljournal.app.service.route.RouteSafetyService;
import com.geotraveljournal.app.service.route.WeatherService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/change-route")
public class RouteController {

    private final WeatherService weatherService;
    private final RouteSafetyService routeSafetyService;

    public RouteController(WeatherService weatherService, RouteSafetyService routeSafetyService) {
        this.weatherService = weatherService;
        this.routeSafetyService = routeSafetyService;
    }

    @PostMapping
    public ResponseEntity<GoodResponse> changeRoute(@RequestBody RouteRequestDto request) {
        int safestRouteId = routeSafetyService.findSafestRoute(request);
        RouteResponseDto routeResponse = new RouteResponseDto(String.valueOf(safestRouteId));

        return ResponseEntity.ok(new GoodResponse(true, routeResponse));
    }
}
