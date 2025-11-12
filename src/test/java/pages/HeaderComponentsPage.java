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

    // Locators for header components (from provided locators and HTML snippet)
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'linkedin') and contains(., 'Create')]");
    private By userIcon = By.cssSelector("i.large.user.red.icon");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By logOutLink = By.linkText("Log Out");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Validate all header components are displayed")
    public boolean validateHeaderComponentsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(contactsLink));
            wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
            wait.until(ExpectedConditions.visibilityOfElementLocated(userIcon));
            wait.until(ExpectedConditions.visibilityOfElementLocated(settingsIcon));
            wait.until(ExpectedConditions.visibilityOfElementLocated(logOutLink));
            return driver.findElement(contactsLink).isDisplayed()
                && driver.findElement(createButton).isDisplayed()
                && driver.findElement(userIcon).isDisplayed()
                && driver.findElement(settingsIcon).isDisplayed()
                && driver.findElement(logOutLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
