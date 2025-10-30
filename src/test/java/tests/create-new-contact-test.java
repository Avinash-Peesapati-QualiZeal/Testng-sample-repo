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
    private String baseUrl;

    @BeforeMethod
    public void setUp() {
        // Retrieve base URL from config if needed
        baseUrl = configReader.getProperty("base.url");
        // Optionally, open the browser and navigate to the base URL
        // driver.get(baseUrl);
    }

    @Test(description = "TC-N001: Verify system accepts valid inputs and displays appropriate message when creating a new contact.")
    @Description("Test Case TC-N001: Verify that the CRM system accepts valid contact creation inputs and displays the appropriate success message. Ensures that the new contact is saved in the CRM system.")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateNewContact() {
        // Initialize Page Objects using page.getInstance()
        loginPage = page.getInstance(LoginPage.class);
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        stepLoginToCRM();
        stepNavigateToContactCreation();
        stepFillContactDetailsAndSubmit();
        stepVerifySuccessMessage();
        stepVerifyContactSaved();
    }

    @Step("Login to CRM with valid credentials")
    private void stepLoginToCRM() {
        loginPage.loginWithValidCredentials();
    }

    @Step("Navigate to contact creation screen")
    private void stepNavigateToContactCreation() {
        contactCreationPage.navigateToContactCreationScreen();
    }

    @Step("Fill in valid contact details and submit the form")
    private void stepFillContactDetailsAndSubmit() {
        // Example data, in real scenario fetch from test data utils or config
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phone = "1234567890";
        String company = "Company Inc.";
        contactCreationPage.createNewContact(firstName, lastName, email, phone, company, "Lead");
    }

    @Step("Verify system displays appropriate success message")
    private void stepVerifySuccessMessage() {
        contactCreationPage.verifyContactCreationSuccessMessage();
    }

    @Step("Verify new contact is saved in CRM system")
    private void stepVerifyContactSaved() {
        contactCreationPage.verifyContactSavedInCRM("john.doe@example.com");
    }
}
