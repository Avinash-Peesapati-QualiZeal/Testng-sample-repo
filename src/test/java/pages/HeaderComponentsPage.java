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

    // Locators for header components
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[@class='ui linkedin button' and contains(., 'Create')]");
    private By logOutLink = By.xpath("//a[contains(@class, 'item') and contains(., 'Log Out')]");
    private By settingsIcon = By.cssSelector("i.settings.icon"); // TODO: Confirm selector accuracy
    private By userIcon = By.cssSelector("i.large.user.red.icon"); // TODO: Confirm selector accuracy

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Validate header components are visible and accessible")
    public boolean validateHeaderComponents() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(contactsLink));
            wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
            wait.until(ExpectedConditions.visibilityOfElementLocated(logOutLink));
            wait.until(ExpectedConditions.visibilityOfElementLocated(settingsIcon));
            wait.until(ExpectedConditions.visibilityOfElementLocated(userIcon));
            return driver.findElement(contactsLink).isDisplayed()
                && driver.findElement(createButton).isDisplayed()
                && driver.findElement(logOutLink).isDisplayed()
                && driver.findElement(settingsIcon).isDisplayed()
                && driver.findElement(userIcon).isDisplayed();
        } catch (Exception e) {
            // Optionally log the exception using LoggerHelper if available
            return false;
        }
    }
}
