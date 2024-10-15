package com.skryl.ui;

import com.skryl.ui.google.GooglePage;
import com.skryl.ui.google.SearchResultsPage;
import org.testng.annotations.Test;

public class GettingStartedWithSelenideTest {

    @Test
    public void userCanSearch() {
        new Browser().openUrl("https://duckduckgo.com");
        new GooglePage().searchFor("selenide java");

        SearchResultsPage results = new SearchResultsPage();
        results.checkResultsSizeIsAtLeast(1);
        results.checkResultHasTest(0, "Selenide: concise UI tests in Java");
    }

}
