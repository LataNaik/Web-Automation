package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Page Object for Complaint Form.
 *
 * Usage:
 *   ComplaintPage complaint = new ComplaintPage(page);
 *   complaint.fillForm("Water Leakage", "India", "Pipe broken in bathroom");
 *   complaint.submit();
 *
 *   // Or with fluent navigation from HomePage:
 *   HomePage home = new HomePage(page);
 *   home.goToCreateComplaint().fillForm("Water Leakage", "India", "Issue description");
 */
public class ComplaintPage extends BasePage {

    // ==================== LOCATORS ====================
    // Dropdowns
    private final String complaintTypeDropdown = ".digit-dropdown-employee-select-wrap ";
    private final String countryDropdown = ".digit-dropdown-employee-select-wrap >> nth=1";
    private final String dropdownOption = ".main-option";

    // Date Picker
    private final String dateInput = "input[name='ComplaintDate']";
    private final String todayDate = ".react-datepicker__day--today";

    // Radio & Other Fields
    private final String radioButtons = ".digit-radio-btn-checkmark";
    private final String descriptionField = "textarea[name='description']";
    private final String fileInput = "input[type='file']";
    private final String submitButton = ".digit-submit-bar";

    public ComplaintPage(Page page) {
        super(page);
    }

    // ==================== MAIN ACTIONS ====================

    /**
     * Fill the complaint form with provided details.
     *
     * @param complaintType Type of complaint (e.g., "Water Leakage")
     * @param country       Country name (e.g., "India")
     * @param description   Description of the complaint
     * @return this ComplaintPage for method chaining
     */
    public ComplaintPage fillForm(String complaintType, String country, String description) {
        selectComplaintType(complaintType);
        selectTodayDate();
        selectCountry(country);
        selectFirstRadioOption();
        enterDescription(description);
        return this;
    }

    /**
     * Fill form by selecting options using their position (0-based index).
     *
     * @param complaintIndex Index of complaint type option
     * @param countryIndex   Index of country option
     * @param radioIndex     Index of radio button
     * @param description    Description text
     * @return this ComplaintPage for method chaining
     */
    public ComplaintPage fillFormByIndex(int complaintIndex, int countryIndex, int radioIndex, String description) {
        selectComplaintTypeByIndex(complaintIndex);
        selectTodayDate();
        selectCountryByIndex(countryIndex);
        selectRadioByIndex(radioIndex);
        enterDescription(description);
        return this;
    }

    /**
     * Fill form and submit with optional file attachment.
     *
     * @param complaintType Type of complaint
     * @param country       Country name
     * @param description   Description text
     * @param filePath      Path to attachment file (can be null)
     */
    public void fillAndSubmit(String complaintType, String country, String description, String filePath) {
        fillForm(complaintType, country, description);

        if (filePath != null && !filePath.isEmpty()) {
            uploadFile(filePath);
        }

        submit();
    }

    /**
     * Submit the complaint form.
     */
    public void submit() {
        form.click(submitButton);
        waitForPageLoad();
    }

    // ==================== INDIVIDUAL FIELD METHODS ====================

    /**
     * Select complaint type by visible text.
     */
    public ComplaintPage selectComplaintType(String complaintType) {
        form.click(complaintTypeDropdown);
        page.getByText(complaintType).click();
        return this;
    }

    /**
     * Select complaint type by index (0 = first option).
     */
    public ComplaintPage selectComplaintTypeByIndex(int index) {
        form.click(complaintTypeDropdown);
        page.locator(dropdownOption).nth(index).click();
        return this;
    }

    /**
     * Select country by visible text.
     */
    public ComplaintPage selectCountry(String country) {
        form.click(countryDropdown);
        page.getByText(country).click();
        return this;
    }

    /**
     * Select country by index (0 = first option).
     */
    public ComplaintPage selectCountryByIndex(int index) {
        form.click(countryDropdown);
        page.locator(dropdownOption).nth(index).click();
        return this;
    }

    /**
     * Select today's date from the date picker.
     */
    public ComplaintPage selectTodayDate() {
        form.click(dateInput);
        form.click(todayDate);
        return this;
    }

    /**
     * Select a specific day from the date picker.
     *
     * @param day Day number (1-31)
     */
    public ComplaintPage selectDate(int day) {
        form.click(dateInput);
        page.locator(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .getByText(String.valueOf(day), new Locator.GetByTextOptions().setExact(true))
                .first()
                .click();
        return this;
    }

    /**
     * Select first radio button option.
     */
    public ComplaintPage selectFirstRadioOption() {
        page.locator(radioButtons).first().click();
        return this;
    }

    /**
     * Select radio button by index (0 = first option).
     */
    public ComplaintPage selectRadioByIndex(int index) {
        page.locator(radioButtons).nth(index).click();
        return this;
    }

    /**
     * Enter complaint description.
     */
    public ComplaintPage enterDescription(String description) {
        form.enterText(descriptionField, description);
        return this;
    }

    /**
     * Upload an attachment file.
     *
     * @param filePath Full path to the file
     */
    public ComplaintPage uploadFile(String filePath) {
        form.uploadFile(fileInput, filePath);
        return this;
    }

    // ==================== VERIFICATION ====================

    /**
     * Check if complaint form is displayed.
     */
    public boolean isFormDisplayed() {
        return page.locator(complaintTypeDropdown).isVisible();
    }
}
