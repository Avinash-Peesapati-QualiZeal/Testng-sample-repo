package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class ChangeOfEmploymentPage {
    private WebDriver driver;

    // Locators (placeholders, as actual locators are not provided)
    private By signInButton = By.xpath("<PLACEHOLDER_SignInButton>"); // TODO: Replace with actual locator
    private By changeOfEmploymentMenu = By.xpath("<PLACEHOLDER_ChangeOfEmploymentMenu>"); // TODO: Replace with actual locator
    private By firstRadioButton = By.xpath("<PLACEHOLDER_FirstRadioButton>"); // TODO: Replace with actual locator
    private By continueButton = By.xpath("<PLACEHOLDER_ContinueButton>"); // TODO: Replace with actual locator
    private By agreementDetailsSection = By.xpath("<PLACEHOLDER_AgreementDetailsSection>"); // TODO: Replace with actual locator
    private By updatePersonalDetailsLink = By.xpath("<PLACEHOLDER_UpdatePersonalDetailsLink>"); // TODO: Replace with actual locator
    private By phoneInput = By.xpath("<PLACEHOLDER_PhoneInput>"); // TODO: Replace with actual locator
    private By emailInput = By.xpath("<PLACEHOLDER_EmailInput>"); // TODO: Replace with actual locator
    private By nextButton = By.xpath("<PLACEHOLDER_NextButton>"); // TODO: Replace with actual locator
    private By reviewCommitteesPage = By.xpath("<PLACEHOLDER_ReviewCommitteesPage>"); // TODO: Replace with actual locator
    private By reviewAndConfirmationPage = By.xpath("<PLACEHOLDER_ReviewAndConfirmationPage>"); // TODO: Replace with actual locator
    private By editButton = By.xpath("<PLACEHOLDER_EditButton>"); // TODO: Replace with actual locator
    private By previousButton = By.xpath("<PLACEHOLDER_PreviousButton>"); // TODO: Replace with actual locator
    private By cancelButton = By.xpath("<PLACEHOLDER_CancelButton>"); // TODO: Replace with actual locator
    private By submitButton = By.xpath("<PLACEHOLDER_SubmitButton>"); // TODO: Replace with actual locator
    private By thankYouPageText = By.xpath("<PLACEHOLDER_ThankYouPageText>"); // TODO: Replace with actual locator
    private By goToMyASTMButton = By.xpath("<PLACEHOLDER_GoToMyASTMButton>"); // TODO: Replace with actual locator
    private By confirmationPopup = By.xpath("<PLACEHOLDER_ConfirmationPopup>"); // TODO: Replace with actual locator
    private By okButton = By.xpath("<PLACEHOLDER_OkButton>"); // TODO: Replace with actual locator

    // Constructor
    public ChangeOfEmploymentPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Complete the change of employment process for ASTM member application")
    public void completeChangeOfEmploymentFlow(String phone, String email) {
        // Click Sign In button (assume already on login page)
        driver.findElement(signInButton).click();
        // Wait/navigate to My ASTM home page is assumed

        // Click on Change of Employment in left panel
        driver.findElement(changeOfEmploymentMenu).click();

        // Select first radio button (COA Representative scenario)
        driver.findElement(firstRadioButton).click();
        driver.findElement(continueButton).click();

        // Fill Agreement with new organization Details (placeholder)
        driver.findElement(agreementDetailsSection).click(); // Placeholder interaction

        // Update personal details (phone/email)
        driver.findElement(updatePersonalDetailsLink).click();
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(nextButton).click();

        // Review your committees page (placeholder interaction)
        driver.findElement(reviewCommitteesPage).click(); // Placeholder
        driver.findElement(nextButton).click();

        // Review and Confirmation page
        driver.findElement(reviewAndConfirmationPage).click(); // Placeholder
        // Optionally click Edit, Previous, or Cancel as per scenario
        // For main flow, proceed to submit
        driver.findElement(submitButton).click();

        // Thank you page
        driver.findElement(thankYouPageText).isDisplayed(); // Assert presence
        driver.findElement(goToMyASTMButton).click();

        // Confirmation popup
        driver.findElement(confirmationPopup).isDisplayed(); // Assert presence
        driver.findElement(okButton).click();
    }
}
