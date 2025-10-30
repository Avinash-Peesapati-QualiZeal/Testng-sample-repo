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

    @Test(description = "TC-N001: Verify system accepts valid inputs and displays appropriate message when creating a new contact.")
    @Description("TC-N001: Create a new contact and verify the system accepts valid inputs and displays the correct message.")
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
            // Assume credentials are loaded from config or test data utils
            String username = "validUser";
            String password = "validPass";
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
            Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");
            PublicCommon.reportPass("User logged in successfully");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to login to CRM: " + e.getMessage());
        }
    }

    @Step("Navigate to the contact creation screen")
    public void stepNavigateToContactCreation() {
        try {
            homePage.goToContactsSection();
            contactPage.clickCreateNewContact();
            Assert.assertTrue(contactPage.isContactCreationScreenDisplayed(), "Contact creation screen should be displayed");
            PublicCommon.reportPass("Navigated to contact creation screen");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to navigate to contact creation screen: " + e.getMessage());
        }
    }

    @Step("Enter valid contact details")
    public void stepEnterContactDetails() {
        try {
            // Example data, should be replaced with test data utils if available
            String firstName = "John";
            String lastName = "Doe";
            String email = "john.doe@example.com";
            String phone = "1234567890";
            String company = "ExampleCorp";
            contactPage.enterFirstName(firstName);
            contactPage.enterLastName(lastName);
            contactPage.enterEmail(email);
            contactPage.enterPhone(phone);
            contactPage.enterCompany(company);
            PublicCommon.reportPass("Entered valid contact details");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to enter contact details: " + e.getMessage());
        }
    }

    @Step("Submit the contact creation form")
    public void stepSubmitContactForm() {
        try {
            contactPage.clickSaveContact();
            PublicCommon.reportPass("Contact creation form submitted");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to submit contact creation form: " + e.getMessage());
        }
    }

    @Step("Verify system displays appropriate success message for contact creation")
    public void stepVerifyContactCreationMessage() {
        try {
            String expectedMessage = "Contact created successfully";
            String actualMessage = contactPage.getContactCreationSuccessMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "Success message should match");
            PublicCommon.reportPass("Correct success message displayed: " + actualMessage);
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Contact creation message verification failed: " + e.getMessage());
        }
    }

    @Step("Verify the new contact is saved in the CRM system")
    public void stepVerifyContactSaved() {
        try {
            // Example: Verify by searching for the contact
            String email = "john.doe@example.com";
            Assert.assertTrue(contactPage.isContactPresent(email), "New contact should be present in the system");
            PublicCommon.reportPass("New contact verified as saved in CRM");
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Contact save verification failed: " + e.getMessage());
        }
    }
}
