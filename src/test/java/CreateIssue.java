import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreateIssuePage;
import pages.HomePage;
import pages.LoginPage;
import pages.TicketPage;
import utils.WebDriverFactory;
import static org.testng.Assert.assertTrue;

public class CreateIssue {

  WebDriver driver = null;
  LoginPage loginPage = null;
  HomePage homePage = null;
  CreateIssuePage createIssuePage = null;
  TicketPage ticketPage = null;
  String name = "IrynaKapustina";
  String password = "IrynaKapustina";
  String projectName = "Webinar (WEBINAR)";
  String issueType = "Задача";
  String summary = "Some summary";
  String reporter = "IrynaKapustina";

  @BeforeMethod
  public void setUp() {
    // Open the browser
    WebDriverFactory.createInstance("Chrome");
    driver = WebDriverFactory.getDriver();
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
    createIssuePage = new CreateIssuePage(driver);
    ticketPage = new TicketPage(driver);
  }

    @Test
    public void createIssue() {
      loginPage.loginJira(name, password);
      assertTrue(homePage.userNameIsPresent());

      homePage.clickCreate();
      assertTrue(createIssuePage.projectFieldIsClickable());

      createIssuePage.fillOutProjectName(projectName);
      assertTrue(createIssuePage.issueTypeFieldIsClickable());

      createIssuePage.fillOutIssueType(issueType);
      assertTrue(createIssuePage.summaryFieldIsClickable());

      createIssuePage.enterSummaryName(summary);


      assertTrue(createIssuePage.reporterFieldIsClickable());
      createIssuePage.fillOutReporter(reporter);
      createIssuePage.clickSubmit();

      assertTrue(createIssuePage.popUpIssueName());

    }

    @AfterMethod()
    public void tearDown() {
      WebDriverFactory.getDriver().quit();
    }

  }