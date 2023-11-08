package org.jubadeveloper.several.interfaces;

import org.jubadeveloper.core.domain.User;

public interface MessageListener {
    void onMessage (User user, String message);
}
