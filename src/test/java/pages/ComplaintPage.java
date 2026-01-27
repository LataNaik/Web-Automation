package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ComplaintPage {

    private Page page;
    private Locator complaintType;
    private Locator dropdownOption;
    private Locator complaintDate;
    private Locator todayDate;
    private Locator country;
    private Locator complaintFor;
    // private Locator complainantName;
    // private Locator complainantNumber;
    private Locator description;
    private Locator submitBtn;

    public ComplaintPage(Page page) {
        this.page = page;
        this.complaintType = page.locator(".digit-dropdown-employee-select-wrap").first();
        this.dropdownOption = page.locator(".main-option");
        this.complaintDate = page.locator("input[name='ComplaintDate']");
        this.todayDate = page.locator(".react-datepicker__day--today");
        this.country = page.locator(".digit-dropdown-employee-select-wrap").nth(1);
        this.complaintFor = page.locator(".digit-radio-btn-checkmark").first();
        // this.complainantName = page.locator("#complainant-name");
        // this.complainantNumber = page.locator("#complainant-number");
        this.description = page.locator("digit-field-container");
        this.submitBtn = page.locator(".digit-submit-bar digit-formcomposer-submitbar");
    }

    public void fillComplaintForm() {
        complaintType.click();
        dropdownOption.first().click();

        complaintDate.click();
        todayDate.click();

        country.click();
        dropdownOption.first().click();

        complaintFor.click();

        // complainantName.fill(name);
        // complainantNumber.fill(number);

        description.fill("");
    }

}