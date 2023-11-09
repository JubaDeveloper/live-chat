package org.jubadeveloper.actors.controller.graphql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jubadeveloper.actors.controller.graphql.contracts.UserControllerGraphQLContract;
import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.UserService;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserControllerGraphQL implements UserControllerGraphQLContract {
    private static final Logger logger = LogManager.getLogger(UserControllerGraphQL.class);
    @Autowired
    UserService userService;
    @Override
    @MutationMapping
    public User insertUser (@Argument User user) throws UserAlreadyExistsException, UserAlreadyExistsUsernameException {
        return userService.createUser(user);
    }

    @Override
    @MutationMapping
    public User updateUser(@Argument String email, @Argument User newUser) throws UserNotFoundException {
        return userService.updateUser(email, newUser);
    }

    @Override
    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @SchemaMapping
    public List<Channel> channels(User user) {
        return userService.getChannelsByUser(user);
    }
}
