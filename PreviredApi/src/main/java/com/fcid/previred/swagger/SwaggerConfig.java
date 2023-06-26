package com.fcid.previred.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

	@Bean
	public OpenAPI api() {
		return new OpenAPI()
				.info(new Info().title("API de gestion de empresas y trabajadores").version("1.0").description(
						"Este proyecto consiste en una API REST que proporciona endpoints para administrar empresas y trabajadores. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en entidades de empresa y trabajador.\r\n"
								+ "\r\n"
								+ "La API incluye endpoints para crear, obtener, actualizar y eliminar empresas, así como para realizar las mismas acciones con trabajadores. Los endpoints están documentados con descripciones claras y concisas, indicando las respuestas HTTP esperadas y los posibles errores."));
	}
}
