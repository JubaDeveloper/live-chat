package io.github.jubadeveloper.core.services.contracts;

import io.github.jubadeveloper.several.exceptions.AuthenticationException;
import io.github.jubadeveloper.core.domain.User;

public interface AuthServiceContract {
    String auth (User user);
    String checkAuth (String token) throws AuthenticationException;
    String updateToken (String token) throws AuthenticationException;
    String getAuthToken (String authToken);
}
