package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.BednetDraftCampaignPage;
import pages.CampaignLandingPage;
import pages.LoginPage;
import utils.ConfigReader;

public class BednetDraftCampaignTest extends BaseTest {

    private CampaignLandingPage landingPage;
    private BednetDraftCampaignPage draftPage;

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setup")
    public void loginAndNavigateToCreateCampaign() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password"));
        page.waitForURL("**/employee", new com.microsoft.playwright.Page.WaitForURLOptions().setTimeout(30000));

        landingPage = new CampaignLandingPage(page);
        landingPage.clickCreateCampaign();
        page.waitForURL("**/campaign/campaign-home", new com.microsoft.playwright.Page.WaitForURLOptions().setTimeout(30000));
        page.waitForLoadState();

        landingPage.clickScratchCard();
        landingPage.clickContinue();
        page.waitForLoadState();
        page.waitForTimeout(3000);

        draftPage = new BednetDraftCampaignPage(page);
    }

    @Test(groups = {"regression"})
    public void verifySelectBednetDistributionAndClickNext() {
        draftPage.selectBednetDistribution();
        draftPage.clickNext();
        page.waitForLoadState();
        page.waitForTimeout(2000);

        // Clear pre-filled name and enter dynamic datetime-based name
        draftPage.clearAndEnterDynamicCampaignName();

        Assert.assertTrue(page.url().contains("create-campaign"),
                "Should remain on create campaign flow after selecting Bednet Distribution and clicking Next");
    }
}
