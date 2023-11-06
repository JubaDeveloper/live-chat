package org.jubadeveloper.core.services.contracts;

import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;

import java.util.List;

public interface UserServiceContract {
    User createUser (User user) throws UserAlreadyExistsException;
    User getUser (String email) throws UserNotFoundException;
    User updateUser (String email, User newUser) throws UserNotFoundException;
    List<Channel> getChannelsByUser (User user);
}
