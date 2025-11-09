package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.ChangeOfEmploymentPage;
import utils.JsonFileReader;
import org.json.simple.JSONObject;

public class ChangeOfEmploymentTest {

    private ChangeOfEmploymentPage changeOfEmploymentPage;
    private final String baseUrl = "https://stage-member.astm.org/";
    private final String testDataPath = "src/test/resources/testdata/change-of-employment-data.json";
    private final String testCaseId = "INT-8038";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Assuming 'driver' and 'page' are managed by a test base or utility
        // Navigate to the base URL before each test
        utils.DriverManager.getDriver().get(baseUrl);
    }

    @Test(description = "INT-8038: Verify user can change employment details in ASTM member application.")
    @Description("Verify user can change employment details in ASTM member application.")
    @Severity(SeverityLevel.NORMAL)
    public void testChangeOfEmployment() {
        try {
            changeOfEmploymentPage = page.getInstance(ChangeOfEmploymentPage.class);
            // Load test data for INT-8038
            JSONObject testData = JsonFileReader.getTestData(testDataPath, testCaseId);
            // Extract required parameters from testData
            String email = (String) testData.get("Email");
            String password = (String) testData.get("Password");
            String newEmployer = (String) testData.get("NewEmployer");
            String newTitle = (String) testData.get("NewTitle");
            String newStartDate = (String) testData.get("NewStartDate");

            // Comprehensive method call as per Page Object best practices
            boolean isChangeSuccessful = changeOfEmploymentPage.completeChangeOfEmploymentFlow(
                email, password, newEmployer, newTitle, newStartDate
            );

            Assert.assertTrue(isChangeSuccessful, "Employment details were not changed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during change of employment test: " + e.getMessage());
        }
    }
}
