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
import java.nio.file.Paths;

public class ValidateHeaderComponentsTest {

    private HeaderComponentsPage headerComponentsPage;
    private JSONObject testData;
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/validate-header-components-data.json";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            // Load test data from JSON file
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(Paths.get(TEST_DATA_PATH).toFile());
            testData = (JSONObject) parser.parse(reader);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to load test data for header components validation.");
        }
    }

    @Test(description = "INT-123: Validate header components are visible after login")
    @Description("User should be able to see the header components successfully after login.")
    @Severity(SeverityLevel.NORMAL)
    public void testValidateHeaderComponents() {
        try {
            // Assume user is already logged in and on the relevant page as precondition is not specified
            headerComponentsPage = page.getInstance(HeaderComponentsPage.class);
            boolean allHeaderComponentsVisible = headerComponentsPage.validateHeaderComponentsDisplayed();
            Assert.assertTrue(allHeaderComponentsVisible, "Not all header components are visible as expected.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
