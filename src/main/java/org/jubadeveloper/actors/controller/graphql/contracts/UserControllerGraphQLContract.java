package org.jubadeveloper.actors.controller.graphql.contracts;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;

import java.util.List;

public interface UserControllerGraphQLContract {
    User insertUser (User user) throws UserAlreadyExistsException;
    User updateUser (String email, User newUser) throws UserNotFoundException;
    List<User> getAllUsers();
    List<Channel> channels (User user);
}
