package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.*;
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
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", notesTab);

    waitForVisibility(noteModalButton);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", noteModalButton);

    waitForVisibility(noteModal);
    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + title + "';", noteTitle);
    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + description + "';", noteDescription);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", saveNoteButton);
  }

  Boolean isNoteDetailsVisible() {
    waitForVisibility(notesTab);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", notesTab);

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
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", notesTab);

    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", tableEditNoteButton);
    waitForVisibility(noteModal);

    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + title + "';", noteTitle);

    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + description + "';", noteDescription);

    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", saveNoteButton);
  }

  void deleteNote() {
    waitForVisibility(notesTab);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", notesTab);

    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", tableDeleteNoteButton);

    waitForVisibility(notesTab);
  }

  void createCredential(String url, String username, String password) {
    waitForVisibility(credentialTab);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", credentialTab);

    waitForVisibility(credentialModalButton);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", credentialModalButton);

    waitForVisibility(credentialModal);
    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + url + "';", credentialUrl);
    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + username + "';", credentialUsername);
    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + password + "';", credentialPassword);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", saveCredentialButton);
  }

  boolean isCredentialDetailsVisible() {
    waitForVisibility(credentialTab);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", credentialTab);

    waitForVisibility(tableCredentialUrl);
    waitForVisibility(tableCredentialUsername);
    waitForVisibility(tableCredentialPassword);

    return tableCredentialUrl.isDisplayed()
        && tableCredentialUsername.isDisplayed()
        && tableCredentialPassword.isDisplayed();
  }

  void editCredential(String url, String username, String password) {
    waitForVisibility(credentialTab);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", credentialTab);

    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].click();", tableEditCredentialButton);
    waitForVisibility(credentialModal);

    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + url + "';", credentialUrl);

    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + username + "';", credentialUsername);

    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + password + "';", credentialPassword);

    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", saveCredentialButton);
  }

  void deleteCredential() {
    waitForVisibility(credentialTab);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", credentialTab);

    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].click();", tableDeleteCredentialButton);

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
