package Lesson6.HW6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FillAndSentMessage extends BaseView {

    public FillAndSentMessage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@class, 'compose-button compose-button_white compose-button_short js-shortcut')]")
    public WebElement buttonNewMessage;

    @FindBy(xpath = "//input[contains(@class, 'container--H9L5q size_s--3_M-_')]")
    public WebElement fillToWhomMessage;

    @FindBy(xpath = "//input[contains(@name, 'Subject')]")
    public WebElement fillThemeMessage;

    @FindBy(xpath = "//div[contains(@role, 'textbox')]")
    public WebElement fillBodyMessage;

    @FindBy(xpath = "//span[contains(., 'Отправить')]")
    public WebElement buttonSentMessage;

    @Step("Нажать кнопку создания новых сообщений")
    public FillAndSentMessage createNewMessage() throws InterruptedException {
        buttonNewMessage.click();
        Thread.sleep(5000);
        return this;
    }

    @Step("Заполнить поле\"Кому\"")
    public FillAndSentMessage writeToWhomMessage(String toWhomMessage){
        fillToWhomMessage.sendKeys(toWhomMessage);
        return this;
    }

    @Step("Заполнить поле\"Тема\"")
    public FillAndSentMessage writeThemeMessage(String themeMessage){
        fillThemeMessage.sendKeys(themeMessage);
        return this;
    }

    @Step("Заполнить поле тела сообщения")
    public FillAndSentMessage writeBodyMessage(String bodyMessage){
        fillBodyMessage.sendKeys(bodyMessage);
        return this;
    }

    @Step("Нажать на кнопку \"Отправить сообщение\"")
    public void sentMessage() throws InterruptedException {
        buttonSentMessage.click();
        Thread.sleep(10000);
    }

}
