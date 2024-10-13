package com.skryl.api.user;

import com.skryl.model.User;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.response.Response;

import static io.restassured.mapper.ObjectMapperType.GSON;

public class UserApi {

    public UserApi(String baseUri) {
        RestAssured.baseURI = baseUri;
        RestAssured.config = RestAssured.config().objectMapperConfig(new ObjectMapperConfig(GSON));
        RestAssured.filters(new AllureRestAssured());
    }

    public Response createUser(User user) {
        return RestAssured.given()
                .body(user)
                .post("/users");
    }


    public Response getUserById(String userId) {
        return RestAssured.get("/users/%s".formatted(userId));
    }
}
