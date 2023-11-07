package org.jubadeveloper.actors.controller.rest.response.contracts;

import org.jubadeveloper.core.domain.User;

public interface UserResponseContract {
    void setUser (User user);
    void setResponse (String response);
    User getUser ();
    String getResponse ();
}
