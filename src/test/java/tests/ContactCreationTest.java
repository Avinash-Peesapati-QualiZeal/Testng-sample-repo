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
        // Any setup if required (e.g., baseUrl)
    }

    @Test(description = "TC-N005: Empty Required Fields Validation")
    @Description("Verify system prevents saving a contact when required fields are left empty. System displays error messages indicating that Name and Email fields are required.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Steps: Log in → Navigate to contact creation → Attempt save with empty required fields → Verify error messages")
    public void testEmptyRequiredFieldsValidation() {
        // Assume user is already logged in as per precondition
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        contactCreationPage.navigateToContactCreation();
        contactCreationPage.clearRequiredFields();
        contactCreationPage.clickSaveButton();

        assert contactCreationPage.isNameRequiredErrorDisplayed() : "Name required error message not displayed.";
        assert contactCreationPage.isEmailRequiredErrorDisplayed() : "Email required error message not displayed.";
        assert !contactCreationPage.isContactSaved() : "Contact should not be saved when required fields are empty.";
    }
}
