package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests {

  private String baseURL;
  @LocalServerPort private int port;
  private WebDriver driver;
  private HomePage homePage;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void beforeEach() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    baseURL = "http://localhost:" + port;
    homePage = new HomePage(driver);
  }

  @AfterEach
  public void afterEach() {
    if (driver != null) {
      driver.quit();
    }
  }

  private void createCredential() {
    String url = "www.secret.com";
    String username = "secret";
    String password = "securePassword123!";
    SignupLoginFlowTests signupFlow = new SignupLoginFlowTests();

    signupFlow.loginUser(driver, baseURL);

    homePage.createCredential(url, username, password);
  }

  @Test
  public void isCredentialCreated() {
    createCredential();

    Assertions.assertTrue(homePage.isCredentialDetailsVisible());
  }

  @Test
  public void isCredentialEdited() {
    String url = "www.newsecret.com";
    String username = "newsecret";
    String password = "newsecurePassword123!";

    createCredential();

    homePage.editCredential(url, username, password);

    Assertions.assertEquals(url, homePage.getTableCredentialUrl().getText());
    Assertions.assertEquals(username, homePage.getTableCredentialUsername().getText());
    Assertions.assertEquals(password, homePage.getTableCredentialPassword().getText());
  }

  @Test
  public void isCredentialDeleted() {
    createCredential();

    homePage.deleteCredential();

    Assertions.assertFalse(homePage.isCredentialPresent());
  }
}
