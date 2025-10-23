package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import com.yourpackage.pages.LoginPage;
import com.yourpackage.pages.HomePage;
import com.yourpackage.pages.ContactPage;
import com.yourpackage.utils.TestDataLoader;
import com.yourpackage.base.BaseTest;

public class DuplicateEmailDetectionTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private ContactPage contactPage;

    @BeforeMethod
    public void setUp() {
        // Initialize page objects
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        contactPage = new ContactPage(getDriver());
    }

    @Test(priority = 1, groups = {"negative", "contacts", "regression"})
    public void testDuplicateEmailDetection() {
        // Preconditions: User is logged in, and 'test@example.com' already exists
        loginPage.loginValidUser(); // Assumes method exists for valid login
        Assert.assertTrue(homePage.isLoggedIn(), "User should be logged in before creating contact");

        // Navigate to Contacts module
        homePage.goToContacts();
        Assert.assertTrue(contactPage.isAtContactPage(), "Should be on Contacts page");

        // Attempt to create a contact with duplicate email
        contactPage.clickAddContact();
        contactPage.enterContactName("Test User");
        contactPage.enterContactEmail("test@example.com");
        contactPage.saveContact();

        // Assert error message is displayed
        String errorMsg = contactPage.getDuplicateEmailErrorMessage();
        Assert.assertTrue(errorMsg != null && errorMsg.contains("already in use"),
            "Expected error message for duplicate email, but got: '" + errorMsg + "'");

        // Assert that contact was not created
        boolean contactExists = contactPage.isContactPresent("test@example.com");
        Assert.assertTrue(contactExists, "Original contact should still exist");
        int count = contactPage.countContactsByEmail("test@example.com");
        Assert.assertEquals(count, 1, "No duplicate contact should be created for email 'test@example.com'");
    }
}
