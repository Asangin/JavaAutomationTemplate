package com.skryl.api;

import com.skryl.api.bookapp.BookAppApiStep;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookApiTest {

    @Test
    void userLoginToBookApp() {
        var url = "http://localhost:8080";
        var app = new BookAppApiStep(url);

        var user = app.login("test", "test");
        assertThat(user.getId()).isEqualTo(1);

        var actualStatus = app.loginStatus();
        assertThat(actualStatus)
                .as("User should be logged in")
                .isTrue();
    }
}
