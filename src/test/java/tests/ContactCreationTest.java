package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import base.BaseTest;
import pages.ContactCreationPage;
import org.testng.Assert;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class ContactCreationTest extends BaseTest {

    private ContactCreationPage contactCreationPage;
    private String maxCharName;

    @BeforeMethod
    public void setUp() throws Exception {
        // Load test data from JSON file
        String testDataPath = "src/test/resources/testdata/maximum-character-limit-for-name-field-data.json";
        String content = new String(Files.readAllBytes(Paths.get(testDataPath)));
        JSONArray arr = new JSONArray(content);
        JSONObject data = arr.getJSONObject(0);
        this.maxCharName = data.getString("maxCharName");
    }

    @Test(description = "TC-N004: Maximum Character Limit for Name Field")
    @Description("Check system behavior when the Name field is filled to its maximum allowed character limit.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Steps: Log in → Navigate to Contact Creation → Enter max characters in Name → Save → Verify confirmation")
    public void testMaximumCharacterLimitForNameField() {
        // Assume login and navigation to contact creation screen are handled by BaseTest or prior steps
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        // Step 1: Enter maximum allowed characters in Name field
        contactCreationPage.enterName(maxCharName);

        // Step 2: Fill other mandatory fields (if any) - assuming method exists or is handled
        contactCreationPage.fillMandatoryFieldsExceptName();

        // Step 3: Save the contact
        contactCreationPage.clickSaveButton();

        // Step 4: Assert confirmation message is displayed
        Assert.assertTrue(contactCreationPage.isConfirmationMessageDisplayed(), "Confirmation message was not displayed after saving contact with max character name.");
    }
}
