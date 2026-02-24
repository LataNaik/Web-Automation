package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.BednetDraftCampaignPage;
import pages.CampaignLandingPage;

public class BednetDraftCampaignTest extends BaseTest {

    private CampaignLandingPage landingPage;
    private BednetDraftCampaignPage draftPage;

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setup")
    public void navigateToCreateCampaign() {
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
    public void verifyCampaignTypeDropdownIsClickable() {
        draftPage.clickCampaignTypeDropdown();
        Assert.assertTrue(page.getByText("Bednet Distribution").isVisible(),
                "Bednet Distribution option should be visible after clicking the campaign type dropdown");
    }
    @Test(groups = {"regression"})
    public void verifySelectBednetDistributionAndClickNext() {
        draftPage.selectBednetDistribution();
        draftPage.clickNext();
        page.waitForLoadState();
        Assert.assertTrue(page.url().contains("create-campaign"),
                "Should remain on create campaign flow after selecting Bednet Distribution and clicking Next");
    }

    @Test(groups = {"regression"})
    public void verifyFullDraftCampaignFormSubmission() {
        draftPage.selectBednetDistribution();
        draftPage.clickNext();
        page.waitForLoadState();
        page.waitForTimeout(2000);

        draftPage.clearAndEnterDynamicCampaignName();
        draftPage.clickNext();
        page.waitForLoadState();
        page.waitForTimeout(2000);

        draftPage.fillStartAndEndDates();
        draftPage.clickNext();
        page.waitForLoadState();

        Assert.assertTrue(page.url().contains("create-campaign"),
                "Should remain in the create campaign flow after submitting the full draft form");
    }


    @Test(groups = {"regression"})
    public void verifyStartDateCanBeFilled() {
        draftPage.selectBednetDistribution();
        draftPage.clickNext();
        page.waitForLoadState();
        page.waitForTimeout(2000);

        draftPage.clearAndEnterDynamicCampaignName();
        draftPage.clickNext();
        page.waitForLoadState();
        page.waitForTimeout(2000);

        draftPage.fillStartDate();
        page.waitForTimeout(1000);
        String startValue = page.locator("input[placeholder='Start date']").inputValue();
        if (startValue.isEmpty()) {
            startValue = (String) page.locator("input[placeholder='Start date']").evaluate("el => el.value");
        }
        Assert.assertFalse(startValue.isEmpty(),
                "Start date input should not be empty after filling");
    }

    @Test(groups = {"regression"})
    public void verifyEndDateCanBeFilled() {
        draftPage.selectBednetDistribution();
        draftPage.clickNext();
        page.waitForLoadState();
        page.waitForTimeout(2000);

        draftPage.clearAndEnterDynamicCampaignName();
        draftPage.clickNext();
        page.waitForLoadState();
        page.waitForTimeout(2000);

        draftPage.fillEndDate();
        page.waitForTimeout(1000);
        String endValue = page.locator("input[placeholder='End date']").inputValue();
        if (endValue.isEmpty()) {
            endValue = (String) page.locator("input[placeholder='End date']").evaluate("el => el.value");
        }
        Assert.assertFalse(endValue.isEmpty(),
                "End date input should not be empty after filling");
    }

    
}