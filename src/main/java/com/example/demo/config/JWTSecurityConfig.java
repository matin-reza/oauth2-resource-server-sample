package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JWTSecurityConfig {

    @Bean
    protected DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth ->
                        auth
                                .requestMatchers("/test").hasRole("custom_string_value")
                                // Require authentication for any other requests
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter())));
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new CustomJwtGrantedAuthoritiesConverter());
        return jwtConverter;
    }

    /*@Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("http://localhost:8080/realms/nobino");
    }*/
}
