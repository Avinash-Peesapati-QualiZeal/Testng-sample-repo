package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.LoginPage;
import pages.ContactCreationPage;

public class CreateContactTest {
    private LoginPage loginPage;
    private ContactCreationPage contactCreationPage;

    @BeforeMethod
    public void setUp() {
        loginPage = page.getInstance(LoginPage.class);
        contactCreationPage = page.getInstance(ContactCreationPage.class);
    }

    @Test(description = "TC-N001: Create a new contact - Verify system accepts valid inputs and displays appropriate message.")
    @Description("Verify that the CRM system accepts valid inputs for creating a new contact and displays the appropriate success message.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Create a new contact with valid details and verify the success message is displayed.")
    public void testCreateNewContact() {
        // Step 1: Ensure user is logged in (precondition)
        loginPage.loginWithValidCredentials();

        // Step 2: Navigate to the contact creation screen
        contactCreationPage.navigateToContactCreation();

        // Step 3: Fill in contact details (using valid data)
        contactCreationPage.enterFirstName("John");
        contactCreationPage.enterLastName("Doe");
        contactCreationPage.enterEmail("john.doe@example.com");
        contactCreationPage.enterPhone("1234567890");
        contactCreationPage.enterAddress("123 Main St, City, Country");

        // Step 4: Submit the contact creation form
        contactCreationPage.submitContactForm();

        // Step 5: Verify the appropriate message is displayed (no assertion per instructions)
        contactCreationPage.verifySuccessMessageDisplayed();
        // Post-condition: New contact is saved in the CRM system
    }
}
