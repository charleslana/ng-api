package com.charles.ngapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class NgConfig {

    private final BCryptPasswordEncoder encoder;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
        };
    }
}
