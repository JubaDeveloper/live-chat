package io.github.jubadeveloper.context;

import io.github.jubadeveloper.core.domain.Channel;
import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.core.services.ChannelService;
import io.github.jubadeveloper.core.services.UserService;
import io.github.jubadeveloper.several.repository.ChannelRepository;
import io.github.jubadeveloper.several.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserChannelCreationContextTest {
    @Autowired
    UserService userService;
    @Autowired
    ChannelService channelService;

    @BeforeAll
    public static void releaseDatabaseToMyUse (
            @Autowired UserRepository userRepository,
            @Autowired ChannelRepository channelRepository) {
        userRepository.deleteAll();
        channelRepository.deleteAll();
    }
    @Test
    public void testCreateUserChannel () throws Exception {
        // Creation user
        User user = new User("juba@gmail.com", "jubadev", "juba");
        User createdUser = userService.createUser(user);
        List<Channel> userChannels = createdUser.getChannels();
        // Creating user channel
        Channel channel = new Channel(createdUser.getEmail(), "My test channel", "Test");
        Channel createdChannel = channelService.createChannel(channel);
        userChannels.add(createdChannel);
        createdUser.setChannels(userChannels);
        createdUser = userService.updateUser(createdUser.getEmail(), createdUser);
        System.out.println("User channel size: " + createdUser.getChannels().size());
        Assertions.assertThat(userService.getChannelsByUser(createdUser)).hasSize(1);
        assertThat(userService.getUser(createdUser.getEmail()).getChannels()).hasSize(1);
    }
}
