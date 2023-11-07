package org.jubadeveloper.context;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.ChannelService;
import org.jubadeveloper.core.services.UserService;
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
    @Test
    public void testCreateUserChannel () throws Exception {
        // Creation user
        User user = new User("juba@gmail.com", "jubadev", "juba");
        User createdUser = userService.createUser(user);
        List<Channel> userChannels = createdUser.getChannels();
        // Creating user channel
        Channel channel = new Channel(createdUser.getEmail(), "My test channel");
        userChannels.add(channel);
        createdUser.setChannels(userChannels);
        createdUser = userService.updateUser(createdUser.getEmail(), createdUser);
        System.out.println("User channel size: " + createdUser.getChannels().size());
        assertThat(userService.getChannelsByUser(createdUser)).hasSize(1);
        assertThat(userService.getUser(createdUser.getEmail()).getChannels()).hasSize(1);
    }
}
