package com.solutions.medilabo.gateway.gateway;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = "*")
public class RisqueGateway {

    @Value("${routes.risque}")
    private String risqueRoute;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("risque-route", r -> r
                        .path("/risque/**")
                        .uri(risqueRoute)
                )
                .build();
    }
}
