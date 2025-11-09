package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderComponentsPage;
import utils.JsonFileReader;
import org.json.simple.JSONObject;

public class HeaderComponentsTest {

    private HeaderComponentsPage headerComponentsPage;
    private final String baseUrl = "https://stage-secure.astm.org/login?redirectUrl=aHR0cHM6Ly9zdGFnZS13d3cuYXN0bS5vcmcv";
    private final String testDataPath = "src/test/resources/testdata/validate-header-components-data.json";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            // Assuming DriverManager provides the driver instance
            utils.DriverManager.getDriver().get(baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("User should be able to see the header components successfully")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerComponentsPage = new HeaderComponentsPage(utils.DriverManager.getDriver());

            // Load test data (if needed in future for dynamic header content)
            JSONObject testData = JsonFileReader.getTestData(testDataPath, "INT-123");
            // No dynamic data needed for this test currently

            // Call a comprehensive method if exists, else check each header component
            // Assuming a comprehensive method: areAllHeaderComponentsVisible()
            Assert.assertTrue(headerComponentsPage.areAllHeaderComponentsVisible(), "Not all header components are visible.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
