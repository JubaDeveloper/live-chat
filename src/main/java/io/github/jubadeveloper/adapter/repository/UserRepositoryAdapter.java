package io.github.jubadeveloper.adapter.repository;

import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.core.ports.UserRepositoryPort;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import io.github.jubadeveloper.several.repository.UserRepository;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserRepositoryAdapter implements UserRepositoryPort {
    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser(User user) throws UserAlreadyExistsException, UserAlreadyExistsUsernameException {
        // Validate if user already exists
        Optional<User> existentUser = userRepository.findById(user.getEmail());
        Optional<User> existentUserUsername = userRepository.findByUsername(user.getUsername());
        if (existentUser.isEmpty() && existentUserUsername.isEmpty()) {
            return userRepository.save(user);
        }
        if (existentUserUsername.isPresent()) {
            throw new UserAlreadyExistsUsernameException(user.getUsername());
        }
        throw new UserAlreadyExistsException(user.getEmail());
    }

    @Override
    public User updateUser(String email, User newUser) throws UserNotFoundException {
        return userRepository
                .findById(email)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setUsername(newUser.getUsername());
                    user.setChannels(newUser.getChannels());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User getUser(String email) throws UserNotFoundException {
        return userRepository
                .findById(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String email) throws UserNotFoundException {
        userRepository
                .findById(email)
                .orElseThrow(() ->  new UserNotFoundException(email));
        userRepository.deleteById(email);
    }
}
