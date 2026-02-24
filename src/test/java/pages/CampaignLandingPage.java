package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CampaignLandingPage {

    private Page page;

    // HCM Console card
    private Locator hcmConsoleCard;
    private Locator hcmConsoleTitle;
    private Locator hcmConsoleDescription;
    private Locator createCampaignLink;
    private Locator myCampaignsLink;

    // Create Campaign page elements
    private Locator homeBreadcrumb;
    private Locator createCampaignBreadcrumb;
    private Locator heading;
    private Locator description;
    private Locator templateCardTitle;
    private Locator templateCardDescription;
    private Locator templatesAvailableText;
    private Locator cloneCardTitle;
    private Locator cloneCardDescription;
    private Locator scratchCardTitle;
    private Locator scratchCardDescription;
    private Locator continueButton;

    public CampaignLandingPage(Page page) {
        this.page = page;
        this.createCampaignLink = page.locator(".digit-button-label").nth(2);
        

       
        this.scratchCardTitle = page.locator("#campaign-home-card-campaign-create-methods-campaign-create-from-scratch");
        this.continueButton = page.locator("#campaign-campaign-home-standalone-create-new-campaign-from-scratch-btn");
    }

    // --- Actions ---

    public void clickCreateCampaign() {
        createCampaignLink.click();
    }

    // public void clickTemplateCard() {
    //     templateCardTitle.click();
    // }

    // public void clickCloneCard() {
    //     cloneCardTitle.click();
    // }

    public void clickScratchCard() {
        scratchCardTitle.click();
    }

    public void clickContinue() {
        continueButton.click();
    }

  


   
}