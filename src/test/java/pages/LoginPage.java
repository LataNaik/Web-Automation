package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {

    private Locator username;
    private Locator password;
    private Locator tenant;
    private Locator tenantId;
    private Locator privacy;
    private Locator loginBtn;


    public LoginPage(Page page) {
        this.username = page.locator("input[name='username']");
        this.password = page.locator("input[name='password']");
        this.tenant = page.locator("#user-login-core_common_city");
        this.tenantId=page.locator(".main-option");
        this.privacy = page.locator("#privacy-component-check");
        this.loginBtn = page.locator("#user-login-continue");

    }
    
    public void login(String user, String pass) {
        username.fill(user);
        password.fill(pass);
        tenant.click(); // Open dropdown
        tenantId.first().click(); // Select first option
        privacy.click();
        loginBtn.click();
    }
}
//   options:                                                                                                                  
//   - page.locator("#username") - by ID                                                                                       
//   - page.locator("input[name='username']") - by name attribute                                                              
//   - page.getByPlaceholder("Username") - by placeholder text                                                                 
//   - page.getByLabel("Username") - by associated label  
