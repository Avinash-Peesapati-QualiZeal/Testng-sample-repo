package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.ContactCreationPage;
import base.BaseTest;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class ContactCreationTest extends BaseTest {

    private ContactCreationPage contactCreationPage;

    @BeforeMethod
    public void setUp() {
        // Setup steps if any (e.g., baseUrl assignment) can be added here
    }

    @Test(description = "TC-N006: Handling Special Characters in Name")
    @Description("Ensure the system can handle special characters in the Name field without errors.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Steps: Log in → Navigate to contact creation → Enter name with special characters → Save → Verify confirmation message")
    public void testHandlingSpecialCharactersInName() throws Exception {
        // Initialize page object
        contactCreationPage = page.getInstance(ContactCreationPage.class);

        // Load test data from JSON
        String testDataPath = "src/test/resources/testdata/handling-special-characters-in-name-data.json";
        String content = new String(Files.readAllBytes(Paths.get(testDataPath)));
        JSONArray testDataArray = new JSONArray(content);
        JSONObject testData = testDataArray.getJSONObject(0);
        String specialName = testData.getString("name");
        String lastName = testData.optString("lastName", "Test");
        String email = testData.optString("email", "special.name@example.com");
        String contactType = testData.optString("contactType", "Personal");
        String leadSource = testData.optString("leadSource", "Web");

        // Preconditions: Assume user is already logged in and on contact creation screen
        // If login/navigation is needed, reuse existing page object methods here

        // Fill in contact details with special characters in the name field
        contactCreationPage.enterContactName(specialName, lastName);
        contactCreationPage.enterContactEmail(email);
        contactCreationPage.selectContactType(contactType);
        contactCreationPage.selectLeadSource(leadSource);
        contactCreationPage.saveContact();

        // Assert confirmation message is displayed
        assert contactCreationPage.isConfirmationMessageDisplayed() : "Confirmation message not displayed for special character name input.";
    }
}
