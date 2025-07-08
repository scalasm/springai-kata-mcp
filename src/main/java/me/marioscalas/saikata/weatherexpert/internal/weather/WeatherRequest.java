package me.marioscalas.saikata.weatherexpert.internal.weather;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Note that is only available with Premium account - we need to use lat/long instead, see WeatherRequestV2 .
 */
@JsonClassDescription("Request for Weather conditions in a given city")
public record WeatherRequest(
    @JsonPropertyDescription("City name") @JsonProperty(value = "city", required = true) String city,
    @JsonPropertyDescription("Optional country where the city is located") @JsonProperty(value = "country", required = false) String country) {

    @JsonIgnore
    public boolean hasCountry() {
        return country != null && !country.isBlank();
    }
}
