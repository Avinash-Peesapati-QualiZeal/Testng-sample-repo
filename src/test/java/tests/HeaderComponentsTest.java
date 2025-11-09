package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderComponentsPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class HeaderComponentsTest {

    private HeaderComponentsPage headerComponentsPage;
    private JSONObject testData;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // No URL provided in test data, so no navigation step here
        // Load test data for header components
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/test/resources/testdata/validate-header-components-data.json");
            Object obj = parser.parse(reader);
            testData = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to load test data for header components");
        }
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("User should be able to see the header components successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerComponentsPage = page.getInstance(HeaderComponentsPage.class);

            // Extract expected header component names from test data
            // Example assumes testData contains a JSONArray named "headerComponents"
            org.json.simple.JSONArray headerComponents = (org.json.simple.JSONArray) testData.get("headerComponents");

            for (Object componentObj : headerComponents) {
                JSONObject component = (JSONObject) componentObj;
                String componentName = (String) component.get("name");
                String locatorKey = (String) component.get("locatorKey");

                // For each component, call a method to verify its visibility
                // Assumes HeaderComponentsPage has a method: isHeaderComponentVisible(String locatorKey)
                boolean isVisible = headerComponentsPage.isHeaderComponentVisible(locatorKey);
                Assert.assertTrue(isVisible, componentName + " is not visible in the header!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred while validating header components: " + e.getMessage());
        }
    }
}
