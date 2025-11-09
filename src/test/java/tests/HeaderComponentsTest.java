package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import pages.HeaderComponentsPage;
import org.json.simple.JSONObject;
import utils.JsonFileReader;

public class HeaderComponentsTest {

    private HeaderComponentsPage headerComponentsPage;
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/validate-header-components-data.json";
    private JSONObject testData;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Load test data for INT-123
        try {
            testData = JsonFileReader.getTestData(TEST_DATA_PATH, "INT-123");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to load test data for INT-123");
        }
    }

    @Test(description = "INT-123: Validate header components are visible to the user")
    @Description("User should be able to see the header components successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerComponentsPage = page.getInstance(HeaderComponentsPage.class);
            String url = (String) testData.get("URL");
            // Navigate to the URL
            headerComponentsPage.navigateTo(url);
            // Validate header components
            boolean areHeadersVisible = headerComponentsPage.areHeaderComponentsVisible();
            Assert.assertTrue(areHeadersVisible, "Header components are not visible as expected.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation test: " + e.getMessage());
        }
    }
}
