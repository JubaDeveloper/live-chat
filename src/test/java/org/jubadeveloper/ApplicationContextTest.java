package org.jubadeveloper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApplicationContextTest {
    @Autowired Main main;
    @Test
    public void applicationInitializationContext () {
        assertThat(main).isNotNull();
    }
}
