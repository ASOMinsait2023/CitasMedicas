package com.minsait.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers(
                            "/api/v1/pacientes/**",
                                    "/api/v1/doctores/**",
                                    "/api/v1/especialidades/**",
                                    "/api/v1/citas/**",
                                    "/api/v1/tipocitas/**").authenticated();
                })
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        return new InMemoryUserDetailsManager(
                User.withUsername("exel")
                        .password(passwordEncoder().encode("12345"))
                        .build()
        );
    }












}
