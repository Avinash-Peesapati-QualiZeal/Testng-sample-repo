package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class ChangeOfEmploymentPage {
    private WebDriver driver;

    // Locators (placeholders where actual locators are missing)
    private By signInButton = By.xpath("<PLACEHOLDER_SignInButton>"); // TODO: Replace with actual locator
    private By emailInput = By.name("email");
    private By passwordInput = By.name("password");
    private By loginButton = By.xpath("//div[contains(@class, 'submit button') and text()='Login']");
    private By leftPanelChangeOfEmployment = By.xpath("<PLACEHOLDER_LeftPanelChangeOfEmployment>"); // TODO: Replace with actual locator
    private By coaRepFirstRadioButton = By.xpath("<PLACEHOLDER_COARepFirstRadioButton>"); // TODO: Replace with actual locator
    private By continueButton = By.xpath("<PLACEHOLDER_ContinueButton>"); // TODO: Replace with actual locator
    private By agreementOptionChangedEmployment = By.xpath("<PLACEHOLDER_AgreementOptionChangedEmployment>"); // TODO: Replace with actual locator
    private By agreementOptionMergedAcquired = By.xpath("<PLACEHOLDER_AgreementOptionMergedAcquired>"); // TODO: Replace with actual locator
    private By agreementOptionNoOrg = By.xpath("<PLACEHOLDER_AgreementOptionNoOrg>"); // TODO: Replace with actual locator
    private By updatePersonalDetailsLink = By.xpath("<PLACEHOLDER_UpdatePersonalDetailsLink>"); // TODO: Replace with actual locator
    private By phoneInput = By.xpath("<PLACEHOLDER_PhoneInput>"); // TODO: Replace with actual locator
    private By emailUpdateInput = By.xpath("<PLACEHOLDER_EmailUpdateInput>"); // TODO: Replace with actual locator
    private By nextButton = By.xpath("<PLACEHOLDER_NextButton>"); // TODO: Replace with actual locator
    private By reviewCommitteesMandatoryFields = By.xpath("<PLACEHOLDER_ReviewCommitteesMandatoryFields>"); // TODO: Replace with actual locator
    private By reviewCommitteesNextButton = By.xpath("<PLACEHOLDER_ReviewCommitteesNextButton>"); // TODO: Replace with actual locator
    private By reviewAndConfirmationPage = By.xpath("<PLACEHOLDER_ReviewAndConfirmationPage>"); // TODO: Replace with actual locator
    private By editButton = By.xpath("<PLACEHOLDER_EditButton>"); // TODO: Replace with actual locator
    private By previousButton = By.xpath("<PLACEHOLDER_PreviousButton>"); // TODO: Replace with actual locator
    private By cancelButton = By.xpath("<PLACEHOLDER_CancelButton>"); // TODO: Replace with actual locator
    private By submitButton = By.xpath("<PLACEHOLDER_SubmitButton>"); // TODO: Replace with actual locator
    private By goToMyASTMButton = By.xpath("<PLACEHOLDER_GoToMyASTMButton>"); // TODO: Replace with actual locator
    private By secondRadioButtonEditOrgName = By.xpath("<PLACEHOLDER_SecondRadioButtonEditOrgName>"); // TODO: Replace with actual locator
    private By organizationNameInput = By.xpath("<PLACEHOLDER_OrganizationNameInput>"); // TODO: Replace with actual locator
    private By iaUrlLoginButton = By.xpath("<PLACEHOLDER_IAUrlLoginButton>"); // TODO: Replace with actual locator
    private By iaSearchUserInput = By.xpath("<PLACEHOLDER_IASearchUserInput>"); // TODO: Replace with actual locator
    private By iaAuditLogButton = By.xpath("<PLACEHOLDER_IAAuditLogButton>"); // TODO: Replace with actual locator

    // Constructor
    public ChangeOfEmploymentPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Complete the change of employment flow as described in test case INT-8038.
     * This method covers login, navigating to change of employment, handling both COA Representative and organization name edit flows, updating personal details, reviewing, confirming, submitting, and post-approval checks.
     * @param email User email
     * @param password User password
     * @param isCOARepresentative true if user is COA Representative, false otherwise
     * @param changeType String: "changed_employment", "merged_acquired", "no_org", "edit_org_name"
     * @param newOrgName New organization name (if applicable)
     * @param phone New phone (if applicable)
     * @param updatedEmail New email (if applicable)
     */
    @Step("Complete change of employment flow for ASTM member application")
    public void completeChangeOfEmploymentFlow(String email, String password, boolean isCOARepresentative, String changeType, String newOrgName, String phone, String updatedEmail) {
        // Login steps
        driver.findElement(signInButton).click();
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        // Navigate to Change of Employment
        driver.findElement(leftPanelChangeOfEmployment).click();

        if (isCOARepresentative) {
            // COA Rep flow
            driver.findElement(coaRepFirstRadioButton).click();
            driver.findElement(continueButton).click();
            // Agreement options
            switch (changeType) {
                case "changed_employment":
                    driver.findElement(agreementOptionChangedEmployment).click();
                    break;
                case "merged_acquired":
                    driver.findElement(agreementOptionMergedAcquired).click();
                    break;
                case "no_org":
                    driver.findElement(agreementOptionNoOrg).click();
                    break;
                default:
                    // No action
            }
            // Update personal details if needed
            if ((phone != null && !phone.isEmpty()) || (updatedEmail != null && !updatedEmail.isEmpty())) {
                driver.findElement(updatePersonalDetailsLink).click();
                if (phone != null && !phone.isEmpty()) {
                    driver.findElement(phoneInput).clear();
                    driver.findElement(phoneInput).sendKeys(phone);
                }
                if (updatedEmail != null && !updatedEmail.isEmpty()) {
                    driver.findElement(emailUpdateInput).clear();
                    driver.findElement(emailUpdateInput).sendKeys(updatedEmail);
                }
            }
            driver.findElement(nextButton).click();
            // Review committees
            driver.findElement(reviewCommitteesMandatoryFields).click(); // Placeholder for filling mandatory fields
            driver.findElement(reviewCommitteesNextButton).click();
            // Review and confirmation
            driver.findElement(reviewAndConfirmationPage).isDisplayed();
            // Optionally edit, previous, or cancel
            // driver.findElement(editButton).click();
            // driver.findElement(previousButton).click();
            // driver.findElement(cancelButton).click();
            driver.findElement(submitButton).click();
            driver.findElement(goToMyASTMButton).click();
            // Email verification and post-approval steps would be handled outside this method
        } else if ("edit_org_name".equals(changeType)) {
            // Edit organization name flow
            driver.findElement(secondRadioButtonEditOrgName).click();
            driver.findElement(organizationNameInput).clear();
            driver.findElement(organizationNameInput).sendKeys(newOrgName);
            driver.findElement(submitButton).click();
            // Email verification and post-approval steps would be handled outside this method
        }
    }
}
