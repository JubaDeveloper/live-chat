package org.jubadeveloper.adapter.repository;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.ports.ChannelRepositoryPort;
import org.jubadeveloper.several.exceptions.ChannelNotFoundException;
import org.jubadeveloper.several.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChannelRepositoryAdapter implements ChannelRepositoryPort {
    @Autowired
    ChannelRepository channelRepository;
    @Override
    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public Channel getChannel(Long id) throws ChannelNotFoundException {
        return channelRepository
                .findById(id)
                .orElseThrow(() -> new ChannelNotFoundException(id));
    }

    @Override
    public Channel updateChannel(Long id, Channel newChannel) throws ChannelNotFoundException {
        Channel channel = channelRepository.findById(id).orElseThrow(() -> new ChannelNotFoundException(id));
        channel.setUsers(newChannel.getUsers());
        channel.setCreator(newChannel.getCreator());
        return channelRepository.save(channel);
    }

    @Override
    public List<Channel> getAll() {
        return channelRepository.findAll();
    }
}