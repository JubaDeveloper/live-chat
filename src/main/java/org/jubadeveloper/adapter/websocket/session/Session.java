package org.jubadeveloper.adapter.websocket.session;

import org.springframework.web.socket.WebSocketSession;

public record Session (String channelId,
        WebSocketSession webSocketSession) {
    public String getChannelId() {
        return channelId;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }
}
