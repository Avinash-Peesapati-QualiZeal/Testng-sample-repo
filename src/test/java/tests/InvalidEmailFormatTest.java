package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import com.yourpackage.pages.LoginPage;
import com.yourpackage.pages.HomePage;
import com.yourpackage.pages.ContactCreationPage;
import com.yourpackage.utils.TestDataLoader;
import com.yourpackage.base.BaseTest;

public class InvalidEmailFormatTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private ContactCreationPage contactCreationPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        contactCreationPage = new ContactCreationPage(getDriver());
    }

    @DataProvider(name = "invalidEmailProvider")
    public Object[][] invalidEmailProvider() {
        return new Object[][] {
            {"plainaddress"},
            {"@missingusername.com"},
            {"username@.com"},
            {"username@com"},
            {"username@domain..com"}
        };
    }

    /**
     * Test Case ID: TC-N002
     * Name: Invalid Email Format Rejection
     * Objective: Verify system rejects invalid email formats and displays appropriate error message.
     * Preconditions: User is logged into the CRM system and navigates to the contact creation screen.
     * Priority: Medium
     */
    @Test(dataProvider = "invalidEmailProvider", priority = 2, groups = {"negative", "contact", "email"})
    public void testInvalidEmailFormatRejection(String invalidEmail) {
        try {
            // Assume user is already logged in as per precondition, else uncomment below:
            // loginPage.loginValidUser();

            // Navigate to contact creation screen
            homePage.navigateToContactCreation();

            // Fill in contact details with invalid email
            contactCreationPage.enterContactName("Test User");
            contactCreationPage.enterContactEmail(invalidEmail);
            contactCreationPage.saveContact();

            // Assert error message is displayed
            String actualError = contactCreationPage.getEmailValidationError();
            Assert.assertTrue(actualError != null && !actualError.isEmpty(), "Expected error message for invalid email, but none was displayed.");
            Assert.assertTrue(actualError.toLowerCase().contains("invalid"), "Error message should indicate invalid email format. Actual: " + actualError);

            // Assert that no contact was saved (e.g., check that contact is not in list or confirmation is not shown)
            Assert.assertFalse(contactCreationPage.isContactSaved(), "Contact should not be saved with invalid email format.");
        } catch (Exception e) {
            Assert.fail("Exception occurred during invalid email format test: " + e.getMessage(), e);
        }
    }
}
