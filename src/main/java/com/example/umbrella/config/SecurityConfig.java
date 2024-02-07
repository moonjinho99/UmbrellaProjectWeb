package com.example.umbrella.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoding(){
        return new BCryptPasswordEncoder();
    }

}
