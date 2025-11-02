package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.ContactCreationPage;
import utils.TestDataReader;
import static org.testng.Assert.assertTrue;

public class ContactCreationTest {

    private ContactCreationPage contactCreationPage;
    private String maxName;

    @BeforeMethod
    public void setUp() {
        // Assuming TestDataReader is a utility to read properties files
        maxName = TestDataReader.getInstance().getProperty("maximum-character-limit-for-name-field-data.properties", "maxName");
    }

    @Test(description = "TC-N004: Maximum Character Limit for Name Field")
    @Description("Check system behavior when the Name field is filled to its maximum allowed character limit.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Steps: Log in → Navigate to contact creation → Enter max characters in Name → Save → Verify confirmation")
    public void testMaximumCharacterLimitForNameField() {
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        // Precondition: User is logged in and navigated to the contact creation screen
        contactCreationPage.ensureUserIsLoggedInAndOnContactCreationScreen();

        // Step: Enter maximum allowed characters in the Name field
        contactCreationPage.enterName(maxName);

        // Step: Fill other mandatory fields if required (assuming method exists)
        contactCreationPage.fillMandatoryFieldsExceptName();

        // Step: Save the contact
        contactCreationPage.clickSaveButton();

        // Assertion: System accepts the input and displays a confirmation message
        assertTrue(contactCreationPage.isConfirmationMessageDisplayed(), "Confirmation message was not displayed after saving contact with max character name.");
    }
}
