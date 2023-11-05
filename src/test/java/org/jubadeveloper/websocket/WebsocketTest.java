package org.jubadeveloper.websocket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.Socket;
import java.net.URI;
import java.time.Duration;

@SpringBootTest
public class WebsocketTest {
    @Test
    public void enableWebsocket () throws Exception {
        System.out.println("Testing websocket layer");
        WebSocketClient webSocketClient = new ReactorNettyWebSocketClient();
        URI uri = URI.create("ws://localhost:8080");
        webSocketClient.execute(uri, session -> session
                        .send(Mono.just(session.textMessage("Event client message")))
                        .thenMany(session.receive()
                                .map(WebSocketMessage::getPayloadAsText)
                                .log()
                        )
                        .then())
                .block(Duration.ofSeconds(10L));
    }
}
