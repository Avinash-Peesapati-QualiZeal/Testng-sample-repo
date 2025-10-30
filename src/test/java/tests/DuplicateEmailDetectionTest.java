package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.LoginPage;
import pages.ContactCreationPage;
import base.BaseTest;

public class DuplicateEmailDetectionTest extends BaseTest {

    private LoginPage loginPage;
    private ContactCreationPage contactCreationPage;

    @BeforeMethod
    public void setUp() {
        // Set base URL from config or as required
        baseUrl = "https://example.com"; // Replace with actual URL extraction logic if needed
        // driver.get(baseUrl); // Uncomment if navigation is necessary
    }

    @Test(description = "TC-N003: Ensure system prevents creation of contacts with duplicate emails and displays error message.")
    @Description("Test Case TC-N003: Ensure the CRM system prevents creation of contacts with duplicate emails and displays an appropriate error message. No duplicate contact should be saved.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Test steps: Log in, attempt to create contact with duplicate email, verify error message.")
    public void testDuplicateEmailDetection() {
        // Initialize Page Objects using page.getInstance()
        loginPage = page.getInstance(LoginPage.class);
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        // Step 1: Ensure user is logged in
        loginPage.loginWithValidCredentials();

        // Step 2: Attempt to create a new contact with an email that already exists
        contactCreationPage.createNewContact("Jane", "Smith", "test@example.com", "9876543210", "Acme Corp", "Lead");

        // Step 3: Verify the error message for duplicate email is displayed
        contactCreationPage.verifyDuplicateEmailErrorMessage();

        // Step 4: Optionally verify that no duplicate contact is saved in the CRM
        contactCreationPage.verifyContactNotSavedInCRM("test@example.com");
    }
}
