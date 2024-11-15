package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    By userName = By.name("uid");
    By password = By.name("password");
    By titleText = By.className("barone");
    By login = By.name("btnLogin");
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    // Заполняем поле ввода логина userName значением strUserName
    public void setUserName(String strUserName){
        driver.findElement(userName).sendKeys(strUserName);
    }
    // Заполняем поле ввода пароля password значением strPassword
    public void setPassword(String strPassword){
        driver.findElement(password).sendKeys(strPassword);
    }
    // Клик по кнопке логина
    public void clickLogin(){
        driver.findElement(login).click();
    }
}
