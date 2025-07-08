package me.marioscalas.saikata.weatherexpert.internal.weather;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Request for Weather conditions location identitified by longitude and latitude on planet Earth")
public record WeatherRequestV2(
    @JsonPropertyDescription("Longitude of the location") @JsonProperty(value = "longitude", required = true) Double longitude,
    @JsonPropertyDescription("Latitude of the location") @JsonProperty(value = "latitude", required = true) Double latitude) {
}
