package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderComponentsPage;
import org.testng.Assert;

public class HeaderComponentsTest {

    private HeaderComponentsPage headerComponentsPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // No URL navigation as per test data; assumed handled elsewhere or not required
    }

    @Test(description = "INT-123: Validate header components are visible and accessible")
    @Description("INT-123: User should be able to see the header components successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponentsTest() {
        try {
            headerComponentsPage = page.getInstance(HeaderComponentsPage.class);
            headerComponentsPage.validateHeaderComponents();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during header components validation: " + e.getMessage());
        }
    }
}
