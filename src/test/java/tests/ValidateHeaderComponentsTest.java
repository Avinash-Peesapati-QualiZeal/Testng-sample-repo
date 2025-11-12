package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import pages.HeaderComponentsPage;
import pages.LoginPage; // Assuming LoginPage exists or would be implemented similarly

public class ValidateHeaderComponentsTest {

    private String baseUrl;
    private String username;
    private String password;
    private JSONObject testData;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            // Load test data from JSON
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/test/resources/testdata/validate-header-components-data.json");
            testData = (JSONObject) parser.parse(reader);
            baseUrl = (String) testData.get("url");
            // Hardcoded credentials for demonstration; ideally, fetch from secure config
            username = "sivasai.arava@gmail.com";
            password = "QAZqaz852@";
            driver.get(baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "INT-123: Validate header components are visible after login")
    @Description("User should be able to see the header components successfully after logging in.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            // Login flow
            LoginPage loginPage = page.getInstance(LoginPage.class);
            loginPage.login(username, password);

            // Validate header components
            HeaderComponentsPage headerPage = page.getInstance(HeaderComponentsPage.class);
            boolean headerVisible = headerPage.validateHeaderComponentsDisplayed();
            Assert.assertTrue(headerVisible, "Header components are not visible as expected after login.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header component validation: " + e.getMessage());
        }
    }
}
