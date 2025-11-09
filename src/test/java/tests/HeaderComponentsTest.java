package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderComponentsPage;

public class HeaderComponentsTest {

    private HeaderComponentsPage headerComponentsPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // No URL provided in test data, so no navigation here
    }

    @Test(description = "INT-123: Validate header components are visible to the user")
    @Description("User should be able to see the header components successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerComponentsPage = page.getInstance(HeaderComponentsPage.class);
            boolean areHeadersVisible = headerComponentsPage.validateHeaderComponentsVisible();
            Assert.assertTrue(areHeadersVisible, "Header components are not visible as expected.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
