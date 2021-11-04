package Lesson3.HW3.SeleniumIDE;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

//Написать письмо
public class SeleniumTest_1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://mail.ru/?from=logout");
        login(driver);

        driver.findElement(By.xpath("//a[contains(@class, 'compose-button compose-button_white compose-button_short js-shortcut')]")).click();

        driver.findElement(By.xpath("//input[contains(@class, 'container--H9L5q size_s--3_M-_')]")).sendKeys("albuzik97@gmail.com");
        driver.findElement(By.xpath("//input[contains(@name, 'Subject')]")).sendKeys("TestWriter");

        driver.findElement(By.xpath("//div[contains(@role, 'textbox')]")).sendKeys("TestWriter");

        driver.findElement(By.xpath("//span[contains(., 'Отправить')]")).click();

        Thread.sleep(15000);
        driver.quit();
    }

    static void login(WebDriver driver) {
        WebElement element = driver.findElement(By.xpath("//input[contains(@name, 'login') and @placeholder='Имя ящика']"));
        element.sendKeys("test1234522@mail.ru");
        driver.findElement(By.xpath("//button[contains(@data-testid, 'enter-password')]")).click();
        driver.findElement(By.xpath("//input[contains(@name, 'password')]")).sendKeys("sUuRIayp1A9(");
        driver.findElement(By.xpath("//button[contains(@data-testid, 'login-to-mail')]")).click();
    }
}
