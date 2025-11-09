package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[text()='Create']");
    private By userIcon = By.cssSelector("i.large.user.red.icon");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By logOutLink = By.linkText("Log Out");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validate that all header components are displayed successfully.
     * This method checks for the presence of Contacts link, Create button, User icon, Settings icon, and Log Out link.
     */
    @Step("Validate header components are visible on the page")
    public boolean validateHeaderComponentsVisible() {
        try {
            boolean contactsVisible = driver.findElement(contactsLink).isDisplayed();
            boolean createVisible = driver.findElement(createButton).isDisplayed();
            boolean userIconVisible = driver.findElement(userIcon).isDisplayed();
            boolean settingsIconVisible = driver.findElement(settingsIcon).isDisplayed();
            boolean logOutVisible = driver.findElement(logOutLink).isDisplayed();
            return contactsVisible && createVisible && userIconVisible && settingsIconVisible && logOutVisible;
        } catch (Exception e) {
            return false;
        }
    }
}
