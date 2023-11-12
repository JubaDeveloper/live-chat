package io.github.jubadeveloper.core.services.contracts;

import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import io.github.jubadeveloper.core.domain.Channel;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;

import java.util.List;

public interface UserServiceContract {
    User createUser (User user) throws UserAlreadyExistsException, UserAlreadyExistsUsernameException;
    User getUser (String email) throws UserNotFoundException;
    List<User> getAllUsers ();
    User updateUser (String email, User newUser) throws UserNotFoundException;
    List<Channel> getChannelsByUser (User user);
}
