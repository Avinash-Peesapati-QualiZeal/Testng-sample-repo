// Page Object for the Account List Page
import { Page } from '@playwright/test';

export class AccountListPage {
  readonly page: Page;
  constructor(page: Page) {
    this.page = page;
  }

  async waitForAccountListLoaded() {
    // Implement wait logic for account list to be visible
    await this.page.waitForSelector('[data-test="account-list"]');
  }

  async searchAccount(accountName: string) {
    // Implement search logic using the search box
    await this.page.fill('[data-test="account-search-input"]', accountName);
    await this.page.press('[data-test="account-search-input"]', 'Enter');
    await this.page.waitForTimeout(1000); // Wait for results to load
  }

  async selectAccountByName(accountName: string) {
    // Click on the account with the given name
    await this.page.click(`text=${accountName}`);
  }
}
