package com.geotraveljournal.app.controller.route;

import com.geotraveljournal.app.dto.route.RouteRequestDto;
import com.geotraveljournal.app.dto.route.RouteResponseDto;
import com.geotraveljournal.app.responses.core.GoodResponse;
import com.geotraveljournal.app.service.route.RouteSafetyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/change-route")
public class RouteController {

    private final RouteSafetyService routeSafetyService;

    public RouteController(RouteSafetyService routeSafetyService) {
        this.routeSafetyService = routeSafetyService;
    }

    @Operation(
            summary = "Изменение маршрута с учетом погодных условий",
            description = "Анализирует несколько маршрутов и выбирает самый безопасный на основе погодных условий, видимости, ветра и других факторов"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Маршрут успешно рассчитан",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GoodResponse.class)
            )
    )
    @PostMapping
    public ResponseEntity<GoodResponse> changeRoute(@RequestBody RouteRequestDto request) {
        int safestRouteId = routeSafetyService.findSafestRoute(request);
        RouteResponseDto routeResponse = new RouteResponseDto(String.valueOf(safestRouteId));

        return ResponseEntity.ok(new GoodResponse(true, routeResponse));
    }
}
