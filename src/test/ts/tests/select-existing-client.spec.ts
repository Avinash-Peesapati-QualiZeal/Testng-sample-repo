// Playwright test for: TC_002 - Verify User is able select an existing client from the list
import { test, expect } from '@playwright/test';
import { LoginPage } from '../pages/login-page';
import { HomePage } from '../pages/home-page';
import { AccountListPage } from '../pages/account-list-page';
import { AccountPage } from '../pages/account-page';
import { testData } from '../test-data/select-existing-client-data.json';

// Test Case: TC_002 - Verify User is able select an existing client from the list
test.describe('TC_002: Select Existing Client from Account List', () => {
  let loginPage: LoginPage;
  let homePage: HomePage;
  let accountListPage: AccountListPage;
  let accountPage: AccountPage;

  test.beforeEach(async ({ page }) => {
    loginPage = new LoginPage(page);
    homePage = new HomePage(page);
    accountListPage = new AccountListPage(page);
    accountPage = new AccountPage(page);
    await page.goto(testData.salesforceUrl);
  });

  test('User can select an existing client from the account list', async ({ page }) => {
    // Step 1: Login
    await loginPage.enterUsername(testData.username);
    await loginPage.enterPassword(testData.password);
    await loginPage.clickLogin();
    await homePage.waitForHomePageLoaded();

    // Step 2: Navigate to Accounts
    await homePage.navigateToAccounts();
    await accountListPage.waitForAccountListLoaded();

    // Step 3: Search and select existing client
    await accountListPage.searchAccount(testData.clientName);
    await accountListPage.selectAccountByName(testData.clientName);
    await accountPage.waitForAccountPageLoaded();

    // Step 4: Assert navigation to correct client account page
    const displayedAccountName = await accountPage.getAccountName();
    expect(displayedAccountName).toContain(testData.clientName);
  });
});
