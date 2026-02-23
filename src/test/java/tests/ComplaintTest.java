package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ComplaintPage;

/**
 * Test class for Complaint functionality.
 *
 * Navigation options available:
 *   1. nav.goToCreateComplaint()     - Uses NavigationHelper
 *   2. homePage.goToCreateComplaint() - Uses HomePage (pre-initialized)
 */
public class ComplaintTest extends BaseTest {

    @Test
    public void createComplaint() {
        // Option 1: Using NavigationHelper (recommended)
        ComplaintPage complaint = nav.goToCreateComplaint();

        // Verify we're on the complaint form
        Assert.assertTrue(complaint.isFormDisplayed(), "Complaint form should be displayed");

        // Fill the form
        complaint.fillForm("Water Leakage", "India", "Water pipe broken in bathroom");
    }

    @Test
    public void createComplaintWithFluentNavigation() {
        // Option 2: Using fluent navigation from HomePage
        homePage.goToCreateComplaint()
                .selectComplaintType("Garbage")
                .selectTodayDate()
                .selectCountryByIndex(0)
                .selectFirstRadioOption()
                .enterDescription("Garbage not collected for 3 days")
                .submit();
    }

    @Test
    public void createComplaintByIndex() {
        // Navigate and fill form using index-based selection
        ComplaintPage complaint = nav.goToCreateComplaint();
        complaint.fillFormByIndex(0, 0, 0, "Test complaint description");
        complaint.submit();
    }

    @Test
    public void createComplaintWithAttachment() {
        // Fill form with attachment and submit in one call
        ComplaintPage complaint = nav.goToCreateComplaint();
        complaint.fillAndSubmit(
                "Street Light",
                "India",
                "Street light not working",
                "/path/to/attachment.pdf"  // Update with actual file path
        );
    }
}
