package org.jubadeveloper.core.services;

import org.jubadeveloper.adapter.repository.UserRepositoryAdapter;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.ports.UserRepositoryPort;
import org.jubadeveloper.core.services.contracts.UserServiceContract;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceContract {
    @Autowired
    UserRepositoryPort userRepositoryPort;
    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        return userRepositoryPort.createUser(user);
    }
}
