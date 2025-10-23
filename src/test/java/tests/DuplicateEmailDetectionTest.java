package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import com.yourpackage.pages.LoginPage;
import com.yourpackage.pages.HomePage;
import com.yourpackage.pages.ContactsPage;
import com.yourpackage.utils.TestDataLoader;
import com.yourpackage.base.BaseTest;

public class DuplicateEmailDetectionTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ContactsPage contactsPage;

    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        contactsPage = new ContactsPage(driver);
    }

    @Test(priority = 1, groups = {"regression", "negative"})
    public void testDuplicateEmailDetection() {
        // Preconditions: User is logged in, and a contact with 'test@example.com' exists
        // (Assume login is handled in BaseTest or here)
        if (!homePage.isLoggedIn()) {
            loginPage.login(TestDataLoader.getUsername(), TestDataLoader.getPassword());
            Assert.assertTrue(homePage.isLoggedIn(), "Login failed - user is not logged in");
        }

        // Navigate to Contacts page
        homePage.goToContacts();
        Assert.assertTrue(contactsPage.isAt(), "Failed to navigate to Contacts page");

        // Attempt to create a contact with duplicate email
        contactsPage.clickAddContact();
        contactsPage.enterContactName("Test User");
        contactsPage.enterContactEmail("test@example.com");
        contactsPage.saveContact();

        // Validate error message is displayed
        String errorMsg = contactsPage.getDuplicateEmailErrorMessage();
        Assert.assertTrue(errorMsg != null && errorMsg.contains("already in use"),
            "Expected duplicate email error message was not displayed. Actual: " + errorMsg);

        // Validate that no duplicate contact is saved
        boolean isDuplicatePresent = contactsPage.isContactPresentByEmail("test@example.com", "Test User");
        Assert.assertFalse(isDuplicatePresent && contactsPage.isDuplicateEntry("test@example.com", "Test User"),
            "Duplicate contact was created in the CRM system");
    }

    @AfterMethod
    public void tearDown() {
        // Clean up if needed
        quitDriver();
    }
}
