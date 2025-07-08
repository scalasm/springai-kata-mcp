# Spring-AI kata (MCP Server))

This is my sandbox for experimenting with MCP servers using Spring Boot and [MCP](https://modelcontextprotocol.io/introduction) integration.

In addition to this [Spring AI MCP Server](https://github.com/scalasm/springai-kata-mcp), there are three kata repositories in this series, to keep things a bit separated:
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

You have a few options:
1. **Configure the MCP Server in your VSCode environment**
  See [mcp.json](.vscode/mcp.json) for a ready-to-use example - you configure the URL for your MCP server.
2. Use postman - new Postman include support for inspecting your MCP servers

# References
- [Understand SSE MCP SERVER with Spring Boot and Spring AI | Beginner Tutorial](https://www.youtube.com/watch?v=n5DG0uClbdo) this is a great reference for both building your MCP server ground up or troubleshooting your own!
- [Spring AI - Begineer to Guru](https://www.udemy.com/course/spring-ai-beginner-to-guru) has been my initial reference
