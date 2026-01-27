package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import pages.LoginPage;
import utils.ConfigReader;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();

        BrowserType browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(ConfigReader.get("headless")))
                .setChannel("chrome"));

        page = browser.newPage();
        page.setDefaultTimeout(60000);
        page.navigate(ConfigReader.get("base.url"), new Page.NavigateOptions().setTimeout(60000));

        // Login before every test
        LoginPage loginPage = new LoginPage(page);
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    @AfterMethod
    public void keepBrowserOpen() throws InterruptedException {
        // Keep browser open for 5 minutes after test
        Thread.sleep(10000);
    }
}

