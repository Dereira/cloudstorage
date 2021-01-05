package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTests {

  private String baseURL;
  @LocalServerPort private int port;
  private WebDriver driver;

  @BeforeAll
  static void beforeAll() {
    System.setProperty(
        "webdriver.firefox.bin",
        "/Applications/Firefox Developer Edition.app/Contents/MacOS/firefox-bin");
    WebDriverManager.firefoxdriver().setup();
  }

  @BeforeEach
  public void beforeEach() {
    driver = new FirefoxDriver();
    baseURL = "http://localhost:" + port;
  }

  @AfterEach
  public void afterEach() {
    if (driver != null) {
      driver.quit();
    }
  }

  private void createNote() {
    String title = "Test Title";
    String description = "Test description";
    SignupLoginFlowTests signupFlow = new SignupLoginFlowTests();

    signupFlow.loginUser(driver, baseURL);

    HomePage homePage = new HomePage(driver);
    homePage.createNote(title, description);
  }

  @Test
  public void isNoteCreated() {
    createNote();

    HomePage homePage = new HomePage(driver);

    Assertions.assertTrue(homePage.isNoteDetailsVisible());
  }

  @Test
  public void isNoteEdited() {
    String title = "Changed title";
    String description = "Changed description";

    createNote();

    HomePage homePage = new HomePage(driver);
    homePage.editNote(title, description);

    Assertions.assertEquals(title, homePage.getTableNoteTitle().getText());
    Assertions.assertEquals(description, homePage.getTableNoteDescription().getText());
  }

  @Test
  public void isNoteDeleted() {
    createNote();

    HomePage homePage = new HomePage(driver);

    homePage.deleteNote();

    Assertions.assertFalse(homePage.isNotePresent());
  }
}
