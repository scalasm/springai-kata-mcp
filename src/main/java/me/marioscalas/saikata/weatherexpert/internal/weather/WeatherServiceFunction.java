package me.marioscalas.saikata.weatherexpert.internal.weather;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class WeatherServiceFunction implements Function<WeatherRequestV2, WeatherResponse> {
    private final WeatherClient weatherClient;

    public WeatherServiceFunction(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @Override
    public WeatherResponse apply(WeatherRequestV2 request) {
        return weatherClient.getWeather(request);
    }
}
