package Lesson6.HW6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MailRuTest {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUpBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        driver.get("https://mail.ru/");
    }

    @Test
    public void checkingUnreadMessagesUsingAFilter() throws InterruptedException {
        login();

        new UsingFilter(driver)
                .findFilterPage()
                .useFilterPage();

        WebElement checkMessage = driver.findElement(By.xpath("//div[@class='octopus__text']"));
        assertThat(checkMessage.getText(), equalTo("Писем нет"));
    }

    @Test
    public void checkSendMessage() throws InterruptedException {
        login();
        Thread.sleep(5000);
        new FillAndSentMessage(driver)
                .createNewMessage()
                .writeToWhomMessage("albuzik97@gmail.com")
                .writeThemeMessage("TestWriter")
                .writeBodyMessage("TestWriter")
                .sentMessage();

                WebElement checkMessage = driver.findElement(By.xpath("//div[@class='layer__header']"));
        assertThat(checkMessage.getText(), equalTo("Письмо отправлено"));
    }


    void login() throws InterruptedException {
        new LoginPage(driver)
                .fillLogin("test1234522@mail.ru")
                .buttonPassword()
                .fillPassword("sUuRIayp1A9(")
                .submitLogin();
        Thread.sleep(10000);
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }
}