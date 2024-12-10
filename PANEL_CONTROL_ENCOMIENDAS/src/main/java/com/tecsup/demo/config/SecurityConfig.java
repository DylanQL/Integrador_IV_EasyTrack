package com.tecsup.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    public SecurityConfig(AuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login**").permitAll()
                                .requestMatchers("/h2-console/**").permitAll() // Permitir acceso a la consola H2
                                .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/login")
                                .defaultSuccessUrl("/header", true) // Redirige a /header después del inicio de sesión
                                .successHandler(customAuthenticationSuccessHandler)
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/login")
                );

        // Deshabilitar CSRF para la consola H2
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}