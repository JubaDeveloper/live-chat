package org.jubadeveloper.core.services.contracts;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;

public interface UserServiceContract {
    User createUser (User user) throws UserAlreadyExistsException;
}
