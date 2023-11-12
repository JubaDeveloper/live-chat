package org.jubadeveloper.core.services.contracts;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;

import java.util.List;

public interface ChannelServiceContract {
    Channel createChannel (Channel channel);
    Channel getChannelById (Long id) throws ChannelNotFoundException;
    Channel updateChannel (Long id, Channel newChannel) throws ChannelNotFoundException;
    List<Channel> getAllChannels ();
    Channel saveMessage (Long id, String message) throws ChannelNotFoundException;
    List<String> getLast10Messages (Long id) throws ChannelNotFoundException;
}
