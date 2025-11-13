package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderComponentsPage;
import org.openqa.selenium.WebDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class HeaderComponentsTest {

    private WebDriver driver;
    private HeaderComponentsPage headerComponentsPage;
    private String testDataPath = "src/test/resources/testdata/validate-header-components-data.json";
    private String baseUrl = "https://stage-secure.astm.org/login?redirectUrl=aHR0cHM6Ly9zdGFnZS13d3cuYXN0bS5vcmcv";
    private String username = "sivasai.arava@gmail.com";
    private String password = "QAZqaz852@";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            // Initialize WebDriver using DriverManager or WebDriverFactory as per project utils
            driver = utils.WebDriverFactory.createDriver();
            driver.manage().window().maximize();
            driver.get(baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to initialize WebDriver or open base URL");
        }
    }

    @Test(description = "INT-123: Validate header components are visible and accessible after login")
    @Description("User should be able to see the header components successfully after logging in.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponentsTest() {
        try {
            headerComponentsPage = new pages.HeaderComponentsPage(driver);

            // Perform login steps
            driver.findElement(org.openqa.selenium.By.name("email")).sendKeys(username);
            driver.findElement(org.openqa.selenium.By.name("password")).sendKeys(password);
            driver.findElement(org.openqa.selenium.By.xpath("//div[contains(@class, 'submit button') and text()='Login']")).click();

            // Wait for login to complete and header to load (could be improved with explicit waits for header)
            Thread.sleep(3000); // Replace with better wait in real code

            // Validate header components using the comprehensive method
            boolean headerPresent = headerComponentsPage.validateHeaderComponents();
            Assert.assertTrue(headerPresent, "Header components are not visible or accessible as expected.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
