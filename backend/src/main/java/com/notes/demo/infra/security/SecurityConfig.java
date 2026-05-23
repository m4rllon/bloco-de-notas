//package com.notes.demo.infra.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
//
//@Configuration //executa a classe sempre q a aplicação spring iniciar
//@EnableWebSecurity //desativa configurações padrões de seg
//public class SecurityConfig {
//    @Bean //o Bean fala pra aplicação que esse método deve ser executado ao iniciar
//    public SecurityFilterChain filterChain(HttpSecurity http)
//        throws Exception {
//        http
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
//                .authorizeHttpRequests(request ->
//                request
//                        .requestMatchers("/login", "/resources/**", "/logout").permitAll()
//                        .requestMatchers("/api/**").permitAll()
//                        .anyRequest().authenticated()
//        // esse método serve pro spring security aplicar alguns filtros para todas as requisições http
//        // esses filtros podem ser manipulados a partir de um objeto do tipo "HttpSecurity"
//        ).oauth2Login(oauth ->
//                // define configurações de login
//                oauth.loginPage("/login").defaultSuccessUrl("/", true)) //define uma página
//                // própria de login e diz pra onde deve ir quando o user for autenticado
//        .logout(logout ->
//                // define regras de logout
//                logout.logoutRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher("/logout")).permitAll()
//                        // define rota padrão de logout e permite qualquer user de acessar essa rota
//                                    .logoutSuccessUrl("/login")); // redireciona o user após o logout
//
//        return http.build();
//    }
//}
