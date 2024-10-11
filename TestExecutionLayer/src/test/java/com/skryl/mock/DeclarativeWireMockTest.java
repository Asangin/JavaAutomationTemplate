package com.skryl.mock;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

@Slf4j
@WireMockTest
public class DeclarativeWireMockTest {

    @Test
    void test_something_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) {
        // The static DSL will be automatically configured for you
        stubFor(get("/static-dsl").willReturn(ok()));

        // Instance DSL can be obtained from the runtime info parameter
        WireMock wireMock = wmRuntimeInfo.getWireMock();
        wireMock.register(get("/instance-dsl").willReturn(ok()));

        // Info such as port numbers is also available
        var httpBaseUrl = wmRuntimeInfo.getHttpBaseUrl();
        log.info("HTTP base Url: %s".formatted(httpBaseUrl));
        int port = wmRuntimeInfo.getHttpPort();
        log.info("Port: %d".formatted(port));

        log.info("Test");
        RestAssured.baseURI = httpBaseUrl;
        RestAssured.port = port;
        var response = RestAssured.get("/static-dsl");
        response.then().statusCode(200);
    }

}