package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class HeaderComponentsPage {
    private WebDriver driver;

    // Locators for header components (placeholders where necessary)
    private By contactsLink = By.linkText("Contacts");
    private By createButton = By.className("ui linkedin button");
    private By logOutLink = By.partialLinkText("Log Out");
    private By settingsIcon = By.className("settings icon"); // TODO: Replace with actual locator if needed
    private By userIcon = By.className("large user red icon"); // TODO: Replace with actual locator if needed

    public HeaderComponentsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Validate that all header components are visible and accessible")
    public boolean validateHeaderComponents() {
        try {
            // Validate Contacts link
            WebElement contacts = driver.findElement(contactsLink);
            if (!contacts.isDisplayed()) return false;

            // Validate Create button
            WebElement createBtn = driver.findElement(createButton);
            if (!createBtn.isDisplayed()) return false;

            // Validate Log Out link
            WebElement logout = driver.findElement(logOutLink);
            if (!logout.isDisplayed()) return false;

            // Validate Settings icon (optional, placeholder)
            WebElement settings = driver.findElement(settingsIcon);
            if (!settings.isDisplayed()) return false;

            // Validate User icon (optional, placeholder)
            WebElement user = driver.findElement(userIcon);
            if (!user.isDisplayed()) return false;

            return true;
        } catch (Exception e) {
            // Log exception if needed
            return false;
        }
    }
}
