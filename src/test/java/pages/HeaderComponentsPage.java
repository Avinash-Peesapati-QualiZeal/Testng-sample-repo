package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HeaderComponentsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for header components (placeholders where actual locators are not provided)
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'linkedin button') and text()='Create']");
    private By logOutLink = By.linkText("Log Out");
    private By settingsIcon = By.xpath("<PLACEHOLDER_settings_icon>"); // TODO: Replace with actual locator
    private By userIcon = By.xpath("<PLACEHOLDER_user_icon>"); // TODO: Replace with actual locator

    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Validate all header components are visible and accessible")
    public boolean validateHeaderComponents() {
        try {
            // Validate 'Contacts' link
            WebElement contacts = wait.until(ExpectedConditions.visibilityOfElementLocated(contactsLink));
            if (!contacts.isDisplayed()) return false;

            // Validate 'Create' button
            WebElement createBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
            if (!createBtn.isDisplayed()) return false;

            // Validate 'Log Out' link
            WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(logOutLink));
            if (!logout.isDisplayed()) return false;

            // Validate 'Settings' icon (placeholder)
            WebElement settings = wait.until(ExpectedConditions.visibilityOfElementLocated(settingsIcon));
            if (!settings.isDisplayed()) return false;

            // Validate 'User' icon (placeholder)
            WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(userIcon));
            if (!user.isDisplayed()) return false;

            return true;
        } catch (Exception e) {
            // Log the exception if LoggerHelper or similar utility is available
            // LoggerHelper.logError("Header component validation failed", e);
            return false;
        }
    }
}
