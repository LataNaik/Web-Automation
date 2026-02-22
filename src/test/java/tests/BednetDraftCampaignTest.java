package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.WorkbenchBaseTest;
import pages.BednetDraftCampaignPage;
import pages.CampaignLandingPage;
import pages.WorkbenchLoginPage;
import utils.WorkbenchConfigReader;

public class BednetDraftCampaignTest extends WorkbenchBaseTest {

    private CampaignLandingPage landingPage;
    private BednetDraftCampaignPage draftPage;

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setup")
    public void loginAndNavigateToCreateCampaign() {
        WorkbenchLoginPage loginPage = new WorkbenchLoginPage(page);
        loginPage.login(
                WorkbenchConfigReader.get("username"),
                WorkbenchConfigReader.get("password"));
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
        Assert.assertTrue(page.url().contains("create-campaign"),
                "Should remain on create campaign flow after selecting Bednet Distribution and clicking Next");
    }
}
