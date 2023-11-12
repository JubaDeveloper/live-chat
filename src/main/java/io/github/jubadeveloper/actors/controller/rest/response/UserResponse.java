package io.github.jubadeveloper.actors.controller.rest.response;

import io.github.jubadeveloper.actors.controller.rest.response.contracts.UserResponseContract;
import io.github.jubadeveloper.core.domain.User;

public class UserResponse implements UserResponseContract {
    User user;
    String response;
    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String getResponse() {
        return response;
    }
}
