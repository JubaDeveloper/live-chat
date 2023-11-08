package org.jubadeveloper.actors.controller.rest.contracts;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.InvalidLoginException;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/api")
public interface UserRestControllerContract {
    @PostMapping(path = "/user/register")
    User apiCreateUser (@RequestBody User user) throws UserAlreadyExistsException;
    @PostMapping(path = "/user/login")
    ResponseEntity<User> apiLoginUser (@RequestBody User user) throws InvalidLoginException;
    @PutMapping(path = "/user")
    User apiUpdateUser (@RequestBody String email, @RequestBody User user) throws UserNotFoundException;
    @GetMapping(path = "/user/{email}")
    User apiGetUser (@PathVariable String email) throws UserNotFoundException;
}
