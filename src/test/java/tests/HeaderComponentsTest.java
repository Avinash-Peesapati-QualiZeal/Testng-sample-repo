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
    private JSONObject testData;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Load test data for INT-123
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/test/resources/testdata/validate-header-components-data.json");
            Object obj = parser.parse(reader);
            testData = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("User should be able to see the header components successfully")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerComponentsPage = page.getInstance(HeaderComponentsPage.class);
            // Assuming a comprehensive method exists: verifyAllHeaderComponentsVisible()
            boolean allVisible = headerComponentsPage.verifyAllHeaderComponentsVisible();
            Assert.assertTrue(allVisible, "Not all header components are visible as expected.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
