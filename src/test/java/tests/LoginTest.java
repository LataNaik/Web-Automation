package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test(groups = {"regression"})
    public void verifySuccessfulLoginRedirectsToHomePage() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password"));

        page.waitForURL("**/employee/**", new com.microsoft.playwright.Page.WaitForURLOptions().setTimeout(30000));
        String title = page.title();
        Assert.assertEquals(title, "DIGIT HCM", "Page title should be 'DIGIT HCM' after successful login");
    }
}
