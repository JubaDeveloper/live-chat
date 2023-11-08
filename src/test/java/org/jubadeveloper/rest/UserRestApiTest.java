package org.jubadeveloper.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.UserService;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.assertj.core.api.Assertions.*;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserRestApiTest {
    public static final Logger logger = LogManager.getLogger(UserRestApiTest.class);
    public static final String urlPattern = "http://%s:%d/%s";
    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    int serverPort;
    @BeforeAll
    static void releaseDatabaseToMyUse (@Autowired UserRepository userRepository) {
        userRepository.deleteAll();
    }
    @Test
    @Order(1)
    void testUserCreation () {
        String apiUserCreationEndPointUrl = String.format(urlPattern, "localhost", serverPort, "api/user/register");
        logger.info("Url user api endpoint: " + apiUserCreationEndPointUrl);
        User user = new User("juba@gmail.com", "jubadev", "juba");
        ResponseEntity<User> createdUserResponse= testRestTemplate.postForEntity(URI.create(apiUserCreationEndPointUrl), user, User.class);
        Assertions.assertThat(createdUserResponse.getStatusCode().value()).isEqualTo(200);
        User createdUser = createdUserResponse.getBody();
        Assertions.assertThat(createdUser).isNotNull();
        Assertions.assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());
    }
    @Test
    @Order(2)
    void throwOnUserAlreadyExistsCreation () {
        String apiUserCreationEndPointUrl = String.format(urlPattern, "localhost", serverPort, "api/user/register");
        User user = new User("juba@gmail.com", "jubadev", "juba");
        UserAlreadyExistsException expectedException = new UserAlreadyExistsException(user.getEmail());
        ResponseEntity<String> createdUserResponse = testRestTemplate.postForEntity(URI.create(apiUserCreationEndPointUrl), user, String.class);
        Assertions.assertThat(createdUserResponse.getStatusCode().value()).isEqualTo(400);
        Assertions.assertThat(createdUserResponse.getBody()).isEqualTo(expectedException.getMessage());
    }
    @Test
    @Order(3)
    void testUserLogin () {
        String apiUserLoginEndPointUrl = String.format(urlPattern, "localhost", serverPort, "api/user/login");
        logger.info("Url user api login endpoint: " + apiUserLoginEndPointUrl);
        User user = new User();
        user.setEmail("juba@gmail.com");
        user.setPassword("juba");
        ResponseEntity<User> loggedUserResponse = testRestTemplate.postForEntity(URI.create(apiUserLoginEndPointUrl), user, User.class);
        Assertions.assertThat(loggedUserResponse.getStatusCode().value()).isEqualTo(200);
    }
    @Test
    @Order(4)
    void testFailOnInvalidLogin () {
        String apiUserLoginEndPointUrl = String.format(urlPattern, "localhost", serverPort, "api/user/login");
        logger.info("Url user api login endpoint: " + apiUserLoginEndPointUrl);
        User user = new User();
        user.setEmail("juba@gmail.com");
        user.setPassword("juba90902");
        ResponseEntity<String> loggedUserResponse = testRestTemplate.postForEntity(URI.create(apiUserLoginEndPointUrl), user, String.class);
        Assertions.assertThat(loggedUserResponse.getStatusCode().value()).isEqualTo(400);
    }
}
