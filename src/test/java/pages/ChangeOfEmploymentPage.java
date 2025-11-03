package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class ChangeOfEmploymentPage {
    private WebDriver driver;

    // Locators (placeholders where actual values are not available)
    private By myASTMHomePageIndicator = By.xpath("<PLACEHOLDER_MyASTM_HomePage_Indicator>"); // TODO: Replace with actual locator
    private By signInButton = By.xpath("<PLACEHOLDER_SignIn_Button>"); // TODO: Replace with actual locator
    private By changeOfEmploymentMenu = By.xpath("<PLACEHOLDER_ChangeOfEmployment_Menu>"); // TODO: Replace with actual locator
    private By coaRepresentativeFirstRadio = By.xpath("<PLACEHOLDER_COA_Representative_First_Radio>"); // TODO: Replace with actual locator
    private By continueButton = By.xpath("<PLACEHOLDER_Continue_Button>"); // TODO: Replace with actual locator
    private By agreementOptionChangedEmployment = By.xpath("<PLACEHOLDER_Agreement_Option_Changed_Employment>"); // TODO: Replace with actual locator
    private By agreementOptionMergedAcquired = By.xpath("<PLACEHOLDER_Agreement_Option_Merged_Acquired>"); // TODO: Replace with actual locator
    private By agreementOptionNotAssociated = By.xpath("<PLACEHOLDER_Agreement_Option_Not_Associated>"); // TODO: Replace with actual locator
    private By updatePersonalDetailsLink = By.xpath("<PLACEHOLDER_Update_Personal_Details_Link>"); // TODO: Replace with actual locator
    private By phoneInput = By.xpath("<PLACEHOLDER_Phone_Input>"); // TODO: Replace with actual locator
    private By emailInput = By.xpath("<PLACEHOLDER_Email_Input>"); // TODO: Replace with actual locator
    private By nextButton = By.xpath("<PLACEHOLDER_Next_Button>"); // TODO: Replace with actual locator
    private By reviewYourCommitteesPageIndicator = By.xpath("<PLACEHOLDER_Review_Your_Committees_Page_Indicator>"); // TODO: Replace with actual locator
    private By reviewAndConfirmationPageIndicator = By.xpath("<PLACEHOLDER_Review_And_Confirmation_Page_Indicator>"); // TODO: Replace with actual locator
    private By editButton = By.xpath("<PLACEHOLDER_Edit_Button>"); // TODO: Replace with actual locator
    private By previousButton = By.xpath("<PLACEHOLDER_Previous_Button>"); // TODO: Replace with actual locator
    private By cancelButton = By.xpath("<PLACEHOLDER_Cancel_Button>"); // TODO: Replace with actual locator
    private By submitButton = By.xpath("<PLACEHOLDER_Submit_Button>"); // TODO: Replace with actual locator
    private By thankYouPageIndicator = By.xpath("<PLACEHOLDER_Thank_You_Page_Indicator>"); // TODO: Replace with actual locator
    private By goToMyASTMButton = By.xpath("<PLACEHOLDER_GoToMyASTM_Button>"); // TODO: Replace with actual locator
    private By confirmationPopup = By.xpath("<PLACEHOLDER_Confirmation_Popup>"); // TODO: Replace with actual locator
    private By confirmationOkButton = By.xpath("<PLACEHOLDER_Confirmation_Ok_Button>"); // TODO: Replace with actual locator
    private By coeLogsSection = By.xpath("<PLACEHOLDER_COE_Logs_Section>"); // TODO: Replace with actual locator

    // Constructor
    public ChangeOfEmploymentPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Complete change of employment process for ASTM member")
    public void completeChangeOfEmploymentFlow(String username, String password, String phone, String email, String agreementType) {
        // 1. Navigate to My ASTM home page (assume already on home page after login)
        // 2. Sign in (if not already signed in)
        // TODO: Add logic to check if already signed in, else sign in
        // driver.findElement(signInButton).click();
        // ... (sign in steps)

        // 3. Click on Change of Employment in the left panel
        driver.findElement(changeOfEmploymentMenu).click();

        // 4. If COA Representative, select first radio button and continue
        driver.findElement(coaRepresentativeFirstRadio).click();
        driver.findElement(continueButton).click();

        // 5. Fill Agreement with new organization details based on agreementType
        if ("changedEmployment".equalsIgnoreCase(agreementType)) {
            driver.findElement(agreementOptionChangedEmployment).click();
        } else if ("mergedAcquired".equalsIgnoreCase(agreementType)) {
            driver.findElement(agreementOptionMergedAcquired).click();
        } else if ("notAssociated".equalsIgnoreCase(agreementType)) {
            driver.findElement(agreementOptionNotAssociated).click();
        }

        // 6. Update personal details (Phone/Email) if needed
        driver.findElement(updatePersonalDetailsLink).click();
        WebElement phoneElem = driver.findElement(phoneInput);
        phoneElem.clear();
        phoneElem.sendKeys(phone);
        WebElement emailElem = driver.findElement(emailInput);
        emailElem.clear();
        emailElem.sendKeys(email);

        // 7. Click Next to go to Review Your Committees page
        driver.findElement(nextButton).click();
        // 8. On Review Your Committees page, fill mandatory fields (assumed handled elsewhere or not required)
        driver.findElement(nextButton).click();

        // 9. On Review and Confirmation page, verify details (assumed handled in assertions in test)
        // 10. If needed, click Edit to go back and change Phone/Email
        // driver.findElement(editButton).click(); // Optional
        // 11. If needed, click Previous to go back
        // driver.findElement(previousButton).click(); // Optional
        // 12. If needed, click Cancel to reset
        // driver.findElement(cancelButton).click(); // Optional

        // 13. Submit the application
        driver.findElement(submitButton).click();

        // 14. Handle confirmation popup
        driver.findElement(confirmationOkButton).click();

        // 15. Wait for Thank You page and click Go To MyASTM
        driver.findElement(goToMyASTMButton).click();

        // 16. (Optional) Verify COE logs, emails, etc. (handled in test or other page objects)
    }
}
