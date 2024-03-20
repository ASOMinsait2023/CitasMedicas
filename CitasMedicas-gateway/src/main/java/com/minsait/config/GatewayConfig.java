package com.minsait.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class GatewayConfig {
    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {
        return route()
                .path("/api/v1", builder -> builder
                        .nest(GatewayRequestPredicates.path("/pacientes"), pacientesBuilder ->
                                pacientesBuilder.route(GatewayRequestPredicates.path("/**"),
                                        this::forwardToPacientesService)
                        )
                        .nest(GatewayRequestPredicates.path("/doctores"), doctoresBuilder ->
                                doctoresBuilder.route(GatewayRequestPredicates.path("/**"),
                                        this::forwardToDoctoresService)
                        )
                        .nest(GatewayRequestPredicates.path("/especialidades"), especialidadesBuilder ->
                                especialidadesBuilder.route(GatewayRequestPredicates.path("/**"),
                                        this::forwardToEspecialidadesService)

                        )
                        .nest(GatewayRequestPredicates.path("/citas"), citasBuilder ->
                                citasBuilder.route(GatewayRequestPredicates.path("/**"),
                                        this::forwardToCitasService)
                        )
                        .nest(GatewayRequestPredicates.path("/tipocitas"), tipocitasBuilder ->
                                tipocitasBuilder.route(GatewayRequestPredicates.path("/**"),
                                        this::forwardToTipoCitasService)

                        )).build();
    }
@NotNull
    private ServerResponse forwardToTipoCitasService(ServerRequest serverRequest) {
        return ServerResponse.permanentRedirect(URI.create("http://localhost:7097"
                +serverRequest.uri().getPath())).build();

    }
    @NotNull
    private ServerResponse forwardToCitasService(ServerRequest serverRequest) {
        return ServerResponse.permanentRedirect(URI.create("http://localhost:7096"
                +serverRequest.uri().getPath())).build();

    }
    @NotNull
    private ServerResponse forwardToEspecialidadesService(ServerRequest serverRequest) {
        return ServerResponse.permanentRedirect(URI.create("http://localhost:7095"
                +serverRequest.uri().getPath())).build();
    }
    @NotNull
    private ServerResponse forwardToDoctoresService(ServerRequest serverRequest) {
        return ServerResponse.permanentRedirect(URI.create("http://localhost:7091"
                +serverRequest.uri().getPath())).build();
    }
    @NotNull
    private ServerResponse forwardToPacientesService(ServerRequest serverRequest) {
        return ServerResponse.permanentRedirect(URI.create("http://localhost:7090"
                +serverRequest.uri().getPath())).build();
    }
}
