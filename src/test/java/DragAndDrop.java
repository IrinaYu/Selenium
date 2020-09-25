import com.codeborne.selenide.impl.WebElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.WebDriverFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class DragAndDrop {
    WebDriver driver = null;


    @BeforeMethod
    public void setUp() {

        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();

    }

    @Test
    public void dragAndDrop() throws InterruptedException {

        String url = "http://demo.guru99.com/test/drag_drop.html";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

        //Get the list of elements
        List<WebElement> elements = driver.findElements(By.cssSelector("#fourth > a"));
        //Get first element from the list
        WebElement from = elements.get(0);

        WebElement to = driver.findElement(By.cssSelector("#amt7"));

        //Actions class method to drag and drop
        Actions builder = new Actions(driver);
        //Perform drag and drop
        builder.dragAndDrop(from, to).perform();

        WebElement check = driver.findElement(By.cssSelector("div#t7"));
        //Verify if WebElement "check" contains needful text
        assertTrue (check.getText().contains("5000"));

    }

    @AfterMethod()
    public void tearDown() {
        driver.quit();
    }
}

