package com.skryl.api;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.google.gson.Gson;
import com.skryl.api.user.UserApi;
import com.skryl.api.user.UserApiStep;
import com.skryl.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiExampleTest {
    private static UserApi userApi;
    private static UserApiStep userApiStep;

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort().dynamicHttpsPort())
            .build();

    @BeforeAll
    static void setupMock() {
        WireMockRuntimeInfo wm1RuntimeInfo = wm.getRuntimeInfo();
        userApi = new UserApi(wm1RuntimeInfo.getHttpBaseUrl());
        userApiStep = new UserApiStep(userApi);
    }

    @Test
    void createUserTest() {
        wm.stubFor(post("/users").willReturn(okJson("{\"id\":\"12345\"}")));
        var user = new User()
                .name("Skryl")
                .age(39);
        log.info("Create user %s".formatted(user));
        var userId = userApiStep.createUser(user);
        assertThat(userId).isEqualTo("12345");
    }

    @Test
    void retrieveUserDataByUserIdTest() {
        var user = new User()
                .name("Hello")
                .age(39);
        wm.stubFor(get("/users/12345")
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(new Gson().toJson(user))
                )
        );

        var actualUser = userApiStep.getUserById("12345");
        log.info("Get user %s".formatted(user));
        assertThat(actualUser.age()).isGreaterThan(30);
    }
}
