package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderPage {
    private WebDriver driver;

    // Locators for header components
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'linkedin button') and text()='Create']");
    private By logOutLink = By.linkText("Log Out");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By userIcon = By.cssSelector("i.large.user.red.icon");

    // Constructor
    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validate that all header components are displayed successfully.
     * This method checks visibility of Contacts link, Create button, Log Out link, Settings icon, and User icon.
     */
    @Step("Validate header components are visible")
    public boolean validateHeaderComponents() {
        try {
            boolean contactsVisible = driver.findElement(contactsLink).isDisplayed();
            boolean createBtnVisible = driver.findElement(createButton).isDisplayed();
            boolean logOutVisible = driver.findElement(logOutLink).isDisplayed();
            boolean settingsIconVisible = driver.findElement(settingsIcon).isDisplayed();
            boolean userIconVisible = driver.findElement(userIcon).isDisplayed();
            return contactsVisible && createBtnVisible && logOutVisible && settingsIconVisible && userIconVisible;
        } catch (Exception e) {
            return false;
        }
    }
}
