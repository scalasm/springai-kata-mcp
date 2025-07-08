package me.marioscalas.saikata.stockprices.internal;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StockPricesClient {
    @JsonClassDescription("Request for stock price for a given ticker")
    public static record StockPricesRequest(
        @JsonPropertyDescription("Ticker symbol (e.g., AAPL)") @JsonProperty(value = "ticker", required = true) String ticker,
        @JsonPropertyDescription("Time interval between data points. Valid values are: 1m, 5m, 15m, 30m, 1h, 4h. Default is 1h.") @JsonProperty(value = "period", required = false) String period,
        @JsonPropertyDescription("Start timestamp in Unix format. If not provided, defaults to 24 hours ago") @JsonProperty(value = "start", required = false) Long start,
        @JsonPropertyDescription("End timestamp in Unix format. If not provided, defaults to current time") @JsonProperty(value = "end", required = false) Long end
    ) {}

    @JsonClassDescription("Response for stock price for a given ticker")
    public static record StockPricesResponse(
        @JsonPropertyDescription("Ticker symbol (e.g., AAPL)") @JsonProperty(value = "ticker", required = true) String ticker,
        @JsonPropertyDescription("Ticker NAME (e.g., Apple Inc.)") @JsonProperty(value = "name", required = true) String name,
        @JsonPropertyDescription("Price") @JsonProperty(value = "price", required = true) BigDecimal price,
        @JsonPropertyDescription("Currency (e.g., USD)") @JsonProperty(value = "currency", required = true) String currency,
        @JsonPropertyDescription("Stock Exchange Market (e.g., NASDAQ)") @JsonProperty(value = "exchange", required = true) String exchange,
        @JsonPropertyDescription("Stock price update timestamp in Unix format") @JsonProperty(value = "updated", required = true) Long updated
    ) {}

    private static final String STOCK_PRICE_API_ENDPOINT = "https://api.api-ninjas.com/v1/stockprice";

    private final String apiKey;

    public StockPricesClient(@Value("${ninjas.api-key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public StockPricesResponse getStockPrice(StockPricesRequest request) {
        final var restClient = RestClient.builder()
            .baseUrl(STOCK_PRICE_API_ENDPOINT)
            .defaultHeaders( headers -> {
                headers.set("X-Api-Key", apiKey);
                headers.set("Accept", "application/json");
                headers.set("Content-Type", "application/json");
            })
            .build();
    
        return restClient.get().uri(uriBuilder -> {
            log.debug("Requesting stock price for ticker: {}, period = {}, start = {}, end = {}", 
                request.ticker(), request.period(), request.start(), request.end());
            
            uriBuilder.queryParam("ticker", request.ticker());
            if (request.period() != null && !request.period().isBlank()) {
                uriBuilder.queryParam("period", request.period());
            }
            if (request.start() != null) {
                uriBuilder.queryParam("start", request.start());
            }
            if (request.end() != null) {
                uriBuilder.queryParam("end", request.end());
            }
            return uriBuilder.build();
        })
        .retrieve()
        .body(StockPricesResponse.class);
    }    
}
