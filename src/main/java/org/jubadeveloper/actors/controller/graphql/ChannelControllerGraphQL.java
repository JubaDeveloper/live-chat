package org.jubadeveloper.actors.controller.graphql;

import org.jubadeveloper.actors.controller.graphql.contracts.ChannelControllerGraphQLContract;
import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.ChannelService;
import org.jubadeveloper.core.services.UserService;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChannelControllerGraphQL implements ChannelControllerGraphQLContract {
    @Autowired
    ChannelService channelService;
    @Autowired
    UserService userService;
    @MutationMapping
    @Override
    public Channel insertChannel(@Argument Channel channel) {
        return channelService.createChannel(channel);
    }
    @MutationMapping
    @Override
    public Channel updateChannel(@Argument Long id, @Argument Channel newChannel) throws ChannelNotFoundException {
        return channelService.updateChannel(id, newChannel);
    }
    @QueryMapping
    @Override
    public Channel getChannel(@Argument Long id) throws ChannelNotFoundException {
        return channelService.getChannelById(id);
    }
    @QueryMapping
    @Override
    public List<Channel> getChannels() {
        return channelService.getAllChannels();
    }

    @SchemaMapping
    @Override
    public User owner(Channel channel) throws UserNotFoundException {
        return userService.getUser(channel.getCreatorId());
    }
}
