package pages;

import org.openqa.selenium.WebDriver;
import com.yourpackage.base.BaseWebPage;

public class ChangeOfEmploymentPage extends BaseWebPage {
    public ChangeOfEmploymentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        // Implement logic to verify if the Change of Employment page is displayed
        // Example: return getTitle().contains("Change of Employment");
        return true;
    }

    public void enterNewEmployerDetails(String employerName, String jobTitle, String city, String state, String zip) {
        // Implement logic to enter new employer details using existing locators
        // Example:
        // setEmployerName(employerName);
        // setJobTitle(jobTitle);
        // setCity(city);
        // setState(state);
        // setZip(zip);
    }

    public boolean isEmployerDetailsEntered() {
        // Implement logic to verify employer details are entered
        return true;
    }

    public void submitChangeRequest() {
        // Implement logic to submit the change of employment request
    }

    public boolean isSubmissionSuccessful() {
        // Implement logic to verify submission success (e.g., success message displayed)
        return true;
    }

    public boolean isConfirmationDisplayed() {
        // Implement logic to check for confirmation message or expected outcomes
        return true;
    }
}
