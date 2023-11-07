package org.jubadeveloper.domain;

import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.services.UserService;
import org.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import org.jubadeveloper.several.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserDomainTest {
    @Autowired
    UserService userService;
    @BeforeAll
    static void releaseDatabaseToMyUse (@Autowired UserRepository userRepository) {
        userRepository.deleteAll();
    }

    @Test
    public void testUserCreation () throws UserAlreadyExistsException {
        User user = userService.createUser(new User("juba@gmail.com", "juba silva"));
        assertThat(user.getEmail()).isEqualTo("juba@gmail.com");
    }
}
