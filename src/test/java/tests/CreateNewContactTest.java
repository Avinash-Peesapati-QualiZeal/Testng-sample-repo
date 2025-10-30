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

public class CreateNewContactTest extends BaseTest {

    private LoginPage loginPage;
    private ContactCreationPage contactCreationPage;

    @BeforeMethod
    public void setUp() {
        // Set baseUrl from config if needed
        baseUrl = "https://example.com"; // Replace with actual URL or config reader if required
        // driver.get(baseUrl); // Uncomment if navigation to base URL is needed
    }

    @Test(description = "TC-N001: Verify system accepts valid inputs and displays appropriate message when creating a new contact.")
    @Description("Test Case TC-N001: Verify that the CRM system accepts valid contact creation inputs and displays the appropriate success message. Ensures that the new contact is saved in the CRM system.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Test steps: Log in, create new contact, verify success message.")
    public void testCreateNewContact() {
        // Initialize Page Objects using page.getInstance()
        loginPage = page.getInstance(LoginPage.class);
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        // Step 1: Ensure user is logged in
        loginPage.loginWithValidCredentials();

        // Step 2: Create a new contact using the high-level method
        contactCreationPage.createNewContact(
            "John", // First Name
            "Doe", // Last Name
            "john.doe@example.com", // Email
            "1234567890", // Phone
            "Company Inc.", // Company
            "Lead" // Contact Type or Status
        );

        // Step 3: Verify the success message is displayed
        contactCreationPage.verifyContactCreationSuccessMessage();

        // Step 4: Verify that the new contact is saved in the CRM system
        contactCreationPage.verifyContactSavedInCRM("john.doe@example.com");
    }
}
