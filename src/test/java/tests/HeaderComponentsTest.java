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

public class HeaderComponentsTest {

    private String baseUrl;
    private String username;
    private String password;
    private HeaderComponentsPage headerComponentsPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Load test data from JSON (first scenario as example)
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/test/resources/testdata/validate-header-components-data.json"));
            JSONObject jsonObject = (JSONObject) obj;
            // Example: Use the first scenario for login data
            JSONObject scenario = (JSONObject) ((org.json.simple.JSONArray) jsonObject.get("testData")).get(0);
            // For demonstration, hardcode or fetch from config as needed
            baseUrl = "https://stage-secure.astm.org/login?redirectUrl=aHR0cHM6Ly9zdGFnZS13d3cuYXN0bS5vcmcv";
            username = "sivasai.arava@gmail.com";
            password = "QAZqaz852@";
        } catch (Exception e) {
            e.printStackTrace();
            baseUrl = "";
            username = "";
            password = "";
        }
    }

    @Test(description = "INT-123: Validate header components are visible after login")
    @Description("INT-123: User should be able to see the header components successfully after logging in.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponentsTest() {
        try {
            // Navigate to login page
            driver.get(baseUrl);

            // Login steps (using locators from generated context)
            driver.findElement(org.openqa.selenium.By.name("email")).sendKeys(username);
            driver.findElement(org.openqa.selenium.By.name("password")).sendKeys(password);
            driver.findElement(org.openqa.selenium.By.className("submit")).click();

            // Wait for header to be present (simple sleep for demo; replace with explicit wait in real code)
            Thread.sleep(2000);

            // Validate header components using Page Object
            headerComponentsPage = page.getInstance(HeaderComponentsPage.class);
            boolean headerVisible = headerComponentsPage.validateHeaderComponents();
            Assert.assertTrue(headerVisible, "Header components are not all visible after login!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
