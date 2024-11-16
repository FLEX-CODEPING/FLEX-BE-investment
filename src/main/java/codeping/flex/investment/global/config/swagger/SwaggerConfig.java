package codeping.flex.investment.global.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server.url}")
    private String serverUrl;

    @Bean
    @Profile("local")
    public OpenAPI localOpenAPI() {
        return createOpenAPI(getLocalServer());
    }

    @Bean
    @Profile("dev")
    public OpenAPI devOpenAPI() {
        return createOpenAPI(getDevServer());
    }

    private OpenAPI createOpenAPI(Server server) {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")))
                .servers(List.of(server))
                .info(new Info().title("FLEX 모의투자 API").version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

    private Server getLocalServer() {
        return new Server()
                .url(serverUrl)
                .description("Local Server");
    }

    private Server getDevServer() {
        return new Server()
                .url(serverUrl)
                .description("Development Server");
    }
}