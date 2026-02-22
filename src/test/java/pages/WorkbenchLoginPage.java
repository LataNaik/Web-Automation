package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WorkbenchLoginPage {

    private Page page;
    private Locator usernameInput;
    private Locator passwordInput;
    private Locator privacyCheckbox;
    private Locator loginButton;
    private Locator errorMessage;

    public WorkbenchLoginPage(Page page) {
        this.page = page;
        this.usernameInput = page.locator("input[id='user-login-standalone-core_login_username-field']");
        this.passwordInput = page.locator("input[id='user-login-standalone-core_login_password-field']");
        this.privacyCheckbox = page.locator("input[id='privacy-component-check']");
        this.loginButton = page.locator("#formcomposer-submit-action");
        this.errorMessage = page.locator(".digit-toast-success.animate.digit-error");
    }

    // --- Actions ---

    public void enterUsername(String username) {
        usernameInput.fill(username);
    }

    public void enterPassword(String password) {
        passwordInput.fill(password);
    }

    public void checkPrivacy() {
        privacyCheckbox.click();
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void clearUsername() {
        usernameInput.clear();
    }

    public void clearPassword() {
        passwordInput.clear();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        checkPrivacy();
        clickLogin();
    }

    // --- Validations ---

    public boolean isUsernameVisible() {
        return usernameInput.isVisible();
    }

    public boolean isPasswordVisible() {
        return passwordInput.isVisible();
    }

    public boolean isPrivacyCheckboxVisible() {
        return privacyCheckbox.isVisible();
    }

    public boolean isLoginButtonVisible() {
        return loginButton.isVisible();
    }

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }

    public boolean isErrorMessageVisible() {
        errorMessage.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        return errorMessage.isVisible();
    }

    public String getErrorMessageText() {
        errorMessage.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        return errorMessage.textContent();
    }

    public String getUsernamePlaceholder() {
        return usernameInput.getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return passwordInput.getAttribute("placeholder");
    }
}
