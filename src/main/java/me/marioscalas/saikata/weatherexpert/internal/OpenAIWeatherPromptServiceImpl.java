package me.marioscalas.saikata.weatherexpert.internal;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import jakarta.validation.Valid;
import me.marioscalas.saikata.weatherexpert.WeatherPromptService;
import me.marioscalas.saikata.weatherexpert.adapters.mcp.WeatherTools;
import me.marioscalas.saikata.weatherexpert.internal.weather.WeatherRequestV2;
import me.marioscalas.saikata.weatherexpert.internal.weather.WeatherServiceFunction;
import me.marioscalas.saikata.weatherexpert.model.Answer;
import me.marioscalas.saikata.weatherexpert.model.Question;

@Component
public class OpenAIWeatherPromptServiceImpl implements WeatherPromptService {

    @Value("classpath:/templates/get-weather-prompt.st")
    private Resource getWeatherPromptTemplate;

    private final OpenAiChatModel chatModel;
    
    private final WeatherServiceFunction weatherServiceFunction;

    private final WeatherTools weatherTools;

    public OpenAIWeatherPromptServiceImpl(OpenAiChatModel chatModel, WeatherServiceFunction weatherServiceFunction, WeatherTools weatherTools) {
        this.chatModel = chatModel;
        this.weatherServiceFunction = weatherServiceFunction;
        this.weatherTools = weatherTools;
    }

    @Override
    public Answer getAnswer(@Valid Question question) {
        // We have two ways to integrate functions:
        // 1 - Programmatic - Using the function in the options (OpenAIChatOptions)
        // 2 - Declarative - Using the "function tools" (OpenAiChatModel)
        // Option 2 looks like the easiest way, tbh
        // See https://docs.spring.io/spring-ai/reference/api/tools-migration.html for more details.
        Prompt prompt = new Prompt(
            new SystemMessage(getWeatherPromptTemplate),
            new UserMessage(question.text())
        );

        return getAnswerUsingTools(prompt);
//        return getAnswerUsingFunctionsInOptions(prompt);
    }


    private Answer getAnswerUsingFunctionsInOptions(Prompt prompt) {
        final OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
            .toolCallbacks(List.of(
                FunctionToolCallback.builder("getWeather", weatherServiceFunction)
                    .description("Get the current weather for a given city")
                    .inputType(WeatherRequestV2.class)
                    .build()
            ))
            .temperature(0.7)
            .maxTokens(1000)
            .build();

        final String responseContent = ChatClient.create(chatModel)
            .prompt(prompt)
            .options(chatOptions)
            .call()
            .content();

        return new Answer(responseContent);
    }

    private Answer getAnswerUsingTools(Prompt prompt) {
        final String responseContent = ChatClient.create(chatModel)
            .prompt(prompt)
            .tools(weatherTools)
            .call()
            .content();

        return new Answer(responseContent);
    }

}
