package org.jubadeveloper.actors.controller.rest.contracts;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;

public interface ChannelRestControllerContract {
    Channel apiCreateChannel (User user, Channel channel); // User should be passed through some auth method
    Channel apiUpdateChannel (User user, Long channelId, Channel channel) throws ChannelNotFoundException;
    Channel apiGetChannel (User user, Long channelId) throws ChannelNotFoundException;
}
