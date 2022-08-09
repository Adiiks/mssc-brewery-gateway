package com.adrian.mssc.brewery.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
@Configuration
public class LoadBalancedRoutesConfig {

    @Bean
    public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("beer-service", r -> r.path("/api/v1/beer", "/api/v1/beer/**")
                        .uri("lb://beer-service"))
                .route("beer-order-service", r -> r.path("/api/v1/customers/**")
                        .uri("lb://beer-order-service"))
                .route("beer-inventory-service", r -> r.path("api/v1/beer/*/inventory")
                        .uri("lb://beer-inventory-service"))
                .build();
    }
}
