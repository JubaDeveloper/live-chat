package org.jubadeveloper.config;

import org.jubadeveloper.adapter.auth.AuthenticatorAdapter;
import org.jubadeveloper.adapter.repository.ChannelRepositoryAdapter;
import org.jubadeveloper.adapter.repository.UserRepositoryAdapter;
import org.jubadeveloper.core.ports.AuthenticatorPort;
import org.jubadeveloper.core.ports.ChannelRepositoryPort;
import org.jubadeveloper.core.ports.UserRepositoryPort;
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
