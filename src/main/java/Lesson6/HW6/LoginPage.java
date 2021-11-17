package Lesson6.HW6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseView {

    @FindBy(xpath = "//input[contains(@name, 'login') and @placeholder='Имя ящика']")
    public WebElement inputLogin;

    @Step("Заполнить поле логина")
    public LoginPage fillLogin(String login) {
        inputLogin.sendKeys(login);
        return this;
    }

    @FindBy(xpath = "//button[contains(@data-testid, 'enter-password')]")
    public WebElement buttonPassword;

    @Step("Нажать на кнопку ввода пароля")
    public LoginPage buttonPassword() throws InterruptedException {
        buttonPassword.click();
        Thread.sleep(2000);
        return this;
    }

    @FindBy(xpath = "//input[contains(@name, 'password')]")
    public WebElement inputPassword;
    @Step("Заполнить поле пароль")
    public LoginPage fillPassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    @FindBy(xpath = "//button[contains(@data-testid, 'login-to-mail')]")
    public WebElement buttonLogin;

    @Step("Нажать на кнопку войти")
    public void submitLogin() {
        buttonLogin.click();
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
