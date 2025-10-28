package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangeOfEmploymentPage extends BaseWebPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators (placeholders, replace with actual locators)
    private By employmentTab = By.id("PLACEHOLDER_employment_tab"); // TODO: Replace with actual locator
    private By changeEmploymentButton = By.id("PLACEHOLDER_change_employment_button"); // TODO: Replace with actual locator
    private By employerNameField = By.id("PLACEHOLDER_employer_name_field"); // TODO: Replace with actual locator
    private By employmentStartDateField = By.id("PLACEHOLDER_employment_start_date_field"); // TODO: Replace with actual locator
    private By saveEmploymentButton = By.id("PLACEHOLDER_save_employment_button"); // TODO: Replace with actual locator

    // Constructor
    public ChangeOfEmploymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    // Action: Navigate to Employment Tab
    public void goToEmploymentTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employmentTab));
        driver.findElement(employmentTab).click();
    }

    // Action: Click Change Employment Button
    public void clickChangeEmployment() {
        wait.until(ExpectedConditions.elementToBeClickable(changeEmploymentButton));
        driver.findElement(changeEmploymentButton).click();
    }

    // Action: Enter Employer Name
    public void enterEmployerName(String employerName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employerNameField));
        WebElement employerField = driver.findElement(employerNameField);
        employerField.clear();
        employerField.sendKeys(employerName);
    }

    // Action: Enter Employment Start Date
    public void enterEmploymentStartDate(String startDate) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employmentStartDateField));
        WebElement dateField = driver.findElement(employmentStartDateField);
        dateField.clear();
        dateField.sendKeys(startDate);
    }

    // Action: Save Employment Details
    public void clickSaveEmployment() {
        wait.until(ExpectedConditions.elementToBeClickable(saveEmploymentButton));
        driver.findElement(saveEmploymentButton).click();
    }

    // Validation: Check if employment details updated (placeholder)
    public boolean isEmploymentUpdated(String expectedEmployer) {
        // TODO: Replace with actual locator and logic for validation
        By updatedEmployerField = By.id("PLACEHOLDER_updated_employer_field");
        wait.until(ExpectedConditions.visibilityOfElementLocated(updatedEmployerField));
        String actualEmployer = driver.findElement(updatedEmployerField).getText();
        return actualEmployer.equals(expectedEmployer);
    }

    // Validation: Check if success email sent (placeholder)
    public boolean isSuccessEmailSent() {
        // TODO: Implement email verification if applicable
        return true;
    }
}
