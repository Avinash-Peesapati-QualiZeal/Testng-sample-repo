package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pages.ChangeOfEmploymentPage;
import pages.LoginPage;
import pages.HomePage;
import base.BaseTest;

public class ChangeOfEmploymentTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ChangeOfEmploymentPage changeOfEmploymentPage;

    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        changeOfEmploymentPage = new ChangeOfEmploymentPage(driver);
    }

    @Test(priority = 1, groups = {"employment", "astm", "regression"})
    public void testChangeOfEmploymentProcess() {
        try {
            // Step 1: Login as ASTM member
            loginPage.navigateToLoginPage();
            loginPage.enterUsername("astm_member_user"); // Replace with valid test user
            loginPage.enterPassword("password123"); // Replace with valid password
            loginPage.clickLogin();
            Assert.assertTrue(homePage.isLoggedIn(), "Login failed: ASTM member was not logged in successfully.");

            // Step 2: Navigate to Change of Employment section
            homePage.goToProfile();
            Assert.assertTrue(homePage.isProfilePageDisplayed(), "Profile page was not displayed.");
            homePage.clickChangeOfEmployment();
            Assert.assertTrue(changeOfEmploymentPage.isChangeOfEmploymentPageDisplayed(), "Change of Employment page was not displayed.");

            // Step 3: Fill out Change of Employment form
            changeOfEmploymentPage.selectEmploymentStatus("Employed");
            changeOfEmploymentPage.enterNewEmployerName("Test Company Inc.");
            changeOfEmploymentPage.enterNewEmployerAddress("123 Main St, Test City, USA");
            changeOfEmploymentPage.enterNewJobTitle("QA Engineer");
            changeOfEmploymentPage.enterEffectiveDate("2024-06-01");
            changeOfEmploymentPage.submitChangeOfEmployment();

            // Step 4: Verify confirmation message
            Assert.assertTrue(changeOfEmploymentPage.isConfirmationMessageDisplayed(), "Confirmation message was not displayed after submitting change of employment.");

            // Step 5: (Optional) Verify confirmation email sent (if testable)
            // This step would require integration with email service or mock
            // Assert.assertTrue(changeOfEmploymentPage.isConfirmationEmailSent(), "Confirmation email was not sent as expected.");
        } catch (Exception e) {
            Assert.fail("Exception occurred during change of employment process: " + e.getMessage());
        }
    }
}
