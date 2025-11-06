package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import pages.ContactCreationPage;

public class ContactCreationTest {

    @Test(description = "TC-N004: Maximum Character Limit for Name Field")
    @Description("Check system behavior when the Name field is filled to its maximum allowed character limit.")
    @Severity(SeverityLevel.NORMAL)
    public void testMaximumCharacterLimitForNameField() {
        try {
            // Load test data from JSON
            String testDataPath = "src/test/resources/testdata/maximum-character-limit-for-name-field-data.json";
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(testDataPath));
            org.json.simple.JSONArray testDataArray = (org.json.simple.JSONArray) obj;
            JSONObject testData = (JSONObject) testDataArray.get(0); // Assuming first object is for TC-N004

            // Extract required fields explicitly
            String maxFirstName = (String) testData.get("firstName");
            String lastName = (String) testData.get("lastName");
            String email = (String) testData.get("email");
            String phone = (String) testData.get("phone");
            String company = (String) testData.get("company");

            // Initialize the ContactCreationPage using the dynamic pattern
            ContactCreationPage contactCreationPage = page.getInstance(ContactCreationPage.class);

            // Use the comprehensive method for contact creation
            contactCreationPage.createContactWithMaxName(maxFirstName, lastName, email, phone, company);

            // Assertion: System accepts the input and displays a confirmation message
            Assert.assertTrue(contactCreationPage.isConfirmationMessageDisplayed(), "Confirmation message was not displayed after saving contact with max name length.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during test execution: " + e.getMessage());
        }
    }
}
