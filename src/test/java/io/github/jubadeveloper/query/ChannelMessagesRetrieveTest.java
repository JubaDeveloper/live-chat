package io.github.jubadeveloper.query;

import io.github.jubadeveloper.core.domain.Channel;
import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.core.services.ChannelService;
import io.github.jubadeveloper.several.exceptions.ChannelNotFoundException;
import io.github.jubadeveloper.several.repository.ChannelRepository;
import io.github.jubadeveloper.several.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ChannelMessagesRetrieveTest {
    public static Channel channel;
    @Autowired
    ChannelService channelService;
    private static final Logger logger = LogManager.getLogger(ChannelMessagesRetrieveTest.class);
    @BeforeAll
    public static void releaseAndSetDatabase (
        @Autowired UserRepository userRepository,
        @Autowired ChannelRepository channelRepository) {
            userRepository.deleteAll();
            channelRepository.deleteAll();
            User user = userRepository.save(new User("juba@gmail.com", "juba", "juba"));
            Channel channelToSave = new Channel(user.getEmail(), "My test channel", "Test");
            List<User> channelMembers = channelToSave.getUsers();
            channelMembers.add(user);
            channelToSave.setUsers(channelMembers);
            channel = channelRepository.save(channelToSave);
            // Saving 20 messages to testing
            String mutableMessage = "A";
            List<String> messagesToSaves = new ArrayList<>();
            for (int x = 0; x < 20; x++) {
                messagesToSaves.add(mutableMessage);
                mutableMessage += " A";
            }
            channel.setMessages(messagesToSaves);
            channelRepository.save(channel);
    }
    @Test
    public void retrieveLast10Messages () throws ChannelNotFoundException {
        List<String> last10Messages = channelService.getLast10Messages(channel.getId());
        Assertions.assertThat(last10Messages).hasSize(10);
        logger.info("Messages retrieved: " + last10Messages);
    }
}
