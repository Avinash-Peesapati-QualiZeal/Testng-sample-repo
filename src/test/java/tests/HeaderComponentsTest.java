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
        // No URL or login required as per test data and requirements
    }

    @Test(description = "INT-123: Validate header components are visible")
    @Description("INT-123: User should be able to see the header components successfully.")
    @Severity(SeverityLevel.NORMAL)
    public void validateHeaderComponentsTest() {
        try {
            headerComponentsPage = page.getInstance(HeaderComponentsPage.class);
            headerComponentsPage.validateHeaderComponents();
        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Exception occurred during header components validation: " + e.getMessage();
        }
    }
}
