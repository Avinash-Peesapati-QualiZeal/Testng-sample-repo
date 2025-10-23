package pages;

import org.openqa.selenium.WebDriver;

public class ContactCreationPage {
    private WebDriver driver;

    public ContactCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterContactName(String name) {
        // Implementation using driver to enter contact name
        // e.g., driver.findElement(...).sendKeys(name);
    }

    public void enterContactEmail(String email) {
        // Implementation using driver to enter contact email
        // e.g., driver.findElement(...).sendKeys(email);
    }

    public void saveContact() {
        // Implementation using driver to click save
        // e.g., driver.findElement(...).click();
    }

    public String getEmailValidationError() {
        // Implementation to get the email validation error message
        // e.g., return driver.findElement(...).getText();
        return ""; // Placeholder
    }

    public boolean isContactSaved() {
        // Implementation to check if contact was saved (e.g., confirmation message or contact in list)
        // e.g., return driver.findElements(...).size() > 0;
        return false; // Placeholder
    }
}
