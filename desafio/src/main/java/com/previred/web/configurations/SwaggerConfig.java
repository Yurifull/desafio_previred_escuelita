package com.previred.web.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Company and workers management API")
                        .version("1.0")
                        .description((
                                """
                                        This REST API provides endpoints to manage companies and workers.
                                        It can perform CRUD operations on Worker and Company entities. \r
                                        \r
                                        In addition from CRUD operations, We can get a company or worker by its RUT, and also add a worker to a company. \r
                                        \r
                                        Everything is well-documented with clear examples and responses."""))
                        .license(new License().name("API by Diego Fuentes").url("https://www.diegofuentes.dev")));

    }
}
