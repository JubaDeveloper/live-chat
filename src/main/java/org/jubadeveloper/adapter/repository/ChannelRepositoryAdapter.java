package org.jubadeveloper.adapter.repository;

import jakarta.transaction.Transactional;
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
        channel.setCreatorId(newChannel.getCreatorId());
        channel.setName(newChannel.getName());
        return channelRepository.save(channel);
    }

    @Override
    public List<Channel> getAll() {
        return channelRepository.findAll();
    }

    @Override
    @Transactional
    public List<String> getLast10Messages(Long id) throws ChannelNotFoundException {
        Channel channel = channelRepository.findById(id).orElseThrow(() -> new ChannelNotFoundException(id));
        List<String> messages = channel.getMessages();
        int likelyStartIndex = messages.size() - 10;
        int start = Math.max(likelyStartIndex, 0);
        return channel.getMessages().subList(start, messages.size());
    }

    @Override
    @Transactional
    public Channel saveMessage (Long id, String message) throws ChannelNotFoundException {
        Channel channel = channelRepository.findById(id).orElseThrow(() -> new ChannelNotFoundException(id));
        List<String> channelMessages = channel.getMessages();
        channelMessages.add(message);
        channel.setMessages(channelMessages);
        channelRepository.save(channel);
        return channel;
    }
}