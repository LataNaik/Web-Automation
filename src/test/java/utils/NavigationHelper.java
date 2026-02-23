package utils;

import com.microsoft.playwright.Page;
import pages.*;

/**
 * NavigationHelper - Central place for all page navigation.
 *
 * Usage:
 *   NavigationHelper nav = new NavigationHelper(page);
 *
 *   // Direct navigation
 *   nav.goToHome();
 *   nav.goToCreateComplaint();
 *
 *   // Get page objects
 *   HomePage home = nav.homePage();
 *   ComplaintPage complaint = nav.complaintPage();
 */
public class NavigationHelper {

    private final Page page;

    public NavigationHelper(Page page) {
        this.page = page;
    }

    // ==================== QUICK NAVIGATION ====================

    /**
     * Navigate to Home page and return HomePage object.
     */
    public HomePage goToHome() {
        // Click home button if visible, otherwise use browser back
        if (page.locator(".digit-topbar-home").isVisible()) {
            page.locator(".digit-topbar-home").click();
        }
        return new HomePage(page);
    }

    /**
     * Navigate to Create Complaint page.
     */
    public ComplaintPage goToCreateComplaint() {
        HomePage home = goToHome();
        home.navigateToCreateComplaint();
        return new ComplaintPage(page);
    }

    /**
     * Navigate to Search Complaint page.
     */
    public void goToSearchComplaint() {
        HomePage home = goToHome();
        home.navigateToSearchComplaint();
    }

    /**
     * Navigate to Create User page.
     */
    public void goToCreateUser() {
        HomePage home = goToHome();
        home.navigateToCreateUser();
    }

    /**
     * Navigate to Search User page.
     */
    public void goToSearchUser() {
        HomePage home = goToHome();
        home.navigateToSearchUser();
    }

    // ==================== PAGE OBJECT GETTERS ====================
    // Use these when you're already on the page

    /**
     * Get LoginPage object (use when on login page).
     */
    public LoginPage loginPage() {
        return new LoginPage(page);
    }

    /**
     * Get HomePage object (use when on home page).
     */
    public HomePage homePage() {
        return new HomePage(page);
    }

    /**
     * Get ComplaintPage object (use when on complaint page).
     */
    public ComplaintPage complaintPage() {
        return new ComplaintPage(page);
    }

    // ==================== UTILITY ====================

    /**
     * Go back to previous page.
     */
    public void goBack() {
        page.goBack();
    }

    /**
     * Refresh current page.
     */
    public void refresh() {
        page.reload();
    }

    /**
     * Navigate to any URL.
     *
     * @param url Full URL to navigate to
     */
    public void navigateTo(String url) {
        page.navigate(url);
    }

    /**
     * Get current page URL.
     */
    public String getCurrentUrl() {
        return page.url();
    }

    /**
     * Wait for page to load completely.
     */
    public void waitForPageLoad() {
        page.waitForLoadState();
    }
}
