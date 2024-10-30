package ru;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class PicabuTest {
    WebDriver driver;
    @BeforeMethod
            (alwaysRun = true)
    public void setUpDriver(){
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
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
        driver.findElement(By.xpath("//cite.qLRx3b.tjvcx.GvPZzd.dTxz9.cHaqb")).click();

        /*
        System.setProperty("webdriver.chrome.driver", "C:\\My Drivers\\selenium-java-4.25.0 (1)\\chromedriver");

        String title = driver.getTitle();
        Assert.assertEquals(driver.getTitle(),title);
        driver.findElement(By.xpath("/html/body/div[1]/header/div[2]/div/div/div[3]/div/button")).click();
        driver.findElement(By.xpath("//*[@id=\"signin-form\"]/div[1]/div/div/input")).sendKeys("Login");
        driver.findElement(By.xpath("//*[@id=\"signin-form\"]/div[2]/div/div/input")).sendKeys("Password");
        String responce = "Необходимо войти или зарегистрироваться1";
        Assert.assertEquals(driver.findElement(By.xpath("//div[1][contains(@class,\"auth__notice\")]")).getText(), responce);


         */

    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
