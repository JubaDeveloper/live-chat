package io.github.jubadeveloper.core.ports;

import io.github.jubadeveloper.several.interfaces.MessageListener;

public interface WebsocketPort {
    void addMessageListener (MessageListener messageListener);
}
