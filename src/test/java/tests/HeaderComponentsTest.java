package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderPage;
import utils.JsonFileReader;
import org.json.simple.JSONObject;

public class HeaderComponentsTest {

    private HeaderPage headerPage;
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/validate-header-components-data.json";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // No URL provided in test data, so no navigation here
    }

    @Test(description = "INT-123: Validate header components are displayed successfully")
    @Description("User should be able to see the header components successfully as per INT-123.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerPage = page.getInstance(HeaderPage.class);

            // Load test data for expected header components
            JSONObject testData = JsonFileReader.getTestData(TEST_DATA_PATH, "INT-123");

            // Extract expected header component names from test data
            // (Assuming testData contains a JSON array named "expectedComponents")
            org.json.simple.JSONArray expectedComponents = (org.json.simple.JSONArray) testData.get("expectedComponents");

            // Call a comprehensive method to validate all header components
            boolean allComponentsPresent = headerPage.validateHeaderComponents(expectedComponents);

            Assert.assertTrue(allComponentsPresent, "Not all header components are displayed as expected.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header component validation: " + e.getMessage());
        }
    }
}
