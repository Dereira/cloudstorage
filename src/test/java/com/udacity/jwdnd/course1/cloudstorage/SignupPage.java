package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class SignupPage {
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
    PageFactory.initElements(webDriver, this);
  }

  void signup(String firstName, String lastName, String username, String password) {
    firstNameField.sendKeys(firstName);
    lastNameField.sendKeys(lastName);
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    submitButton.click();
  }
}
