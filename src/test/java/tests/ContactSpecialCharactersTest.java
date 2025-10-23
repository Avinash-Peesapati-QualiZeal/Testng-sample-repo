package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import com.yourpackage.pages.LoginPage;
import com.yourpackage.pages.HomePage;
import com.yourpackage.pages.ContactCreationPage;
import com.yourpackage.utils.TestDataUtils;
import com.yourpackage.base.BaseTest;

public class ContactSpecialCharactersTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ContactCreationPage contactCreationPage;

    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        contactCreationPage = new ContactCreationPage(driver);
    }

    @DataProvider(name = "specialCharacterNames")
    public Object[][] specialCharacterNames() {
        return new Object[][] {
            { "John@Doe" },
            { "Jane-Doe!" },
            { "O'Reilly" },
            { "Anna#Smith" },
            { "Peter$Parker" }
        };
    }

    @Test(priority = 1, dataProvider = "specialCharacterNames", groups = {"contact", "special-characters"})
    public void testHandlingSpecialCharactersInName(String specialName) {
        try {
            // Preconditions: User is logged in and navigates to contact creation
            loginPage.login(TestDataUtils.getUsername(), TestDataUtils.getPassword());
            Assert.assertTrue(homePage.isLoggedIn(), "Login failed - user not logged in.");
            homePage.navigateToContactCreation();
            Assert.assertTrue(contactCreationPage.isAt(), "Failed to navigate to contact creation screen.");

            // Action: Enter special character name and save
            contactCreationPage.enterName(specialName);
            contactCreationPage.fillMandatoryFieldsExceptName(); // Assume this fills other required fields
            contactCreationPage.saveContact();

            // Expected Result: Confirmation message displayed
            Assert.assertTrue(contactCreationPage.isConfirmationDisplayed(),
                "Confirmation message not displayed for name with special characters: " + specialName);

            // Post-condition: Contact is saved with the specified details
            Assert.assertTrue(contactCreationPage.isContactSavedWithName(specialName),
                "Contact not saved with special character name: " + specialName);
        } catch (Exception e) {
            Assert.fail("Exception occurred during special character name test: " + e.getMessage());
        }
    }
}
