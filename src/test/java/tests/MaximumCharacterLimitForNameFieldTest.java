package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.ContactCreationPage;
import base.BaseTest;
import org.testng.Assert;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class MaximumCharacterLimitForNameFieldTest extends BaseTest {

    private ContactCreationPage contactCreationPage;
    private String maxLengthName;

    @BeforeMethod
    public void setUp() throws Exception {
        // Load test data from JSON file
        String testDataPath = "src/test/resources/testdata/maximum-character-limit-for-name-field-data.json";
        String content = new String(Files.readAllBytes(Paths.get(testDataPath)));
        JSONArray arr = new JSONArray(content);
        // Assuming the first object contains the max length name
        JSONObject obj = arr.getJSONObject(0);
        this.maxLengthName = obj.getString("name");
    }

    @Test(description = "TC-N004: Maximum Character Limit for Name Field")
    @Description("Check system behavior when the Name field is filled to its maximum allowed character limit.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Steps: Log in → Navigate to Contact Creation → Enter maximum length name → Save → Verify confirmation message")
    public void testMaximumCharacterLimitForNameField() {
        // Assume login and navigation are handled in BaseTest or via ContactCreationPage
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        // Step 1: Enter maximum allowed characters in Name field
        contactCreationPage.enterName(maxLengthName);

        // Step 2: Fill other required fields (assuming page object provides sensible defaults or method overloads)
        contactCreationPage.fillRequiredFieldsWithDefaults();

        // Step 3: Save the contact
        contactCreationPage.clickSave();

        // Step 4: Verify confirmation message is displayed
        Assert.assertTrue(contactCreationPage.isConfirmationMessageDisplayed(), "Confirmation message not displayed after saving contact with maximum length name.");

        // Step 5: Optionally verify that the contact is saved with the correct name
        Assert.assertTrue(contactCreationPage.isContactSavedWithName(maxLengthName), "Contact not saved with the maximum length name.");
    }
}
