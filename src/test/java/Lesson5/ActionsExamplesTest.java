package Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Lesson5.Helpers.clickWithJS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ActionsExamplesTest {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        login(driver);
    }

    @Test
    void dragAndDropTest() throws InterruptedException {
        driver.get("https://crm.geekbrains.space/dashboard");
        driver.findElement(By.xpath("//div[@class='column-manager dropdown']")).click();

        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(By.xpath("//label[.='Владелец']/ancestor::tr//span")))
                .dragAndDrop(driver.findElement(By.xpath("//label[.='Владелец']/ancestor::tr")),
                        driver.findElement(By.xpath("//label[.='Наименование']/ancestor::tr")))
                .build()
                .perform();

        //Thread.sleep(2000);
        webDriverWait.until(d -> d.findElements(
                By.xpath("//thead[@class='grid-header']//tr[@class='grid-header-row']/th[contains(@class, 'sort')]"))
                .get(0).getText().equals("ВЛАДЕЛЕЦ"));

        List<WebElement> headers = driver.findElements(
                By.xpath("//thead[@class='grid-header']//tr[@class='grid-header-row']/th[contains(@class, 'sort')]"));

        //Assertions.assertTrue(headers.get(0).getText().equals("Владелец"));
        Assertions.assertEquals("ВЛАДЕЛЕЦ", headers.get(0).getText());
        //assertThat(headers.get(0), not(isDisplayed()));
        //assertThat(headers.get(0), hasText("ВЛАДЕЛЕЦ"));
    }

    @Test
    void afishaTest() {
        driver.get("https://www.afisha.ru/msk/events/");
        driver.manage().window().setSize(new Dimension(700, 700));
        //WebElement element = driver.findElement(By.xpath("//button[.='Детские']"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        //Thread.sleep(500);
        clickWithJS(driver, driver.findElement(By.xpath("//button[.='Детские']")));
        //driver.findElement(By.xpath("//button[.='Детские']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Куда сходить с ребенком в Москве']")));
    }

    @Test
    void iframeTest() throws InterruptedException {
        driver.get("https://crm.geekbrains.space/project/create/");
        Wait<WebDriver> customWait = new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(5));
        customWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@id, 'crm_project_planning')]")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id, 'crm_project_planning')]")));
        driver.findElement(By.xpath("//body")).sendKeys("test");
        driver.switchTo().defaultContent();
        driver.findElement(By.name("crm_project[name]")).sendKeys("test1");
        Thread.sleep(4000);
    }

    @Test
    public void endToEndMoveAllMailFromSketchesToSendsTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://mail.ru/");
        driver.findElement(By.name("login")).sendKeys("pustyshkin97");
        driver.findElement(By.xpath("//button[@data-testid='enter-password']")).click();
        driver.findElement(By.xpath("//input[@data-testid='password-input']")).sendKeys("zxc56nm&");
        driver.findElement(By.xpath("//button[@data-testid='login-to-mail']")).click();
        Thread.sleep(7000);

        driver.findElement(By.xpath("//a[@Title='Черновики']")).click();
        driver.findElement(By.xpath("//span[@class='button2__ico'][1]")).click();//очередное топорное решение
        driver.findElement(By.xpath("//span[contains(.,'В папку') and " +
                "parent::span[@class='button2__wrapper button2__wrapper_centered']]")).click();
        driver.findElement(By.xpath("//div[contains(.,'Отправленные') and parent::div[@class='list-item__text']]"))
                .click();

        WebElement checkMessage = driver.findElement(By.xpath("//span[@class='octopus__title']"));
        //вот тут я вообще не понял разницы в ассерте
        assertThat(checkMessage.getText(),equalTo("У вас нет незаконченных\\nили неотправленных писем"));

        Thread.sleep(5000);
        driver.quit();

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    static void login(WebDriver driver) {
        driver.get("https://crm.geekbrains.space/");
        WebElement element = driver.findElement(By.id("prependedInput"));
        element.sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
    }



}
