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
    @Description("Verify system accepts valid inputs and displays appropriate message when creating a new contact.")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateNewContact_TC_N001() {
        stepLoginToCRM();
        stepNavigateToContactCreation();
        stepEnterContactDetails();
        stepSubmitContactForm();
        stepVerifyContactCreationMessage();
        stepVerifyContactSaved();
    }

    @Step("Login to CRM system as a valid user")
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
            contactPage.clickCreateContact();
            Assert.assertTrue(contactPage.isContactCreationScreenDisplayed(), "Contact creation screen should be displayed");
            PublicCommon.reportPass("Navigated to contact creation screen");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to navigate to contact creation screen: " + e.getMessage());
        }
    }

    @Step("Enter valid contact details")
    public void stepEnterContactDetails() {
        try {
            contactPage.enterContactName("John Doe");
            contactPage.enterContactEmail("john.doe@example.com");
            contactPage.enterContactPhone("1234567890");
            contactPage.enterContactAddress("123 Main St, City, Country");
            contactPage.selectContactType("Customer");
            PublicCommon.reportPass("Entered valid contact details");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to enter contact details: " + e.getMessage());
        }
    }

    @Step("Submit the contact creation form")
    public void stepSubmitContactForm() {
        try {
            contactPage.clickSaveContact();
            PublicCommon.reportPass("Clicked save contact");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to submit contact form: " + e.getMessage());
        }
    }

    @Step("Verify system displays appropriate message for contact creation")
    public void stepVerifyContactCreationMessage() {
        try {
            String expectedMessage = "Contact created successfully";
            String actualMessage = contactPage.getCreationSuccessMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "Success message should match");
            PublicCommon.reportPass("System displayed appropriate success message: " + actualMessage);
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Success message verification failed: " + e.getMessage());
        }
    }

    @Step("Verify new contact is saved in CRM system")
    public void stepVerifyContactSaved() {
        try {
            Assert.assertTrue(contactPage.isContactPresent("John Doe"), "New contact should be present in contact list");
            PublicCommon.reportPass("New contact is saved in CRM system");
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Contact save verification failed: " + e.getMessage());
        }
    }
}
