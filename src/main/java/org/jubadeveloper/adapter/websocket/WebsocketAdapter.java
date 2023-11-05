package org.jubadeveloper.adapter.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jubadeveloper.core.ports.WebsocketPort;
import org.jubadeveloper.several.interfaces.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebsocketAdapter extends TextWebSocketHandler implements WebsocketPort {
    MessageListener messageListener = null;
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        this.messageListener.onMessage(message.getPayload());
    }

    @Override
    public void addMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}

