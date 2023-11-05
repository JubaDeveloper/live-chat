package org.jubadeveloper.core.ports;

import org.jubadeveloper.several.interfaces.MessageListener;

public interface WebsocketPort {
    void addMessageListener (MessageListener messageListener);
}
