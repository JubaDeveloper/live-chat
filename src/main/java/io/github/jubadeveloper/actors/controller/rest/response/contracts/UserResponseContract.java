package io.github.jubadeveloper.actors.controller.rest.response.contracts;

import io.github.jubadeveloper.core.domain.User;

public interface UserResponseContract {
    void setUser (User user);
    void setResponse (String response);
    User getUser ();
    String getResponse ();
}
