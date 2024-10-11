package com.skryl.properties;

import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

@Slf4j
public class PropertiesTest {

    @Test
    void test1() {
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        log.info("Server %s:%d will run %s threads".formatted(cfg.hostname(), cfg.port(), cfg.maxThreads()));
    }
}
