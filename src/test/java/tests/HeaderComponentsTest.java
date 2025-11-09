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
        // Initialize the HeaderComponentsPage before each test
        headerComponentsPage = page.getInstance(HeaderComponentsPage.class);
        // Load test data from JSON file
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/test/resources/testdata/validate-header-components-data.json");
            testData = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to load test data for header components.");
        }
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("User should be able to see the header components successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            // Extract expected header component names from test data
            // Assuming testData contains an array of component names under key "headerComponents"
            org.json.simple.JSONArray headerComponents = (org.json.simple.JSONArray) testData.get("headerComponents");
            for (Object componentObj : headerComponents) {
                String componentName = (String) componentObj;
                boolean isDisplayed = headerComponentsPage.isHeaderComponentDisplayed(componentName);
                Assert.assertTrue(isDisplayed, "Header component not visible: " + componentName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred while validating header components: " + e.getMessage());
        }
    }
}
