package pages;

import org.openqa.selenium.WebDriver;

public class ContactCreationPage {
    private WebDriver driver;

    public ContactCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAt() {
        // Implement logic to verify if the contact creation page is loaded
        // Example: return driver.getTitle().contains("Create Contact");
        return true; // Placeholder
    }

    public void enterName(String name) {
        // Implement logic to enter the name in the Name field
        // Example: driver.findElement(By.id("contactName")).sendKeys(name);
    }

    public void fillMandatoryFieldsExceptName() {
        // Implement logic to fill other mandatory fields except Name
    }

    public void saveContact() {
        // Implement logic to click save button
    }

    public boolean isConfirmationDisplayed() {
        // Implement logic to verify confirmation message is displayed
        return true; // Placeholder
    }

    public boolean isContactSavedWithName(String name) {
        // Implement logic to verify contact is saved with the given name
        return true; // Placeholder
    }
}
