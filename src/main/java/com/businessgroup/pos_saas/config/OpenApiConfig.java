package com.businessgroup.pos_saas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
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
                        .title("POS SaaS API")
                        .version("1.0")
                        .description("Documentación del sistema POS SaaS para gestión multiempresa.")
                        .termsOfService("https://businessgroup.com/terms")
                        .contact(new Contact()
                                .name("Business Group D&J")
                                .url("https://businessgroup.com")
                                .email("soporte@businessgroup.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
