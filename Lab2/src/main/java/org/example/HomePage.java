package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class HomePage {
    public static final String HOME_PAGE_URL = "https://www.amazon.com/";
    private final WebDriver driver;

    @FindBy(css = "input[id='twotabsearchtextbox']")
    WebElement searchBar;

    @FindBy(tagName = "body")
    private WebElement body;

    @FindBy(xpath="//a[@href='/ref=nav_logo']")
    WebElement icon;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(HOME_PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String enterKeyword(String keyword) {
        searchBar.sendKeys(keyword);
        return keyword;
    }

    public List<WebElement> getWebElementInBody() {
        return body.findElements(By.tagName("div"));
    }

    public WebElement clickIcon() {
        return icon;
    }

    public void close() {
        driver.close();
    }
}
