package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HeaderPage;

public class HeaderComponentsTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // No specific preconditions or navigation steps provided in test data
        // If navigation to a specific URL is needed, add it here
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("User should be able to see the header components successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponents() {
        try {
            HeaderPage headerPage = page.getInstance(HeaderPage.class);
            boolean areComponentsVisible = headerPage.validateHeaderComponentsVisible();
            assert areComponentsVisible : "Header components are not visible as expected.";
        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Exception occurred during header components validation: " + e.getMessage();
        }
    }
}
