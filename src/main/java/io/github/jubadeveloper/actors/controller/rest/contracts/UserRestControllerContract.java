package io.github.jubadeveloper.actors.controller.rest.contracts;

import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.several.exceptions.InvalidLoginException;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import io.github.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/api")
public interface UserRestControllerContract {
    @PostMapping(path = "/user/register")
    User apiCreateUser (@RequestBody User user) throws UserAlreadyExistsException, UserAlreadyExistsUsernameException;
    @PostMapping(path = "/user/login")
    ResponseEntity<User> apiLoginUser (@RequestBody User user) throws InvalidLoginException;
    @PutMapping(path = "/user")
    User apiUpdateUser (@RequestBody String email, @RequestBody User user) throws UserNotFoundException;
    @GetMapping(path = "/user/{email}")
    User apiGetUser (@PathVariable String email) throws UserNotFoundException;
}
