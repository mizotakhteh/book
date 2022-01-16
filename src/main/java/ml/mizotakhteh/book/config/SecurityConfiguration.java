package ml.mizotakhteh.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
                .pathMatchers("/actuator/**", "/books/docs/**", "/v3/api-docs/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/books").permitAll()
                .pathMatchers(HttpMethod.GET, "/books/*").permitAll()
                .anyExchange().authenticated();

        http.oauth2ResourceServer(oauth2 -> oauth2.jwt());

        return http.build();
    }
}
