package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTests {

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

  private void createNote() {
    String title = "Test Title";
    String description = "Test description";
    SignupLoginFlowTests signupFlow = new SignupLoginFlowTests();

    signupFlow.loginUser(driver, baseURL);

    homePage.createNote(title, description);
  }

  @Test
  public void isNoteCreated() {
    createNote();

    Assertions.assertTrue(homePage.isNoteDetailsVisible());
  }

  @Test
  public void isNoteEdited() {
    String title = "Changed title";
    String description = "Changed description";

    createNote();

    homePage.editNote(title, description);

    Assertions.assertEquals(title, homePage.getTableNoteTitle().getText());
    Assertions.assertEquals(description, homePage.getTableNoteDescription().getText());
  }

  @Test
  public void isNoteDeleted() {
    createNote();

    homePage.deleteNote();

    Assertions.assertFalse(homePage.isNotePresent());
  }
}
