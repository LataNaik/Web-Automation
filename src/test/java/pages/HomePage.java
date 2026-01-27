package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    private Locator homeElement;
    private Locator createComplaint;
    private Locator searchComplaint;
    private Locator createUser;
    private Locator searchUser;


    public HomePage(Page page) {
        this.homeElement = page.locator(".digit-topbar-ulb");
        this.createComplaint = page.locator("h2.digit-button-label:has-text('Create Complaint')");
        this.searchComplaint = page.locator("h2.digit-button-label:has-text('Search Complaint')");
        this.createUser = page.locator("h2.digit-button-label:has-text('Create User')");
        this.searchUser = page.locator("h2.digit-button-label:has-text('Search User')");
    }

    public void navigateToCreateComplaint() {
        createComplaint.click();
    }

    public void navigateToSearchComplaint() {
        searchComplaint.click();
    }

    public void navigateToCreateUser() {
        createUser.click();
    }

    public void navigateToSearchUser() {
        searchUser.click();
    }

    public boolean isHomeVisible() {
        homeElement.waitFor();
        return homeElement.isVisible();
    }
}
