package org.jubadeveloper.core.services.contracts;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.several.exceptions.AuthenticationException;

public interface AuthServiceContract {
    String auth (User user);
    String checkAuth (String token) throws AuthenticationException;
    String updateToken (String token) throws AuthenticationException;
    String getAuthToken (String authToken);
}
