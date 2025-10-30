package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.LoginPage;
import pages.HomePage;
import pages.ContactPage;
import base.BaseTest;
import utils.PublicCommon;

public class CreateContactTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private ContactPage contactPage;

    @BeforeMethod
    public void setUp() {
        loginPage = page.getInstance(LoginPage.class);
        homePage = page.getInstance(HomePage.class);
        contactPage = page.getInstance(ContactPage.class);
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
        stepVerifyContactIsSaved();
    }

    @Step("Login to CRM as a valid user")
    public void stepLoginToCRM() {
        try {
            loginPage.loginWithValidCredentials();
            Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");
            PublicCommon.reportPass("User logged in successfully");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Login failed: " + e.getMessage());
        }
    }

    @Step("Navigate to contact creation screen")
    public void stepNavigateToContactCreation() {
        try {
            homePage.goToContactsSection();
            contactPage.clickCreateContactButton();
            Assert.assertTrue(contactPage.isOnCreateContactPage(), "Should be on create contact page");
            PublicCommon.reportPass("Navigated to contact creation screen");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Navigation to contact creation failed: " + e.getMessage());
        }
    }

    @Step("Enter valid contact details")
    public void stepEnterContactDetails() {
        try {
            contactPage.enterFirstName("John");
            contactPage.enterLastName("Doe");
            contactPage.enterEmail("john.doe@example.com");
            contactPage.enterPhone("1234567890");
            contactPage.enterCompany("Test Company");
            PublicCommon.reportPass("Entered valid contact details");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to enter contact details: " + e.getMessage());
        }
    }

    @Step("Submit the contact creation form")
    public void stepSubmitContactForm() {
        try {
            contactPage.clickSaveContactButton();
            PublicCommon.reportPass("Clicked save contact button");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to submit contact form: " + e.getMessage());
        }
    }

    @Step("Verify system displays appropriate message after contact creation")
    public void stepVerifyContactCreationMessage() {
        try {
            Assert.assertTrue(contactPage.isContactCreationSuccessMessageDisplayed(), "Success message should be displayed");
            PublicCommon.reportPass("Appropriate success message displayed");
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Success message not displayed: " + e.getMessage());
        }
    }

    @Step("Verify new contact is saved in the CRM system")
    public void stepVerifyContactIsSaved() {
        try {
            Assert.assertTrue(contactPage.isContactPresentInList("John", "Doe"), "Contact should be present in the list");
            PublicCommon.reportPass("New contact is saved in the CRM system");
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Contact not saved: " + e.getMessage());
        }
    }
}
