// Page Object for the Account (Client) Page
import { Page } from '@playwright/test';

export class AccountPage {
  readonly page: Page;
  constructor(page: Page) {
    this.page = page;
  }

  async waitForAccountPageLoaded() {
    // Wait for the account page to be loaded (e.g., heading visible)
    await this.page.waitForSelector('[data-test="account-name-heading"]');
  }

  async getAccountName(): Promise<string> {
    // Get the displayed account name
    return await this.page.textContent('[data-test="account-name-heading"]');
  }
}
