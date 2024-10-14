package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Demo API")
                        .version("1.0.0")
                        .description("Cette API fournit des données sur bla bla bla.")
                        .contact(new Contact().name("John Doe").email("john.doe@example.com"))
                );
    }
}
