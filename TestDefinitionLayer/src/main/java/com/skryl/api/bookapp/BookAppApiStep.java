package com.skryl.api.bookapp;

import com.skryl.api.BookAppApi;
import com.skryl.model.book.LoggedInUserResponse;

public class BookAppApiStep {

    private BookAppApi bookAppApi;

    public BookAppApiStep(String baseUrl) {
        bookAppApi = new BookAppApi(baseUrl);
    }

    public LoggedInUserResponse login(String username, String password) {
        var response = bookAppApi.login(username, password);
        response.then().statusCode(200);
        return response.as(LoggedInUserResponse.class);
    }

    public Boolean loginAccount() {
        var response = bookAppApi.loginAccount();
        response.then().statusCode(200);
        return response.getBody().as(Boolean.class);
    }

    public Boolean loginStatus() {
        var response = bookAppApi.loginStatus();
        response.then().statusCode(200);
        return response.getBody().as(Boolean.class);
    }

    public void logout() {
        var response = bookAppApi.logout();
        response.then().statusCode(200);
    }




}
