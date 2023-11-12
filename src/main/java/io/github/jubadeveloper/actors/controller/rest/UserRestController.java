package io.github.jubadeveloper.actors.controller.rest;

import io.github.jubadeveloper.actors.controller.rest.contracts.UserRestControllerContract;
import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.core.services.AuthService;
import io.github.jubadeveloper.core.services.UserService;
import io.github.jubadeveloper.several.exceptions.InvalidLoginException;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController implements UserRestControllerContract {
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Override
    public User apiCreateUser(User user) throws UserAlreadyExistsException, UserAlreadyExistsUsernameException {
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
                         "X-Authorization=" + String.format("%s", authToken) + ";path=/");
                return ResponseEntity
                        .ok()
                        .headers(responseHeaders)
                        .body(user1);
            }
            throw new InvalidLoginException();
        } catch (UserNotFoundException userNotFoundException) {
            throw new InvalidLoginException();
        }
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
