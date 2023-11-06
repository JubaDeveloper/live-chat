package org.jubadeveloper.domain;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.services.ChannelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ChannelDomainTest {
    @Autowired
    ChannelService channelService;
    @Test
    void testChannelCreation () {
        Channel channel = new Channel();
        Channel createdChannel = channelService.createChannel(channel);
        assertThat(createdChannel.getId()).isNotNull();
    }

}
