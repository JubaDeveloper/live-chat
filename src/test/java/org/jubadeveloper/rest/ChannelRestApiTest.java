package org.jubadeveloper.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.jubadeveloper.core.domain.Channel;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.AuthService;
import org.jubadeveloper.several.repository.ChannelRepository;
import org.jubadeveloper.several.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.assertj.core.api.Assertions.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ChannelRestApiTest {
    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    int serverPort;
    public static final User mockUser = new User("juba@gmail.com", "jubadev", "juba");
    public static final String urlPattern = "http://%s:%d/%s";
    public static final Logger logger = LogManager.getLogger(ChannelRestApiTest.class);
    @BeforeAll
    public static void releaseAndSetDatabaseToMyUse (@Autowired ChannelRepository channelRepository,
                                                     @Autowired UserRepository userRepository) {
        userRepository.deleteAll();
        channelRepository.deleteAll();
        userRepository.save(mockUser);

    }
    @Test
    @Order(1)
    void testChannelCreation ()  {
        String urlUserLogin = String.format(urlPattern, "localhost", serverPort, "api/user/login");
        ResponseEntity<User> userLoginResponse = testRestTemplate.postForEntity(urlUserLogin, mockUser, User.class);
        Assertions.assertThat(userLoginResponse.getStatusCode().value()).isEqualTo(200); // Successfully logged in
        List<String> cookies = userLoginResponse.getHeaders().get("Set-Cookie");
        Assertions.assertThat(cookies).isNotNull();
        String authToken = AuthService.getTokenFromCookies(cookies);
        Assertions.assertThat(authToken).isNotNull();
        String urlChannelCreation = String.format(urlPattern, "localhost", serverPort, "panel/api/channel");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("cookie", "X-Authorization=" + authToken + ";");
        Channel channel = new Channel(mockUser.getEmail(), "Mock channel test", "Test");
        HttpEntity<Channel> httpEntity = new HttpEntity<>(channel, httpHeaders);
        ResponseEntity<Channel> channelCreationResponse = testRestTemplate.exchange(urlChannelCreation,
                        HttpMethod.POST,
                        httpEntity,
                        Channel.class);
        logger.info("Channel creation body: " + channelCreationResponse.getBody());
        Assertions.assertThat(channelCreationResponse.getStatusCode().value()).isEqualTo(200);
        Channel createdChannel = channelCreationResponse.getBody();
        Assertions.assertThat(createdChannel).isNotNull();
        Assertions.assertThat(createdChannel.getCreatorId()).isEqualTo(channel.getCreatorId());
        Assertions.assertThat(createdChannel.getName()).isEqualTo(channel.getName());
    }
    @Test
    @Order(2)
    void rejectOnUserNotAuthenticated () {
        String urlChannelCreation = String.format(urlPattern, "localhost", serverPort, "panel/api/channel");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("cookie", "X-Authorization=;");
        Channel channel = new Channel(mockUser.getEmail(), "Mock channel test", "Test");
        HttpEntity<Channel> httpEntity = new HttpEntity<>(channel, httpHeaders);
        boolean throwed = false;
        try {
            testRestTemplate.exchange(urlChannelCreation,
                    HttpMethod.POST,
                    httpEntity,
                    String.class);
        } catch (ResourceAccessException resourceAccessException) {throwed = true;}
        Assertions.assertThat(throwed).isTrue();
    }
}
