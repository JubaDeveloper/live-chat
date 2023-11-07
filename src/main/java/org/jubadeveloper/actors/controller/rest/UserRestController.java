package org.jubadeveloper.actors.controller.rest;

import org.jubadeveloper.actors.controller.rest.contracts.UserRestControllerContract;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.AuthService;
import org.jubadeveloper.core.services.UserService;
import org.jubadeveloper.several.exceptions.InvalidLoginException;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class UserRestController implements UserRestControllerContract {
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Override
    public User apiCreateUser(User user) throws UserAlreadyExistsException {
        return userService.createUser(user);
    }

    @Override
    public ResponseEntity<User> apiLoginUser(User user) throws InvalidLoginException {
        try {
            User user1 = userService.getUser(user.getEmail());
            if (user1.getPassword().equals(user.getPassword())) {
                String authToken = authService.auth(user1);
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Set-Cookie",
                         "X-Authorization=" + String.format("%s", authToken) + ";");
                return ResponseEntity
                        .ok()
                        .headers(responseHeaders)
                        .body(user1);
            }
        } catch (UserNotFoundException userNotFoundException) {
            throw new InvalidLoginException();
        }
        return null;
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
