package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'linkedin button') and text()='Create']");
    private By trashIconButton = By.xpath("//button[contains(@class, 'ui button icon')]/i[@class='trash icon']");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By logOutLink = By.xpath("//a[contains(@class, 'item')]/i[@class='power icon']/following-sibling::span[text()='Log Out']");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validates that all header components are visible to the user after login.
     * This includes Contacts link, Create button, Trash icon, Settings icon, and Log Out link.
     */
    @Step("Validate header components are visible")
    public boolean validateHeaderComponentsVisible() {
        try {
            boolean contactsVisible = driver.findElement(contactsLink).isDisplayed();
            boolean createBtnVisible = driver.findElement(createButton).isDisplayed();
            boolean trashIconVisible = driver.findElement(trashIconButton).isDisplayed();
            boolean settingsIconVisible = driver.findElement(settingsIcon).isDisplayed();
            boolean logOutVisible = driver.findElement(logOutLink).isDisplayed();
            return contactsVisible && createBtnVisible && trashIconVisible && settingsIconVisible && logOutVisible;
        } catch (Exception e) {
            return false;
        }
    }
}
