package Lesson6.HW6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UsingFilter extends BaseView{

    public UsingFilter(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='filters-control filters-control_short filters-control_pure']")
    public WebElement findFilter;

    @FindBy(xpath = "//div[@class='list list_hover-support']/div[2]")
    public WebElement useFilter;

    @Step("Нажать на кнопку Фильтр")
    public UsingFilter findFilterPage() throws InterruptedException {
        findFilter.click();
        Thread.sleep(5000);
        return this;
    }
    @Step("Нажать на фильтр Непрочитанные")
    public void useFilterPage() throws InterruptedException {
        useFilter.click();
        Thread.sleep(5000);
    }




}
