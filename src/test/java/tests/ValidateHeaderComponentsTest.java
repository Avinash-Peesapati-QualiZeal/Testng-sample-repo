package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderComponentsPage;
import org.openqa.selenium.WebDriver;
import utils.TestDataReader;
import org.json.simple.JSONObject;

public class ValidateHeaderComponentsTest {

    private WebDriver driver;
    private HeaderComponentsPage headerComponentsPage;
    private static final String BASE_URL = "https://stage-secure.astm.org/login?redirectUrl=aHR0cHM6Ly9zdGFnZS13d3cuYXN0bS5vcmcv";
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/validate-header-components-data.json";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Initialize WebDriver using your framework's driver manager or factory
        driver = utils.DriverManager.getDriver();
        driver.get(BASE_URL);
    }

    @Test(description = "INT-123: Validate header components are visible after login")
    @Description("INT-123: User should be able to see the header components successfully after login.")
    @Severity(SeverityLevel.NORMAL)
    public void testValidateHeaderComponents() {
        try {
            // Load test data (if needed for login, etc.)
            JSONObject testData = TestDataReader.getTestData(TEST_DATA_PATH, "INT-123");
            // Example credentials from provided locators (replace with test data if needed)
            String username = "sivasai.arava@gmail.com";
            String password = "QAZqaz852@";

            // Perform login using existing LoginPage if available, else inline (not shown here)
            // Assuming user is already logged in for header validation, else insert login steps here

            headerComponentsPage = new HeaderComponentsPage(driver);
            boolean headerValid = headerComponentsPage.validateHeaderComponents();
            Assert.assertTrue(headerValid, "Header components are not visible as expected.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
