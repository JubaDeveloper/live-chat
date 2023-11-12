package io.github.jubadeveloper.actors.controller.graphql.contracts;

import io.github.jubadeveloper.core.domain.Channel;
import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.several.exceptions.ChannelNotFoundException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;

import java.util.List;

public interface ChannelControllerGraphQLContract {
    Channel insertChannel(Channel channel);
    Channel updateChannel(Long id, Channel newChannel) throws ChannelNotFoundException;
    Channel getChannel(Long id) throws ChannelNotFoundException;
    List<Channel> getChannels();
    User owner (Channel channel) throws UserNotFoundException;

}
