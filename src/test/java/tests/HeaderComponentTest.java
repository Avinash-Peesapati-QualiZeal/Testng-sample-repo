package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderComponentPage;

public class HeaderComponentTest {
    private HeaderComponentPage headerComponentPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // No URL or login precondition specified in test data
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("User should be able to see the header components successfully")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerComponentPage = page.getInstance(HeaderComponentPage.class);
            boolean areComponentsVisible = headerComponentPage.validateHeaderComponentsVisible();
            Assert.assertTrue(areComponentsVisible, "Header components are not visible as expected.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header component validation: " + e.getMessage());
        }
    }
}
