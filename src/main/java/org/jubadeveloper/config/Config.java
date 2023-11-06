package org.jubadeveloper.config;

import org.jubadeveloper.adapter.repository.UserRepositoryAdapter;
import org.jubadeveloper.core.ports.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    UserRepositoryPort userRepositoryPort () {
        return new UserRepositoryAdapter();
    }
}
