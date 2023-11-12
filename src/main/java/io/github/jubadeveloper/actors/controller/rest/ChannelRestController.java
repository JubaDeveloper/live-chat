package io.github.jubadeveloper.actors.controller.rest;

import io.github.jubadeveloper.actors.controller.rest.contracts.ChannelRestControllerContract;
import io.github.jubadeveloper.core.domain.Channel;
import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.core.services.ChannelService;
import io.github.jubadeveloper.core.services.UserService;
import io.github.jubadeveloper.several.exceptions.ChannelNotFoundException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;
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
        Channel createdChannel = channelService.createChannel(channel);
        userChannels.add(createdChannel);
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

    @Override
    public List<Channel> apiGetChannels() {
        return channelService.getAllChannels();
    }
}
