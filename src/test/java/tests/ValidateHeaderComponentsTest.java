package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import pages.HeaderComponentsPage;

public class ValidateHeaderComponentsTest {

    private String baseUrl;
    private JSONObject testData;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            // Load test data from JSON file
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/test/resources/testdata/validate-header-components-data.json");
            testData = (JSONObject) parser.parse(reader);
            baseUrl = (String) testData.get("url");
            driver.get(baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to load test data or open URL");
        }
    }

    @Test(description = "INT-123: Validate header components are visible after login")
    @Description("INT-123: User should be able to see the header components successfully after login.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            // Assume login is required; credentials can be parameterized if needed
            // For this demo, using hardcoded values from provided HTML snippet
            String username = "sivasai.arava@gmail.com";
            String password = "QAZqaz852@";

            // LoginPage logic would be here if LoginPage PO exists
            // For now, perform login steps inline using driver (or refactor if LoginPage is added)
            driver.findElement(org.openqa.selenium.By.name("email")).sendKeys(username);
            driver.findElement(org.openqa.selenium.By.name("password")).sendKeys(password);
            driver.findElement(org.openqa.selenium.By.cssSelector("div.ui.fluid.large.blue.submit.button")).click();

            // Wait for header to be visible (could use explicit wait if needed)
            Thread.sleep(2000);

            HeaderComponentsPage headerPage = page.getInstance(HeaderComponentsPage.class);
            headerPage.validateHeaderComponentsVisible();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
