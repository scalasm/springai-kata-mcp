package me.marioscalas.saikata.weatherexpert.adapters.mcp;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import me.marioscalas.saikata.weatherexpert.internal.weather.WeatherClient;
import me.marioscalas.saikata.weatherexpert.internal.weather.WeatherRequestV2;
import me.marioscalas.saikata.weatherexpert.internal.weather.WeatherResponse;

@Component
public class WeatherTools {
    private final WeatherClient weatherClient;

    public WeatherTools(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @Tool(
        name = "getWeather",
        description = "Get the current weather for a given city and optional country"
    )
    public WeatherResponse getWeather(WeatherRequestV2 request) {
        return weatherClient.getWeather(request);
    }    
}
