package org.jubadeveloper.actors.controller.rest;

import org.jubadeveloper.actors.controller.rest.contracts.ChannelRestControllerContract;
import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.ChannelService;
import org.jubadeveloper.core.services.UserService;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChannelRestController implements ChannelRestControllerContract {
    @Autowired
    ChannelService channelService;
    @Autowired
    UserService userService;
    @Override
    public Channel apiCreateChannel(String userEmail, Channel channel) throws UserNotFoundException {
        channel.setCreatorId(userEmail);
        User user = userService.getUser(userEmail);
        List<Channel> userChannels = user.getChannels();
        userChannels.add(channel);
        user.setChannels(userChannels);
        userService.updateUser(user.getEmail(), user);
        return channel;
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
