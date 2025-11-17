// ADD THESE NEW METHODS TO EXISTING CLASS - DO NOT MODIFY EXISTING CODE

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import pages.ContactCreationPage;
import java.io.FileReader;

public class ContactCreationTest {
    private ContactCreationPage contactCreationPage;
    private JSONObject testData;

    @BeforeMethod(alwaysRun = true)
    public void beforeEachTest() {
        // Initialize page object before each test
        contactCreationPage = page.getInstance(ContactCreationPage.class);
        // Load test data for TC-N004
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/test/resources/testdata/maximum-character-limit-for-name-field-testdata.json");
            testData = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to load test data for TC-N004");
        }
    }

    @Test(description = "TC-N004: Maximum Character Limit for Name Field")
    @Description("Check system behavior when the Name field is filled to its maximum allowed character limit.")
    @Severity(SeverityLevel.NORMAL)
    public void testMaximumCharacterLimitForNameField() {
        try {
            // Extract test data for max length scenario
            JSONObject fields = (JSONObject) testData.get("fields");
            JSONObject nameField = (JSONObject) fields.get("name");
            JSONArray testValues = (JSONArray) nameField.get("testValues");
            JSONObject maxLengthCase = null;
            for (Object obj : testValues) {
                JSONObject testCase = (JSONObject) obj;
                if ("Max Length (255 chars)".equals(testCase.get("label"))) {
                    maxLengthCase = testCase;
                    break;
                }
            }
            Assert.assertNotNull(maxLengthCase, "Max Length test data not found");
            String maxFirstName = (String) maxLengthCase.get("value");

            // Provide valid values for other required fields
            String lastName = "Doe";
            String email = "max.name@example.com";
            String company = "QualiZeal";
            String position = "QA Engineer";

            // Use comprehensive Page Object method for the flow
            contactCreationPage.createContactWithMaxNameField(maxFirstName, lastName, email, company, position);

            // Assert confirmation message is displayed
            Assert.assertTrue(contactCreationPage.isConfirmationMessageDisplayedForMaxName(),
                    "Confirmation message not displayed after saving contact with max name length");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during testMaximumCharacterLimitForNameField: " + e.getMessage());
        }
    }
}
