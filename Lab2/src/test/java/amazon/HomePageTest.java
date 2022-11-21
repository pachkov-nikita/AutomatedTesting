package amazon;

import org.example.HomePage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class HomePageTest {
    private static final ChromeDriver driver = new ChromeDriver();
    final static HomePage HOME_PAGE = new HomePage(driver);

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.drive", "C:/Users/derok/Desktop/Учеба/4-1/Автоматизация/Lab2/chromedriver.exe");
    }

    //Test1
    @Test
    public void testAmazonTitle() {
        System.out.print("Test 1");
        String title = HOME_PAGE.getTitle();
        assertThat(title).isNotNull().isEqualTo("Amazon.com");
    }

    //Test2
    @Test
    public void enterSearch(){
        System.out.println("Test 2");
        String data = HOME_PAGE.enterKeyword("test 2");
        assertThat(data).isNotNull().isEqualTo("ne test 2");

    }

    //Test3
    @Test
    public void pageIsNotEmpty() {
        System.out.println("Test 3");
        List<WebElement> webElements = HOME_PAGE.getWebElementInBody();
        assertThat(webElements).isNotEmpty().hasSizeGreaterThan(1);
    }

    //Test4
    @Test
    public void iconIsNotNull() {
        System.out.println("Test 4");
        WebElement logo = HOME_PAGE.clickIcon();
        assertThat(logo).isNull();
    }

}
