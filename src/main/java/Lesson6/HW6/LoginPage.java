package Lesson6.HW6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseView {

    @FindBy(xpath = "//input[contains(@name, 'login') and @placeholder='Имя ящика']")
    public WebElement inputLogin;

    @FindBy(xpath = "//button[contains(@data-testid, 'enter-password')]")
    public WebElement buttonPassword;

    @FindBy(xpath = "//input[contains(@name, 'password')]")
    public WebElement inputPassword;

    @FindBy(xpath = "//button[contains(@data-testid, 'login-to-mail')]")
    public WebElement buttonLogin;

    public LoginPage fillLogin(String login) {
        inputLogin.sendKeys(login);
        return this;
    }

    public LoginPage buttonPassword() throws InterruptedException {
        buttonPassword.click();
        Thread.sleep(2000);
        return this;
    }

    public LoginPage fillPassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    public void submitLogin() {
        buttonLogin.click();
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
