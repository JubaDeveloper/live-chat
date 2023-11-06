package org.jubadeveloper.domain;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.UserService;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserDomainTest {
    @Autowired
    UserService userService;
    @Test
    public void testUserCreation () throws UserAlreadyExistsException {
        User user = userService.createUser(new User("juba@gmail.com", "juba silva"));
        assertThat(user.getEmail()).isEqualTo("juba@gmail.com");
    }
}
