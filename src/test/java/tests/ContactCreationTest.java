// ADD THESE NEW METHODS TO EXISTING CLASS - DO NOT MODIFY EXISTING CODE

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import pages.ContactCreationPage;

@BeforeMethod(alwaysRun = true)
public void beforeMaximumCharacterLimitTest() {
    // No URL provided in test data, assumed navigation handled by test flow or base class
}

@Test(description = "TC-N004: Maximum Character Limit for Name Field")
@Description("Check system behavior when the Name field is filled to its maximum allowed character limit.")
@Severity(SeverityLevel.NORMAL)
public void testMaximumCharacterLimitForNameField() {
    try {
        // Load test data from JSON
        String testDataPath = "src/test/resources/testdata/maximum-character-limit-for-name-field-data.json";
        JSONParser parser = new JSONParser();
        JSONObject testData = (JSONObject) parser.parse(new FileReader(testDataPath));

        // Extract individual values explicitly
        String maxName = (String) testData.get("maxName");
        String lastName = (String) testData.get("lastName");
        String email = (String) testData.get("email");
        String phone = (String) testData.get("phone");
        String company = (String) testData.get("company");

        ContactCreationPage contactCreationPage = page.getInstance(ContactCreationPage.class);
        // Use comprehensive method as per Page Object pattern
        contactCreationPage.createContactWithMaxName(maxName, lastName, email, phone, company);

        // Assertion: System accepts the input and displays a confirmation message.
        // Assuming a method isConfirmationMessageDisplayed() exists in ContactCreationPage
        assert contactCreationPage.isConfirmationMessageDisplayed() : "Confirmation message not displayed after saving contact with max name length.";
    } catch (Exception e) {
        e.printStackTrace();
        assert false : "Exception occurred during test execution: " + e.getMessage();
    }
}
