package org.jubadeveloper.core.services;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.ports.ChannelRepositoryPort;
import org.jubadeveloper.core.services.contracts.ChannelServiceContract;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService implements ChannelServiceContract {
    @Autowired
    ChannelRepositoryPort channelRepositoryPort;

    @Override
    public Channel createChannel(Channel channel) {
        return channelRepositoryPort.createChannel(channel);
    }

    @Override
    public Channel getChannelById(Long id) throws ChannelNotFoundException {
        return channelRepositoryPort.getChannel(id);
    }

    @Override
    public Channel updateChannel (Long id, Channel newChannel) throws ChannelNotFoundException {
        return channelRepositoryPort.updateChannel(id, newChannel);
    }

    @Override
    public List<Channel> getAllChannels() {
        return channelRepositoryPort.getAll();
    }

    @Override
    public Channel saveMessage(Long id, String message) throws ChannelNotFoundException {
        return channelRepositoryPort.saveMessage(id, message);
    }

    @Override
    public List<String> getLast10Messages(Long id) throws ChannelNotFoundException {
        return channelRepositoryPort.getLast10Messages(id);
    }
}
