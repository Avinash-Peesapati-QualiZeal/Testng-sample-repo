package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderPage;

public class HeaderComponentTest {
    private HeaderPage headerPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // No URL provided in test data; navigation should be handled in the Page Object or test setup if needed
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("User should be able to see the header components successfully")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerPage = page.getInstance(HeaderPage.class);
            // Assuming a comprehensive method exists or needs to be added to HeaderPage
            boolean allHeaderComponentsVisible = headerPage.areAllHeaderComponentsVisible();
            Assert.assertTrue(allHeaderComponentsVisible, "Not all header components are visible on the page.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
