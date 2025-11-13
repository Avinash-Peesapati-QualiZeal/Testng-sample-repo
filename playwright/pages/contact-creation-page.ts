// playwright/pages/contact-creation-page.ts
import { Page } from '@playwright/test';

export class ContactCreationPage {
  readonly page: Page;
  constructor(page: Page) {
    this.page = page;
  }

  async enterFirstName(firstName: string) {
    await this.page.fill('[data-test=first-name-input]', firstName);
  }

  async enterLastName(lastName: string) {
    await this.page.fill('[data-test=last-name-input]', lastName);
  }

  async enterEmail(email: string) {
    await this.page.fill('[data-test=email-input]', email);
  }

  async enterPhoneNumber(phoneNumber: string) {
    await this.page.fill('[data-test=phone-input]', phoneNumber);
  }

  async enterCompany(company: string) {
    await this.page.fill('[data-test=company-input]', company);
  }

  async clickSave() {
    await this.page.click('[data-test=save-contact-button]');
  }

  async getEmailErrorMessage(): Promise<string> {
    // Wait for error message to appear
    await this.page.waitForSelector('[data-test=email-error-message]', { state: 'visible' });
    return this.page.textContent('[data-test=email-error-message]');
  }

  async isContactSaved(email: string): Promise<boolean> {
    // Implement logic to check if contact with given email exists in the contact list
    // This is a placeholder; adjust selector as per actual UI
    await this.page.waitForTimeout(1000); // Wait for any UI update
    const contact = await this.page.$(`[data-test=contact-email][data-email='${email}']`);
    return contact !== null;
  }
}
