package org.jubadeveloper.core.services;

import org.jubadeveloper.adapter.repository.UserRepositoryAdapter;
import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.ports.ChannelRepositoryPort;
import org.jubadeveloper.core.ports.UserRepositoryPort;
import org.jubadeveloper.core.services.contracts.UserServiceContract;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.jubadeveloper.several.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceContract {
    @Autowired
    UserRepositoryPort userRepositoryPort;
    @Autowired
    ChannelRepositoryPort channelRepositoryPort;
    @Override
    public User createUser(User user) throws UserAlreadyExistsException, UserAlreadyExistsUsernameException {
        return userRepositoryPort.createUser(user);
    }

    @Override
    public User getUser(String email) throws UserNotFoundException {
        return userRepositoryPort.getUser(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.getAllUsers();
    }

    @Override
    public User updateUser(String email, User newUser) throws UserNotFoundException {
        return userRepositoryPort.updateUser(email, newUser);
    }

    @Override
    public List<Channel> getChannelsByUser(User user) {
        return channelRepositoryPort
                .getAll()
                .stream()
                .filter(channel -> channel.getUsers()
                        .stream()
                        .filter(user1 -> user1.getEmail().equals(user.getEmail())).
                        toList().size() > 0)
                .toList();
    }
}
