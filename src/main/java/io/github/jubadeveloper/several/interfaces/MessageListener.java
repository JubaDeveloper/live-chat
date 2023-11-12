package io.github.jubadeveloper.several.interfaces;

import io.github.jubadeveloper.core.domain.User;

public interface MessageListener {
    void onMessage (User user, String message);
}
