package com.skryl.ui;

import static com.codeborne.selenide.Selenide.open;

public class Browser {

    public void openUrl(String url) {
        open(url);
    }
}
