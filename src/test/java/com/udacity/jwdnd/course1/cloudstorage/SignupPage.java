package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class SignupPage {

  private final WebDriver webDriver;

  @FindBy(css = "#inputFirstName")
  private WebElement firstNameField;

  @FindBy(css = "#inputLastName")
  private WebElement lastNameField;

  @FindBy(css = "#inputUsername")
  private WebElement usernameField;

  @FindBy(css = "#inputPassword")
  private WebElement passwordField;

  @FindBy(css = "#submit-button")
  private WebElement submitButton;

  SignupPage(WebDriver webDriver) {
    this.webDriver = webDriver;
    PageFactory.initElements(webDriver, this);
  }

  void signup(String firstName, String lastName, String username, String password) {
    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + firstName + "';", firstNameField);
    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + lastName + "';", lastNameField);
    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + username + "';", usernameField);
    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].value='" + password + "';", passwordField);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", submitButton);
  }
}
