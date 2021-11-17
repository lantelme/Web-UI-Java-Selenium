package Lesson5.HW5;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MailRuTest {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://mail.ru/");
        login(driver);
    }

    @Test
    public void checkingUnreadMessagesUsingAFilter() throws InterruptedException {

        driver.findElement(
                By.xpath("//div[@class='filters-control filters-control_short filters-control_pure']")).click();
        Wait<WebDriver> customWait = new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(5));
        customWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='list list_hover-support']/div[2]")));
        driver.findElement(
                By.xpath("//div[@class='list list_hover-support']/div[2]")).click();
        //Пришлось указать явно элемент, так как через //span[.='Непрочитанные'] он не захотел
        // искать элемент, даже через ancestor, чтобы добраться до нужного div'а, он все равно его не искал
        //хотя в браузере он находил при вводе xpath в панели разработчика

        WebElement checkMessage = driver.findElement(By.xpath("//div[@class='octopus__text']"));
        assertThat(checkMessage.getText(), equalTo("Писем нет"));
    }

    @Test
    public void checkSendMessage() throws InterruptedException {
        driver.findElement(
                By.xpath("//a[contains(@class, 'compose-button compose-button_white compose-button_short js-shortcut')]")).click();
        Thread.sleep(5000);
        driver.findElement(
                By.xpath("//input[contains(@class, 'container--H9L5q size_s--3_M-_')]")).sendKeys("albuzik97@gmail.com");
        driver.findElement(
                By.xpath("//input[contains(@name, 'Subject')]")).sendKeys("TestWriter");
        driver.findElement(
                By.xpath("//div[contains(@role, 'textbox')]")).sendKeys("TestWriter");
        driver.findElement(By.xpath("//span[contains(., 'Отправить')]")).click();
        Thread.sleep(2000);
        WebElement checkMessage = driver.findElement(By.xpath("//div[@class='layer__header']"));
        assertThat(checkMessage.getText(), equalTo("Письмо отправлено"));
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }


    static void login(WebDriver driver) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//input[contains(@name, 'login') and @placeholder='Имя ящика']"));
        element.sendKeys("test1234522@mail.ru");
        driver.findElement(By.xpath("//button[contains(@data-testid, 'enter-password')]")).click();
        driver.findElement(By.xpath("//input[contains(@name, 'password')]")).sendKeys("sUuRIayp1A9(");
        driver.findElement(By.xpath("//button[contains(@data-testid, 'login-to-mail')]")).click();
        Thread.sleep(7000);
    }
}
