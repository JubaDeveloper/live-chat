package org.jubadeveloper.core.ports;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;

import java.util.List;

public interface ChannelRepositoryPort {
    Channel createChannel (Channel channel);
    Channel getChannel (Long id) throws ChannelNotFoundException;
    Channel updateChannel (Long id, Channel newChannel) throws ChannelNotFoundException;
    Channel saveMessage (Long id, String message) throws ChannelNotFoundException;
    List<Channel> getAll ();
    List<String> getLast10Messages (Long id) throws ChannelNotFoundException;
}
