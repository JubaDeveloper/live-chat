package org.jubadeveloper.core.ports;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;

public interface UserRepositoryPort {
    User createUser (User user) throws UserAlreadyExistsException;
    User updateUser (String email, User newUser) throws UserNotFoundException;
    User getUser (String email) throws UserNotFoundException;
    void deleteUser (String email) throws UserNotFoundException;
}
