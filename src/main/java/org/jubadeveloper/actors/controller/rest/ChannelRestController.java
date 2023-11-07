package org.jubadeveloper.actors.controller.rest;

import org.jubadeveloper.actors.controller.rest.contracts.ChannelRestControllerContract;
import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.services.ChannelService;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChannelRestController implements ChannelRestControllerContract {
    @Autowired
    ChannelService channelService;
    @Override
    public Channel apiCreateChannel(String userEmail, Channel channel) {
        channel.setCreatorId(userEmail);
        return channelService.createChannel(channel);
    }

    @Override
    public Channel apiUpdateChannel(String userEmail, Long channelId, Channel channel) throws ChannelNotFoundException {
        Channel channelToUpdate = channelService.getChannelById(channelId);
        if (channelToUpdate.getCreatorId().equals(userEmail)) {
            channel.setCreatorId(userEmail);
            channel.setId(channelId);
            return channelService.updateChannel(channelId, channel);
        }
        throw new ChannelNotFoundException(channelId);
    }

    @Override
    public Channel apiGetChannel(String userEmail, Long channelId) throws ChannelNotFoundException {
        Channel channel = channelService.getChannelById(channelId);
        if (channel.getCreatorId().equals(userEmail)) {
            return channel;
        }
        throw new ChannelNotFoundException(channelId);
    }
}
