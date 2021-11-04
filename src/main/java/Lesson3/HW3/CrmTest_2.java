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

//Создание хозяйственного договора
public class CrmTest_2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://crm.geekbrains.space/user/login");
        login(driver);

        List<WebElement> menuItems = driver.findElements(By.xpath("//ul[@class='nav nav-multilevel main-menu']/li/a"));
        WebElement expensesMenuItem = menuItems.stream().filter(e -> e.getText().equals("Расходы")).findFirst().get();

        Actions actions = new Actions(driver);
        actions.moveToElement(expensesMenuItem).build().perform();
        driver.findElement(By.xpath("//span[.='Хоз. договоры']")).click();

        driver.findElement(By.xpath("//a[.='Новый хозяйственный договор']")).click();

        driver.findElement(By.name("crm_domcontract[name]")).sendKeys("TestNameName111222333");
        driver.findElement(By.name("crm_domcontract[externalNumber]")).sendKeys("TestExternalNumber");
        driver.findElement(By.name("crm_domcontract[description]")).sendKeys("TestDescription");

        driver.findElement(By.xpath("//input[contains(@id, 'date_selector_crm_domcontract_dateSigned') and @placeholder='Укажите дату']")).click();
        driver.findElement(By.xpath("//a[.='10']")).click();

        Select contractorSelect = new Select(driver.findElement(By.name("crm_domcontract[contractor]")));
        contractorSelect.selectByVisibleText("ИП Чебурашка Иван Иванович");

        Select expenditureSelect = new Select(driver.findElement(By.name("crm_domcontract[expenditure]")));
        expenditureSelect.selectByVisibleText("01108 - ОС: недвижимость");

        driver.findElement(By.xpath("//button[contains(., 'Сохранить и закрыть')]")).click();

        Thread.sleep(5000);
        driver.quit();
    }

    static void login(WebDriver driver) {
        WebElement element = driver.findElement(By.id("prependedInput"));
        element.sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
    }
}
