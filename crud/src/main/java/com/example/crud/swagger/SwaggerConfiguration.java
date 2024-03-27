package com.example.crud.swagger;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfiguration {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.application.version}")
    private String applicationVersion;
    @Value("${swagger.base-url}")
    private String swaggerBaseUrl;

    @Bean
    public OpenAPI springDocOpenApi() {
        return new OpenAPI()
                .info(springDocapiInfo())
                .servers(Arrays.asList(
                        new Server().url(swaggerBaseUrl)))
                .security(List.of(new SecurityRequirement()));

    }

    Info springDocapiInfo() {
        return new Info()
                .description(applicationName)
                .version(applicationVersion);

    }
}
