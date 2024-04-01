package com.phsz.gateway;

import com.nimbusds.jose.JOSEException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http, JwtProperties jwtProperties) throws JOSEException {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtProperties);
        // @formatter:off
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges ->
                        exchanges
                                .pathMatchers("/api/auth/**").permitAll()
                                .anyExchange().permitAll()
                )
                .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHORIZATION);
        // @formatter:on
        return http.build();
    }

}
