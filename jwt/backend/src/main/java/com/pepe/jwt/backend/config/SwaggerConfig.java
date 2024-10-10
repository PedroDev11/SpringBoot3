package com.pepe.jwt.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "API JWT",
                description = "A API for registry and login using JWT",
                version = "1.0.0",
                contact = @Contact(
                        name = "Pedro Rojas",
                        email = "pedro@gmail.com"
                ),
                license = @License(
                        name = "Standard Software Use License for Programming"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "DEV SERVER"
                ),
                @Server(
                        url = "http://programing:8080",
                        description = "PROD SERVER"
                )
        },
        security = @SecurityRequirement(
                name = "Security Token"
        )
)
@SecurityScheme(
        name = "Security Token",
        description = "Access Token for my API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
