package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupLoginFlowTests {

  private String baseURL;
  @LocalServerPort private int port;
  private WebDriver driver;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void beforeEach() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    baseURL = "http://localhost:" + port;
  }

  @AfterEach
  public void afterEach() {
    if (driver != null) {
      driver.quit();
    }
  }

  void loginUser(WebDriver webDriver, String url) {
    String firstName = "Daniel";
    String lastName = "Pereira";
    String username = "daniel";
    String password = "verysecurepassword12";

    webDriver.get(url + "/signup");

    SignupPage signupPage = new SignupPage(webDriver);
    signupPage.signup(firstName, lastName, username, password);

    webDriver.get(url + "/login");

    LoginPage loginPage = new LoginPage(webDriver);
    loginPage.login(username, password);
  }

  @Test
  public void homePageIsNotAccessibleWithoutLoggingIn() {
    driver.get(baseURL + "/home");
    Assertions.assertEquals("Login", driver.getTitle());
  }

  @Test
  public void signupAndLoginFlow() {
    loginUser(driver, baseURL);

    Assertions.assertEquals("Home", driver.getTitle());

    HomePage homePage = new HomePage(driver);
    driver.get(baseURL + "/home");
    homePage.logout();

    Assertions.assertEquals("Login", driver.getTitle());
  }
}
