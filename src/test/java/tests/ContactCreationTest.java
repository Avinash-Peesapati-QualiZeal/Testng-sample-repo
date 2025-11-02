package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
    private String specialCharName;
    private String otherField1;
    private String otherField2;
    // Add additional fields as per ContactCreationPage requirements

    @BeforeMethod
    public void setUp() throws Exception {
        // Load test data from JSON
        String jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/testdata/handling-special-characters-in-name-data.json")));
        JSONArray arr = new JSONArray(jsonContent);
        JSONObject testData = arr.getJSONObject(0);
        specialCharName = testData.getString("name");
        // Assign other fields as needed
        // otherField1 = testData.getString("otherField1");
        // otherField2 = testData.getString("otherField2");
    }

    @Test(description = "TC-N006: Handling Special Characters in Name")
    @Description("Ensure the system can handle special characters in the Name field without errors. System accepts the input and displays a confirmation message.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Steps: Log in → Navigate to contact creation → Enter name with special characters → Save → Verify confirmation message")
    public void testHandlingSpecialCharactersInName() {
        // Assume login is handled by BaseTest or session is already authenticated per precondition
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        // Navigate to contact creation screen (assume method exists or navigation is handled in page object)
        contactCreationPage.navigateToContactCreation();

        // Fill in the contact form with special character name
        contactCreationPage.enterName(specialCharName);
        // Fill other required fields as needed
        // contactCreationPage.enterOtherField1(otherField1);
        // contactCreationPage.enterOtherField2(otherField2);

        contactCreationPage.saveContact();

        // Assert confirmation message is displayed
        Assert.assertTrue(contactCreationPage.isConfirmationMessageDisplayed(), "Confirmation message was not displayed after saving contact with special characters in name.");
    }
}
