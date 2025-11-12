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
    private String baseUrl;
    private String username;
    private String password;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Load test data from JSON
        try {
            JSONParser parser = new JSONParser();
            JSONObject testData = (JSONObject) parser.parse(new FileReader("src/test/resources/testdata/validate-header-components-data.json"));
            // For demonstration, using static credentials as per locators context
            baseUrl = "https://stage-secure.astm.org/login?redirectUrl=aHR0cHM6Ly9zdGFnZS13d3cuYXN0bS5vcmcv";
            username = "sivasai.arava@gmail.com";
            password = "QAZqaz852@";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "INT-123: Validate header components are visible after login")
    @Description("User should be able to see the header components successfully after login.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            // Navigate to login page
            org.openqa.selenium.WebDriver driver = tests.ui.base.DriverManager.getDriver();
            driver.get(baseUrl);

            // Login steps (using direct Selenium as no LoginPage PO provided)
            driver.findElement(org.openqa.selenium.By.name("email")).sendKeys(username);
            driver.findElement(org.openqa.selenium.By.name("password")).sendKeys(password);
            driver.findElement(org.openqa.selenium.By.cssSelector("div.ui.fluid.large.blue.submit.button")).click();

            // Wait for header to load (simple sleep for demo, replace with explicit wait in real code)
            Thread.sleep(3000);

            // Validate header components using Page Object
            headerComponentsPage = new HeaderComponentsPage(driver);
            boolean allHeaderVisible = headerComponentsPage.validateHeaderComponentsVisible();
            Assert.assertTrue(allHeaderVisible, "Not all header components are visible after login!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
