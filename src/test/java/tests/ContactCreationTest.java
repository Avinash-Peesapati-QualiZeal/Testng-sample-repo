package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.ContactCreationPage;
import base.BaseTest;

public class ContactCreationTest extends BaseTest {

    private ContactCreationPage contactCreationPage;

    @BeforeMethod
    public void setUp() {
        // Assuming login and navigation to contact creation screen is handled in BaseTest or via utility methods
    }

    @Test(description = "TC-N005: Empty Required Fields Validation")
    @Description("Verify system prevents saving a contact when required fields are left empty. System should display error messages indicating that Name and Email fields are required.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Steps: Navigate to contact creation → Leave required fields empty → Attempt to save → Verify error messages")
    public void testEmptyRequiredFieldsValidation() {
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        // Step 1: Ensure required fields are empty (assume page opens with empty fields)
        contactCreationPage.clearAllFields(); // Assumes such a method exists, or skip if fields are empty by default

        // Step 2: Attempt to save contact
        contactCreationPage.clickSaveButton();

        // Step 3: Verify error messages for Name and Email fields
        boolean isNameErrorDisplayed = contactCreationPage.isNameRequiredErrorDisplayed();
        boolean isEmailErrorDisplayed = contactCreationPage.isEmailRequiredErrorDisplayed();

        assert isNameErrorDisplayed : "Name required error message not displayed.";
        assert isEmailErrorDisplayed : "Email required error message not displayed.";

        // Step 4: Optionally, verify no contact is created (if such a method exists)
        // assert !contactCreationPage.isContactCreated() : "Contact should not be created when required fields are empty.";
    }
}
