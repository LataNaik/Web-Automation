package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class BoundarySelectionPage {

    private Page page;

    // Campaign template step elements
    private Locator DefineTarget;
    private Locator firstlevel;
    private Locator secondlevel;
    private Locator thirdlevel;
    private Locator fourthlevel;
    private Locator outside;


    public BoundarySelectionPage(Page page) {
    
        this.page = page;
        this.DefineTarget = page.locator("#campaign-details-page-button-selecting-boundaries");
        this.firstlevel = page.locator(".selecting-boundaries-dropdown input").nth(0);
        this.secondlevel = page.locator(".selecting-boundaries-dropdown input").nth(1);
        this.thirdlevel = page.locator(".selecting-boundaries-dropdown input").nth(2);
        this.fourthlevel = page.locator(".selecting-boundaries-dropdown input").nth(3);
        this.outside=page.locator(".boundary-selection-label");
    }

    // --- Actions ---

    public void clickDefineTarget() {
        DefineTarget.click();
        page.waitForTimeout(1000);
    }

     public void clickfirstlevel() {
    
        firstlevel.first().click();
        page.waitForTimeout(1000);
    }

    public void clicksecondlevel() {
     
        secondlevel.first().click();
        outside.click();
        page.waitForTimeout(1000);
    }

    public void clickthirdlevel() {
   
        thirdlevel.first().click();
        outside.click();
        page.waitForTimeout(1000);
    }

    public void clickfourthlevel() {

        fourthlevel.first().click();
        outside.click();
        page.waitForTimeout(1000);
    }
}