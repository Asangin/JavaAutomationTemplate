package com.skryl.api.user;

import com.skryl.api.UserApi;
import com.skryl.model.User;

public class UserApiStep {
    private final UserApi userApi;

    public UserApiStep(String baseUrl) { // TODO need to passed some test data java object
        this.userApi  = new UserApi(baseUrl);
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
