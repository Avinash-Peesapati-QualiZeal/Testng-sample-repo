// ADD THESE NEW METHODS TO EXISTING CLASS - DO NOT MODIFY EXISTING CODE

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import pages.ContactCreationPage;
import static org.testng.Assert.assertTrue;

public class ContactCreationTest {

    private ContactCreationPage contactCreationPage;
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/invalid-email-format-rejection-data.json";

    @BeforeMethod(alwaysRun = true)
    public void beforeInvalidEmailTest() {
        // Assumes user is already logged in and navigated to contact creation screen as per preconditions.
        // If navigation is required, add here.
    }

    @DataProvider(name = "invalidEmailDataProvider")
    public Object[][] invalidEmailDataProvider() {
        List<Object[]> data = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(TEST_DATA_PATH));
            JSONArray testDataArray = (JSONArray) jsonObject.get("testData");
            Iterator<?> iterator = testDataArray.iterator();
            while (iterator.hasNext()) {
                JSONObject testData = (JSONObject) iterator.next();
                String name = (String) testData.get("name");
                String email = (String) testData.get("email");
                String phone = (String) testData.get("phone");
                String expectedError = (String) testData.get("expectedError");
                // For this CRM, let's assume firstName = name (if only one field), lastName = "Test" (fallback), emailType = "Personal", category = "Lead"
                String firstName = name;
                String lastName = "Test";
                String emailType = "Personal";
                String category = "Lead";
                data.add(new Object[]{firstName, lastName, email, emailType, category, expectedError});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toArray(new Object[0][0]);
    }

    @Test(dataProvider = "invalidEmailDataProvider", description = "TC-N002: Invalid Email Format Rejection")
    @Description("Verify system rejects invalid email formats and displays appropriate error message.")
    @Severity(SeverityLevel.NORMAL)
    public void testInvalidEmailFormatRejection(String firstName, String lastName, String invalidEmail, String emailType, String category, String expectedError) {
        try {
            contactCreationPage = page.getInstance(ContactCreationPage.class);
            contactCreationPage.createContactWithInvalidEmail(firstName, lastName, invalidEmail, emailType, category);
            assertTrue(contactCreationPage.isInvalidEmailErrorDisplayed(),
                "Expected error message for invalid email format was not displayed for input: " + invalidEmail);
            // Optionally, verify that no confirmation message is shown (no contact saved)
            assertTrue(!contactCreationPage.isConfirmationMessageDisplayed(),
                "Contact should not be saved for invalid email: " + invalidEmail);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false, "Exception occurred during invalid email format rejection test: " + e.getMessage());
        }
    }
}
