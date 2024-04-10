package com.arquivos.arquivos.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SecurityScheme(
    name = "Authentication: token",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer"
)
@OpenAPIDefinition(security = @SecurityRequirement(name = "Authentication: token"))
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    private static final String DESCRICAO = "Excel manager";
    private static final String URL = "igor_bavand.dev";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api/docs", "/swagger-ui.html");
    }

    @Bean
    public OpenAPI springShopOpenApi() {
        return new OpenAPI()
            .info(new Info().title("Excel manager API")
                .description(DESCRICAO)
                .version("1.0.0")
                .license(new License().url(URL)));
    }
}