package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import com.yourpackage.pages.LoginPage;
import com.yourpackage.pages.HomePage;
import com.yourpackage.pages.ContactCreationPage;
import com.yourpackage.utils.PublicCommon;

public class CreateContactTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private ContactCreationPage contactCreationPage;

    @BeforeMethod
    public void setUp() {
        loginPage = page.getInstance(LoginPage.class);
        homePage = page.getInstance(HomePage.class);
        contactCreationPage = page.getInstance(ContactCreationPage.class);
    }

    @Test(description = "TC-N001: Create a new contact - Verify system accepts valid inputs and displays appropriate message.")
    @Description("Verify system accepts valid inputs and displays appropriate message when creating a new contact.")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateNewContact() {
        stepLoginToCRM();
        stepNavigateToContactCreation();
        stepEnterContactDetails();
        stepSubmitContactForm();
        stepVerifyContactCreationMessage();
        stepVerifyContactSaved();
    }

    @Step("Login to CRM system as precondition")
    public void stepLoginToCRM() {
        try {
            // Assume credentials are fetched from config or testdata utils
            String username = "testuser";
            String password = "password123";
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
            Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");
            PublicCommon.reportPass("Logged into CRM successfully");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to login to CRM: " + e.getMessage());
        }
    }

    @Step("Navigate to contact creation screen")
    public void stepNavigateToContactCreation() {
        try {
            homePage.goToContactsSection();
            homePage.clickCreateContactButton();
            Assert.assertTrue(contactCreationPage.isAtContactCreationPage(), "Should be at contact creation page");
            PublicCommon.reportPass("Navigated to contact creation screen");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to navigate to contact creation screen: " + e.getMessage());
        }
    }

    @Step("Enter valid contact details")
    public void stepEnterContactDetails() {
        try {
            // Example values, replace with dynamic/test data as needed
            contactCreationPage.enterFirstName("John");
            contactCreationPage.enterLastName("Doe");
            contactCreationPage.enterEmail("john.doe@example.com");
            contactCreationPage.enterPhone("1234567890");
            contactCreationPage.enterCompany("ExampleCorp");
            PublicCommon.reportPass("Entered valid contact details");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to enter contact details: " + e.getMessage());
        }
    }

    @Step("Submit the contact creation form")
    public void stepSubmitContactForm() {
        try {
            contactCreationPage.clickSaveButton();
            PublicCommon.reportPass("Submitted the contact creation form");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to submit contact creation form: " + e.getMessage());
        }
    }

    @Step("Verify system displays appropriate success message")
    public void stepVerifyContactCreationMessage() {
        try {
            Assert.assertTrue(contactCreationPage.isSuccessMessageDisplayed(), "Success message should be displayed");
            PublicCommon.reportPass("System displayed appropriate success message");
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Success message not displayed: " + e.getMessage());
        }
    }

    @Step("Verify new contact is saved in CRM system")
    public void stepVerifyContactSaved() {
        try {
            Assert.assertTrue(contactCreationPage.isContactPresentInList("John", "Doe"), "Contact should be present in the list");
            PublicCommon.reportPass("New contact is saved and present in CRM");
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Contact not saved in CRM: " + e.getMessage());
        }
    }
}
