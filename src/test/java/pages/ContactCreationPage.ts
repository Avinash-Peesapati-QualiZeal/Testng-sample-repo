// ContactCreationPage.ts
// Page Object for Contact Creation Page (Playwright + TypeScript)
// Follows patterns from ContactCreationPage.java

import { Page, Locator, expect } from '@playwright/test';

export class ContactCreationPage {
  private page: Page;

  // Locators (following descriptive naming and inline pattern from Java PO)
  readonly firstNameInput: Locator;
  readonly lastNameInput: Locator;
  readonly emailAddressInput: Locator;
  readonly emailTypeInput: Locator;
  readonly addEmailButton: Locator;
  readonly categoryDropdown: Locator;
  readonly saveButton: Locator;
  readonly confirmationMessage: Locator;
  // Placeholder for error message locator
  readonly emailFormatErrorMessage: Locator; // TODO: Replace with actual locator

  constructor(page: Page) {
    this.page = page;
    this.firstNameInput = page.locator('[name="first_name"]');
    this.lastNameInput = page.locator('[name="last_name"]');
    this.emailAddressInput = page.locator('input[placeholder="Email address"]');
    this.emailTypeInput = page.locator('input[placeholder="Personal email, Business, Alt..."]');
    this.addEmailButton = page.locator('button.ui.tiny.basic.icon.button i.add.icon');
    this.categoryDropdown = page.locator('div[name="category"]');
    this.saveButton = page.locator('button.ui.linkedin.button i.save.icon');
    this.confirmationMessage = page.locator('div:has-class("confirmation"), div:has-text("Contact saved")');
    this.emailFormatErrorMessage = page.locator('locator("<PLACEHOLDER_email_format_error_message>")'); // TODO: Replace with actual locator
  }

  /**
   * Enter first name
   */
  async enterFirstName(firstName: string): Promise<void> {
    await this.firstNameInput.fill(firstName);
  }

  /**
   * Enter last name
   */
  async enterLastName(lastName: string): Promise<void> {
    await this.lastNameInput.fill(lastName);
  }

  /**
   * Enter email address (can be invalid for negative test)
   */
  async enterEmailAddress(email: string): Promise<void> {
    await this.emailAddressInput.fill(email);
  }

  /**
   * Enter email type (e.g., Personal, Business)
   */
  async enterEmailType(emailType: string): Promise<void> {
    await this.emailTypeInput.fill(emailType);
  }

  /**
   * Click add email button
   */
  async clickAddEmailButton(): Promise<void> {
    await this.addEmailButton.click();
  }

  /**
   * Select category from dropdown
   */
  async selectCategory(category: string): Promise<void> {
    await this.categoryDropdown.click();
    const categoryOption = this.page.locator(`//div[@name='category']//span[text()='${category}']`);
    await categoryOption.click();
  }

  /**
   * Click save button
   */
  async clickSaveButton(): Promise<void> {
    await this.saveButton.click();
  }

  /**
   * Check if confirmation message is displayed (should NOT be for invalid email)
   */
  async isConfirmationMessageDisplayed(): Promise<boolean> {
    return await this.confirmationMessage.isVisible();
  }

  /**
   * Check if email format error message is displayed
   */
  async isEmailFormatErrorMessageDisplayed(): Promise<boolean> {
    // TODO: Replace with actual locator for error message
    return await this.emailFormatErrorMessage.isVisible();
  }

  /**
   * Get email format error message text
   */
  async getEmailFormatErrorMessageText(): Promise<string> {
    // TODO: Replace with actual locator for error message
    return await this.emailFormatErrorMessage.textContent() ?? '';
  }

  /**
   * Complete the negative contact creation flow with invalid email
   */
  async createContactWithInvalidEmail(firstName: string, lastName: string, invalidEmail: string, emailType: string, category: string): Promise<void> {
    await this.enterFirstName(firstName);
    await this.enterLastName(lastName);
    await this.enterEmailAddress(invalidEmail);
    await this.enterEmailType(emailType);
    await this.clickAddEmailButton();
    await this.selectCategory(category);
    await this.clickSaveButton();
  }
}
