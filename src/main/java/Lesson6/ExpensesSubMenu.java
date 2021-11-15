package Lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExpensesSubMenu extends BaseView {

    public ExpensesSubMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Заявки на расходы']")
    public WebElement expensesSubMenuItem;

    public void expensesSubMenuItemClick() {
        expensesSubMenuItem.click();
    }
}
