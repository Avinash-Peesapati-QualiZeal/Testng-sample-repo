package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class ChangeOfEmploymentPage {
    private WebDriver driver;

    // Locators (placeholders where actual locators are not provided)
    private By signInButton = By.xpath("<PLACEHOLDER_SignInButton>"); // TODO: Replace with actual locator
    private By emailInput = By.name("email");
    private By passwordInput = By.name("password");
    private By loginButton = By.xpath("//div[contains(@class, 'submit button') and text()='Login']");
    private By leftPanelChangeOfEmployment = By.xpath("<PLACEHOLDER_LeftPanelChangeOfEmployment>"); // TODO: Replace with actual locator
    private By firstRadioButton = By.xpath("<PLACEHOLDER_FirstRadioButton>"); // TODO: Replace with actual locator
    private By continueButton = By.xpath("<PLACEHOLDER_ContinueButton>"); // TODO: Replace with actual locator
    private By agreementWithNewOrgDetails = By.xpath("<PLACEHOLDER_AgreementWithNewOrgDetails>"); // TODO: Replace with actual locator
    private By reviewCommitteesNextButton = By.xpath("<PLACEHOLDER_ReviewCommitteesNextButton>"); // TODO: Replace with actual locator
    private By reviewAndConfirmationPage = By.xpath("<PLACEHOLDER_ReviewAndConfirmationPage>"); // TODO: Replace with actual locator
    private By editButton = By.xpath("<PLACEHOLDER_EditButton>"); // TODO: Replace with actual locator
    private By previousButton = By.xpath("<PLACEHOLDER_PreviousButton>"); // TODO: Replace with actual locator
    private By cancelButton = By.xpath("<PLACEHOLDER_CancelButton>"); // TODO: Replace with actual locator
    private By submitButton = By.xpath("<PLACEHOLDER_SubmitButton>"); // TODO: Replace with actual locator
    private By goToMyASTMButton = By.xpath("<PLACEHOLDER_GoToMyASTMButton>"); // TODO: Replace with actual locator
    private By secondRadioButton = By.xpath("<PLACEHOLDER_SecondRadioButton>"); // TODO: Replace with actual locator
    private By orgNameInput = By.xpath("<PLACEHOLDER_OrgNameInput>"); // TODO: Replace with actual locator
    private By iaSearchUserInput = By.xpath("<PLACEHOLDER_IASearchUserInput>"); // TODO: Replace with actual locator
    private By auditLogButton = By.xpath("<PLACEHOLDER_AuditLogButton>"); // TODO: Replace with actual locator

    // Constructor
    public ChangeOfEmploymentPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Complete the change of employment flow for ASTM member application.
     * This method covers login, navigating to change of employment, handling both COA Representative and organization name edit flows, and submission.
     *
     * @param email User email
     * @param password User password
     * @param isCOARepresentative true if user is COA Representative, false otherwise
     * @param newOrgDetails Details for new organization (if applicable)
     * @param newOrgName New organization name (if editing name)
     */
    @Step("Complete the change of employment process for ASTM member application")
    public void completeChangeOfEmploymentFlow(String email, String password, boolean isCOARepresentative, String newOrgDetails, String newOrgName) {
        // Click Sign In
        driver.findElement(signInButton).click();

        // Enter credentials and login
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        // Navigate to Change of Employment
        driver.findElement(leftPanelChangeOfEmployment).click();

        if (isCOARepresentative) {
            // Select first radio button
            driver.findElement(firstRadioButton).click();
            driver.findElement(continueButton).click();

            // Fill agreement with new organization details
            driver.findElement(agreementWithNewOrgDetails).sendKeys(newOrgDetails);
            driver.findElement(continueButton).click();

            // On review your committees page, fill mandatory fields and click next
            driver.findElement(reviewCommitteesNextButton).click();

            // Review and Confirmation page
            driver.findElement(reviewAndConfirmationPage).isDisplayed();
            // Optionally edit and cancel
            // driver.findElement(editButton).click();
            // driver.findElement(previousButton).click();
            // driver.findElement(cancelButton).click();

            // Submit
            driver.findElement(submitButton).click();
            driver.findElement(goToMyASTMButton).click();
        } else {
            // Edit organization name flow
            driver.findElement(secondRadioButton).click();
            driver.findElement(orgNameInput).clear();
            driver.findElement(orgNameInput).sendKeys(newOrgName);
            driver.findElement(submitButton).click();
        }
    }

    /**
     * After Staff Manager Approval, verify change of employment in Member application and IA.
     * @param userEmail The email of the user to search in IA
     */
    @Step("Verify change of employment in Member application and IA after Staff Manager Approval")
    public void verifyChangeOfEmploymentInIA(String userEmail) {
        // Navigate to IA, search for user, and check audit log
        driver.findElement(iaSearchUserInput).clear();
        driver.findElement(iaSearchUserInput).sendKeys(userEmail);
        driver.findElement(auditLogButton).click();
        // TODO: Add assertions or checks as needed
    }
}
