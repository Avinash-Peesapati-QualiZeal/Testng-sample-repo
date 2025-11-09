package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components (from provided locators and UI analysis)
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'linkedin button') and text()='Create']");
    private By logOutLink = By.xpath("//a[contains(@class, 'item') and .//span[text()='Log Out']]");
    private By settingsIcon = By.cssSelector("i.settings.icon");
    private By userIcon = By.cssSelector("i.large.user.red.icon");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validate that all header components are displayed after successful login.
     * This method checks for the presence and visibility of key header elements.
     */
    @Step("Validate header components are visible to the user")
    public boolean validateHeaderComponents() {
        try {
            boolean isContactsVisible = driver.findElement(contactsLink).isDisplayed();
            boolean isCreateButtonVisible = driver.findElement(createButton).isDisplayed();
            boolean isLogOutVisible = driver.findElement(logOutLink).isDisplayed();
            boolean isSettingsIconVisible = driver.findElement(settingsIcon).isDisplayed();
            boolean isUserIconVisible = driver.findElement(userIcon).isDisplayed();
            return isContactsVisible && isCreateButtonVisible && isLogOutVisible && isSettingsIconVisible && isUserIconVisible;
        } catch (Exception e) {
            return false;
        }
    }
}
