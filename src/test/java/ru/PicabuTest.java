package ru;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class PicabuTest {
    WebDriver driver;

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
        driver.findElement(By.xpath("//textarea[contains(@class,\"gLFyf\")]")).sendKeys("Сайт авиакомпании Победа");
        driver.findElement(By.xpath("//textarea[contains(@class,\"gLFyf\")]")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//*[@id=\"rso\"]/div[2]/div[1]/div/div/div/div[1]/div/div/span/a/div/div/div/div[2]/cite")).click();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div/div[1]/button[4]/div[2]/div/div"));
        waitForVisibilityOfElement(element);
        wait.until(elementToBeClickable(driver.findElement
                (By.xpath("//*[@id=\"__next\"]/div[2]/header/div/div/div[1]/div[2]/button[1]"))));
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/header/div/div/div[1]/div[2]/button[1]")).click();
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

    @After
    public void tearDown() {
        driver.quit();
    }


}
