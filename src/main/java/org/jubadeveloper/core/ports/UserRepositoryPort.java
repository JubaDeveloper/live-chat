package org.jubadeveloper.core.ports;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;

public interface UserRepositoryPort {
    User createUser (User user) throws UserAlreadyExistsException;
    User updateUser (String email, User newUser) throws UserAlreadyExistsException;
    User getUser (String email) throws UserAlreadyExistsException;
    void deleteUser (String email) throws UserAlreadyExistsException;
}
