package pages;

import org.openqa.selenium.WebDriver;

public class ThankYouPage {
    private WebDriver driver;

    public ThankYouPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAtThankYouPage() {
        // Implement logic to verify at thank you page
        return true;
    }

    public boolean hasGoToMyASTMButton() {
        // Implement logic to verify Go To MyASTM button
        return true;
    }

    public boolean hasThankYouText() {
        // Implement logic to verify thank you text
        return true;
    }

    public boolean isConfirmationPopupDisplayed() {
        // Implement logic to verify confirmation pop-up
        return true;
    }

    public String getConfirmationPopupText() {
        // Implement logic to get confirmation pop-up text
        return "Thank you Your Request has been Submitted. The changes may take 1-3 business days to process and appear on your account";
    }

    public void clickConfirmationOk() {
        // Implement logic to click Ok on confirmation pop-up
    }
}
