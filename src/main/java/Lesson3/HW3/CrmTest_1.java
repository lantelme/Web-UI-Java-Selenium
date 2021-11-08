package Lesson3.HW3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

//Создание контактного лица
public class CrmTest_1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://crm.geekbrains.space/user/login");
        login(driver);

        List<WebElement> menuItems = driver.findElements(By.xpath("//ul[@class='nav nav-multilevel main-menu']/li/a"));
        WebElement expensesMenuItem = menuItems.stream().filter(e -> e.getText().equals("Контрагенты")).findFirst().get();

        Actions actions = new Actions(driver);
        actions.moveToElement(expensesMenuItem).build().perform();
        driver.findElement(By.xpath("//span[.='Контактные лица']")).click();

        driver.findElement(By.xpath("//a[.='Создать контактное лицо']")).click();

        driver.findElement(By.name("crm_contact[lastName]")).sendKeys("TestLastName");
        driver.findElement(By.name("crm_contact[firstName]")).sendKeys("TestFirstName");
        driver.findElement(By.name("crm_contact[middleName]")).sendKeys("TestMiddleName");

        driver.findElement(By.xpath("//input[contains(@id, 'date_selector_crm_contact_birthday') and @placeholder='Укажите дату']")).click();
        driver.findElement(By.xpath("//a[.='10']")).click();

        Select birthdayRemindBeforeSelect = new Select(driver.findElement(By.name("crm_contact[birthdayRemindBefore]")));
        birthdayRemindBeforeSelect.selectByVisibleText("День");

        driver.findElement(By.xpath("//span[.='Укажите организацию']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[.='12323142342134']")).click();

        driver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("TestJob");

        driver.findElement(By.xpath("//button[contains(., 'Сохранить и закрыть')]")).click();

        Thread.sleep(7000);
        driver.quit();
    }

    static void login(WebDriver driver) {
        WebElement element = driver.findElement(By.id("prependedInput"));
        element.sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
    }
}
