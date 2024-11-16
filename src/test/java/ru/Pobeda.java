package ru;

import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Pobeda {
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;
    String pobeda = "Сайт авиакомпании Победа";

    @BeforeMethod
            (alwaysRun = true)
    public void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://google.com/");

    }

    @Test
    public void pobeda() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        enterPobeda(pobeda);
        driver.findElement(By.xpath("//textarea[contains(@class,\"gLFyf\")]")).sendKeys(Keys.ENTER); // кликаю именно pobeda.aero
        wait.until(visibilityOf(driver.findElement(By.xpath("//a[contains(@href,\"https://www.pobeda.aero/ru/\")]")))).click(); // ищу именно pobeda.aero
        clickLogin();
        element = driver.findElement(By.xpath("//div[contains(@class,\"dp-13gqklo-root\")][.=\"Калининград\"]"));// ищу Калининград
        waitForVisibilityOfElement(element);
        wait.until(visibilityOf(driver.findElement(By.xpath("//div[@class=\"dp-4ksyid-root-root\"]/div[@class=\"dp-qq7t6o-root\"]"))));// ищу кнопку переклбчения языков
        driver.findElement(By.xpath("//div[@class=\"dp-4ksyid-root-root\"]/div[@class=\"dp-qq7t6o-root\"]")).click();
        wait.until(visibilityOf(driver.findElement(By.xpath("//div[.=\"English\"]"))));// ищу кнопку переключения на английский
        driver.findElement(By.xpath("//div[.=\"English\"]")).click();
        wait.until((visibilityOf(driver.findElement(By.xpath("//div[@class = \"dp-YpbSQV-textVisible-ref dp-1sr61pz-root-textVisible\"][.=\"Ticket search\"]")))));// кнопка выбора Buy ticket
    }

    public static void waitForVisibilityOfElement(WebElement element) {
        float waitingTime = 0;
        float MAX_WAITING_TIME = 600000;
        float startLoadingTime = System.currentTimeMillis();
        while (!element.isDisplayed()) {
            if (waitingTime <= MAX_WAITING_TIME) {
                waitingTime = System.currentTimeMillis() - startLoadingTime;
            } else {
                System.out.println("No Kaliningrad within time limit");
                break;
            }
        }
        if (element.isDisplayed()) {
            System.out.println("We've waited for Kaliningrad for " + waitingTime + " seconds");
        }
    }
    public void enterPobeda(String pobeda){
        driver.findElement(By.xpath("//textarea[contains(@class,\"gLFyf\")]")).sendKeys(pobeda);
    }
    public void clickLogin(){
        driver.findElement(By.xpath("//a[contains(@class,\"dp-410c1w-root-root-root\")]"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void enterTextToGoogleSelenide(){
        Selenide.open("https://google.com/");
        $(By.xpath("//textarea[contains(@class,\"gLFyf\")]")).pressEnter();
    }
}
