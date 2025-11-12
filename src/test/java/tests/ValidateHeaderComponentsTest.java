package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderComponentsPage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class ValidateHeaderComponentsTest {

    private static final String TEST_DATA_PATH = "src/test/resources/testdata/validate-header-components-data.json";
    private JSONArray testDataArray;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Load test data before each test
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(TEST_DATA_PATH);
            Object obj = parser.parse(reader);
            testDataArray = (JSONArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to load test data for header components validation");
        }
    }

    @Test(description = "INT-123: Validate header components for various user roles and scenarios")
    @Description("User should be able to see the header components successfully for all data-driven scenarios.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponentsForAllScenarios() {
        try {
            HeaderComponentsPage headerComponentsPage = page.getInstance(HeaderComponentsPage.class);

            for (Object obj : testDataArray) {
                JSONObject scenario = (JSONObject) obj;
                String description = (String) scenario.get("description");

                // Step: Validate header components using comprehensive Page Object method
                boolean result = headerComponentsPage.validateHeaderComponents();
                Assert.assertTrue(result, "Header components validation failed for scenario: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation test: " + e.getMessage());
        }
    }
}
