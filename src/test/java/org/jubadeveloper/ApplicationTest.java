package org.jubadeveloper;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ApplicationTest {
    private final Logger logger = LogManager.getLogger(Test.class);

    public ApplicationTest (String serialPortName) {
        System.out.println(logger.isInfoEnabled());
        logger.entry();
        logger.info("info! {}", serialPortName);
        logger.error("error! {}", serialPortName);
        logger.debug("debug! {}", serialPortName);
    }

    public static void main(String args[])
    {
        ApplicationTest h1 = new ApplicationTest("1001");
    }
}
