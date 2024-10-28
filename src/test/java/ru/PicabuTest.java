package ru;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PicabuTest {
    WebDriver driver;
    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://pikabu.ru/");
    }
    @Test
    public void picabuTest() {

        // System.setProperty("webdriver.chrome.driver", "C:\\My Drivers\\selenium-java-4.25.0 (1)\\chromedriver");

        String title = driver.getTitle();
        Assert.assertEquals(driver.getTitle(),title);
        driver.findElement(By.xpath("/html/body/div[1]/header/div[2]/div/div/div[3]/div/button")).click();
        driver.findElement(By.xpath("//*[@id=\"signin-form\"]/div[1]/div/div/input")).sendKeys("Login");
        driver.findElement(By.xpath("//*[@id=\"signin-form\"]/div[2]/div/div/input")).sendKeys("Password");
        String responce = "Необходимо войти или зарегистрироваться1";
        Assert.assertEquals(driver.findElement(By.xpath("//div[1][contains(@class,\"auth__notice\")]")).getText(), responce);


    }

@After
    public void tearDown(){
    driver.quit();
}



}
