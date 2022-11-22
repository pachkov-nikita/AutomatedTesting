package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {
    public static final String PRODUCT_PAGE_URL = "https://www.amazon.com/SteelSeries-Arctis-Wireless-Gaming-Headset/dp/B09KMGHPCY/ref=sr_1_1?content-id=amzn1.sym.4ea2637b-78ea-4080-a167-eab91512057a%3Aamzn1.sym.4ea2637b-78ea-4080-a167-eab91512057a&keywords=wireless+gaming+headset&pd_rd_r=cc7677e0-2f1b-49a2-b3dd-ce0b58f19245&pd_rd_w=881Pw&pd_rd_wg=cHi0v&pf_rd_p=4ea2637b-78ea-4080-a167-eab91512057a&pf_rd_r=CAGE7DWHYAYE9SFF5G4X&qid=1669128030&sr=8-1";

    private final WebDriver driver;

    @FindBy(id = "productTitle")
    WebElement productName;
    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;
    @FindBy(id = "priceblock_ourprice")
    WebElement price;
    @FindBy(className = "a-dropdown-label")
    WebElement quantity;
    @FindBy(css = "[id*='quantity_']")
    List<WebElement> quantityListItems;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PRODUCT_PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return productName.getText();
    }

    public double getPrice() {
        return Double.parseDouble(price.getText().replace("$", ""));
    }

    public void QuantityClick(){
        quantity.click();
    }

    public void quantityList(int number){
        quantityListItems.get(number).click();
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public void close() {
        driver.close();
    }
}
