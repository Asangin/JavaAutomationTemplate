package com.skryl.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.skryl.model.User;
import com.skryl.user.api.UserApiStep;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MockApiTest {

    private UserApiStep userApiStep;
    WireMockServer wireMockServer = new WireMockServer(options().port(8089)); //No-args constructor will start on port 8080, no HTTPS


    @BeforeClass
    void setupMock() {
        wireMockServer.start();
        var baseUrl = wireMockServer.baseUrl();
        log.info("Base url: %s".formatted(baseUrl));
        userApiStep = new UserApiStep(baseUrl);
    }

    @AfterClass
    void teardownMock() {
        wireMockServer.stop();
    }

    @Test
    void createUserThenFindItTest() {
        wireMockServer.stubFor(post("/users").willReturn(okJson("{\"id\":\"12345\"}")));
        var user = new User()
                .name("Hello")
                .age(39);
        wireMockServer.stubFor(get("/users/12345")
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(new Gson().toJson(user))
                )
        );

        log.info("Create user %s".formatted(user));
        var userId = userApiStep.createUser(user);
        log.info("User created with id: %s".formatted(userId));
        assertThat(userId).isEqualTo("12345");

        var actualUser = userApiStep.getUserById(userId);
        log.info("Get user %s".formatted(user));
        assertThat(actualUser.age()).isGreaterThan(30);
    }

}
