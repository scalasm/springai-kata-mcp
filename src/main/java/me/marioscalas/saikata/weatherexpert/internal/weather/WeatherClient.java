package me.marioscalas.saikata.weatherexpert.internal.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WeatherClient {
    private static final String WEATHER_API_ENDPOINT = "https://api.api-ninjas.com/v1/weather";

    private final String apiKey;

    public WeatherClient(@Value("${ninjas.api-key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public WeatherResponse getWeather(WeatherRequestV2 request) {
        final var restClient = RestClient.builder()
            .baseUrl(WEATHER_API_ENDPOINT)
            .defaultHeaders( headers -> {
                headers.set("X-Api-Key", apiKey);
                headers.set("Accept", "application/json");
                headers.set("Content-Type", "application/json");
            })
            .build();
        return restClient.get().uri(uriBuilder -> {
            log.debug("Requesting weather for (lat,lon): {} {}", request.latitude(), request.longitude());
            uriBuilder.queryParam("lat", request.latitude());
            uriBuilder.queryParam("lon", request.longitude());
            return uriBuilder.build();
        })
        .retrieve()
        .body(WeatherResponse.class);
    }
}