package com.skryl.ui.google;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;

public class SearchResultsPage {
  private final ElementsCollection results = $$("[data-testid=\"result\"]");

  public void checkResultsSizeIsAtLeast(int expectedSize) {
    results.shouldHave(sizeGreaterThan(expectedSize));
  }

  public void checkResultHasTest(int index, String expectedText) {
    results.get(index).shouldHave(text(expectedText));
  }

}