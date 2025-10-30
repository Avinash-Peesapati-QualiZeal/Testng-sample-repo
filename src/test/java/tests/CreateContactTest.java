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
    @Description("Verify that the system accepts valid inputs for creating a new contact and displays the appropriate confirmation message.")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateNewContact() {
        stepLoginToCRM();
        stepNavigateToContactCreation();
        stepEnterContactDetails();
        stepSubmitContactForm();
        stepVerifyContactCreationMessage();
        stepVerifyContactSaved();
    }

    @Step("Login to CRM as a valid user")
    public void stepLoginToCRM() {
        try {
            loginPage.loginWithValidCredentials();
            Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in successfully");
            PublicCommon.reportPass("User logged in successfully");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to login: " + e.getMessage());
        }
    }

    @Step("Navigate to Contact Creation screen")
    public void stepNavigateToContactCreation() {
        try {
            homePage.goToContactCreation();
            Assert.assertTrue(contactPage.isAtContactCreationPage(), "Should be at contact creation page");
            PublicCommon.reportPass("Navigated to contact creation screen");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to navigate to contact creation: " + e.getMessage());
        }
    }

    @Step("Enter valid contact details")
    public void stepEnterContactDetails() {
        try {
            contactPage.enterContactName("John Doe");
            contactPage.enterContactEmail("john.doe@example.com");
            contactPage.enterContactPhone("1234567890");
            contactPage.selectContactType("Customer");
            contactPage.enterContactAddress("123 Main St, Cityville");
            PublicCommon.reportPass("Entered valid contact details");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to enter contact details: " + e.getMessage());
        }
    }

    @Step("Submit the contact creation form")
    public void stepSubmitContactForm() {
        try {
            contactPage.clickSaveContact();
            PublicCommon.reportPass("Clicked save contact button");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to submit contact form: " + e.getMessage());
        }
    }

    @Step("Verify system displays appropriate confirmation message")
    public void stepVerifyContactCreationMessage() {
        try {
            String confirmationMsg = contactPage.getContactCreationConfirmationMessage();
            Assert.assertTrue(confirmationMsg.contains("Contact created successfully"), "Confirmation message should indicate success");
            PublicCommon.reportPass("Confirmation message displayed: " + confirmationMsg);
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to verify confirmation message: " + e.getMessage());
        }
    }

    @Step("Verify new contact is saved in the CRM system")
    public void stepVerifyContactSaved() {
        try {
            Assert.assertTrue(contactPage.isContactPresent("John Doe"), "New contact should be present in the system");
            PublicCommon.reportPass("New contact is saved and present in the CRM");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to verify contact is saved: " + e.getMessage());
        }
    }
}
