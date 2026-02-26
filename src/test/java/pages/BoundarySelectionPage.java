package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;



public class BoundarySelectionPage {

    private Page page;

    // Campaign template step elements
    private Locator DefineTarget;
    private Locator firstlevel;
    private Locator secondlevel;
    private Locator thirdlevel;
    private Locator fourthlevel;


    public BoundarySelectionPage(Page page) {
    
        this.page = page;
        this.DefineTarget = page.locator("#campaign-details-page-button-selecting-boundaries");
        this.firstlevel = page.locator(".selecting-boundaries-dropdown input").nth(0);
        this.secondlevel = page.locator(".selecting-boundaries-dropdown input").nth(1);
        this.thirdlevel = page.locator(".selecting-boundaries-dropdown input").nth(2);
        this.fourthlevel = page.locator(".selecting-boundaries-dropdown input").nth(3);
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
        page.waitForTimeout(1000);
    }

    public void clickthirdlevel() {
   
        thirdlevel.first().click();
        page.waitForTimeout(1000);
    }

    public void clickfourthlevel() {

        fourthlevel.first().click();
        page.waitForTimeout(1000);
    }
}