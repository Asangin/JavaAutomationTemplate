package com.skryl.ui;

import com.skryl.ui.pages.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@Testcontainers
public class GettingStartedWithPlaywrightTest {

    private static PlaywrightBrowser playwrightBrowser;
    private static UserFlowUI userFlowUI;

    @Container
    public static final GenericContainer app = new GenericContainer(DockerImageName.parse("asangin/test-app:latest"))
            .withExposedPorts(8080);

    @BeforeAll
    static void setUp() {
        var baseUrl = app.getHost() + ":" + app.getMappedPort(8080);
        log.info("Base url: %s".formatted(baseUrl));
        playwrightBrowser = new PlaywrightBrowser(baseUrl);
        userFlowUI = new UserFlowUI(playwrightBrowser);
    }

    @AfterAll
    static void closeBrowser() {
        playwrightBrowser.close();
    }

    @BeforeEach
    void createContextAndPage() {
        playwrightBrowser.newContext();
        playwrightBrowser.newPage();
    }

    @AfterEach
    void closeContext() {
        playwrightBrowser.closeContext();
    }

    @Test
    void trySelectors() {
        userFlowUI.login("test", "test");
        userFlowUI.addNewBook(
                "Book1",
                "11111-11111",
                MainPage.BookCategory.Magazine,
                MainPage.BookFormat.eBook
        );
    }

}
