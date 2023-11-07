package org.jubadeveloper.domain;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.services.ChannelService;
import org.jubadeveloper.several.repository.ChannelRepository;
import org.junit.jupiter.api.AfterAll;
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
    @Test
    void testChannelCreation () {
        Channel channel = new Channel();
        Channel createdChannel = channelService.createChannel(channel);
        assertThat(createdChannel.getId()).isNotNull();
    }

}
