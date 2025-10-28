package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import com.yourpackage.pages.LoginPage;
import com.yourpackage.pages.HomePage;
import com.yourpackage.pages.ChangeOfEmploymentPage;
import com.yourpackage.utils.TestDataLoader;
import com.yourpackage.base.BaseTest;

/**
 * TestNG test class for TC-N001: Change of Employment
 * Objective: To verify the change of employment process in the ASTM member application.
 * Priority: Medium
 * Type: Functional
 */
public class ChangeOfEmploymentTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ChangeOfEmploymentPage changeOfEmploymentPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        changeOfEmploymentPage = new ChangeOfEmploymentPage(driver);
    }

    @Test(priority = 1, groups = {"change-of-employment", "functional"})
    public void testChangeOfEmploymentProcess() {
        try {
            // Step 1: Login as valid ASTM member
            loginPage.loginAsValidMember();
            Assert.assertTrue(homePage.isLoggedIn(), "Login failed: User is not logged in.");

            // Step 2: Navigate to Change of Employment section
            homePage.goToChangeOfEmployment();
            Assert.assertTrue(changeOfEmploymentPage.isAt(), "Navigation failed: Not on Change of Employment page.");

            // Step 3: Fill in new employment details
            changeOfEmploymentPage.enterNewEmployerDetails("Acme Corp", "Software Engineer", "New York", "NY", "10001");
            Assert.assertTrue(changeOfEmploymentPage.isEmployerDetailsEntered(), "Employer details not entered correctly.");

            // Step 4: Submit the change of employment request
            changeOfEmploymentPage.submitChangeRequest();
            Assert.assertTrue(changeOfEmploymentPage.isSubmissionSuccessful(), "Submission failed: Change of employment not successful.");

            // Step 5: Verify confirmation and all expected outcomes
            Assert.assertTrue(changeOfEmploymentPage.isConfirmationDisplayed(), "Confirmation message not displayed after change of employment.");
        } catch (Exception e) {
            Assert.fail("Exception occurred during change of employment test: " + e.getMessage());
        }
    }
}
