package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CampaignLandingPage;
import pages.LoginPage;
import utils.ConfigReader;

public class CampaignLandingTest extends BaseTest {

    private CampaignLandingPage landingPage;

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setup")
    public void loginAndNavigate() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password"));
        page.waitForURL("**/employee", new com.microsoft.playwright.Page.WaitForURLOptions().setTimeout(30000));
        landingPage = new CampaignLandingPage(page);
    }

    // ===== Navigation Test =====

    @Test(groups = {"regression"})
    public void verifyCreateCampaignLinkNavigates() {
        landingPage.clickCreateCampaign();
        page.waitForLoadState();
        Assert.assertFalse(page.url().endsWith("/employee"),
                "Page should navigate away from landing page after clicking Create campaign");
    }

    // ===== Create Campaign Page - Scratch Card Tests =====

    private void navigateToCreateCampaignPage() {
        landingPage.clickCreateCampaign();
        page.waitForURL("**/campaign/campaign-home", new com.microsoft.playwright.Page.WaitForURLOptions().setTimeout(30000));
        page.waitForLoadState();
    }

    @Test(groups = {"regression"})
    public void verifyContinueButtonNavigates() {
        navigateToCreateCampaignPage();
        landingPage.clickScratchCard();
        landingPage.clickContinue();
        page.waitForLoadState();
        Assert.assertFalse(page.url().contains("/campaign-home"),
                "Page should navigate away from campaign-home after clicking Continue");
    }
}
