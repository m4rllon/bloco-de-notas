package com.notes.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
        throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests(request ->
                request
                        .requestMatchers("/login", "/resources/**", "/logout").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()
        ).oauth2Login(oauth ->
                oauth.loginPage("/login").defaultSuccessUrl("/", true))
        .logout(logout ->
                logout.logoutRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher("/logout")).permitAll()
                                    .logoutSuccessUrl("/login"));

        return http.build();
    }
}
