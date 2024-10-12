package com.skryl.api;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.google.gson.Gson;
import com.skryl.model.User;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.mapper.ObjectMapperType.GSON;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiExampleTest {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort().dynamicHttpsPort())
            .build();

    @BeforeAll
    static void setupMock() {
        // You can get ports, base URL etc. via WireMockRuntimeInfo
        WireMockRuntimeInfo wm1RuntimeInfo = wm.getRuntimeInfo();
        RestAssured.baseURI = wm1RuntimeInfo.getHttpBaseUrl();
        RestAssured.config = RestAssured.config().objectMapperConfig(new ObjectMapperConfig(GSON));
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    void test1() {
        wm.stubFor(post("/users").willReturn(okJson("{\"id\":\"12345\"}")));
        var user = new User()
                .name("Skryl")
                .age(39);
        log.info("Created user %s".formatted(user));
        var response = RestAssured.given()
                .body(user)
                .post("/users");
        response.then().statusCode(200);
        assertThat(response.getBody().jsonPath().getString("id"))
                .isEqualTo("12345");
    }

    @Test
    void test2() {
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
        var response = RestAssured.get("/users/12345");
        response.then().statusCode(200);
        var actualUser = response.as(User.class);
        log.info("Created user %s".formatted(user));
        assertThat(actualUser.age()).isGreaterThan(30);
    }
}
