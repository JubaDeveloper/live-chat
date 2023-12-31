package io.github.jubadeveloper;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

// @SpringBootTest
public class ApplicationTest {
    private final Logger logger = LogManager.getLogger(Test.class);

    @Test
    public void test () {
        System.out.println("Working well");
        logger.info("info! {}", "1001");
        logger.error("error! {}", "1001");
        logger.debug("debug! {}", "1001");
    }

    @Test
    public void date () {
        LocalTime localTime = LocalTime.now();
        logger.info("Time: " + localTime.toString().split("\\.")[0]);
    }
}
