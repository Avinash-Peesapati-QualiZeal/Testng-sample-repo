package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.ChangeOfEmploymentPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class ChangeOfEmploymentTest {

    private String baseUrl;
    private ChangeOfEmploymentPage changeOfEmploymentPage;
    private JSONObject testData;

    @BeforeMethod
    public void setUp() throws Exception {
        // Assuming baseUrl is set in config.properties or similar
        baseUrl = "https://astm.org/myastm"; // Replace with actual base URL if available
        // Load test data for INT-8038
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/test/resources/testdata/change-of-employment-data.json"));
        org.json.simple.JSONArray arr = (org.json.simple.JSONArray) obj;
        for (Object o : arr) {
            JSONObject item = (JSONObject) o;
            if ("INT-8038".equals(item.get("testCaseId"))) {
                testData = item;
                break;
            }
        }
    }

    @Test(description = "INT-8038: Verify change of employment process for ASTM members")
    @Description("Test the end-to-end change of employment process for ASTM members, including navigation, form editing, confirmation, and email/log validation.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Steps: Navigate to MyASTM → Sign In → Change of Employment → Edit/Review/Cancel → Submit → Verify Confirmation and Emails")
    public void testChangeOfEmploymentFlow() {
        changeOfEmploymentPage = page.getInstance(ChangeOfEmploymentPage.class);

        // Extract required test data fields explicitly
        String memberEmail = (String) testData.get("memberEmail");
        String memberPassword = (String) testData.get("memberPassword");
        String newOrganization = (String) testData.get("newOrganization");
        String newPhone = (String) testData.get("newPhone");
        String newEmail = (String) testData.get("newEmail");
        String staffEmail = (String) testData.get("staffEmail");

        // Call the comprehensive Page Object method for the full flow
        boolean isSuccess = changeOfEmploymentPage.completeChangeOfEmploymentFlow(
            memberEmail,
            memberPassword,
            newOrganization,
            newPhone,
            newEmail,
            staffEmail
        );

        Assert.assertTrue(isSuccess, "Change of employment process failed or did not complete as expected.");
    }
}
