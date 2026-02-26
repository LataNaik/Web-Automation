package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.time.LocalDate;

public class BednetDraftCampaignPage {

    private Page page;

    // Campaign template step elements
    private Locator campaignTypeDropdown;
    private Locator bednetDropdown;
    private Locator nextButton;
    private Locator campaignName;
    private Locator startDateInput;
    private Locator endDateInput;

    public BednetDraftCampaignPage(Page page) {
    
        this.page = page;
        this.campaignTypeDropdown = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Select an option"));
        this.bednetDropdown = page.locator("div.digit-dropdown-item").nth(1);
        this.nextButton = page.locator("#campaign-create-campaign-formcomposer-setup-campaign-primary-submit-btn");
        this.campaignName = page.locator("input[name='CampaignName']");
        this.startDateInput = page.locator("input[placeholder='Start date']");
        this.endDateInput = page.locator("input[placeholder='End date']");
    }

    // --- Actions ---

    public void clickCampaignTypeDropdown() {
        campaignTypeDropdown.click(new Locator.ClickOptions().setForce(true));
        page.waitForTimeout(1000);
    }

    public void selectBednetDistribution() {
        clickCampaignTypeDropdown();
        page.getByRole(AriaRole.OPTION).nth(1).click();
        page.waitForTimeout(1000);
    }

    public void clickBednetDropdown() {
        bednetDropdown.click(new Locator.ClickOptions().setForce(true));
        page.waitForTimeout(1000);
    }

    public void clickNext() {
        nextButton.click();
    }

    public void clearAndEnterDynamicCampaignName() {
        campaignName.clear();
        String dynamicName = "BednetCampaign" + java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        campaignName.fill(dynamicName);
    }

    public void fillStartDate() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        startDateInput.click();
        page.waitForTimeout(500);
        // Pick the day from the calendar popup
        page.locator(".react-datepicker__day--0" + String.format("%02d", tomorrow.getDayOfMonth())
                + ":not(.react-datepicker__day--outside-month)").first().click();
        page.waitForTimeout(500);
    }

    public void fillEndDate() {
        LocalDate oneMonthLater = LocalDate.now().plusMonths(1);
        endDateInput.click();
        page.waitForTimeout(500);
        // Navigate to next month in calendar
        page.locator(".react-datepicker__navigation--next").click();
        page.waitForTimeout(500);
        // Pick the day from the calendar popup
        page.locator(".react-datepicker__day--0" + String.format("%02d", oneMonthLater.getDayOfMonth())
                + ":not(.react-datepicker__day--outside-month)").first().click();
        page.waitForTimeout(500);
    }

    public void fillStartAndEndDates() {
        fillStartDate();
        fillEndDate();
    }
}