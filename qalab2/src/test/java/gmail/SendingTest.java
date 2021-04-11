package gmail;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SendingTest {
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com");
    }

    @Test
    public void checkEmailSending() {
        WebElement mailField = driver.findElement(By.id("identifierId"));
        mailField.sendKeys("test2.1.2.2.w@gmail.com", Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(driver.findElement(By.name("password"))));
        WebElement passField = driver.findElement(By.name("password"));
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        passField.sendKeys("qwertyip1",Keys.ENTER);
        driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
        WebElement addressField = driver.findElement(By.cssSelector("textarea.vO"));
        addressField.sendKeys("my.test.0.0.1.00@gmail.com");
        driver.findElement(By.id(":9m")).sendKeys("letter for test");
        driver.findElement(By.xpath("//div[@id=':87']")).click();
        WebElement sendingConfirmation = driver.findElement(By.xpath("//span[text()='Лист надіслано.']"));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(sendingConfirmation));
        Assert.assertTrue(sendingConfirmation.isDisplayed());

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
