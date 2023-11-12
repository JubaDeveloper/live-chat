package io.github.jubadeveloper.domain;

import io.github.jubadeveloper.core.domain.User;
import io.github.jubadeveloper.core.services.UserService;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsException;
import io.github.jubadeveloper.several.exceptions.UserAlreadyExistsUsernameException;
import io.github.jubadeveloper.several.repository.UserRepository;
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
    public void testUserCreation () throws UserAlreadyExistsException, UserAlreadyExistsUsernameException {
        User user = userService.createUser(new User("juba@gmail.com", "juba silva", "juba"));
        assertThat(user.getEmail()).isEqualTo("juba@gmail.com");
    }
}
