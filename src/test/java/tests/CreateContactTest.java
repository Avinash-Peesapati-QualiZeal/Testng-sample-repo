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
import com.yourpackage.pages.ContactPage;
import com.yourpackage.utils.PublicCommon;
import base.BaseTest;

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
    @Description("Verify that the CRM system accepts valid inputs for creating a new contact and displays the appropriate success message.")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateNewContact() {
        stepLoginToCRM();
        stepNavigateToContactCreation();
        stepEnterContactDetails();
        stepSubmitContactForm();
        stepVerifyContactCreationMessage();
        stepVerifyContactIsSaved();
    }

    @Step("Login to CRM system with valid credentials")
    public void stepLoginToCRM() {
        try {
            loginPage.loginWithValidCredentials(); // Assumes encapsulated login
            Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");
            PublicCommon.reportPass("Logged in to CRM successfully");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to login to CRM: " + e.getMessage());
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
            PublicCommon.reportFailAssert("Failed to navigate to contact creation screen: " + e.getMessage());
        }
    }

    @Step("Enter valid contact details")
    public void stepEnterContactDetails() {
        try {
            contactPage.enterFirstName("John");
            contactPage.enterLastName("Doe");
            contactPage.enterEmail("john.doe@example.com");
            contactPage.enterPhone("1234567890");
            contactPage.enterCompany("Example Corp");
            PublicCommon.reportPass("Entered valid contact details");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to enter contact details: " + e.getMessage());
        }
    }

    @Step("Submit the contact creation form")
    public void stepSubmitContactForm() {
        try {
            contactPage.clickSaveContactButton();
            PublicCommon.reportPass("Submitted contact creation form");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to submit contact form: " + e.getMessage());
        }
    }

    @Step("Verify system displays appropriate success message for contact creation")
    public void stepVerifyContactCreationMessage() {
        try {
            Assert.assertTrue(contactPage.isSuccessMessageDisplayed(), "Success message should be displayed");
            PublicCommon.reportPass("Appropriate success message displayed");
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Success message not displayed: " + e.getMessage());
        }
    }

    @Step("Verify new contact is saved in the CRM system")
    public void stepVerifyContactIsSaved() {
        try {
            Assert.assertTrue(contactPage.isContactPresent("John", "Doe"), "Contact should be saved and present in the list");
            PublicCommon.reportPass("New contact saved and verified in CRM");
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Contact not saved or not found: " + e.getMessage());
        }
    }
}
