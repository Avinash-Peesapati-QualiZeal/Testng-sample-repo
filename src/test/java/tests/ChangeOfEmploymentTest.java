package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.ChangeOfEmploymentPage;
import utils.TestDataReader;
import java.util.Properties;

public class ChangeOfEmploymentTest {

    private String baseUrl;
    private Properties testData;

    @BeforeMethod
    public void setUp() {
        // Load baseUrl if required (not specified in test data)
        // Load test data for INT-8038
        testData = TestDataReader.getTestData("src/test/resources/testdata/change-of-employment-data.properties");
    }

    @Test(description = "INT-8038: Verify change of employment mail is received by the member")
    @Description("Test the change of employment functionality in ASTM member application. Expected: Change of Employment mail should be received to the member.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Steps: Complete Change of Employment flow and verify mail receipt")
    public void testChangeOfEmploymentMailReceived() {
        ChangeOfEmploymentPage changeOfEmploymentPage = page.getInstance(ChangeOfEmploymentPage.class);

        // Extract any required test data parameters (none specified, so call method directly)
        changeOfEmploymentPage.completeChangeOfEmploymentFlow();

        // Verification step: Mail should be received to the member
        // Assuming the page object provides a method to verify mail receipt (if not, this should be implemented)
        boolean isMailReceived = changeOfEmploymentPage.isChangeOfEmploymentMailReceived();
        Assert.assertTrue(isMailReceived, "Change of Employment mail was not received by the member.");
    }
}
