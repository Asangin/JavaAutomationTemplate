package com.skryl.mock;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;

public class ProgrammaticWireMockTest {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort().dynamicHttpsPort())
            .build();

    @BeforeAll
    static void setupMock() {
        // You can get ports, base URL etc. via WireMockRuntimeInfo
        WireMockRuntimeInfo wm1RuntimeInfo = wm.getRuntimeInfo();
        RestAssured.baseURI = wm1RuntimeInfo.getHttpBaseUrl();
    }


    @Test
    void test_something_with_wiremock() {
        wm.stubFor(get("/api-1-thing").willReturn(ok()));

        RestAssured.get("/api-1-thing ")
                .then()
                .statusCode(200);
    }
}