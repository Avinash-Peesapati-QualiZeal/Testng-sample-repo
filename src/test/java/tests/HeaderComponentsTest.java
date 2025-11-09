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
    private String testDataPath = "src/test/resources/testdata/validate-header-components-data.json";
    private String url;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            JSONObject testData = JsonFileReader.getTestData(testDataPath, "INT-123");
            url = (String) testData.get("url");
            // Assuming DriverManager or similar utility is used for navigation
            utils.DriverManager.getDriver().get(url);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to load test data or navigate to URL");
        }
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("User should be able to see the header components successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerComponentsPage = new HeaderComponentsPage(utils.DriverManager.getDriver());
            // Assuming a comprehensive method exists or needs to be added
            boolean areAllHeaderComponentsVisible = headerComponentsPage.areAllHeaderComponentsVisible();
            Assert.assertTrue(areAllHeaderComponentsVisible, "Not all header components are visible on the page.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation");
        }
    }
}
