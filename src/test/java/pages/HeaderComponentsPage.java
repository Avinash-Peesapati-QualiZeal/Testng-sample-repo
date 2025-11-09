package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components (placeholders where actual locators are not provided)
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.xpath("//button[contains(@class, 'linkedin button') and contains(text(), 'Create')]");
    private By trashIconButton = By.xpath("//button[contains(@class, 'button icon')]//i[contains(@class, 'trash icon')]");
    private By settingsIcon = By.xpath("//i[contains(@class, 'settings icon')]"); // TODO: Replace with actual locator if needed
    private By logOutLink = By.linkText("Log Out");

    // Constructor
    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Validate that all expected header components are displayed.
     * This includes Contacts link, Create button, Trash icon, Settings icon, and Log Out link.
     * Throws AssertionError if any component is missing.
     */
    @Step("Validate header components are visible")
    public void validateHeaderComponentsVisible() {
        // Validate Contacts link
        WebElement contacts = driver.findElement(contactsLink);
        if (!contacts.isDisplayed()) {
            throw new AssertionError("Contacts link is not visible in the header");
        }

        // Validate Create button
        WebElement createBtn = driver.findElement(createButton);
        if (!createBtn.isDisplayed()) {
            throw new AssertionError("Create button is not visible in the header");
        }

        // Validate Trash icon button
        WebElement trashBtn = driver.findElement(trashIconButton);
        if (!trashBtn.isDisplayed()) {
            throw new AssertionError("Trash icon button is not visible in the header");
        }

        // Validate Settings icon
        WebElement settings = driver.findElement(settingsIcon);
        if (!settings.isDisplayed()) {
            throw new AssertionError("Settings icon is not visible in the header");
        }

        // Validate Log Out link
        WebElement logout = driver.findElement(logOutLink);
        if (!logout.isDisplayed()) {
            throw new AssertionError("Log Out link is not visible in the header");
        }
    }
}
