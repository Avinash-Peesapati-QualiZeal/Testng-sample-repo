package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import pages.HeaderComponentsPage;

public class HeaderComponentsTest {

    private HeaderComponentsPage headerComponentsPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // No URL provided in test data, so no navigation here
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("User should be able to see the header components successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerComponentsPage = page.getInstance(HeaderComponentsPage.class);

            // Assuming comprehensive method exists: areAllHeaderComponentsVisible()
            boolean allVisible = headerComponentsPage.areAllHeaderComponentsVisible();
            Assert.assertTrue(allVisible, "Not all header components are visible on the page.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
