package com.geotraveljournal.app.controller.route;

import com.geotraveljournal.app.dao.auth.UserDao;
import com.geotraveljournal.app.dto.route.RouteRequestDto;
import com.geotraveljournal.app.dto.route.RouteResponseDto;
import com.geotraveljournal.app.model.auth.User;
import com.geotraveljournal.app.responses.core.GoodResponse;
import com.geotraveljournal.app.service.route.RouteSafetyService;
import com.geotraveljournal.app.utils.ApiAuth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/change-route")
@Tag(name = "Route Controller", description = "Контроллер для выбора лучшего маршрута по погодным условиям")
public class RouteController {

    private final RouteSafetyService routeSafetyService;
    private final UserDao userDao;

    public RouteController(RouteSafetyService routeSafetyService, UserDao userDao) {
        this.routeSafetyService = routeSafetyService;
        this.userDao = userDao;
    }

    @Operation(
            summary = "Изменение маршрута с учетом погодных условий",
            description = "Анализирует несколько маршрутов и выбирает самый безопасный на основе погодных условий, видимости, ветра и других факторов.",
            parameters = @Parameter(
                    name = "Authorization",
                    description = "Токен авторизации",
                    in = ParameterIn.HEADER,
                    required = true
            )
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
    public ResponseEntity<GoodResponse> changeRoute(
            @RequestBody RouteRequestDto request,
            HttpServletRequest httpServletRequest
    ) {
        User currentUser = ApiAuth.getCurrentUser(httpServletRequest, userDao);

        int safestRouteId = routeSafetyService.findSafestRoute(request);
        RouteResponseDto routeResponse = new RouteResponseDto(String.valueOf(safestRouteId));

        return ResponseEntity.ok(new GoodResponse(true, routeResponse));
    }
}
