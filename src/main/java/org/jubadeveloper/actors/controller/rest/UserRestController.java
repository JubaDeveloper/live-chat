package org.jubadeveloper.actors.controller.rest;

import org.jubadeveloper.actors.controller.rest.contracts.UserRestControllerContract;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.UserService;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController implements UserRestControllerContract {
    @Autowired
    UserService userService;
    @Override
    public User apiCreateUser(User user) throws UserAlreadyExistsException {
        return userService.createUser(user);
    }
    @Override
    public User apiUpdateUser(String email, User user) throws UserNotFoundException {
        return userService.updateUser(email, user);
    }
    @Override
    public User apiGetUser(String email) throws UserNotFoundException {
        return userService.getUser(email);
    }
}
