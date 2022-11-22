package amazon;

import org.example.SearchPage;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchPageTest {

    private static final ChromeDriver driver = new ChromeDriver();
    final static SearchPage SEARCH_PAGE = new SearchPage(driver);

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.drive", "C:/Users/derok/Desktop/Учеба/4-1/Автоматизация/Lab2/chromedriver.exe");
    }

    //Test5
    @Test(priority = 1)
    public void searchNote() {
        System.out.println("Test 5");
        String name_search = "notebook";
        SEARCH_PAGE.sendKey(name_search);
        SEARCH_PAGE.clickEnter();
        assertThat(driver.getCurrentUrl()).contains("notebook");
    }

    //Test6
    @Test(priority = 2)
    public void searchByPrice() {
        System.out.println("Test 6");
        String min_price = "25", max_price = "50";
        SEARCH_PAGE.LowPrice(min_price);
        SEARCH_PAGE.HighPrice(max_price);
        SEARCH_PAGE.GoButton();
        assertThat(driver.getCurrentUrl()).contains("2500-5000");
    }

    //Test7
    @Test(priority = 3)
    public void searchByFiler() {
        System.out.println("Test 7");
        SEARCH_PAGE.getFiler();
        assertThat(driver.getCurrentUrl()).contains("Five+Star");
    }


    //Test8
    @Test(priority = 4)
    public void clickOnFirstProduct() {
        System.out.println("Test 8");
        SEARCH_PAGE.firstProduct();
        SEARCH_PAGE.clickOnFirstProduct();
        assertThat(driver.getCurrentUrl()).contains("sr_1_1");
    }

}
