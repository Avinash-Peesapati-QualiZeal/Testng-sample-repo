package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.HomePage;
import pages.ChangeOfEmploymentPage;
import utils.TestDataReader;
import utils.DriverManager;

public class ChangeOfEmploymentTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ChangeOfEmploymentPage coePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        coePage = new ChangeOfEmploymentPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @DataProvider(name = "changeOfEmploymentData")
    public Object[][] getChangeOfEmploymentData() {
        // Replace with actual test data loading logic if available
        // For now, using static data as testdata.properties/json is empty
        return new Object[][] {
            { "testuser@example.com", "password123", "New Organization", "123 New Address", "1234567890", "testuser@example.com" }
        };
    }

    @Test(priority = 1, groups = {"changeOfEmployment", "regression"}, dataProvider = "changeOfEmploymentData")
    public void testChangeOfEmploymentFlow(String username, String password, String newOrg, String newAddress, String newPhone, String newEmail) {
        // Step 1: Login
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(homePage.isAtHomePage(), "User should navigate to My ASTM home page after login");

        // Step 2: Navigate to Change of Employment
        homePage.goToChangeOfEmployment();
        Assert.assertTrue(coePage.isAtChangeOfEmploymentPage(), "User should land on Change of Employment page");

        // Step 3: Validate presence of Account Address, Current Organization, Edit button
        Assert.assertTrue(coePage.isAccountAddressDisplayed(), "Account Address should be displayed");
        Assert.assertTrue(coePage.isCurrentOrganizationDisplayed(), "Current Organization should be displayed");
        Assert.assertTrue(coePage.isEditButtonDisplayed(), "Edit button should be displayed");

        // Step 4: Edit details
        coePage.clickEditButton();
        coePage.enterNewOrganization(newOrg);
        coePage.enterNewAddress(newAddress);
        coePage.enterPhone(newPhone);
        coePage.enterEmail(newEmail);
        coePage.clickNext();

        // Step 5: Co-operative Agreement representative page
        Assert.assertTrue(coePage.isCoopAgreementRepPageDisplayed(), "User should land on Change of employment Co-operative Agreement representative page");
        coePage.clickNext();

        // Step 6: Review your committees page
        Assert.assertTrue(coePage.isReviewCommitteesPageDisplayed(), "User should land on Review your committees page");
        coePage.clickNext();

        // Step 7: Review and confirmation page
        Assert.assertTrue(coePage.isReviewAndConfirmationPageDisplayed(), "User should land on review and confirmation page");
        Assert.assertEquals(coePage.getPersonalDetailsSectionPhone(), newPhone, "Phone should match the one entered in Step 1");
        Assert.assertEquals(coePage.getPersonalDetailsSectionEmail(), newEmail, "Email should match the one entered in Step 1");

        // Step 8: Edit on review step
        coePage.clickEditOnReview();
        String updatedPhone = newPhone + "1";
        String updatedEmail = "updated_" + newEmail;
        coePage.enterPhone(updatedPhone);
        coePage.enterEmail(updatedEmail);
        coePage.clickNext();
        coePage.clickNext(); // Co-op Agreement
        coePage.clickNext(); // Review Committees
        Assert.assertEquals(coePage.getPersonalDetailsSectionPhone(), updatedPhone, "Phone should be updated after editing");
        Assert.assertEquals(coePage.getPersonalDetailsSectionEmail(), updatedEmail, "Email should be updated after editing");

        // Step 9: Previous action
        coePage.clickPrevious();
        coePage.enterPhone(newPhone);
        coePage.enterEmail(newEmail);
        coePage.clickNext();
        coePage.clickNext(); // Co-op Agreement
        coePage.clickNext(); // Review Committees
        Assert.assertEquals(coePage.getPersonalDetailsSectionPhone(), newPhone, "Phone should be reverted after Previous action");
        Assert.assertEquals(coePage.getPersonalDetailsSectionEmail(), newEmail, "Email should be reverted after Previous action");

        // Step 10: Cancel action resets fields
        coePage.clickCancel();
        Assert.assertTrue(coePage.isStaticPhoneEmailDisplayed(), "Phone/Email should reset to original static text after Cancel");

        // Step 11: Submit and Thank You page
        homePage.goToChangeOfEmployment();
        coePage.clickEditButton();
        coePage.enterNewOrganization(newOrg);
        coePage.enterNewAddress(newAddress);
        coePage.enterPhone(newPhone);
        coePage.enterEmail(newEmail);
        coePage.clickNext(); // Co-op Agreement
        coePage.clickNext(); // Review Committees
        coePage.clickNext(); // Review and Confirmation
        coePage.clickSubmit();
        Assert.assertTrue(coePage.isThankYouPageDisplayed(), "User should redirect to thank you page after submission");
        Assert.assertTrue(coePage.isGoToMyASTMButtonDisplayed(), "Go To MyASTM button should be displayed on Thank You page");
        Assert.assertTrue(coePage.isThankYouTextDisplayed(), "Thank You text should be displayed on Thank You page");

        // Step 12: Confirmation pop-up
        Assert.assertTrue(coePage.isConfirmationPopupDisplayed(), "Confirmation pop-up should be displayed after submission");
        Assert.assertEquals(coePage.getConfirmationPopupMessage(), "Thank you Your Request has been Submitted. The changes may take 1-3 business days to process and appear on your account", "Confirmation pop-up message should match");
        coePage.clickConfirmationOk();

        // Step 13: Email and log checks (pseudo, as actual email/log check would require integration)
        // These would normally be API/database/email service assertions or mocks
        Assert.assertTrue(coePage.isChangeOfEmploymentMailReceived("stctestlab@yopmail.com"), "Change of Employment mail should be received to staff");
        Assert.assertTrue(coePage.isChangeOfEmploymentMailReceived(username), "Change of Employment mail should be received to the member");
        Assert.assertTrue(coePage.isCOELogCaptured(username), "COE logs should be captured for the user");
        Assert.assertTrue(coePage.isChangeReflectedInMemberApp(username), "The changes should reflect in Member application");
        Assert.assertTrue(coePage.isChangeReflectedInIA(username), "The changes should reflect in IA");
    }
}
