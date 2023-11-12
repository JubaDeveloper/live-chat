package io.github.jubadeveloper.actors.controller.graphql.contracts;

import io.github.jubadeveloper.core.domain.Channel;
import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;

import java.util.List;

public interface UserControllerGraphQLContract {
    User insertUser (User user) throws UserAlreadyExistsException, UserAlreadyExistsUsernameException;
    User updateUser (String email, User newUser) throws UserNotFoundException;
    List<User> getAllUsers();
    List<Channel> channels (User user);
}
