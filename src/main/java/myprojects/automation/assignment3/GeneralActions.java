package myprojects.automation.assignment3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(login);
        WebElement pass = driver.findElement(By.id("passwd"));
        pass.sendKeys(password);
        WebElement submit = driver.findElement(By.name("submitLogin"));
        submit.click();
        waitForContentLoad();
    }

    /**
     * Adds new category in Admin Panel.
     * @param categoryName
     */
    public void createCategory(String categoryName) {
        WebElement catalogElement = driver.findElement(By.id("subtab-AdminCatalog"));
        Actions builder = new Actions(driver);
        builder.moveToElement(catalogElement).build().perform();
        driver.findElement(By.xpath("//*[@id = 'subtab-AdminCatalog']//li[2]")).click();
        waitForContentLoad();
        driver.findElement(By.xpath("//*[@id='page-header-desc-category-new_category']/div")).click();
        waitForContentLoad();
        driver.findElement(By.id("name_1")).sendKeys(categoryName);
        driver.findElement(By.id("category_form_submit_btn")).click();
        waitForContentLoad();
    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("//*[@id = 'ajax_running']")));

    }

    public List<String> getAllNamesFromTable(String tableID){
        List<String> tableNames = new ArrayList<>();
        WebElement tableElement = driver.findElement(By.id(tableID));
        for(WebElement element : tableElement.findElements(By.xpath("./tbody/tr/td[3]"))){
            tableNames.add(element.getText());
        }
        return tableNames;
    }

    public void filterCategoryByName(){
        driver.findElement(By.xpath("//span[contains(text() , 'Имя')]/a")).click();
        waitForContentLoad();
    }


}
