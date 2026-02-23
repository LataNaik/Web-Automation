package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.WorkbenchConfigReader;

public class WorkbenchBaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        playwright = Playwright.create();

        BrowserType browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(WorkbenchConfigReader.get("headless")))
                .setChannel("chrome"));

        page = browser.newPage();
        page.setViewportSize(1800, 900);
        page.setDefaultTimeout(60000);
        page.navigate(WorkbenchConfigReader.get("base.url"), new Page.NavigateOptions().setTimeout(60000));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
