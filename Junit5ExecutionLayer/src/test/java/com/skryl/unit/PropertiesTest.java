package com.skryl.unit;

import com.skryl.properties.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PropertiesTest {

    @Test
    @Tags({@Tag("smoke"), @Tag("regression")})
    void verifyDefaultProperties() {
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        log.info("Server %s:%d will run %s threads".formatted(cfg.hostname(), cfg.port(), cfg.maxThreads()));
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(cfg.hostname()).isEqualTo("foobar.com");
        softAssertions.assertThat(cfg.port()).isEqualTo(80);
        softAssertions.assertThat(cfg.maxThreads()).isEqualTo(100);
        softAssertions.assertAll();
    }

    @Test
    @Tag("regression")
    void verifySystemProperties() {
        var property = new Properties();
        property.setProperty("hostname", "hello.world");
        System.setProperties(property);
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        assertThat(cfg.hostname()).isEqualTo("hello.world");
    }

}
