package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.ComplaintPage;
import pages.HomePage;

public class ComplaintTest extends BaseTest {
    
    @Test
    public void createComplaint() {
        HomePage homePage = new HomePage(page);
        homePage.navigateToCreateComplaint();
        
        ComplaintPage complaintPage = new ComplaintPage(page);
        complaintPage.fillComplaintForm();
            
    }
}
