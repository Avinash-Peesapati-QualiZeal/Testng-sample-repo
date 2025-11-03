package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import pages.ChangeOfEmploymentPage;

public class ChangeOfEmploymentTest {

    private String testDataPath = "src/test/resources/testdata/change-of-employment-data.json";

    @BeforeMethod
    public void setUp() {
        // If any setup is required, such as navigating to the base URL, it can be added here.
        // Example: driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @Test(description = "INT-8038-TC-001: Verify change of employment process in ASTM member application")
    @Description("Test the end-to-end change of employment process for ASTM members, including navigation, data entry, review, confirmation, and email/log verification.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Steps: Navigate to My ASTM Home → Change of Employment → Fill Details → Review → Confirm → Verify Thank You Page and Emails")
    public void testChangeOfEmployment() throws Exception {
        ChangeOfEmploymentPage changeOfEmploymentPage = page.getInstance(ChangeOfEmploymentPage.class);

        // Load test data for INT-8038-TC-001
        JSONParser parser = new JSONParser();
        JSONArray testDataArray = (JSONArray) parser.parse(new FileReader(testDataPath));
        JSONObject testData = null;
        for (Object obj : testDataArray) {
            JSONObject item = (JSONObject) obj;
            if ("INT-8038-TC-001".equals(item.get("testCaseId"))) {
                testData = item;
                break;
            }
        }
        Assert.assertNotNull(testData, "Test data for INT-8038-TC-001 not found!");

        // Extract explicit parameters from test data (example keys, adjust as per actual data)
        String memberEmail = (String) testData.get("memberEmail");
        String memberPassword = (String) testData.get("memberPassword");
        String newOrganization = (String) testData.get("newOrganization");
        String newPhone = (String) testData.get("newPhone");
        String newEmail = (String) testData.get("newEmail");
        String coeRepresentative = (String) testData.get("coeRepresentative");
        // Add other fields as needed from the JSON structure

        // Call the comprehensive method for the full flow
        boolean isSuccess = changeOfEmploymentPage.completeChangeOfEmploymentFlow(
            memberEmail,
            memberPassword,
            newOrganization,
            newPhone,
            newEmail,
            coeRepresentative
            // Add other explicit parameters as required by the method signature
        );

        Assert.assertTrue(isSuccess, "Change of employment flow did not complete successfully!");
    }
}
