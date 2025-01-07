package com.geotraveljournal.app.service.route;

import com.geotraveljournal.app.dto.route.WeatherDataDto;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final Dotenv envConfig;
    private final String apiKey;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.envConfig = Dotenv.load();
        this.apiKey = this.envConfig.get("WEATHER_TOKEN");
    }

    public WeatherDataDto getWeather(double latitude, double longitude) {
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s",
                latitude, longitude, apiKey);
        return restTemplate.getForObject(url, WeatherDataDto.class);
    }
}

