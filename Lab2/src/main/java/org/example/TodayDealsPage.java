package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TodayDealsPage {
    public static final String TD_PAGE_URL = "https://www.amazon.com/gp/goldbox?ref_=nav_cs_gb";

    private final WebDriver driver;
    @FindBy(id = "searchDropdownBox")
    private WebElement searchDropdown;
    @FindBy(css = "span[aria-label='Departments filter'] ul")
    WebElement departmentsListElement;
    List<WebElement> departmentsListItems;
    @FindBy(className = "a-pagination")
    WebElement pagin;
    List<WebElement> listOfPages;


    public TodayDealsPage(WebDriver driver) {
        this.driver = driver;
        driver.get(TD_PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void initDepartmentsListItems() {
        departmentsListItems = departmentsListElement.findElements(By.xpath("./child::*"));
    }

    public WebElement getSearchDropdown() {
        return searchDropdown;
    }
    public void selectBook() {
        for (WebElement element : departmentsListItems) {
            if (element.getText().equals("Books")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                element.click();

            }
        }
    }

    public void selectThreeDepartmants() throws InterruptedException {
        for (WebElement element : departmentsListItems) {
            if (element.getText().equals("Headphones")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                element.click();

            } if (element.getText().equals("Kitchen")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                element.click();

            } if (element.getText().equals("Music")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                element.click();

            }
        }
    }

    public void initListPage() {
        listOfPages = pagin.findElements(By.xpath("./child::*"));
    }
    public void goPage(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listOfPages.get(3));
        listOfPages.get(3).findElement(By.cssSelector("a[href='#']")).click();
    }

    public void close() {
        driver.close();
    }

}
