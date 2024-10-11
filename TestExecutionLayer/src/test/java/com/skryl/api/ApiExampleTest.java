package com.skryl.api;

import com.skryl.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ApiExampleTest {

    @Test
    void test1() {
        var user = new User()
                .name("Skryl")
                .age(39);
        log.info("Created user %s".formatted(user));
    }
}
