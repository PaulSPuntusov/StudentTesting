package ru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Google {
    WebDriver driver;
    public Google() {
        this.driver = driver;
    }

    public void setUp(WebDriver driver) {
        driver = new ChromeDriver();
        driver.get("https://google.com/");

    }
}
