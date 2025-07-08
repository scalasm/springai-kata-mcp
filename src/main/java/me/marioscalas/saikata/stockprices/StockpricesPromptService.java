package me.marioscalas.saikata.stockprices;

import me.marioscalas.saikata.stockprices.model.StockPriceAnswer;
import me.marioscalas.saikata.stockprices.model.StockPriceQuestion;

public interface StockpricesPromptService {
    StockPriceAnswer getAnswer(StockPriceQuestion question);
}
