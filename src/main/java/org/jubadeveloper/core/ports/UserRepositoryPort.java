package org.jubadeveloper.core.ports;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;

import java.util.List;

public interface UserRepositoryPort {
    User createUser (User user) throws UserAlreadyExistsException, UserAlreadyExistsUsernameException;
    User updateUser (String email, User newUser) throws UserNotFoundException;
    User getUser (String email) throws UserNotFoundException;
    List<User> getAllUsers ();
    void deleteUser (String email) throws UserNotFoundException;
}
