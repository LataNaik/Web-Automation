package base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.FormHelper;
import utils.NavigationHelper;
import utils.ScreenshotHelper;

/**
 * Base class for all test classes.
 * Handles browser setup, login, and teardown.
 *
 * Usage:
 *   public class MyTest extends BaseTest {
 *
 *       @Test
 *       public void myTest() {
 *           // Use nav for navigation
 *           ComplaintPage complaint = nav.goToCreateComplaint();
 *
 *           // Use form for form interactions
 *           form.enterText("#field", "value");
 *
 *           // Use homePage directly
 *           homePage.goToCreateComplaint();
 *       }
 *   }
 */
public class BaseTest {

    // Core Playwright objects
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    // Helpers available in all tests
    protected NavigationHelper nav;
    protected FormHelper form;
    protected ScreenshotHelper screenshot;

    // Pre-initialized page objects
    protected HomePage homePage;

    @BeforeMethod
    public void setup() {
        // Initialize Playwright and browser
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(ConfigReader.get("headless")))
                .setChannel("chrome"));

        // Create page with timeout
        page = browser.newPage();
        page.setDefaultTimeout(60000);

        // Initialize helpers
        nav = new NavigationHelper(page);
        form = new FormHelper(page);
        screenshot = new ScreenshotHelper(page);

        // Navigate to app and login
        page.navigate(ConfigReader.get("base.url"), new Page.NavigateOptions().setTimeout(60000));
        LoginPage loginPage = new LoginPage(page);
        homePage = loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
        // Capture screenshot on failure
        if (result.getStatus() == ITestResult.FAILURE) {
            String testName = result.getMethod().getMethodName();
            String className = result.getTestClass().getRealClass().getSimpleName();
            screenshot.captureOnFailure(className, testName);
        }

        // Brief pause for debugging
        Thread.sleep(1000);

        // Cleanup
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
