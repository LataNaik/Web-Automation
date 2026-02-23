package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.WorkbenchBaseTest;
import pages.CampaignLandingPage;
import pages.WorkbenchLoginPage;
import utils.WorkbenchConfigReader;

public class CampaignLandingTest extends WorkbenchBaseTest {

    private CampaignLandingPage landingPage;

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setup")
    public void loginAndNavigate() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        loginPage.login(
                WorkbenchConfigReader.get("username"),
                WorkbenchConfigReader.get("password"));
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

    // @Test(groups = {"regression"})
    // public void verifyScratchCardTitleIsVisible() {
    //     navigateToCreateCampaignPage();
    //     Assert.assertTrue(landingPage.isScratchCardTitleVisible(), "Scratch card title is not visible");
    // }

    // @Test(groups = {"regression"})
    // public void verifyScratchCardTitleText() {
    //     navigateToCreateCampaignPage();
    //     Assert.assertEquals(landingPage.getScratchCardTitleText(), "Create a New Campaign from Scratch",
    //             "Scratch card title text does not match");
    // }

    // @Test(groups = {"regression"})
    // public void verifyScratchCardDescriptionIsVisible() {
    //     navigateToCreateCampaignPage();
    //     Assert.assertTrue(landingPage.isScratchCardDescriptionVisible(), "Scratch card description is not visible");
    // }

    // @Test(groups = {"regression"})
    // public void verifyScratchCardIsClickable() {
    //     navigateToCreateCampaignPage();
    //     landingPage.clickScratchCard();
    //     page.waitForLoadState();
    //     Assert.assertTrue(true, "Scratch card is clickable");
    // }

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
