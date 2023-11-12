package io.github.jubadeveloper.adapter.websocket.session;

import io.github.jubadeveloper.core.domain.User;
import org.springframework.web.socket.WebSocketSession;

public record Session (String channelId,
                       WebSocketSession webSocketSession,
                       User user) {
    public String getChannelId() {
        return channelId;
    }
    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }
    public User getUser () { return user; }
}
