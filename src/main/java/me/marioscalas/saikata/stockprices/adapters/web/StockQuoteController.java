package me.marioscalas.saikata.stockprices.adapters.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import me.marioscalas.saikata.stockprices.StockpricesPromptService;
import me.marioscalas.saikata.stockprices.model.StockPriceAnswer;
import me.marioscalas.saikata.stockprices.model.StockPriceQuestion;

/**
 * Simple REST API for a chatbot.
 */
@RestController
@RequestMapping("/api/v1/stockprice")
public class StockQuoteController {
    
    private final StockpricesPromptService stockPricesPromptService;
    
    StockQuoteController(StockpricesPromptService stockPricesPromptService) {
        this.stockPricesPromptService = stockPricesPromptService;
    }
    
    @Operation(summary = "Submit a ticker to get a stock price quote")
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<StockPriceAnswer> submitStockPriceTicker(@RequestBody @Valid StockPriceQuestion question) {
        return ResponseEntity.ok().body(
            stockPricesPromptService.getAnswer(question)    
        );
    }
}
