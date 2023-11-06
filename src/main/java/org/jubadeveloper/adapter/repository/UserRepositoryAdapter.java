package org.jubadeveloper.adapter.repository;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.ports.UserRepositoryPort;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserRepositoryAdapter implements UserRepositoryPort {
    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        // Validate if user already exists
        Optional<User> existentUser = userRepository.findById(user.getEmail());
        if (existentUser.isEmpty()) {
            return userRepository.save(user);
        }
        throw new UserAlreadyExistsException(user.getEmail());
    }

    @Override
    public User updateUser(String email, User newUser) throws UserAlreadyExistsException {
//        userRepository
//                .findById(email)
//                .map(user -> {
//                    user.setEmail(newUser.getEmail());
//                    user.setUsername(newUser.getUsername());
//                    return user;
//                })
//                .orElseThrow(() -> new UserAlreadyExistsException());
        return null;
    }

    @Override
    public User getUser(String email) throws UserAlreadyExistsException {
        return null;
//        return userRepository
//                .findById(email)
//                .orElseThrow(() -> new UserAlreadyExistsException());
    }

    @Override
    public void deleteUser(String email) throws UserAlreadyExistsException {
//        userRepository
//                .findById(email)
//                .orElseThrow(() ->  new UserAlreadyExistsException());
//        userRepository.deleteById(email);
    }
}
