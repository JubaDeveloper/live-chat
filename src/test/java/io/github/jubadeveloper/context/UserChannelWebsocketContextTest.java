package io.github.jubadeveloper.context;


import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.core.services.AuthService;
import io.github.jubadeveloper.several.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import io.github.jubadeveloper.core.domain.Channel;
import io.github.jubadeveloper.several.repository.ChannelRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserChannelWebsocketContextTest {
    public static CompletableFuture<WebSocket> webSocketCompletableFuture;
    public static User user;
    public static Channel channel;
    public static final String websocketUrlPattern = "ws://localhost:%d/channel/%d";
    public static final String urlPattern = "http://localhost:%d/%s";
    @Autowired
    TestRestTemplate testRestTemplate;
    public static final Logger logger = LogManager.getLogger(UserChannelWebsocketContextTest.class);

    @LocalServerPort
    int port;
    @BeforeAll
    public static void releaseDatabaseToMyUse (
            @Autowired UserRepository userRepository,
            @Autowired ChannelRepository channelRepository) {
        userRepository.deleteAll();
        channelRepository.deleteAll();
        user = userRepository.save(new User("juba@gmail.com", "juba", "juba"));
        Channel channelToSave = new Channel(user.getEmail(), "My test channel", "Test");
        List<User> channelMembers = channelToSave.getUsers();
        channelMembers.add(user);
        channelToSave.setUsers(channelMembers);
        channel = channelRepository.save(channelToSave);
    }
    @Test
    void testConnectToCreatedChannel () throws ExecutionException, InterruptedException {
        String apiUserLoginEndPointUrl = String.format(urlPattern, port, "api/user/login");
        User user = new User();
        user.setEmail("juba@gmail.com");
        user.setPassword("juba");
        ResponseEntity<User> loggedUserResponse = testRestTemplate.postForEntity(URI.create(apiUserLoginEndPointUrl), user, User.class);
        Assertions.assertThat(loggedUserResponse.getStatusCode().value()).isEqualTo(200);
        List<String> cookies = loggedUserResponse.getHeaders().get("Set-Cookie");
        Assertions.assertThat(cookies).isNotNull();
        String authToken = AuthService.getTokenFromCookies(cookies);
        String url = String.format(websocketUrlPattern, port, channel.getId());
        webSocketCompletableFuture = HttpClient.newHttpClient()
                .newWebSocketBuilder()
                .header("cookie", "X-Authorization=" + authToken + ";")
                .buildAsync(URI.create(url), new WebSocket.Listener() {
            @Override
            public void onOpen(WebSocket webSocket) {
                WebSocket.Listener.super.onOpen(webSocket);
                logger.info("Websocket connected: " + webSocket);
            }
        }).orTimeout(5, TimeUnit.SECONDS);
        WebSocket webSocket = webSocketCompletableFuture.get();
        logger.info("Websocket has been created: " + webSocket);
        webSocket.sendText("Hello world", true).whenComplete((action, err) -> {
            Assertions.assertThat(err).isNull();
        }).completeOnTimeout(webSocket, 5, TimeUnit.SECONDS);
    }
}
