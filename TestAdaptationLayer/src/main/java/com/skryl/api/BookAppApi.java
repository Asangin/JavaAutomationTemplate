package com.skryl.api;

import com.google.gson.JsonObject;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.mapper.ObjectMapperType.GSON;

public class BookAppApi {
    private Cookies cookies;
    private RequestSpecification requestSpec;

    public BookAppApi(String baseUrl) {
        requestSpec = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/api")
                .config(new RestAssuredConfig().objectMapperConfig(new ObjectMapperConfig(GSON)))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .filter(new AllureRestAssured());
    }

    public Response login(String username, String password) {
        var body = new JsonObject();
        body.addProperty("password", password);
        body.addProperty("username", username);
        var response = RestAssured.given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(body)
                .post("/auth/login");
        cookies = response.getDetailedCookies();
        return response;
    }

    public Response loginAccount() {
        return RestAssured.given()
                .spec(requestSpec)
                .cookies(cookies)
                .get("/auth/loginAccount");
    }

    public Response loginStatus() {
        return RestAssured.given()
                .spec(requestSpec)
                .cookies(cookies)
                .get("/auth/loginStatus");
    }

    public Response logout() {
        return RestAssured.given()
                .spec(requestSpec)
                .cookies(cookies)
                .post("/auth/logout");
    }

}
