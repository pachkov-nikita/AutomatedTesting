package amazon;

import org.example.TodayDealsPage;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TodayDealsPageTest {
    private static final ChromeDriver driver = new ChromeDriver();
    final static TodayDealsPage TODAY_DEALS_PAGE = new TodayDealsPage(driver);


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.drive", "C:/Users/derok/Desktop/Учеба/4-1/Автоматизация/Lab2/chromedriver.exe");
    }

    //Test13
    @Test(priority = 1)
    void isDropDownNotNull() {
        System.out.print("Test 13");
        WebElement searchDropdown = TODAY_DEALS_PAGE.getSearchDropdown();
        assertThat(searchDropdown).isNotNull();
    }

    //Test14
    @Test(priority = 2)
    public void selectBook() {
        System.out.print("Test 14");
        TODAY_DEALS_PAGE.initDepartmentsListItems();
        TODAY_DEALS_PAGE.selectBook();
        System.out.println("Test 14 successful");

    }

    //Test15
    @Test(priority = 3)
    public void selectThreeDepartments() {
        System.out.print("Test 15");
        TODAY_DEALS_PAGE.initDepartmentsListItems();
        try {
            TODAY_DEALS_PAGE.selectThreeDepartmants();
            System.out.println("Test 15 successful");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    //Test16
    @Test(priority = 4)
    public void goToPage3() {
        System.out.print("Test 16");
        TODAY_DEALS_PAGE.initListPage();
        TODAY_DEALS_PAGE.goPage();
    }

}
