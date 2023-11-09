package org.jubadeveloper.adapter.repository;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.ports.UserRepositoryPort;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.jubadeveloper.several.repository.UserRepository;
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
