package amazon;

import org.example.ProductPage;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductPageTest {

    private static final ChromeDriver driver = new ChromeDriver();
    final static ProductPage PRODUCT_PAGE = new ProductPage(driver);

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.drive", "C:/Users/derok/Desktop/Учеба/4-1/Автоматизация/Lab2/chromedriver.exe");
    }

    //Test9
    @Test(priority = 1)
    public void getProductName() {
        System.out.print("Test 9");
        String productName = PRODUCT_PAGE.getProductName();
        assertThat(productName).isNotNull().isEqualTo("Headset");
    }

    //Test10
    @Test(priority = 2)
    public void getPrice() {
       System.out.print("Test 10");
       Double test = PRODUCT_PAGE.getPrice();
       assertThat(test).isNotNull().isEqualTo(118.22);
    }

    //Test11
    @Test(priority = 3)
    public void selectQuantity() {
        System.out.print("Test 11");
        try {
            Thread.sleep(8000);//Для выставления страны: Канада.
            PRODUCT_PAGE.QuantityClick();
            int number = 1;
            PRODUCT_PAGE.quantityList(number);
            System.out.println("Test 11 successful");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Test12
    @Test(priority = 4)
    public void clickAddToCart() {
        System.out.print("Test 12");
        PRODUCT_PAGE.clickAddToCart();
        assertThat(driver.getCurrentUrl()).contains("newItems=");
    }

}
