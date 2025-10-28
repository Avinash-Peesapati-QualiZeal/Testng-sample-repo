package pages;

import org.openqa.selenium.WebDriver;
import base.BaseWebPage;

public class ChangeOfEmploymentPage extends BaseWebPage {
    public ChangeOfEmploymentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isChangeOfEmploymentPageDisplayed() {
        // Implement logic to verify Change of Employment page is displayed
        // Example: return isElementVisible("changeOfEmploymentHeader");
        return true;
    }

    public void selectEmploymentStatus(String status) {
        // Implement logic to select employment status from dropdown
    }

    public void enterNewEmployerName(String employerName) {
        // Implement logic to enter new employer name
    }

    public void enterNewEmployerAddress(String address) {
        // Implement logic to enter new employer address
    }

    public void enterNewJobTitle(String jobTitle) {
        // Implement logic to enter new job title
    }

    public void enterEffectiveDate(String date) {
        // Implement logic to enter effective date
    }

    public void submitChangeOfEmployment() {
        // Implement logic to submit the change of employment form
    }

    public boolean isConfirmationMessageDisplayed() {
        // Implement logic to verify confirmation message is displayed
        // Example: return isElementVisible("confirmationMessage");
        return true;
    }

    // Optional: Implement email verification if feasible
    // public boolean isConfirmationEmailSent() {
    //     // Implement logic to verify confirmation email was sent
    //     return true;
    // }
}
