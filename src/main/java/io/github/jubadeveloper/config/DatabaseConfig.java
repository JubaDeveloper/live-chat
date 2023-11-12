package io.github.jubadeveloper.config;

import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.several.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.github.jubadeveloper.core.domain.Channel;
import io.github.jubadeveloper.core.services.ChannelService;
import io.github.jubadeveloper.several.repository.ChannelRepository;
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
