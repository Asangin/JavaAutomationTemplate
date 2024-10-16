package com.skryl.ui.google;

import static com.codeborne.selenide.Selenide.open;

public class BrowserNavigation {

    public void openUrl(String url) {
        open(url);
    }
}
