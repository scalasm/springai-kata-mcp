package me.marioscalas.saikata.stockprices.adapters.mcp;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import me.marioscalas.saikata.stockprices.internal.StockPricesClient;
import me.marioscalas.saikata.stockprices.internal.StockPricesClient.StockPricesRequest;
import me.marioscalas.saikata.stockprices.internal.StockPricesClient.StockPricesResponse;

@Component
public class StockPricesTools {
    private final StockPricesClient stockPricesClient;

    public StockPricesTools(StockPricesClient stockPricesClient) {
        this.stockPricesClient = stockPricesClient;
    }

    @Tool(
        name = "getStockPrice",
        description = "Get the stock price for a given ticker."
    )
    public StockPricesResponse getStockPrice(StockPricesRequest request) {
        return stockPricesClient.getStockPrice(request);
    }       
}
