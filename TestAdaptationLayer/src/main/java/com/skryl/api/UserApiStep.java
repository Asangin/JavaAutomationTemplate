package com.skryl.api;

import com.skryl.model.User;

public class UserApiStep {
    private UserApi userApi;

    public UserApiStep(UserApi userApi) {
        this.userApi = userApi;
    }

    public String createUser(User user) {
        var response = userApi.createUser(user);
        response.then().statusCode(200);
        return response.getBody().jsonPath().getString("id");

    }

    public User getUserById(String userId) {
        var response = userApi.getUserById(userId);
        response.then().statusCode(200);
        return response.as(User.class);
    }
}
