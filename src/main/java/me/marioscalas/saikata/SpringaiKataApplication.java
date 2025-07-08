package me.marioscalas.saikata;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import me.marioscalas.saikata.stockprices.adapters.mcp.StockPricesTools;
import me.marioscalas.saikata.weatherexpert.adapters.mcp.WeatherTools;

@SpringBootApplication
public class SpringaiKataApplication {

	@Bean
	ToolCallbackProvider toolCallbacks(StockPricesTools stockPricesTools,
			WeatherTools weatherTools) {
		return MethodToolCallbackProvider.builder()
			.toolObjects(stockPricesTools, weatherTools)
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringaiKataApplication.class, args);
	}
}
