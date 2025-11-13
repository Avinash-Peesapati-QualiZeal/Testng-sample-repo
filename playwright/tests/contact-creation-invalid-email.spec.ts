// playwright/tests/contact-creation-invalid-email.spec.ts
import { test, expect } from '@playwright/test';
import { LoginPage } from '../pages/login-page';
import { HomePage } from '../pages/home-page';
import { ContactCreationPage } from '../pages/contact-creation-page';
import { invalidEmailTestData } from '../test-data/contact-creation-invalid-email-data.json';

// Test Case: TC-N002 - Invalid Email Format Rejection
test.describe('Contact Creation - Invalid Email Format Rejection', () => {
  test.beforeEach(async ({ page }) => {
    // Login as a valid user using Page Object methods
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.loginWithValidCredentials(); // Assumes method exists for valid login
    // Navigate to Contact Creation screen using HomePage Page Object
    const homePage = new HomePage(page);
    await homePage.navigateToContactCreation(); // Assumes method exists
  });

  for (const data of invalidEmailTestData) {
    test(`should reject invalid email format: ${data.invalidEmail}`, async ({ page }) => {
      const contactCreationPage = new ContactCreationPage(page);
      // Fill the contact form with invalid email using Page Object methods
      await contactCreationPage.enterFirstName(data.firstName);
      await contactCreationPage.enterLastName(data.lastName);
      await contactCreationPage.enterEmail(data.invalidEmail);
      // Fill other required fields if necessary using Page Object methods
      if (data.phoneNumber) {
        await contactCreationPage.enterPhoneNumber(data.phoneNumber);
      }
      if (data.company) {
        await contactCreationPage.enterCompany(data.company);
      }
      // Attempt to save the contact
      await contactCreationPage.clickSave();
      // Assert error message is displayed for invalid email
      await expect(await contactCreationPage.getEmailErrorMessage()).toContain('invalid');
      // Optionally, assert that the contact is not saved (e.g., check for absence in list or confirmation)
      await expect(await contactCreationPage.isContactSaved(data.invalidEmail)).toBeFalsy();
    });
  }
});
