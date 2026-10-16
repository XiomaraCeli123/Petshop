package com.dominio.PetShop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
        return http
                .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/products/create", "/products/update") // Excluir CSRF para rutas de creación/actualización
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()  // Acceso libre a la página de inicio
                        .requestMatchers("/contact").permitAll()  // Acceso libre a contacto
                        .requestMatchers("/store/**").permitAll()  // Acceso libre a store
                        .requestMatchers("/register").permitAll()  // Acceso libre a registro
                        .requestMatchers("/login").permitAll()  // Acceso libre a login
                        .requestMatchers("/logout").permitAll()  // Acceso libre a logout
                        .requestMatchers("/novedades.html").permitAll()  // Permitir acceso libre a /novedades (estática)
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()  // Archivos estáticos
                        .requestMatchers("/products/**").authenticated()  // Acceso solo autenticado a productos
                        .requestMatchers("/products/create", "/products/update" ).hasRole("ADMIN")  // Solo ADMIN puede crear o actualizar productos
                        .requestMatchers("/export-pdf").permitAll()  // Permitir acceso libre a la exportación a PDF

                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)  
                )
                .logout(config -> config
                        .logoutSuccessUrl("/") 
                )
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  
    }
}