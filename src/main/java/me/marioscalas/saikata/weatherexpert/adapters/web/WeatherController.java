package me.marioscalas.saikata.weatherexpert.adapters.web;

import jakarta.validation.Valid;
import me.marioscalas.saikata.weatherexpert.WeatherPromptService;
import me.marioscalas.saikata.weatherexpert.model.Answer;
import me.marioscalas.saikata.weatherexpert.model.Question;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Simple REST API for a chatbot.
 */
@RestController
@RequestMapping("/api/v1")
public class WeatherController {
    
    private final WeatherPromptService weatherPromptService;
    
    WeatherController(WeatherPromptService weatherPromptService) {
        this.weatherPromptService = weatherPromptService;
    }
    
    @Operation(summary = "Submit a Weather Prompt to the Weather expert")
    @PostMapping(value = "/weather", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Answer> submitQuestionAboutWeather(@RequestBody @Valid Question question) {
        return ResponseEntity.ok().body(
            weatherPromptService.getAnswer(question)    
        );
    }
}
