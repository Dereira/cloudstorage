package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class HomePage {

  private final WebDriver webDriver;

  @FindBy(xpath = "//*[@id='1-note-title']")
  private WebElement tableNoteTitle;

  @FindBy(xpath = "//*[@id='1-note-description']")
  private WebElement tableNoteDescription;

  @FindBy(xpath = "//*[@id='1-note-edit-button']")
  private WebElement tableEditNoteButton;

  @FindBy(xpath = "//*[@id='1-note-delete-button']")
  private WebElement tableDeleteNoteButton;

  @FindBy(css = "#noteModal")
  private WebElement noteModal;

  @FindBy(css = "#logout-button")
  private WebElement logoutButton;

  @FindBy(css = "#note-modal-button")
  private WebElement noteModalButton;

  @FindBy(css = "#nav-notes-tab")
  private WebElement notesTab;

  @FindBy(css = "#note-title")
  private WebElement noteTitle;

  @FindBy(css = "#note-description")
  private WebElement noteDescription;

  @FindBy(css = "#save-note-button")
  private WebElement saveNoteButton;

  @FindBy(xpath = "//*[@id='1-credential-url']")
  private WebElement tableCredentialUrl;

  @FindBy(xpath = "//*[@id='1-credential-username']")
  private WebElement tableCredentialUsername;

  @FindBy(xpath = "//*[@id='1-credential-password']")
  private WebElement tableCredentialPassword;

  @FindBy(css = "#nav-credentials-tab")
  private WebElement credentialTab;

  @FindBy(css = "#credential-modal-button")
  private WebElement credentialModalButton;

  @FindBy(css = "#credentialModal")
  private WebElement credentialModal;

  @FindBy(css = "#credential-url")
  private WebElement credentialUrl;

  @FindBy(css = "#credential-username")
  private WebElement credentialUsername;

  @FindBy(css = "#credential-password")
  private WebElement credentialPassword;

  @FindBy(css = "#save-credential-button")
  private WebElement saveCredentialButton;

  @FindBy(xpath = "//*[@id='1-credential-edit-button']")
  private WebElement tableEditCredentialButton;

  @FindBy(xpath = "//*[@id='1-credential-edit-button']")
  private WebElement tableDeleteCredentialButton;

  HomePage(WebDriver webDriver) {
    this.webDriver = webDriver;
    PageFactory.initElements(webDriver, this);
  }

  WebElement getTableNoteTitle() {
    return tableNoteTitle;
  }

  WebElement getTableNoteDescription() {
    return tableNoteDescription;
  }

  private void waitForVisibility(WebElement element) throws Error {
    new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOf(element));
  }

  WebElement getTableCredentialUrl() {
    return tableCredentialUrl;
  }

  WebElement getTableCredentialUsername() {
    return tableCredentialUsername;
  }

  WebElement getTableCredentialPassword() {
    return tableCredentialPassword;
  }

  void logout() {
    logoutButton.click();
  }

  void createNote(String title, String description) {
    waitForVisibility(notesTab);
    notesTab.click();

    waitForVisibility(noteModalButton);
    noteModalButton.click();

    waitForVisibility(noteModal);
    noteTitle.sendKeys(title);
    noteDescription.sendKeys(description);
    saveNoteButton.click();
  }

  Boolean isNoteDetailsVisible() {
    waitForVisibility(notesTab);
    notesTab.click();

    waitForVisibility(tableNoteTitle);
    waitForVisibility(tableNoteDescription);

    return tableNoteTitle.isDisplayed() && tableNoteDescription.isDisplayed();
  }

  Boolean isNotePresent() {
    try {
      webDriver.findElement(By.cssSelector("#1-note-title"));
      webDriver.findElement(By.cssSelector("#1-note-description"));
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  void editNote(String title, String description) {
    waitForVisibility(notesTab);
    notesTab.click();

    tableEditNoteButton.click();
    waitForVisibility(noteModal);

    noteTitle.clear();
    noteTitle.sendKeys(title);

    noteDescription.clear();
    noteDescription.sendKeys(description);

    saveNoteButton.click();
  }

  void deleteNote() {
    waitForVisibility(notesTab);
    notesTab.click();

    tableDeleteNoteButton.click();
    waitForVisibility(notesTab);
  }

  void createCredential(String url, String username, String password) {
    waitForVisibility(credentialTab);
    credentialTab.click();

    waitForVisibility(credentialModalButton);
    credentialModalButton.click();

    waitForVisibility(credentialModal);
    credentialUrl.sendKeys(url);
    credentialUsername.sendKeys(username);
    credentialPassword.sendKeys(password);
    saveCredentialButton.click();
  }

  boolean isCredentialDetailsVisible() {
    waitForVisibility(credentialTab);
    credentialTab.click();

    waitForVisibility(tableCredentialUrl);
    waitForVisibility(tableCredentialUsername);
    waitForVisibility(tableCredentialPassword);

    return tableCredentialUrl.isDisplayed()
        && tableCredentialUsername.isDisplayed()
        && tableCredentialPassword.isDisplayed();
  }

  void editCredential(String url, String username, String password) {
    waitForVisibility(credentialTab);
    credentialTab.click();

    tableEditCredentialButton.click();
    waitForVisibility(credentialModal);

    credentialUrl.clear();
    credentialUrl.sendKeys(url);

    credentialUsername.clear();
    credentialUsername.sendKeys(username);

    credentialPassword.clear();
    credentialPassword.sendKeys(password);

    saveCredentialButton.click();
  }

  void deleteCredential() {
    waitForVisibility(credentialTab);
    credentialTab.click();

    tableDeleteCredentialButton.click();
    waitForVisibility(credentialTab);
  }

  boolean isCredentialPresent() {
    try {
      webDriver.findElement(By.cssSelector("#-credential-url"));
      webDriver.findElement(By.cssSelector("#1-credential-username"));
      webDriver.findElement(By.cssSelector("#1-credential-password"));
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}