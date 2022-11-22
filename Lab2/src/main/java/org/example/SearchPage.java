package org.example;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {

    public static final String SEARCH_PAGE_URL = "https://www.amazon.com/";
    private final WebDriver driver;


    @FindBy(css = "input[id='twotabsearchtextbox']")
    WebElement searchBar;
    @FindBy(xpath = "*//div[@data-component-type='s-search-result']")
    List<WebElement> searchResultsList;
    @FindBy(id = "low-price")
    WebElement LowPriceFilter;
    @FindBy(id = "high-price")
    WebElement HighPriceFilter;
    @FindBy(xpath = "//span[@id='a-autoid-1']")
    WebElement GoButton;
    @FindBy(xpath = "//span[text()= 'Five Star']")
    WebElement AmazonFashionfilter;
    public WebElement firstProduct;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        driver.get(SEARCH_PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void sendKey(String keyword) {
        searchBar.sendKeys(keyword);
    }

    public void clickEnter() {
        searchBar.sendKeys(Keys.ENTER);
    }

    public void firstProduct() {
        firstProduct = searchResultsList.get(0);
    }

    public void clickOnFirstProduct() {
        firstProduct.click();
    }

    public void LowPrice(String min){
        LowPriceFilter.sendKeys(min);
    }

    public void HighPrice(String max){
        HighPriceFilter.sendKeys(max);
    }

    public void GoButton(){
        GoButton.click();
    }

    public void getFiler(){
        AmazonFashionfilter.click();
    }


    public void close() {
        driver.close();
    }

}
