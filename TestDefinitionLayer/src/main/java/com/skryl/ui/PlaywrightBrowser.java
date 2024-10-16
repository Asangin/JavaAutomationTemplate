package com.skryl.ui;

import com.microsoft.playwright.*;
import lombok.Getter;

public class PlaywrightBrowser {

    @Getter
    private final String navigationUrl;
    private final Playwright playwright;
    private final Browser browser;
    private BrowserContext context;
    @Getter
    private Page page;

    public PlaywrightBrowser(String baseUrl) {
        this.navigationUrl = baseUrl;
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(150));
    }

    public void close() {
        playwright.close();
    }

    public void newContext() {
        this.context = browser.newContext();
    }

    public void newPage() {
        this.page = context.newPage();
    }

    public void closeContext() {
        this.context.close();
    }


}
