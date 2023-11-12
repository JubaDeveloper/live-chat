package io.github.jubadeveloper.core.services;

import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.core.ports.UserRepositoryPort;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import io.github.jubadeveloper.core.domain.Channel;
import io.github.jubadeveloper.core.ports.ChannelRepositoryPort;
import io.github.jubadeveloper.core.services.contracts.UserServiceContract;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;
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
