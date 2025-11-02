package tests;

import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.ChangeOfEmploymentPage;

public class ChangeOfEmploymentTest {

    @Test(description = "INT-8038: Verify change of employment process on ASTM member application")
    @Description("Test the change of employment process on ASTM member application and verify email communications.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Steps: Complete change of employment → Edit organization name → Wait for staff manager approval → Verify change in IA → Verify audit log for user")
    public void testChangeOfEmploymentProcess() {
        ChangeOfEmploymentPage changeOfEmploymentPage = page.getInstance(ChangeOfEmploymentPage.class);

        // Complete the change of employment process
        changeOfEmploymentPage.completeChangeOfEmployment();

        // Edit the organization name as part of the process
        changeOfEmploymentPage.editOrganizationName();

        // Wait for staff manager approval (simulate or check status)
        changeOfEmploymentPage.waitForStaffManagerApproval();

        // Verify the change of employment is reflected in IA
        changeOfEmploymentPage.verifyChangeOfEmploymentInIA();

        // Verify audit log for the user
        changeOfEmploymentPage.verifyAuditLogForUser();
    }
}
