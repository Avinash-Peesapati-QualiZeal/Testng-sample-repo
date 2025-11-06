// ADD THESE NEW METHODS TO EXISTING CLASS - DO NOT MODIFY EXISTING CODE

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.simple.JSONObject;
import utils.TestDataReader;
import pages.ContactCreationPage;

@BeforeMethod(alwaysRun = true)
public void beforeMaximumCharacterLimitTest() {
    // No URL provided in test data, assuming user is already logged in and navigated to contact creation screen as per precondition.
}

@Test(description = "TC-N004: Maximum Character Limit for Name Field")
@Description("Check system behavior when the Name field is filled to its maximum allowed character limit.")
@Severity(SeverityLevel.NORMAL)
public void testMaximumCharacterLimitForNameField() {
    try {
        ContactCreationPage contactCreationPage = page.getInstance(ContactCreationPage.class);
        // Load test data for TC-N004
        String testDataPath = "src/test/resources/testdata/maximum-character-limit-for-name-field-data.json";
        JSONObject testData = TestDataReader.getTestData(testDataPath, "TC-N004");
        // Extract explicit values from test data
        String maxFirstName = (String) testData.get("maxFirstName");
        String lastName = (String) testData.get("lastName");
        String email = (String) testData.get("email");
        String phone = (String) testData.get("phone");
        // Call comprehensive method for contact creation with maximum name length
        contactCreationPage.createContactWithMaxName(maxFirstName, lastName, email, phone);
        // Assert confirmation message is displayed
        Assert.assertTrue(contactCreationPage.isConfirmationMessageDisplayed(), "Confirmation message not displayed after saving contact with max name length.");
    } catch (Exception e) {
        e.printStackTrace();
        Assert.fail("Exception occurred in testMaximumCharacterLimitForNameField: " + e.getMessage());
    }
}
