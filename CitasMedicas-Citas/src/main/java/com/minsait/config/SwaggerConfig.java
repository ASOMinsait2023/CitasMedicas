package com.minsait.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public  OpenAPI springOpenApi(){
        return new OpenAPI().info(new Info()
                .title("Citas API")
                .description("Citas API")
                .version("0.0.1-SNAPSHOT")
        ).externalDocs(new ExternalDocumentation()
                .description("Spring-openapi")
                .url("http://springdoc.org"));

    }
}
