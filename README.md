# Spring-AI kata (Functions)

This is my sandbox for working with Spring AI using more advanced features like functions integrations: it is a follow up
of the other [Spring AI Kata Functions](https://github.com/scalasm/springai-kata-functions) which deals with OpenAI functions and their invocations!

In addition to this [Spring AI MCP Server](https://github.com/scalasm/springai-kata-mcp), there are four kata repositories in this series, to keep things a bit separated:
 - [Spring AI Kata](https://github.com/scalasm/springai-kata) - prompts, RAG with Milvus
 - [Spring AI Kata with Azure Functions](https://github.com/scalasm/springai-kata-mcp) dealing with Azure functions integration: this should be superseded by a [MCP implementation](https://docs.spring.io/spring-ai-mcp/reference/spring-mcp.html)
 - [Spring AI Kata w/ Multimedia content](https://github.com/scalasm/springai-kata-multimedia), deals with image generation, and text2speech

# Requirements
- JDK 21 or better
  - I use Amazon Corretto 21 but anything should be fine
- Apache Maven 3.9.x 
- OPENAI API Key - to be obtained by the [Open AI Console](https://platform.openai.com/settings/organization/api-keys)
  - ensure there is an environment variable called `OPENAI_API_KEY` set and your will be ready to go!
- NINJAS_API_KEY - to be obtained from [API Ninjas](https://api-ninjas.com/) - the free tier is ok!
  - We want to consume the [Weather API](https://api-ninjas.com/api/weather)

# How to run

## Option 1 - from Visual Studio Code
A launch configuration is provided for Visual Studio Code - just create a `.env` file in the workspace root with you `OPENAPI_API_KEY` set.

## Option 2 - Command line
```
mvn spring-boot:run
```

## How to test

There is a [Swagger UI](http://localhost:8080/swagger-ui/index.html) available.

# References
- [Spring AI - Begineer to Guru](https://www.udemy.com/course/spring-ai-beginner-to-guru) has been my initial reference
- [Understand SSE MCP SERVER with Spring Boot and Spring AI | Beginner Tutorial](https://www.youtube.com/watch?v=n5DG0uClbdo) this is a great reference for both building your MCP server ground up or troubleshooting your own!
