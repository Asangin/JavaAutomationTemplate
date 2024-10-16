package com.skryl.ui;

import com.skryl.ui.pages.LoginPage;
import com.skryl.ui.pages.MainPage;

public class UserFlowUI {
    private PlaywrightBrowser playwrightBrowser;
    private LoginPage loginPage;
    private MainPage mainPage;

    public UserFlowUI(PlaywrightBrowser playwrightBrowser) {
        this.playwrightBrowser = playwrightBrowser;
    }

    public void login(String username, String password) {
        this.mainPage = new LoginPage(playwrightBrowser.getPage())
                .goToLoginPage(playwrightBrowser.getNavigationUrl())
                .enterUserName(username)
                .enterPassword(password)
                .clickLogin();
    }

    public void addNewBook(String name, String ISBNnumber, MainPage.BookCategory bookCategory, MainPage.BookFormat bookFormat) {
        this.mainPage.addNewBook()
                .enterBookName("name")
                .enterISBNnumber(ISBNnumber)
                .chooseCategory(bookCategory)
                .chooseFormat(bookFormat)
                .createBook();
    }
}
