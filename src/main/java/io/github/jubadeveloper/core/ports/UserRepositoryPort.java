package io.github.jubadeveloper.core.ports;

import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;

import java.util.List;

public interface UserRepositoryPort {
    User createUser (User user) throws UserAlreadyExistsException, UserAlreadyExistsUsernameException;
    User updateUser (String email, User newUser) throws UserNotFoundException;
    User getUser (String email) throws UserNotFoundException;
    List<User> getAllUsers ();
    void deleteUser (String email) throws UserNotFoundException;
}
