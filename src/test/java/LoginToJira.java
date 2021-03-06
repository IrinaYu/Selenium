
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.CreateIssuePage;
import pages.HomePage;
import pages.LoginPage;
import pages.TicketPage;
import utils.WebDriverFactory;


import static com.sun.javaws.security.AppPolicy.createInstance;
import static org.testng.Assert.assertTrue;

public class LoginToJira {
  WebDriver driver = null;
  LoginPage loginPage = null;
  HomePage homePage = null;

  @BeforeMethod
  public void setUp() {
    // Open the browser
    WebDriverFactory.createInstance("Chrome");
    driver = WebDriverFactory.getDriver();
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
  }

  @DataProvider(name = "loginNegative")
  public Object[][] createData1() {
    return new Object[][]{
        {"SomeName", "IrynaKapustina", "Sorry, your username and password are incorrect - please try again."},
        {"SomeName", "SomePassword", "Sorry, your username and password are incorrect - please try again."},
        {"IrynaKapustina", "SomePassword", "Sorry, your username and password are incorrect - please try again."},

    };
  }

  @Test(dataProvider = "loginNegative")
  public void unsuccessfulLoginTest(String name, String password, String expectedResult) throws InterruptedException{
    loginPage.navigateTo();
    loginPage.enterUserName(name);
    loginPage.enterPassword(password);
    loginPage.clickLogin();

    //TODO - add check "Sorry, your username and password are incorrect - please try again."
    assertTrue(loginPage.errorMessageIsPresent(expectedResult));
  }

  @Test
  public void successfulLoginTest() {
    loginPage.navigateTo();
    loginPage.enterUserName("IrynaKapustina");
    loginPage.enterPassword("IrynaKapustina");
    loginPage.clickLogin();
    assertTrue(homePage.userNameIsPresent());

    //TODO log out
  }

  @AfterMethod()
  public void tearDown() {
    driver.quit();
  }
}