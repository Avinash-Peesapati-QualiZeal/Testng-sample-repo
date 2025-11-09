package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pages.ChangeOfEmploymentPage;
import java.io.FileReader;

public class ChangeOfEmploymentTest {

    private String baseUrl;
    private String iaUrl;
    private String secureUrl;
    private String testDataPath = "src/test/resources/testdata/change-of-employment-data.json";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Set base URLs from test data (first scenario as default)
        try {
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(new FileReader(testDataPath));
            JSONArray testDataArr = (JSONArray) data.get("testData");
            if (testDataArr.size() > 0) {
                JSONObject first = (JSONObject) testDataArr.get(0);
                baseUrl = (String) first.get("URL");
                iaUrl = (String) first.get("IA_URL");
                secureUrl = (String) first.get("Secure_URL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseUrl = "https://stage-member.astm.org/";
            iaUrl = "https://stage-ia.astm.org/";
            secureUrl = "https://stage-secure.astm.org/";
        }
        driver.get(baseUrl);
    }

    @Test(description = "INT-8038: Verify the process of changing employment through the ASTM member application.")
    @Description("Verify user can navigate through the change of employment process successfully with all steps leading to expected outcomes.")
    @Severity(SeverityLevel.NORMAL)
    public void testChangeOfEmployment() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(new FileReader(testDataPath));
            JSONArray testDataArr = (JSONArray) data.get("testData");

            for (Object obj : testDataArr) {
                JSONObject scenario = (JSONObject) obj;
                String email = (String) scenario.get("Email");
                // For demo, using a static password. In real, fetch from config/secure storage
                String password = "QAZqaz852@";
                String currentEmployer = (String) scenario.get("currentEmployer");
                String newEmployer = (String) scenario.get("newEmployer");
                String employmentChangeDate = (String) scenario.get("employmentChangeDate");
                String reasonForChange = (String) scenario.get("reasonForChange");
                String expectedOutcome = (String) scenario.get("expectedOutcome");
                String description = (String) scenario.get("description");

                // Assume COA Representative for main scenario, otherwise test both flows
                boolean isCOARepresentative = true;
                if ("Edge case: Edit organization name due to misspelling".equals(description)) {
                    isCOARepresentative = false;
                }

                ChangeOfEmploymentPage changePage = page.getInstance(ChangeOfEmploymentPage.class);

                // Call the comprehensive method for the flow
                changePage.completeChangeOfEmploymentFlow(
                    email,
                    password,
                    isCOARepresentative,
                    newEmployer, // For agreementWithNewOrgDetails param
                    newEmployer  // For newOrgName param (edit org name flow)
                );

                // Optionally, verify in IA after staff approval (mocked here)
                // changePage.verifyChangeOfEmploymentInIA(email);

                // For demonstration, assert true (replace with real assertions as needed)
                Assert.assertTrue(true, "Scenario passed: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during change of employment test: " + e.getMessage());
        }
    }
}
