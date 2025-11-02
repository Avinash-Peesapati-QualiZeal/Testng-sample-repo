package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class ChangeOfEmploymentPage {
    private WebDriver driver;

    // Locators (placeholders and mapped from provided locators)
    private By emailInput = By.name("email");
    private By passwordInput = By.name("password");
    private By loginButton = By.className("submit");
    private By changeOfEmploymentLink = By.xpath("<PLACEHOLDER_change_of_employment_link>"); // TODO: Replace with actual locator
    private By firstRadioButton = By.xpath("<PLACEHOLDER_first_radio_button>"); // TODO: Replace with actual locator
    private By continueButton = By.xpath("<PLACEHOLDER_continue_button>"); // TODO: Replace with actual locator
    private By agreementOptionChangedEmployment = By.xpath("<PLACEHOLDER_agreement_option_changed_employment>"); // TODO: Replace with actual locator
    private By agreementOptionMerged = By.xpath("<PLACEHOLDER_agreement_option_merged>"); // TODO: Replace with actual locator
    private By agreementOptionNoOrg = By.xpath("<PLACEHOLDER_agreement_option_no_org>"); // TODO: Replace with actual locator
    private By updatePersonalDetailsLink = By.xpath("<PLACEHOLDER_update_personal_details_link>"); // TODO: Replace with actual locator
    private By phoneInput = By.xpath("<PLACEHOLDER_phone_input>"); // TODO: Replace with actual locator
    private By emailAddressInput = By.name("value");
    private By nextButton = By.xpath("<PLACEHOLDER_next_button>"); // TODO: Replace with actual locator
    private By reviewCommitteesMandatoryFields = By.xpath("<PLACEHOLDER_review_committees_mandatory_fields>"); // TODO: Replace with actual locator
    private By reviewAndConfirmationPage = By.xpath("<PLACEHOLDER_review_and_confirmation_page>"); // TODO: Replace with actual locator
    private By editButton = By.xpath("<PLACEHOLDER_edit_button>"); // TODO: Replace with actual locator
    private By previousButton = By.xpath("<PLACEHOLDER_previous_button>"); // TODO: Replace with actual locator
    private By cancelButton = By.xpath("<PLACEHOLDER_cancel_button>"); // TODO: Replace with actual locator
    private By submitButton = By.xpath("<PLACEHOLDER_submit_button>"); // TODO: Replace with actual locator
    private By goToMyASTMButton = By.xpath("<PLACEHOLDER_go_to_my_astm_button>"); // TODO: Replace with actual locator
    private By secondRadioButton = By.xpath("<PLACEHOLDER_second_radio_button>"); // TODO: Replace with actual locator
    private By organizationNameInput = By.xpath("<PLACEHOLDER_organization_name_input>"); // TODO: Replace with actual locator

    public ChangeOfEmploymentPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Complete the change of employment process for ASTM member application")
    public void completeChangeOfEmployment(String email, String password, String newPhone, String newEmail, String newOrgName) {
        // Login
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        // Navigate to Change of Employment
        driver.findElement(changeOfEmploymentLink).click();

        // Select first radio button (COA Representative flow)
        driver.findElement(firstRadioButton).click();
        driver.findElement(continueButton).click();

        // Select agreement options (simulate all three for demo)
        driver.findElement(agreementOptionChangedEmployment).click();
        driver.findElement(agreementOptionMerged).click();
        driver.findElement(agreementOptionNoOrg).click();

        // Update personal details
        driver.findElement(updatePersonalDetailsLink).click();
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(newPhone);
        driver.findElement(emailAddressInput).clear();
        driver.findElement(emailAddressInput).sendKeys(newEmail);
        driver.findElement(nextButton).click();

        // Fill mandatory fields on Review Your Committees page
        driver.findElement(reviewCommitteesMandatoryFields).click(); // Placeholder for filling fields
        driver.findElement(nextButton).click();

        // Review and Confirmation
        driver.findElement(reviewAndConfirmationPage); // Just to ensure page is loaded
        // Optionally edit or go back
        // driver.findElement(editButton).click();
        // driver.findElement(previousButton).click();
        // driver.findElement(cancelButton).click();
        driver.findElement(submitButton).click();

        // Go to My ASTM and verify email (placeholder)
        driver.findElement(goToMyASTMButton).click();
        // TODO: Add logic to verify email communications
    }

    @Step("Edit organization name due to misspelling or change in company name only")
    public void editOrganizationName(String email, String password, String correctOrgName) {
        // Login
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        // Navigate to Change of Employment
        driver.findElement(changeOfEmploymentLink).click();

        // Select second radio button
        driver.findElement(secondRadioButton).click();
        driver.findElement(organizationNameInput).clear();
        driver.findElement(organizationNameInput).sendKeys(correctOrgName);
        driver.findElement(submitButton).click();
        // TODO: Add logic to verify email communications
    }

    // Additional methods for staff manager/IA approval and audit log can be added as needed
    // Placeholders for those flows:
    public void waitForStaffManagerApproval() {
        // TODO: Implement wait or polling for staff manager approval (may take 1-3 business days)
    }

    public void verifyChangeOfEmploymentInIA() {
        // TODO: Implement navigation and verification in IA after approval
    }

    public void verifyAuditLogForUser(String userEmail) {
        // TODO: Implement audit log search and verification
    }
}
