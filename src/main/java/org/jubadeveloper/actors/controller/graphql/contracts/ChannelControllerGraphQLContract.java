package org.jubadeveloper.actors.controller.graphql.contracts;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;

import java.util.List;

public interface ChannelControllerGraphQLContract {
    Channel insertChannel(Channel channel);
    Channel updateChannel(Long id, Channel newChannel) throws ChannelNotFoundException;
    Channel getChannel(Long id) throws ChannelNotFoundException;
    List<Channel> getChannels();
    User owner (Channel channel) throws UserNotFoundException;

}
