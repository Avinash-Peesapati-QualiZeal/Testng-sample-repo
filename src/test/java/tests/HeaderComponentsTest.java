package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderComponentsPage;

public class HeaderComponentsTest {

    private HeaderComponentsPage headerComponentsPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // No URL or login step provided in test data.
        // If navigation is required, add here.
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("User should be able to see the header components successfully")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            headerComponentsPage = page.getInstance(HeaderComponentsPage.class);
            boolean areHeaderComponentsVisible = headerComponentsPage.validateHeaderComponentsVisible();
            assert areHeaderComponentsVisible : "Header components are not visible as expected.";
        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Exception occurred during header components validation: " + e.getMessage();
        }
    }
}
