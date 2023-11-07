package org.jubadeveloper.actors.controller.rest.contracts;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.InvalidLoginException;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserRestControllerContract {
    @PostMapping(path = "/api/user/register")
    User apiCreateUser (@RequestBody User user) throws UserAlreadyExistsException;
    @PostMapping(path = "/api/user/login")
    ResponseEntity<User> apiLoginUser (@RequestBody User user) throws InvalidLoginException;
    @PutMapping(path = "/api/user")
    User apiUpdateUser (@RequestBody String email, @RequestBody User user) throws UserNotFoundException;
    @GetMapping(path = "/api/user/{email}")
    User apiGetUser (@PathVariable String email) throws UserNotFoundException;
}
