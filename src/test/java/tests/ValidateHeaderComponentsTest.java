package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.LoginPage;
import pages.HeaderPage;
import utils.JsonFileReader;
import org.json.simple.JSONObject;

public class ValidateHeaderComponentsTest {

    private LoginPage loginPage;
    private HeaderPage headerPage;
    private final String baseUrl = "https://stage-secure.astm.org/login?redirectUrl=aHR0cHM6Ly9zdGFnZS13d3cuYXN0bS5vcmcv";
    private final String testDataPath = "src/test/resources/testdata/validate-header-components-data.json";

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        try {
            // Assuming DriverManager provides static driver instance
            DriverManager.getDriver().get(baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "INT-123: Validate header components are visible after login")
    @Description("User should be able to see the header components successfully after login.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            loginPage = page.getInstance(LoginPage.class);
            headerPage = page.getInstance(HeaderPage.class);

            // Load test data
            JSONObject testData = JsonFileReader.getTestData(testDataPath, "INT-123");
            String email = (String) testData.get("email");
            String password = (String) testData.get("password");

            loginPage.login(email, password);

            // Assuming HeaderPage has a comprehensive method to validate all header components
            boolean headerComponentsVisible = headerPage.areAllHeaderComponentsVisible();
            Assert.assertTrue(headerComponentsVisible, "Header components are not visible as expected.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation test.");
        }
    }
}
