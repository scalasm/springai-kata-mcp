package me.marioscalas.saikata.weatherexpert.model;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Weather request")
public record Question(
    @NotNull
    @Schema(description = "The text of the question", example = "What is the weather at coordinates 53.3498° N, 6.2603° W (which is Dublin, Ireland)?")
    String text
) {
}
