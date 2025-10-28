package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import base.BaseTest;
import pages.LoginPage;
import pages.HomePage;
import pages.EmploymentPage;
import utils.PublicCommon;

public class ChangeOfEmploymentTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private EmploymentPage employmentPage;

    @BeforeMethod
    public void setUp() {
        loginPage = page.getInstance(LoginPage.class);
        homePage = page.getInstance(HomePage.class);
        employmentPage = page.getInstance(EmploymentPage.class);
    }

    @Test(description = "TC-N001: Test the change of employment process")
    @Description("Verify that the change of employment process works as expected, application reflects the changes, and emails are sent to the correct addresses.")
    @Severity(SeverityLevel.NORMAL)
    public void testChangeOfEmployment() {
        stepLoginAsAstMember();
        stepNavigateToEmploymentSection();
        stepInitiateChangeOfEmployment();
        stepSubmitEmploymentChange();
        stepVerifyEmploymentChangeReflected();
        stepVerifyNotificationEmailsSent();
    }

    @Step("Login as ASTM member user")
    public void stepLoginAsAstMember() {
        try {
            loginPage.loginAsAstMember();
            Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");
            PublicCommon.reportPass("ASTM member login successful");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Login failed: " + e.getMessage());
        }
    }

    @Step("Navigate to Employment section")
    public void stepNavigateToEmploymentSection() {
        try {
            homePage.goToEmploymentSection();
            Assert.assertTrue(employmentPage.isAtEmploymentSection(), "Should be at Employment section");
            PublicCommon.reportPass("Navigated to Employment section");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Navigation to Employment section failed: " + e.getMessage());
        }
    }

    @Step("Initiate change of employment process")
    public void stepInitiateChangeOfEmployment() {
        try {
            employmentPage.clickChangeEmploymentButton();
            Assert.assertTrue(employmentPage.isChangeEmploymentFormDisplayed(), "Change Employment form should be displayed");
            PublicCommon.reportPass("Change of employment process initiated");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to initiate change of employment: " + e.getMessage());
        }
    }

    @Step("Submit new employment details and save changes")
    public void stepSubmitEmploymentChange() {
        try {
            employmentPage.enterNewEmploymentDetails();
            employmentPage.submitEmploymentChange();
            Assert.assertTrue(employmentPage.isChangeSubmittedSuccessfully(), "Employment change should be submitted successfully");
            PublicCommon.reportPass("Employment change submitted successfully");
        } catch (Exception e) {
            PublicCommon.reportFailAssert("Failed to submit employment change: " + e.getMessage());
        }
    }

    @Step("Verify that employment change is reflected in the application")
    public void stepVerifyEmploymentChangeReflected() {
        try {
            Assert.assertTrue(employmentPage.isEmploymentChangeReflected(), "Employment change should be reflected in user account");
            PublicCommon.reportPass("Employment change reflected in user account");
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Employment change not reflected: " + e.getMessage());
        }
    }

    @Step("Verify notification emails are sent to correct addresses")
    public void stepVerifyNotificationEmailsSent() {
        try {
            Assert.assertTrue(employmentPage.areNotificationEmailsSent(), "Notification emails should be sent");
            PublicCommon.reportPass("Notification emails sent to correct addresses");
        } catch (AssertionError | Exception e) {
            PublicCommon.reportFailAssert("Notification emails not sent: " + e.getMessage());
        }
    }
}
