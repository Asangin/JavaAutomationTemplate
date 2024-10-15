package com.skryl.ui;

import com.skryl.ui.google.GooglePage;
import com.skryl.ui.google.SearchResultsPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;



public class GettingStartedWithSelenideTest {

    @Test
    @Tag("smoke")
    public void userCanSearch() {
        new Browser().openUrl("https://duckduckgo.com");
        new GooglePage().searchFor("selenide java");

        SearchResultsPage results = new SearchResultsPage();
        results.checkResultsSizeIsAtLeast(1);
        results.checkResultHasTest(0, "Selenide: concise UI tests in Java");
    }
}
