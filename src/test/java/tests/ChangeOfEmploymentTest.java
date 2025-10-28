package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.HomePage;
import pages.ChangeOfEmploymentPage;
import pages.CommitteeReviewPage;
import pages.ThankYouPage;
import utils.TestDataReader;
import utils.DriverManager;

public class ChangeOfEmploymentTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ChangeOfEmploymentPage coePage;
    private CommitteeReviewPage committeeReviewPage;
    private ThankYouPage thankYouPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        coePage = new ChangeOfEmploymentPage(driver);
        committeeReviewPage = new CommitteeReviewPage(driver);
        thankYouPage = new ThankYouPage(driver);
    }

    @DataProvider(name = "memberCredentials")
    public Object[][] memberCredentials() {
        // Replace with actual data loading logic if available
        return new Object[][] {
            {"member1@example.com", "Password1", "555-1234", "member1@example.com"}
        };
    }

    @Test(priority = 1, groups = {"changeOfEmployment", "smoke"}, dataProvider = "memberCredentials")
    public void testChangeOfEmploymentProcess(String username, String password, String newPhone, String newEmail) {
        // Step 1: Login and verify My ASTM Home Page
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        Assert.assertTrue(homePage.isAtHomePage(), "User did not land on My ASTM home page after login.");
        Assert.assertTrue(homePage.isUserSignedIn(username), "User is not signed in successfully.");

        // Step 2: Navigate to Change of Employment page
        homePage.goToChangeOfEmployment();
        Assert.assertTrue(coePage.isAtChangeOfEmploymentPage(), "User did not land on Change of Employment page.");
        Assert.assertTrue(coePage.hasAccountAddress(), "Account Address is not displayed.");
        Assert.assertTrue(coePage.hasCurrentOrganization(), "Current Organization is not displayed.");
        Assert.assertTrue(coePage.hasEditButton(), "Edit button is not displayed.");

        // Step 3: Proceed to Co-operative Agreement representative step
        coePage.clickNext();
        Assert.assertTrue(coePage.isAtCooperativeAgreementStep(), "Did not land on Change of employment Co-operative Agreement representative step.");
        coePage.acceptCooperativeAgreement();
        coePage.clickNext();

        // Step 4: Review your committees page
        Assert.assertTrue(committeeReviewPage.isAtCommitteeReviewPage(), "Did not land on Review your committees page.");
        committeeReviewPage.clickNext();

        // Step 5: Review and confirmation page
        Assert.assertTrue(coePage.isAtReviewPage(), "Did not navigate to review and confirmation page.");
        Assert.assertTrue(coePage.isPersonalDetailsDisplayedBelowNewOrgDetails(newPhone, newEmail), "Personal details (Phone/Email) not displayed correctly below New Organization Details.");

        // Step 6: Edit button on review step
        coePage.clickEditOnReview();
        Assert.assertTrue(coePage.isAtStep1Form(), "Did not return to Step 1 form after clicking Edit.");
        coePage.updatePhone(newPhone + "9");
        coePage.updateEmail("edit_" + newEmail);
        coePage.clickNext();
        coePage.clickNext(); // Co-op agreement
        committeeReviewPage.clickNext();
        Assert.assertTrue(coePage.isPersonalDetailsDisplayedBelowNewOrgDetails(newPhone + "9", "edit_" + newEmail), "Edited Phone/Email not reflected on review step.");

        // Step 7: Previous action and make changes
        coePage.clickPrevious();
        coePage.updatePhone(newPhone);
        coePage.updateEmail(newEmail);
        coePage.clickNext();
        coePage.clickNext(); // Co-op agreement
        committeeReviewPage.clickNext();
        Assert.assertTrue(coePage.isPersonalDetailsDisplayedBelowNewOrgDetails(newPhone, newEmail), "Phone/Email not updated after using Previous action.");

        // Step 8: Cancel action resets details
        coePage.clickCancel();
        Assert.assertTrue(coePage.isPhoneEmailResetToOriginal(), "Cancel did not reset Phone/Email to original state.");

        // Step 9: Submit application
        homePage.goToChangeOfEmployment();
        coePage.fillOutChangeOfEmploymentForm(newPhone, newEmail);
        coePage.acceptCooperativeAgreement();
        coePage.clickNext();
        committeeReviewPage.clickNext();
        coePage.clickSubmit();

        // Step 10: Thank you page
        Assert.assertTrue(thankYouPage.isAtThankYouPage(), "Did not redirect to thank you page after submission.");
        Assert.assertTrue(thankYouPage.hasGoToMyASTMButton(), "Go To MyASTM button not present on thank you page.");
        Assert.assertTrue(thankYouPage.hasThankYouText(), "Thank you text not present on thank you page.");

        // Step 11: Confirmation pop-up
        Assert.assertTrue(thankYouPage.isConfirmationPopupDisplayed(), "Confirmation pop-up not displayed after submission.");
        Assert.assertEquals(thankYouPage.getConfirmationPopupText(),
                "Thank you Your Request has been Submitted. The changes may take 1-3 business days to process and appear on your account",
                "Confirmation pop-up message is incorrect.");
        thankYouPage.clickConfirmationOk();

        // Step 12: Email verification (pseudo, as actual email check may require integration)
        Assert.assertTrue(coePage.isChangeOfEmploymentEmailReceived("stctestlab@yopmail.com"), "Change of Employment mail not received by staff.");
        Assert.assertTrue(coePage.isChangeOfEmploymentEmailReceived(username), "Change of Employment mail not received by member.");

        // Step 13: Member application and IA reflection (pseudo, as actual check may require integration)
        Assert.assertTrue(coePage.isChangeReflectedInMemberApplication(), "Changes not reflected in Member application.");
        Assert.assertTrue(coePage.isChangeReflectedInIA(), "Changes not reflected in IA.");

        // Step 14: COE logs captured (pseudo, as actual check may require integration)
        Assert.assertTrue(coePage.isCOELogCaptured(username), "COE logs not captured for the user.");
    }
}
