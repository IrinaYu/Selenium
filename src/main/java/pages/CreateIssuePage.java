package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CreateIssuePage {

    private WebDriver driver;

    private By projectNameField = By.id("project-field");
    private By issueTypeField = By.id("issuetype-field");
    private By summaryField = By.id("summary");
    private By reporterField = By.id("reporter-field");
    private By submitButton = By.id("create-issue-submit");
    private By popUpSuccessfulCreate = By.xpath("//*[@id='aui-flag-container']/div");
    private By issueLink = By.cssSelector("#aui-flag-container>div>div>a");
    private By cancelButton = By.cssSelector("div.buttons-container.form-footer > div > a");

    public CreateIssuePage(WebDriver driver) {
        this.driver = driver;
    }

    //waiting until elements are clickable or displayed
    public boolean projectFieldIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
        return wait.until(elementToBeClickable(projectNameField)).isDisplayed();
    }

    public boolean issueTypeFieldIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
        return wait.until(elementToBeClickable(issueTypeField)).isDisplayed();
    }

    public boolean summaryFieldIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
        return wait.until(elementToBeClickable(summaryField)).isDisplayed();
    }

    public boolean reporterFieldIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
        return wait.until(elementToBeClickable(reporterField)).isDisplayed();
    }

    public boolean popUpIssueName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
        return wait.until(presenceOfElementLocated(popUpSuccessfulCreate)).isDisplayed();
    }

    public boolean cancelButtonIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
        return wait.until(presenceOfElementLocated(cancelButton)).isDisplayed();
    }

    //clearing fields
    public void clearProjectField() {
        driver.findElement(projectNameField).clear();
    }

    public void clearIssueTypeField() {
        driver.findElement(issueTypeField).clear();
    }

    public void clearReporterField() {
        driver.findElement(reporterField).clear();
    }

    //sending string keys
    public void enterProjectName(String projectName) {
        driver.findElement(projectNameField).sendKeys(projectName);
    }

    public void enterIssueTypeName(String issueTypeName) {
        driver.findElement(issueTypeField).sendKeys(issueTypeName);
    }

    public void enterSummaryName(String summaryName) {
        driver.findElement(summaryField).sendKeys(summaryName);
    }

    public void enterReporterName(String reporterName) {
        driver.findElement(reporterField).sendKeys(reporterName);
    }

    //sending TAB to fields
    public void tabProjectName() {
        driver.findElement(projectNameField).sendKeys(Keys.TAB);
    }

    public void tabIssueType() {
        driver.findElement(issueTypeField).sendKeys(Keys.TAB);
    }

    public void tabReporter() {
        driver.findElement(reporterField).sendKeys(Keys.TAB);
    }

    //clicking
    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public void clickLinkIssue() {
        driver.findElement(issueLink).click();
    }

    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }

    //methods for each field
    public void fillOutProjectName(String projectName) {
        clearProjectField();
        enterProjectName(projectName);
        tabProjectName();
    }

    public void fillOutIssueType(String issueType) {
        clearIssueTypeField();
        enterIssueTypeName(issueType);
        tabIssueType();
    }

    public void fillOutSummary(String summary) {
        enterSummaryName(summary);
    }

    public void fillOutReporter(String reporter) {
        clearReporterField();
        enterReporterName(reporter);
        tabReporter();
    }

    private void clickOnElementWithRetry(By elementToBeClicked, By successCriteriaElement, int attempts, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        for (int i = 0; i < attempts; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(successCriteriaElement)).isDisplayed();
                break;
            } catch (TimeoutException e) {
                wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
                driver.findElement(elementToBeClicked).click();
            }
            // break - прервёт только цикл
        }

    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public boolean projectFieldIsPresent() {
        try {
            driver.findElement(projectNameField).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
