package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import pages.HeaderComponentsPage;
import java.io.FileReader;

public class ValidateHeaderComponentsTest {

    private static final String TEST_DATA_PATH = "src/test/resources/testdata/validate-header-components-data.json";
    private JSONObject testData;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Load test data from JSON file
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(TEST_DATA_PATH));
            testData = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to load test data for header components test.");
        }
    }

    @Test(description = "INT-123: Validate header components are visible and accessible")
    @Description("User should be able to see the header components successfully as per INT-123")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            HeaderComponentsPage headerPage = page.getInstance(HeaderComponentsPage.class);
            boolean allHeadersVisible = headerPage.validateHeaderComponents();
            Assert.assertTrue(allHeadersVisible, "Not all header components are visible as expected.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
