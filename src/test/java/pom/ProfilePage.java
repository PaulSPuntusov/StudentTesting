package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    WebDriver driver;
    By userName = By.xpath("//table//tr[@class='heading3]");
    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }
    // Получаем имя юзера с домашней страницы
    public String getHomePageDashboardUserName(){
        return driver.findElement(userName).getText();
    }
}
