package org.jubadeveloper.config;

import graphql.com.google.common.base.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.ChannelService;
import org.jubadeveloper.several.repository.ChannelRepository;
import org.jubadeveloper.several.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseConfig {
    private static final Logger logger = LogManager.getLogger(DatabaseConfig.class);
    @Autowired
    ChannelService channelService;
    @Bean
    CommandLineRunner fillDatabase (@Autowired UserRepository userRepository, @Autowired ChannelRepository channelRepository) {
        return val -> {
            logger.info("Filling up database");
            User user = userRepository.save(new User("juba@gmail.com", "juba", "juba"));
            Channel channelToSave = new Channel(user.getEmail(), "My test channel", "Channel for testing");
            Channel channel = channelService.createChannel(channelToSave);
            List<Channel> userChannels = user.getChannels();
            userChannels.add(channel);
            user.setChannels(userChannels);
            userRepository.save(user);
        };
    }
}
