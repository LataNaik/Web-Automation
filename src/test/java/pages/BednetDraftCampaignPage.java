package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BednetDraftCampaignPage {

    private Page page;

    // Campaign template step elements
    private Locator campaignTypeDropdown;
    private Locator bednetDropdown;
    private Locator nextButton;

    public BednetDraftCampaignPage(Page page) {
        this.page = page;
        this.campaignTypeDropdown = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Select an option"));
        this.bednetDropdown = page.getByRole(AriaRole.BUTTON).nth(1);
        this.nextButton = page.locator("#campaign-create-campaign-formcomposer-setup-campaign-primary-submit-btn");
    }

    // --- Actions ---

    public void clickCampaignTypeDropdown() {
        campaignTypeDropdown.click(new Locator.ClickOptions().setForce(true));
        page.waitForTimeout(1000);
    }

    public void selectBednetDistribution() {
        clickCampaignTypeDropdown();
        page.getByText("Bednet Distribution").click();
    }

    public void clickBednetDropdown() {
        bednetDropdown.click(new Locator.ClickOptions().setForce(true));
        page.waitForTimeout(1000);
    }

    public void clickNext() {
        nextButton.click();
    }

    // --- Verifications ---

    public boolean isCampaignTypeDropdownVisible() {
        campaignTypeDropdown.waitFor();
        return campaignTypeDropdown.isVisible();
    }

    public boolean isBednetDropdownVisible() {
        bednetDropdown.waitFor();
        return bednetDropdown.isVisible();
    }

    public boolean isNextButtonVisible() {
        nextButton.waitFor();
        return nextButton.isVisible();
    }

    public boolean isNextButtonEnabled() {
        return nextButton.isEnabled();
    }
}
