package io.github.jubadeveloper.config;

import io.github.jubadeveloper.adapter.auth.AuthenticatorAdapter;
import io.github.jubadeveloper.adapter.repository.UserRepositoryAdapter;
import io.github.jubadeveloper.core.ports.AuthenticatorPort;
import io.github.jubadeveloper.core.ports.ChannelRepositoryPort;
import io.github.jubadeveloper.core.ports.UserRepositoryPort;
import io.github.jubadeveloper.adapter.repository.ChannelRepositoryAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

@Configuration
public class Config {
    @Bean
    UserRepositoryPort userRepositoryPort () {
        return new UserRepositoryAdapter();
    }
    @Bean
    ChannelRepositoryPort channelRepositoryPort () {
        return new ChannelRepositoryAdapter();
    }
    @Bean
    AuthenticatorPort authenticatorPort (@Value("${properties.private_key}") String privateKey) throws UnsupportedEncodingException {
        return new AuthenticatorAdapter(privateKey);
    }
}
