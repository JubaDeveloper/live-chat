package org.jubadeveloper.actors.controller.rest.contracts;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

public interface UserRestControllerContract {
    @PostMapping(path = "/api/user")
    User apiCreateUser (@RequestBody User user) throws UserAlreadyExistsException;
    @PutMapping(path = "/api/user")
    User apiUpdateUser (@RequestBody String email, @RequestBody User user) throws UserNotFoundException;
    @GetMapping(path = "/api/user/{email}")
    User apiGetUser (@PathVariable String email) throws UserNotFoundException;
}
