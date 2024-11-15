package pom;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Locale;

public class AutorizeTest {
    String driverPath = "C:\\geckodriver.exe";
    WebDriver driver;
    LoginPage objLogin;
    ProfilePage objHomePage;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://demo.guru99.com/V4/");
    }

    @Test
    public void testSuccessfullLogin() {
        // Создаем экземпляр объекта страницы логина и страницы профиля
        objLogin = new LoginPage(driver);
        objHomePage = new ProfilePage(driver);
        // Проверяем заголовок страницы
        String loginPageTitle = objLogin.getLoginTitle();
        Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
        // входим под аккаунтом
        objLogin.loginToGuru99("mgr123", "mgr!23");
        // Проверяем, что действительно попали на страницу профиля
        Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));
    }
}
