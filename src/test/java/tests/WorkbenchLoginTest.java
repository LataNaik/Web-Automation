package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.WorkbenchBaseTest;
import pages.WorkbenchLoginPage;
import utils.WorkbenchConfigReader;

public class WorkbenchLoginTest extends WorkbenchBaseTest {

    // ===== Validation Tests =====

    @Test
    public void verifyUsernameFieldIsVisible() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        Assert.assertTrue(loginPage.isUsernameVisible(), "Username field is not visible");
    }

    @Test
    public void verifyPasswordFieldIsVisible() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        Assert.assertTrue(loginPage.isPasswordVisible(), "Password field is not visible");
    }

    @Test
    public void verifyPrivacyCheckboxIsVisible() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        Assert.assertTrue(loginPage.isPrivacyCheckboxVisible(), "Privacy checkbox is not visible");
    }

    @Test
    public void verifyLoginButtonIsVisible() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button is not visible");
    }

    @Test
    public void verifyUsernamePlaceholder() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        String placeholder = loginPage.getUsernamePlaceholder();
        Assert.assertNotNull(placeholder, "Username placeholder is null");
    }

    @Test
    public void verifyPasswordPlaceholder() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        String placeholder = loginPage.getPasswordPlaceholder();
        Assert.assertNotNull(placeholder, "Password placeholder is null");
    }

    // ===== Positive Test =====

    @Test
    public void verifyValidLogin() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        loginPage.login(
                WorkbenchConfigReader.get("username"),
                WorkbenchConfigReader.get("password"));

        // Wait for navigation after login
        page.waitForURL("**/employee/**", new com.microsoft.playwright.Page.WaitForURLOptions().setTimeout(30000));
        Assert.assertFalse(page.url().contains("/login"), "Still on login page after valid login");
    }

    @Test(groups = {"regression"})
    public void verifySuccessfulLoginRedirectsToHomePage() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        loginPage.login(
                WorkbenchConfigReader.get("username"),
                WorkbenchConfigReader.get("password"));

        page.waitForURL("**/employee/**", new com.microsoft.playwright.Page.WaitForURLOptions().setTimeout(30000));
        String title = page.title();
        Assert.assertEquals(title, "DIGIT HCM", "Page title should be 'DIGIT HCM' after successful login");
    }

    // ===== Negative Tests =====

    @Test
    public void verifyLoginWithInvalidUsername() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        loginPage.login("invalidUser123", WorkbenchConfigReader.get("password"));

        Assert.assertTrue(loginPage.isErrorMessageVisible(), "Error message not shown for invalid username");
    }

    @Test
    public void verifyLoginWithInvalidPassword() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        loginPage.login(WorkbenchConfigReader.get("username"), "wrongPassword123");

        Assert.assertTrue(loginPage.isErrorMessageVisible(), "Error message not shown for invalid password");
    }

    @Test
    public void verifyLoginWithEmptyUsername() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        loginPage.enterPassword(WorkbenchConfigReader.get("password"));
        loginPage.checkPrivacy();
        loginPage.clickLogin();

        Assert.assertTrue(page.url().contains("/login"), "Should remain on login page with empty username");
    }

    @Test
    public void verifyLoginWithEmptyPassword() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        loginPage.enterUsername(WorkbenchConfigReader.get("username"));
        loginPage.checkPrivacy();
        loginPage.clickLogin();

        Assert.assertTrue(page.url().contains("/login"), "Should remain on login page with empty password");
    }

    @Test
    public void verifyLoginWithEmptyFields() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        loginPage.checkPrivacy();
        loginPage.clickLogin();

        Assert.assertTrue(page.url().contains("/login"), "Should remain on login page with empty fields");
    }

    @Test
    public void verifyLoginWithoutPrivacyCheckbox() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        loginPage.enterUsername(WorkbenchConfigReader.get("username"));
        loginPage.enterPassword(WorkbenchConfigReader.get("password"));
        // Skip checking privacy checkbox
        loginPage.clickLogin();

        Assert.assertTrue(page.url().contains("/login"), "Should remain on login page without privacy checkbox");
    }
}
