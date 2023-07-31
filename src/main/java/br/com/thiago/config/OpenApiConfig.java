package br.com.thiago.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTFULL API with Java 18 and Spring Boot 3")
                        .version("v1")
                        .description("descrição da API")
                        .termsOfService("https://github.com/Thiago-CSantos?tab=repositories")
                        .license(new License()
                                .name("https://github.com/Thiago-CSantos?tab=repositories")
                                .url("https://github.com/Thiago-CSantos?tab=repositories")
                        )
                );
    }

}
