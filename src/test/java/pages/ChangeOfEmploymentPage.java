package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ChangeOfEmploymentPage extends BaseWebPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Placeholder locators for Change of Employment functionality
    // TODO: Replace with actual locators when available
    private By employmentTab = By.id("PLACEHOLDER_employment_tab"); // TODO: Replace with actual locator
    private By changeEmploymentButton = By.id("PLACEHOLDER_change_employment_button"); // TODO: Replace with actual locator
    private By employerNameField = By.id("PLACEHOLDER_employer_name_field"); // TODO: Replace with actual locator
    private By employmentStartDateField = By.id("PLACEHOLDER_employment_start_date_field"); // TODO: Replace with actual locator
    private By saveEmploymentButton = By.id("PLACEHOLDER_save_employment_button"); // TODO: Replace with actual locator
    private By myASTMHomeLink = By.id("PLACEHOLDER_my_astm_home_link"); // TODO: Replace with actual locator

    public ChangeOfEmploymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Navigate to the Employment tab/section
     */
    public void goToEmploymentTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employmentTab));
        driver.findElement(employmentTab).click();
    }

    /**
     * Click on the 'Change Employment' button
     */
    public void clickChangeEmployment() {
        wait.until(ExpectedConditions.elementToBeClickable(changeEmploymentButton));
        driver.findElement(changeEmploymentButton).click();
    }

    /**
     * Enter new employer name
     * @param employerName The new employer's name
     */
    public void enterEmployerName(String employerName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employerNameField));
        WebElement employerField = driver.findElement(employerNameField);
        employerField.clear();
        employerField.sendKeys(employerName);
    }

    /**
     * Enter employment start date
     * @param startDate The start date (format: MM/dd/yyyy or as required)
     */
    public void enterEmploymentStartDate(String startDate) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employmentStartDateField));
        WebElement dateField = driver.findElement(employmentStartDateField);
        dateField.clear();
        dateField.sendKeys(startDate);
    }

    /**
     * Click the 'Save Employment' button
     */
    public void clickSaveEmployment() {
        wait.until(ExpectedConditions.elementToBeClickable(saveEmploymentButton));
        driver.findElement(saveEmploymentButton).click();
    }

    /**
     * Navigate to My ASTM Home page (after change of employment)
     */
    public void goToMyASTMHome() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(myASTMHomeLink));
        driver.findElement(myASTMHomeLink).click();
    }

    /**
     * Verify navigation to My ASTM Home page
     * This can be enhanced with a more robust check (e.g., page title, unique element)
     */
    public boolean isAtMyASTMHomePage() {
        // TODO: Replace with actual verification logic for My ASTM Home page
        return driver.findElements(myASTMHomeLink).size() > 0;
    }
}
