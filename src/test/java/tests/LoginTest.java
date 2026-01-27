package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyUserCanLogin() {
        // Login is handled automatically in BaseTest.setup()
        HomePage homePage = new HomePage(page);
        Assert.assertTrue(homePage.isHomeVisible(), "Home element is not visible after login");
    }
}

