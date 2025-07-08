package me.marioscalas.saikata.weatherexpert.internal.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record WeatherResponse(
    @JsonPropertyDescription("Wind speed in meters per second") @JsonProperty("wind_speed") String windSpeed,
    @JsonPropertyDescription("Wind direction in degrees") @JsonProperty("wind_degrees") int windDegrees,
    @JsonPropertyDescription("Current temperature in Celsius") @JsonProperty("temp") double temperature,
    @JsonPropertyDescription("Current humidity percentage") @JsonProperty("humidity") double humidity,
    @JsonPropertyDescription("Minimum temperature in Celsius") @JsonProperty("min_temp") double minTemperature,
    @JsonPropertyDescription("Maximum temperature in Celsius") @JsonProperty("max_temp") double maxTemperature,
    @JsonPropertyDescription("What the temperature feels like in Celsius") @JsonProperty("feels_like") double feelsLike,
    @JsonPropertyDescription("Percentage of cloud cover (100=completely overcast, 0=perfectly clear)") @JsonProperty("cloud_pct") double cloudPercentage,
    @JsonPropertyDescription("Sunset time in Unix timestamp") @JsonProperty("sunset") long sunset,
    @JsonPropertyDescription("Sunrise time in Unix timestamp") @JsonProperty("sunrise") long sunrise
) {}