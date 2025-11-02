package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
    private String maxFirstName;
    private String maxLastName;
    private String emailAddress;
    private String emailType;

    @BeforeMethod
    public void setUpTestData() throws Exception {
        // Load test data from JSON
        String data = new String(Files.readAllBytes(Paths.get("src/test/resources/testdata/maximum-character-limit-for-name-field-data.json")));
        JSONArray arr = new JSONArray(data);
        JSONObject testData = arr.getJSONObject(0);
        maxFirstName = testData.getString("maxFirstName");
        maxLastName = testData.getString("maxLastName");
        emailAddress = testData.getString("emailAddress");
        emailType = testData.getString("emailType");
    }

    @Test(description = "TC-N004: Maximum Character Limit for Name Field")
    @Description("Check system behavior when the Name field is filled to its maximum allowed character limit.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Steps: Log in → Navigate to contact creation → Enter max-length name → Save → Verify confirmation")
    public void testMaximumCharacterLimitForNameField() {
        // Assume login and navigation to contact creation is handled by BaseTest or test setup
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        contactCreationPage.enterFirstName(maxFirstName);
        contactCreationPage.enterLastName(maxLastName);
        contactCreationPage.enterEmailAddress(emailAddress);
        contactCreationPage.enterEmailType(emailType);
        contactCreationPage.clickAddEmailButton();
        contactCreationPage.clickSaveButton();

        // Assuming ContactCreationPage has method isConfirmationMessageDisplayed
        Assert.assertTrue(contactCreationPage.isConfirmationMessageDisplayed(), "Confirmation message was not displayed after saving contact with max-length name.");
    }
}
