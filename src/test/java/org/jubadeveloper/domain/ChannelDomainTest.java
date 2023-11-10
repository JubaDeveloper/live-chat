package org.jubadeveloper.domain;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.services.ChannelService;
import org.jubadeveloper.several.repository.ChannelRepository;
import org.jubadeveloper.several.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ChannelDomainTest {
    @Autowired
    ChannelService channelService;
    @AfterAll
    static void releaseDatabaseToMyUse (@Autowired ChannelRepository channelRepository) {
        channelRepository.deleteAll();
    }
    @BeforeAll
    public static void releaseDatabaseToMyUse (
            @Autowired UserRepository userRepository,
            @Autowired ChannelRepository channelRepository) {
        userRepository.deleteAll();
        channelRepository.deleteAll();
    }
    @Test
    void testChannelCreation () {

    }

}
