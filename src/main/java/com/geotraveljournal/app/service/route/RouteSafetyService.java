package com.geotraveljournal.app.service.route;

import com.geotraveljournal.app.dto.route.CoordinatesDto;
import com.geotraveljournal.app.dto.route.RouteRequestDto;
import com.geotraveljournal.app.dto.route.WeatherDataDto;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteSafetyService {

    private final WeatherService weatherService;

    public RouteSafetyService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public int findSafestRoute(RouteRequestDto request) {
        Map<Integer, Double> routeScores = new HashMap<>();

        for (Map.Entry<Integer, List<CoordinatesDto>> entry : request.getRoutes().entrySet()) {
            int routeId = entry.getKey();
            List<CoordinatesDto> coordinates = entry.getValue();
            double score = calculateRouteSafetyScore(coordinates);
            routeScores.put(routeId, score);
        }
        return routeScores.entrySet().stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("No routes available"));
    }

    private double calculateRouteSafetyScore(List<CoordinatesDto> coordinates) {
        double totalScore = 0.0;
        for (CoordinatesDto coord : coordinates) {
            WeatherDataDto weather = weatherService.getWeather(coord.getLatitude(), coord.getLongitude());
            totalScore += evaluateWeatherSafety(weather);
        }
        return totalScore;
    }

    private double evaluateWeatherSafety(WeatherDataDto weather) {
        double score = 0.0;

        if (Arrays.stream(weather.getWeather())
                .anyMatch(w -> w.getMain().equalsIgnoreCase("Rain") || w.getMain().equalsIgnoreCase("Snow"))) {
            score += 50;
        }

        if (weather.getVisibility() < 5000) {
            score += 20;
        }

        if (weather.getMain().getTemp() < 0) {
            score += 30;
        }

        if (weather.getWind() != null) {
            double windSpeed = weather.getWind().getSpeed();
            double windGust = weather.getWind().getGust();

            if (windSpeed > 10) {
                score += 20;
            }
            if (windSpeed > 20) {
                score += 50;
            }

            if (windGust > 15) {
                score += 30;
            }
        }

        return score;
    }
}
